package com.noah.breakit.entity;

import java.util.Random;

import com.noah.breakit.gamestate.PlayField;

public abstract class Entity {

	protected int x;
	protected int y;

	protected PlayField playField;

	private boolean remove = false;

	protected static Random random = new Random();

	public Entity() {
	}

	public void init(PlayField level) {
		this.playField = level;
	}

	protected void remove() {
		remove = true;
	}

	public boolean isRemoved() {
		return remove;
	}

	public abstract void update();
}