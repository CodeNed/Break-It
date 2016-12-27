package com.noah.breakit.entity.mob.decoration;

import com.noah.breakit.entity.mob.Mob;
import com.noah.breakit.graphics.Screen;

public abstract class Decoration extends Mob {

	public Decoration(int x, int y) {
		super(x, y, null);
	}
	
	public abstract void render(Screen screen);
}