package com.noah.breakit.entity.mob.powerup;

import com.noah.breakit.entity.mob.ball.Ball;
import com.noah.breakit.graphics.Screen;

class MultiBallPowerup extends Powerup {

	MultiBallPowerup(int x, int y) {
		super(x, y);
	}
	
	public void render(Screen screen) {
		super.render(screen);
		renderChar(screen, 'b');
	}

	Powerup spawnPowerup(int x, int y) {
		return new MultiBallPowerup(x, y);
	}
	
	void trigger() {
		playField.addBall(new Ball(x + width / 2, y + width / 2, -1, -1));
		playField.addBall(new Ball(x + width / 2, y + width / 2,  1, -1));
	}
}