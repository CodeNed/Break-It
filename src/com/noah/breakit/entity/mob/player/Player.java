package com.noah.breakit.entity.mob.player;

import java.util.Collections;

import com.noah.breakit.entity.mob.Mob;
import com.noah.breakit.entity.state.State;
import com.noah.breakit.game.Game;
import com.noah.breakit.graphics.Screen;
import com.noah.breakit.input.Keyboard;

public class Player extends Mob {

	Keyboard key = null;

	int score = 0;
	int rank = -1;
	String scoreStr = "";
	
	Integer toNext1UP = 20000;
	
	int lives = 3;

	public Player(int x, int y, Keyboard key, State state) {
		super(x, y, state);
		this.key = key;

		xspeed = 4;

		width = 32;
		height = 4;
		col = 0x00ffff;
	}

	public void update() {
		state.update();
	}

	public void render(Screen screen) {
		state.render(screen);
	}

	void updateHiScores() {
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
}