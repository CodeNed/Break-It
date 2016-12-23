package com.noah.breakit.entity.mob.powerup;

import java.util.List;

import com.noah.breakit.entity.mob.ball.Ball;
import com.noah.breakit.entity.mob.ball.PowerBall;
import com.noah.breakit.graphics.Screen;

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
		renderChar(screen, 'p');
	}
}