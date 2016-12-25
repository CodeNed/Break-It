package com.noah.breakit.entity.spawner;

import com.noah.breakit.entity.Entity;
import com.noah.breakit.entity.mob.decoration.Particle;

public class ParticleSpawner extends Entity {

	private int num = 0;

	public ParticleSpawner(int x, int y, int num) {
		this.x = x;
		this.y = y;
		this.num = num;
	}

	public void update() {
		for (int i = 0; i < num; i++)
			playField.addDecoration(new Particle(x, y, random.nextInt(3) + 1, random.nextInt(3) + 1));
		remove();
	}
}