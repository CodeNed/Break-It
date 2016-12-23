package com.noah.breakit.entity.mob.powerup;

import com.noah.breakit.graphics.Screen;

class WidthPowerup extends Powerup {

	WidthPowerup(int x, int y) {
		super(x, y);
	}

	public void render(Screen screen) {
		super.render(screen);
		renderChar(screen, 'w');
	}

	Powerup spawnPowerup(int x, int y) {
		return new WidthPowerup(x, y);
	}

	void trigger() {
		playField.getPlayer().setWide(true);
	}
}