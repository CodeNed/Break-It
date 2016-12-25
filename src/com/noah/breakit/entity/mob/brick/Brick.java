package com.noah.breakit.entity.mob.brick;

import com.noah.breakit.entity.mob.Mob;
import com.noah.breakit.entity.mob.ball.Ball;
import com.noah.breakit.entity.mob.projectile.Projectile;
import com.noah.breakit.graphics.Screen;

public abstract class Brick extends Mob {

	public Brick(int x, int y) {
		super(x, y);
		width = 16;
		height = 8;
	}
	
	public abstract void render(Screen screen);
	
	public abstract void processCollision(Ball b);
	
	public abstract void processCollision(Projectile p);
}
