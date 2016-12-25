package com.noah.breakit.entity.mob.ball;

import com.noah.breakit.entity.mob.Mob;
import com.noah.breakit.entity.mob.brick.DestructibleBrick;
import com.noah.breakit.graphics.Screen;

public class PowerBall extends Ball {
	
	private final int NUM_TAILS = 6;
	private final int TIME_TO_LIVE = 7 * 60;
	
	private int[] xlast = new int[NUM_TAILS];
	private int[] ylast = new int[NUM_TAILS];
	
	private int count = 0;
	
	public PowerBall(Ball b) {
		super(b);
	}
	
	public void update() {

		processWallCollision();

		xa = xspeed * xdir;
		ya = yspeed * ydir;

		for(int i = NUM_TAILS - 1; i >= 1; i--) {
			xlast[i] = xlast[i - 1];
			ylast[i] = ylast[i - 1];
		}
		
		xlast[0] = x;
		ylast[0] = y;
		
		x += xa;
		y += ya;
		
		if(count++ % 7 == 0)
			col = random.nextInt(0xffffff);
		
		if(count == TIME_TO_LIVE)
			removeAndReplace();
	}
	
	public void render(Screen screen) {
		screen.drawRect(x, y, width, height, col);
		
		int c = ~col;
		for(int i = 0; i < NUM_TAILS; i++) {
			screen.drawRect(xlast[i], ylast[i], width, height, c);
			c = ~c;
		}
	}
	
	public void resetCount() {
		count = 0;
	}
	
	protected void processXCollision(Mob m) {
		if(m instanceof DestructibleBrick)
			return;
		super.processXCollision(m);
	}
	
	protected void processYCollision(Mob m) {
		if(m instanceof DestructibleBrick)
			return;
		super.processYCollision(m);
	}
	
	private void removeAndReplace() {
		remove();
		playField.addBall(new Ball(this));
	}
}