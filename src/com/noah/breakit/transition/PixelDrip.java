package com.noah.breakit.transition;

import com.noah.breakit.game.Game;
import com.noah.breakit.util.Util;

public class PixelDrip extends Transition {
	private int[] ylast = new int[Game.WIDTH];
	private int[] yrate = new int[Game.WIDTH];

	public PixelDrip(int col){
		super(col);
		for (int i = 0; i < Game.WIDTH; i++)
			yrate[i] = random.nextInt(10) + 4;
	}
	
	public void update(int[] pixels) {
		for (int x = 0; x < Game.WIDTH; x++) {
			int max = Util.clamp(ylast[x] + yrate[x], 0, Game.HEIGHT);
			for (int y = ylast[x]; y < max; y++) {
				pixels[x + y * Game.WIDTH] = col;
			}
			ylast[x] = Util.clamp(ylast[x] + yrate[x], 0, Game.HEIGHT);
		}
		fadeToBlack(4, pixels);
	}
	
	public boolean isFinished(){
		return finished;
	}
}