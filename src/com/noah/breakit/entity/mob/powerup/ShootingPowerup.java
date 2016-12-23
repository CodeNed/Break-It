package com.noah.breakit.entity.mob.powerup;

import com.noah.breakit.graphics.Screen;

public class ShootingPowerup extends Powerup {

	ShootingPowerup(int x, int y) {
		super(x, y);
	}

	public void render(Screen screen) {
		super.render(screen);
		renderChar(screen, 's');
	}
	
	Powerup spawnPowerup(int x, int y) {
		return new ShootingPowerup(x, y);
	}
	
	void trigger() {
		playField.getPlayer().setShooting(true);
	}
}
