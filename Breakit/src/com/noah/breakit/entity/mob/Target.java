package com.noah.breakit.entity.mob;

import com.noah.breakit.entity.spawner.Spawner;
import com.noah.breakit.graphics.Screen;
import com.noah.breakit.sound.SoundFX;

public class Target extends Mob {
	private int life = 1;

	public Target(int x, int y) {
		super(x, y);
		width = 16;
		height = 8;

		int[] colors = { 0xff0000, 0xff00ff, 0x0000ff, 0x00ffff, 0x00ff00, 0xffff00 };
		col = colors[(y / 8 - 2) % 6];
	}

	public void update() {
	}

	public void processCollision() {
		if (--life == 0) {
			remove();
			playField.setStagePattern(((x - 1) >> 4) + ((y >> 3) - 2) * 10, '0');
			playField.addSpawner(new Spawner(x + (width >> 1), y + (width >> 1), 50));
			playField.getPlayer().addToScore(100 * playField.getBall().multiplier);
			playField.getBall().multiplier++; 
			SoundFX.explode.play();
		}
	}

	public void render(Screen screen) {
		if (life == 2)
			screen.fillRect(x, y, width, height, col);
		else
			screen.drawRect(x, y, width, height, col);
	}
}