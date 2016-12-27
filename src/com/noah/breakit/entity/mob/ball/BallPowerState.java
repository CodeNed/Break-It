package com.noah.breakit.entity.mob.ball;

import com.noah.breakit.entity.mob.Mob;
import com.noah.breakit.entity.mob.brick.BrickDestructibleState;
import com.noah.breakit.graphics.Screen;

public class BallPowerState extends BallNormalState {
	
	private final int NUM_TAILS = 6;
	private final int TIME_TO_LIVE = 7 * 60;
	
	private int[] xlast = new int[NUM_TAILS];
	private int[] ylast = new int[NUM_TAILS];
	private int[] cols = { 0xff0000, 0xff7700, 0xffdd00, 0x00ff00, 0x0000ff, 0x8a2be2, 0xc77df3 };
	
	private int count = 0;
	
	public void init(Mob m) {
		super.init(m);
		
	}
	
	public void update() {
		
		b.processWallCollision();

		b.setxa(b.getxspeed() * b.getxdir());
		b.setya(b.getyspeed() * b.getydir());

		for(int i = NUM_TAILS - 1; i >= 1; i--) {
			xlast[i] = xlast[i - 1];
			ylast[i] = ylast[i - 1];
		}
		
		xlast[0] = b.getx();
		ylast[0] = b.gety();
		
		b.setx(b.getx() + b.getxa());
		b.sety(b.gety() + b.getya());
		
		if(count++ % 2 == 0) {
			int temp = cols[cols.length - 1];
			for(int i = cols.length - 1; i > 0; i--)
				cols[i] = cols[i - 1];
			cols[0] = temp;
			b.setCol(temp);
			
		}
		
		if(count == TIME_TO_LIVE) {
			b.setState(new BallNormalState(b));
			b.getState().init(b);
		}
	}

	public void render(Screen s) {
		
		s.drawRect(b.getx(), b.gety(), b.getWidth(), b.getHeight(), b.getCol());
		
		for(int i = 0; i < NUM_TAILS; i++) {
			s.drawRect(xlast[i], ylast[i], b.getWidth(), b.getHeight(), cols[i + 1]);
		}
	}
	
	protected void processXCollision(Mob m) {
		if(m.getState() instanceof BrickDestructibleState)
			return;
		super.processXCollision(m);
	}

	protected void processYCollision(Mob m) {
		if(m.getState() instanceof BrickDestructibleState)
			return;
		super.processYCollision(m);
	}
}