package com.noah.breakit.entity.mob.ball;

import com.noah.breakit.entity.mob.Mob;

public class BallGiantState extends BallNormalState {
	
	private static final int TTL = 7 * 60;
	
	private int count = 0;
	
	public void init(Mob m) {
		super.init(m);
		b.setWidth(12);
		b.setHeight(12);
	}
	
	public void update() {
		super.update();
		
		if(count++ == TTL) {
			b.setState(new BallNormalState());
			b.getState().init(b);
			b.setWidth(4);
			b.setHeight(4);
		}
	}
}