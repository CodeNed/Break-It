package com.noah.breakit.entity.spawner;

import com.noah.breakit.entity.Entity;
import com.noah.breakit.entity.mob.decoration.Particle;
import com.noah.breakit.util.Util;

public class ParticleSpawner extends Entity {

	private int num = 0;

	public ParticleSpawner(int x, int y, int num) {
		this.x = x;
		this.y = y;
		this.num = num;
	}

	public void update() {
		for (int i = 0; i < num; i++)
			playfield.addDecoration(new Particle(x, y, Util.random.nextInt(3) + 1, Util.random.nextInt(3) + 1));
		remove();
	}
}