package com.noah.breakit.entity.state;

import com.noah.breakit.entity.mob.Mob;
import com.noah.breakit.graphics.Screen;

public interface State {
	
	public State init(Mob m);
	
	public State update();
	
	public void render(Screen s);
	
	public void processCollision(Mob m);
	
	public Mob getMob();
}