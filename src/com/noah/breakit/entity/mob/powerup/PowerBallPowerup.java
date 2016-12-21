package com.noah.breakit.entity.mob.powerup;

import java.util.List;

import com.noah.breakit.entity.mob.Ball;
import com.noah.breakit.entity.mob.PowerBall;
import com.noah.breakit.graphics.Font8x8;
import com.noah.breakit.graphics.Screen;
import com.noah.breakit.util.ColorFlasher;

public class PowerBallPowerup extends Powerup {

	PowerBallPowerup(int x, int y) {
		super(x, y);
	}

	Powerup spawnPowerup(int x, int y) {
		return new PowerBallPowerup(x, y);
	}

	void trigger() {
		List<Ball> balls = playField.getBalls();
		
		for (int i = 0; i < balls.size(); i++)
			if (!(balls.get(i) instanceof PowerBall)){
				Ball b = balls.get(i);
				playField.addBall(new PowerBall(b.getx(), b.gety(), b.getxdir(), b.getydir(), b.getxspeed(), b.getyspeed()));
				b.remove();
			}
	}
	
	public void render(Screen screen) {
		super.render(screen);
		screen.renderChar8x8(x + (width >> 1) - 4, y + (height >> 1) - 4, ~ColorFlasher.col, Font8x8.getChar('p'));
	}

}