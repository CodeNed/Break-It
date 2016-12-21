package com.noah.breakit.entity.mob;

import java.util.Collections;

import com.noah.breakit.game.Game;
import com.noah.breakit.gamestate.PauseMenu;
import com.noah.breakit.graphics.Screen;
import com.noah.breakit.input.Keyboard;
import com.noah.breakit.sound.SoundFX;
import com.noah.breakit.sound.music.Jukebox;
import com.noah.breakit.util.Hud;

public class Player extends Mob {

	private Keyboard key = null;

	private int score = 0;
	private int rank = -1;
	private String scoreStr = "";
	
	private final int TO_1ST_1UP = 20000;
	private final int TO_NEXT_1UP = 30000;
	private int to1up = TO_1ST_1UP;

	private int lives = 3;

	private int count = 0;
	
	private boolean isAlive = true;
	private boolean isWide = false;
	
	private int wideTimer = 0;

	public Player(int x, int y, Keyboard key) {
		super(x, y);
		this.key = key;

		xspeed = 4;

		width = 32;
		height = 4;
		col = 0x00ffff;
	}

	public void update() {

		if (!isAlive) {
			if (count++ == 60 * 2) {
				count = 0;
				playField.getPlayer().addToLives(-1);
				playField.captureScreen();
				playField.setTransitioning(true);
			}
			return;
		}
		
		if(isWide) {
			if(--wideTimer <= 3 * 60)
				if(wideTimer % (1 * 15) == 0)
					col = ~col;
			if(wideTimer == 0) {
				setWide(false);
				SoundFX.POWER_DOWN.play();
			}
		}

		key.update();

		xdir = 0;

		if (key.left && x > 0 + xspeed) xdir = -1;

		if (key.right && x < Game.WIDTH - width - xspeed) xdir = 1;
		
		if((key.esc && !key.escLast) || (key.enter && !key.enterLast)) {
			Jukebox.setVolume(Jukebox.MENU_VOLUME);
			SoundFX.PAUSE.play();
			playField.captureScreen();
			PauseMenu p = new PauseMenu(key, playField);
			p.init();
			Game.GSM.push(p);
		}
		
		if (key.space && !playField.getBalls().get(0).released) {
			Ball b = playField.getBalls().get(0);
			b.released = true;
			b.xdir = xdir;
			b.xspeed = xspeed;
			b.ydir = -1;
			SoundFX.LAUNCH.play();
		}

		xa = xdir * xspeed;
		x += xa;

		scoreStr = Hud.parseScore(score);
		if (score >= to1up) {
			lives++;
			to1up += TO_NEXT_1UP;
			SoundFX.ONE_UP.play();
		}

		updateHiScores();
	}

	public void render(Screen screen) {
		if (isAlive) screen.fillRect(x, y, width, height, col);

		Hud.renderScore(screen, 4, 4, 0xffffff, scoreStr);
		Hud.renderLives(screen, lives);
	}

	private void updateHiScores() {
		if (rank == -1) {
			for (int i = 0; i < Game.HI_SCORES.size(); i++) {
				if (score > Game.HI_SCORES.get(i)) {
					rank = i;
					Game.HI_SCORES.add(i, score);
					Game.HI_SCORES.remove(Game.HI_SCORES.size() - 1);
					break;
				}
			}
		} else {
			Game.HI_SCORES.set(rank, score);
			Collections.sort(Game.HI_SCORES, Collections.reverseOrder());
			for (int i = 0; i < Game.HI_SCORES.size(); i++) {
				if (score == Game.HI_SCORES.get(i)) {
					rank = i;
					break;
				}
			}
		}
	}

	public Player setCoordinates(int x, int y) {
		this.x = x;
		this.y = y;
		return this;
	}

	public void addToScore(int add) {
		score += add;
	}

	public int getScore() {
		return score;
	}

	public void addToLives(int add) {
		lives += add;
	}

	public int getLives() {
		return lives;
	}

	public int getRank() {
		return rank;
	}
	
	public Keyboard getKey() {
		return key;
	}
	
	public boolean isAlive() {
		return isAlive;
	}
	
	public void setIsAlive(boolean alive) {
		isAlive = alive;
	}
	
	public void setWidth(int w) {
		if(w < 1) {
			System.err.println("Error in Player.setWidth(int): width must be at least 1 pixel!");
			return;
		}
		this.width = w;
	}
	
	public void setWide(boolean b) {
		isWide = b;
		if(isWide == true) {
			width = 48;
			col = 0x00ff00;
			wideTimer = 15 * 60;
		} else { 
			width = 32;
			col = 0x00ffff;
		}
	}
}