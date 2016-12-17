package com.noah.breakit.panel;

import com.noah.breakit.graphics.Screen;

public abstract class Component{
	protected int x, y;
	protected int w, h;
	
	protected String name = null;
	
	protected int col = 0xffffff;
	protected boolean solid = false;
	
	public Component(int x, int y, String name) {
		this.x = x;
		this.y = y;
		
		this.name = name;
		
		w = name.length() * 8 + 2;
		h = 12;
	}
	
	public Component(int x, int y, int col, String name) {
		this(x, y, name);
		this.col = col;
	}
	
	public Component(int x, int y, String name, boolean solid) {
		this(x, y, name);
		this.solid = solid;
	}
	
	public Component(int x, int y, int col, String name, boolean solid) {
		this(x, y, col, name);
		this.solid = solid;
	}
	
	public abstract void update();
	
	public void render(Screen screen) {
		
		if(solid == false)
			screen.drawRect(x, y, w, h, col);
		else
			screen.fillRect(x, y, w, h, col);
		
		screen.renderString8x8(x + 1, y + 2, col, name);
	}
}