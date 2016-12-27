package com.noah.breakit.entity.mob.projectile;

import com.noah.breakit.entity.mob.Mob;
import com.noah.breakit.graphics.Screen;

public class Projectile extends Mob {

	public Projectile(int x, int y) {
		super(x, y, null);
		
		col = 0xffffff;
		width = 1;
		height = 2;
		
		xspeed = 0;
		yspeed = 6;
		
		xdir = 0;
		ydir = -1;
	}

	public void update() {
		xa = xdir * xspeed;
		ya = ydir * yspeed;
		x += xa;
		y += ya;
		
		if(y < 0)
			remove();
	}
	
	public void render(Screen screen) {
		screen.drawRect(x, y, width, height, col);
	}
	
	public void processCollision() {
			remove();
	}
}