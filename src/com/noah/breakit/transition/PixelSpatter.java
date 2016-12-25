package com.noah.breakit.transition;

import java.util.ArrayList;
import java.util.List;

import com.noah.breakit.game.Game;

public class PixelSpatter extends Transition {

	private List<Integer> n = new ArrayList<Integer>();

	public PixelSpatter(int col) {
		super(col);
		for (int i = 0; i < Game.WIDTH * Game.HEIGHT; i++)
			n.add(i, i);
	}

	public void update(int[] pixels) {

		for (int i = 0; i < 512; i++) {
			if (n.size() > 0) {
				int rand = n.remove(random.nextInt(n.size()));
				pixels[rand] = col;
			}
		}

		fadeToBlack(4, pixels);
	}

	public boolean isFinished() {
		return finished;
	}
}