package com.noah.breakit.transition;

import com.noah.breakit.game.Game;
import com.noah.breakit.util.Util;

public class PixelDrip extends Transition {
	private int[] ylast = new int[Game.width];
	private int[] yrate = new int[Game.width];

	public PixelDrip(){
		for (int i = 0; i < Game.width; i++)
			yrate[i] = random.nextInt(10) + 4;
	}
	
	public void pixelDrip(int col, int[] pixels) {
		for (int x = 0; x < Game.width; x++) {
			int max = Util.clamp(ylast[x] + yrate[x], 0, Game.height);
			for (int y = ylast[x]; y < max; y++) {
				pixels[x + y * Game.width] = col;
			}
			ylast[x] = Util.clamp(ylast[x] + yrate[x], 0, Game.height);
		}
		fadeToBlack(4, pixels);
	}
	
	public void pixelDripZX(int[] pixels) {

		int[] colors = { 0xff0000, 0xff00ff, 0x0000ff, 0x00ffff, 0x00ff00, 0xffff00 };

		pixelDrip(colors[random.nextInt(colors.length)], pixels);
	}
	
	public boolean isFinished(){
		return finished;
	}
}