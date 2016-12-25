package com.noah.breakit.entity.mob.brick;

import com.noah.breakit.entity.mob.ball.Ball;
import com.noah.breakit.entity.mob.projectile.Projectile;
import com.noah.breakit.graphics.Screen;

public class PortalBrick extends Brick {

	private PortalBrick mate = null;
	
	public PortalBrick(int x, int y, int id) {
		super(x, y);
		
		col = 0x800080;
	}
	
	public PortalBrick(int x, int y, int id, PortalBrick mate) {
		this(x, y, id);
		this.mate = mate;
	}

	public void render(Screen screen) {
		screen.fillRect(x,  y,  width,  height, col);
		
	}

	public void processCollision(Ball b) {
		// leave empty
	}

	public void processCollision(Projectile p) {
		// leave empty
	}

	public void update() {
		// leave empty
		
	}
	
	public PortalBrick getMate() {
		return mate;
	}
	
	public void setMate(PortalBrick p) {
		mate = p;
	}

}
