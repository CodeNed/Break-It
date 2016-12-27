package com.noah.breakit.entity;

import com.noah.breakit.gamestate.Playfield;

public abstract class Entity {

	protected Playfield playfield = null;
	
	protected int x = 0;
	protected int y = 0;

	private boolean remove = false;

	public void init(Playfield playfield) {
		this.playfield = playfield;
	}

	public final void remove() {
		remove = true;
	}

	public final boolean isRemoved() {
		return remove;
	}
	
	public final Playfield getPlayfield() {
		return playfield;
	}
	
	public int getx() {
		return x;
	}
	
	public int gety() {
		return y;
	}
	
	public void setx(int x) {
		this.x = x;
	}
	
	public void sety(int y) {
		this.y = y;
	}
	
	public abstract void update();
}