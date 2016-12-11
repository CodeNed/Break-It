package com.noah.breakit.entity.mob;

import java.util.Collections;

import com.noah.breakit.game.Hud;
import com.noah.breakit.graphics.Screen;
import com.noah.breakit.input.Keyboard;
import com.noah.breakit.sound.SoundFX;

public class Player extends Mob {

	private Keyboard key;

	private int score;
	private int rank = -1;
	private String scoreStr = "";
	
	private final int TO_1ST_1UP = 20000;
	private final int TO_NEXT_1UP = 30000;
	private int to1up = TO_1ST_1UP;

	private int lives = 3;

	public boolean died;
	private int count;

	public Player(int x, int y, Keyboard key) {
		super(x, y);
		this.key = key;

		xspeed = 4;

		width = 32;
		height = 4;
		col = 0x00ffff;
	}

	public void update() {

		if (died) {
			if (count++ == 60 * 2) {
				count = 0;
				playField.getPlayer().addToLives(-1);
				playField.captureScreen();
				playField.transition = true;
			}
			return;
		}

		key.update();

		xdir = 0;

		if (key.left && x > 0 + xspeed) xdir = -1;

		if (key.right && x < playField.getWidth() - width - xspeed) xdir = 1;

		xa = xdir * xspeed;

		x += xa;

		scoreStr = Hud.parseScore(score);
		if (score >= to1up) {
			lives++;
			if(to1up == TO_1ST_1UP)
				to1up += TO_NEXT_1UP;
			SoundFX.oneup.play();
		}

		updateHiScores();
	}

	public void render(Screen screen) {
		if (!died) screen.fillRect(x, y, width, height, col);

		Hud.renderScore(screen, 4, 4, 0xffffff, scoreStr);
		Hud.renderLives(screen, lives);
	}

	public void updateHiScores() {
		if (rank == -1) {
			for (int i = 0; i < playField.hiScores.size(); i++) {
				if (score > playField.hiScores.get(i)) {
					rank = i;
					playField.hiScores.add(i, score);
					playField.hiScores.remove(playField.hiScores.size() - 1);
					break;
				}
			}
		} else {
			playField.hiScores.set(rank, score);
			Collections.sort(playField.hiScores, Collections.reverseOrder());
			for (int i = 0; i < playField.hiScores.size(); i++) {
				if (score == playField.hiScores.get(i)) {
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
}