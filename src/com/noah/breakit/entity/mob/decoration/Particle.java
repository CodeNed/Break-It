package com.noah.breakit.entity.mob.decoration;

import com.noah.breakit.graphics.Screen;
import com.noah.breakit.util.Util;

public class Particle extends Decoration {

	private int life = 0;
	private int count = 0;

	public Particle(int x, int y, int width, int height) {
		super(x, y);
		this.width = width;
		this.height = height;
		this.col = Util.random.nextInt(0xffffff);
		xspeed = Util.random.nextInt(3) + 1;
		yspeed = Util.random.nextInt(3) + 1;
		xdir = Util.random.nextInt(2) > 0 ? 1 : -1;
		ydir = Util.random.nextInt(2) > 0 ? 1 : -1;
		xa = xdir * xspeed;
		ya = ydir * yspeed;
		life = (Util.random.nextInt(3) + 1) << 4;
		if (xdir == 0 || ydir == 0) life = 0;
	}

	public void update() {

		x += xa;
		y += ya;

		if (count++ == life) remove();

		width = Util.random.nextInt(3) + 1;
		height = Util.random.nextInt(3) + 1;

		col = Util.random.nextInt(0xffffff);
	}

	public void render(Screen screen) {
		screen.drawRect(x, y, width, height, col);
	}

}
