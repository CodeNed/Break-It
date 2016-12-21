package com.noah.breakit.entity.mob;

import com.noah.breakit.graphics.Screen;

public class FloatingScore extends Mob {

	private String val = null;
	private int count = 0;
	
	public FloatingScore(int x, int y, Integer val) {
		super(x, y);
		this.val = val.toString();
	}

	public void update() {
		if(count++ == 2 * 60)
			remove();
	}
	
	public void render(Screen screen) {
		screen.renderString5x5(x, y, 0xffffff, val);
	}
}