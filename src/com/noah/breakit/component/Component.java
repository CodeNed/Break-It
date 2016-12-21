package com.noah.breakit.component;

import com.noah.breakit.graphics.Screen;

public abstract class Component{
	protected int x = 0, y = 0;
	protected int w = 0, h = 0;
	
	protected int col;
	
	public Component(int x, int y, int col) {
		this.x = x;
		this.y = y;
		this.col = col;
	}
	
	public Component(int x, int y, int w, int h, int col) {
		this(x, y, col);
		this.w = w;
		this.h = h;
	}
	
	public abstract void update();
	
	public abstract void render(Screen screen);
}