package com.noah.breakit.entity.mob.ball;

import com.noah.breakit.entity.mob.Mob;

public class BallGiantState extends BallNormalState {
	
	private static final int TTL = 7 * 60;
	
	private int count = 0;
	
	public void init(Mob m) {
		super.init(m);
		adjustSize(12);
	}
	
	public void update() {
		super.update();
		
		if(count++ == TTL) {
			b.setState(new BallNormalState());
			b.getState().init(b);
			adjustSize(4);
		}
	}
		
	private void adjustSize(int size) {
		int wOld = b.getWidth();
		int hOld = b.getHeight();
		b.setWidth(size);
		b.setHeight(size);
		b.setx(b.getx() - (b.getWidth() / 2) + (wOld / 2));
		b.sety(b.gety() - (b.getHeight() / 2) + (hOld / 2));
	}
}