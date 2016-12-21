package com.noah.breakit.entity.mob.powerup;

import com.noah.breakit.graphics.Font8x8;
import com.noah.breakit.graphics.Screen;
import com.noah.breakit.util.ColorFlasher;

class WidthPowerup extends Powerup {

	WidthPowerup(int x, int y) {
		super(x, y);
	}

	public void render(Screen screen) {
		super.render(screen);
		screen.renderChar8x8(x + (width >> 1) - 4, y + (height >> 1) - 4, ~ColorFlasher.col, Font8x8.getChar('w'));
	}

	Powerup spawnPowerup(int x, int y) {
		return new WidthPowerup(x, y);
	}

	void trigger() {
		playField.getPlayer().setWide(true);
	}
}