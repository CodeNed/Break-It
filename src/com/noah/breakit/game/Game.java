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
import com.noah.breakit.input.Keyboard;
import com.noah.breakit.sound.SoundFX;
import com.noah.breakit.util.ColorFlasher;
import com.noah.breakit.util.ShutdownThread;

import kuusisto.tinysound.TinySound;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	private Thread thread;

	private String title = "Break-It";

	private boolean running = false;

	public static final int width = 160;
	public static final int height = 260;
	private int scale = 3;

	private JFrame frame;
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	private Screen screen;

	//private GameStateManager gsm;
	public static Stack<GameState> gsm;
	
	public static List<Integer> hiScores = new ArrayList<Integer>() {
		
		private static final long serialVersionUID = 1L;

		{
			add(50000);
			add(30000);
			add(20000);
			add(15000);
			add(12000);
			add(10000);
			add(9000);
			add(8000);
			add(7000);
			add(6000);
		}
	};

	public static final Keyboard key = new Keyboard();

	Game() {
		
		Runtime.getRuntime().addShutdownHook(new ShutdownThread());
		
		Dimension size = new Dimension(width * scale, height * scale);
		setPreferredSize(size);
		screen = new Screen(width, height);
		frame = new JFrame();
		addKeyListener(key);

		//gsm = new GameStateManager(new CreditScreen(key, hiScores));
		gsm = new Stack<GameState>();
		gsm.push(new CreditScreen(key, hiScores));
		
		TinySound.init();
		SoundFX.voidsound.play();
	}

	public synchronized void start() {
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

	public void update() {
		
		ColorFlasher.update();
		
		gsm.peek().update();

		GameState gs = null;
		
		if(gsm.peek().finished) {
			gs = gsm.peek().getNextGameState();
			gsm.pop();
			
			if(gs != null)
				gsm.push(gs);
		}
	}

	public void render() {

		BufferStrategy bs = getBufferStrategy();

		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		screen.clear();

		gsm.peek().render(screen);

		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = Screen.pixels[i];
		}

		Graphics g = bs.getDrawGraphics();
		g.setColor(new Color(0xff00ff));
		g.fillRect(0, 0, width, height);
		g.drawImage(image, 0, 0, width * scale, height * scale, null);
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