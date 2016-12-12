package com.noah.breakit.transition;

import com.noah.breakit.game.Game;
import com.noah.breakit.util.Util;

public class SpiralIn extends Transition {

	int x, y;
	int xstep, ystep;
	int squarew = 4, squareh = 4;
	int leg;
	int xdir = 1, ydir = 1;
	int legDistX = Game.width - squarew, legDistY = Game.height - squareh;

	public void spiralIn(int col, int pixels[]) {

		for (int i = 0; i < 32; i++) {
			if (legDistX != 0 && legDistY != 0) {
				for (int yy = y; yy < y + squareh; yy++) {
					for (int xx = x; xx < x + squarew; xx++) {
						if (xx < Game.width && yy < Game.height) pixels[xx + yy * Game.width] = col;
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
	
	public void spiralInZX(int[] pixels){
		int[] colors = { 0xff0000, 0xff00ff, 0x0000ff, 0x00ffff, 0x00ff00, 0xffff00 };
		spiralIn(colors[random.nextInt(colors.length)], pixels);
	}
	
	public boolean isFinished(){
		return finished;
	}
}