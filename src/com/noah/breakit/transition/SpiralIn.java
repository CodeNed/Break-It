package com.noah.breakit.transition;

import com.noah.breakit.game.Game;
import com.noah.breakit.util.Util;

public class SpiralIn extends Transition {

	private int x = 0, y = 0;
	private int xstep = 0, ystep = 0;
	private int squarew = 4, squareh = 4;
	private int leg = 0;
	private int xdir = 1, ydir = 1;
	private int legDistX = Game.WIDTH - squarew, legDistY = Game.HEIGHT - squareh;

	public SpiralIn(int col) {
		super(col);
	}
	
	public void update(int pixels[]) {

		for (int i = 0; i < 32; i++) {
			if (legDistX != 0 && legDistY != 0) {
				for (int yy = y; yy < y + squareh; yy++) {
					for (int xx = x; xx < x + squarew; xx++) {
						if (xx < Game.WIDTH && yy < Game.HEIGHT) pixels[xx + yy * Game.WIDTH] = col;
					}
				}
			}

			if (leg % 2 == 0) {
				x += squarew * xdir;
				xstep += squarew;
				if (xstep >= legDistX) {
					xstep = 0;
					xdir *= -1;
					if (leg != 0) legDistX = Util.min(legDistX - squarew, 0);
					leg++;
				}
			} else {
				y += squareh * ydir;
				ystep += squareh;
				if (ystep >= legDistY) {
					ystep = 0;
					ydir *= -1;
					legDistY = Util.min(legDistY - squareh, 0);
					leg++;
				}
			}
		}
		fadeToBlack(4, pixels);
	}

	public boolean isFinished(){
		return finished;
	}
}