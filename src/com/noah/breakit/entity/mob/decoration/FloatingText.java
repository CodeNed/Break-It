package com.noah.breakit.entity.mob.decoration;

import com.noah.breakit.entity.mob.Mob;
import com.noah.breakit.graphics.Screen;

public class FloatingText extends Mob {

	private String val = null;
	private int count = 0;
	
	public FloatingText(int x, int y, Integer val) {
		super(x, y);
		this.val = val.toString();
	}
	
	public FloatingText(int x, int y, String val) {
		super(x, y);
		this.val = val;
	}

	public void update() {
		if(count++ == 2 * 60)
			remove();
	}
	
	public void render(Screen screen) {
		screen.renderString5x5(x, y, 0xffffff, val);
	}
}