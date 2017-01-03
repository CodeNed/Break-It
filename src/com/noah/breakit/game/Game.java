package com.noah.breakit.game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import com.noah.breakit.gamestate.CreditScreen;
import com.noah.breakit.gamestate.GameState;
import com.noah.breakit.graphics.Screen;
import com.noah.breakit.hiscore.HiScore;
import com.noah.breakit.input.Keyboard;
import com.noah.breakit.sound.SoundFX;
import com.noah.breakit.sound.music.Jukebox;
import com.noah.breakit.util.ColorFlasher;
import com.noah.breakit.util.ShutdownThread;

import kuusisto.tinysound.TinySound;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	private Thread thread = null;

	private final String title = "Break-It";

	private boolean running = false;

	public static final int WIDTH = 160;
	public static final int HEIGHT = 260;
	private int scale = 3;

	private JFrame frame = null;
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	private Screen screen = null;

	public static final Stack<GameState> GSM = new Stack<GameState>();
	
	private final Keyboard key = new Keyboard();
	
	public static final List<HiScore> HI_SCORES = new ArrayList<HiScore>() {
		
		private static final long serialVersionUID = 1L;

		{
			add(new HiScore("aaa", 50000));
			add(new HiScore("aaa", 30000));
			add(new HiScore("aaa", 20000));
			add(new HiScore("aaa", 15000));
			add(new HiScore("aaa", 12000));
			add(new HiScore("aaa", 10000));
			add(new HiScore("aaa", 9000));
			add(new HiScore("aaa", 8000));
			add(new HiScore("aaa", 7000));
			add(new HiScore("aaa", 6000));
		}
	};

	Game() {
		
		Runtime.getRuntime().addShutdownHook(new ShutdownThread());
		
		Dimension size = new Dimension(WIDTH * scale, HEIGHT * scale);
		setPreferredSize(size);
		screen = new Screen(WIDTH, HEIGHT);
		frame = new JFrame();
		addKeyListener(key);

		GSM.push(new CreditScreen(key));
		
		TinySound.init();
		SoundFX.VOID_SOUND.play();
		Jukebox.play("voidsong", false);
	}

	private synchronized void start() {
		running = true;
		thread = new Thread(this, "Game");
		thread.start();
	}

	private void stop() {
		running = false;

		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {

		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000D / 60D;
		double delta = 0D;
		int frames = 0;
		int updates = 0;

		requestFocus();

		while (running) {

			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;

			while (delta >= 1) {
				update();// 60 times per second
				delta--;
				updates++;
			}

			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frame.setTitle(title + " | " + updates + " ups" + " | " + frames + " fps");
				frames = 0;
				updates = 0;
			}
		}
		stop();
	}

	private void update() {
		
		ColorFlasher.update();
		
		GSM.peek().update();
		
		if(GSM.peek().isFinished()) {
			GameState gs = GSM.peek().getNextGameState();
			GSM.pop();
			
			if(gs != null)
				GSM.push(gs);
		}
	}

	private void render() {

		BufferStrategy bs = getBufferStrategy();

		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		screen.clear();

		GSM.peek().render(screen);

		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = Screen.pixels[i];
		}

		Graphics g = bs.getDrawGraphics();
		g.setColor(new Color(0xff00ff));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.drawImage(image, 0, 0, WIDTH * scale, HEIGHT * scale, null);
		g.dispose();
		bs.show();
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.frame.setResizable(false);
		game.frame.setTitle("Break-It");
		
		URL url = game.getClass().getClassLoader().getResource("burger_icon.png");
		
		game.frame.setIconImage(new ImageIcon(url).getImage());
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);
		game.start();
	}
}