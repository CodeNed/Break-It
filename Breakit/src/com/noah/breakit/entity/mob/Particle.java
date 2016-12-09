package com.noah.breakit.entity.mob;

import com.noah.breakit.graphics.Screen;

public class Particle extends Mob {

	private int life;
	private int count;

	public Particle(int x, int y, int width, int height) {
		super(x, y);
		this.width = width;
		this.height = height;
		this.col = random.nextInt(0xffffff);
		xspeed = random.nextInt(3) + 1;
		yspeed = random.nextInt(3) + 1;
		xdir = (int) Math.ceil(random.nextGaussian());
		ydir = (int) Math.ceil(random.nextGaussian());
		xa = xdir * xspeed;
		ya = ydir * yspeed;
		life = (random.nextInt(3) + 1) << 4;
		if (xdir == 0 || ydir == 0) life = 0;
	}

	public void update() {

		x += xa;
		y += ya;

		if (count++ == life) remove();

		width = random.nextInt(3) + 1;
		height = random.nextInt(3) + 1;

		col = random.nextInt(0xffffff);
	}

	public void render(Screen screen) {
		screen.drawRect(x, y, width, height, col);
	}

}
