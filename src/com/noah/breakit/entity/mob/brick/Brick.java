package com.noah.breakit.entity.mob.brick;

import com.noah.breakit.entity.mob.Mob;
import com.noah.breakit.entity.state.State;
import com.noah.breakit.graphics.Screen;

public class Brick extends Mob {

	public Brick(int x, int y, State state) {
		super(x, y, state);
		width = 16;
		height = 8;
	}
	
	public void update() {
		state.update();
	}
	
	public void render(Screen screen) {
		state.render(screen);
	}
	
	public void processCollision(Mob m) {
		state.processCollision(m);
	}
}