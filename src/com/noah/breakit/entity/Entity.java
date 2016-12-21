package com.noah.breakit.entity;

import java.util.Random;

import com.noah.breakit.gamestate.Playfield;

public abstract class Entity {

	protected int x = 0;
	protected int y = 0;

	protected Playfield playField = null;

	private boolean remove = false;

	protected static Random random = new Random();

	public Entity() {
	}

	public final void init(Playfield level) {
		this.playField = level;
	}

	public final void remove() {
		remove = true;
	}

	public final boolean isRemoved() {
		return remove;
	}

	public abstract void update();
}