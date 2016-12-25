package com.noah.breakit.entity.mob.powerup;

import com.noah.breakit.entity.mob.forcefield.ForceField;
import com.noah.breakit.game.Game;
import com.noah.breakit.graphics.Screen;

public class ForceFieldPowerup extends Powerup {

	ForceFieldPowerup(int x, int y) {
		super(x, y);
	}

	Powerup spawnPowerup(int x, int y) {
		return new ForceFieldPowerup(x, y);
	}

	void trigger() {
		playField.addForceField(new ForceField(0, Game.HEIGHT - 3));
	}
	
	public void render(Screen screen) {
		super.render(screen);
		renderChar(screen, 'f');
	}
}