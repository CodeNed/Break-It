package com.noah.breakit.transition;

import java.util.ArrayList;
import java.util.List;

import com.noah.breakit.game.Game;

public class PixelSpatter extends Transition {

	private List<Integer> n = new ArrayList<Integer>();

	public PixelSpatter() {
		for (int i = 0; i < Game.width * Game.height; i++)
			n.add(i, i);
	}

	public void pixelSpatter(int col, int[] pixels) {

		for (int i = 0; i < 512; i++) {
			if (n.size() > 0) {
				int rand = n.remove(random.nextInt(n.size()));
				pixels[rand] = col;
			}
		}

		fadeToBlack(4, pixels);
	}

	public void pixelSpatterZX(int[] pixels) {

		int[] colors = { 0xff0000, 0xff00ff, 0x0000ff, 0x00ffff, 0x00ff00, 0xffff00 };

		pixelSpatter(colors[random.nextInt(6)], pixels);
	}

	public boolean isFinished() {
		return finished;
	}
}