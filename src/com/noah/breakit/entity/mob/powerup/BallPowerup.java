package com.noah.breakit.entity.mob.powerup;

import com.noah.breakit.entity.mob.Ball;
import com.noah.breakit.graphics.Font8x8;
import com.noah.breakit.graphics.Screen;
import com.noah.breakit.util.ColorFlasher;

class BallPowerup extends Powerup {

	BallPowerup(int x, int y) {
		super(x, y);
	}
	
	public void render(Screen screen) {
		super.render(screen);
		screen.renderChar8x8(x + (width >> 1) - 4, y + (height >> 1) - 4, ~ColorFlasher.col, Font8x8.getChar('b'));
	}

	Powerup spawnPowerup(int x, int y) {
		return new BallPowerup(x, y);
	}
	
	void trigger() {
		
		playField.addBall(new Ball(x + width / 2, y + width / 2, -1, -1));
		playField.addBall(new Ball(x + width / 2, y + width / 2,  1, -1));
	}
}
