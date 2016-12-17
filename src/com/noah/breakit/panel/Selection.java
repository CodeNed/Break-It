package com.noah.breakit.panel;

import com.noah.breakit.graphics.Screen;

public class Selection {
	private int x, y;
	private int w, h;
	
	private String label = null;
	
	private int col = 0xffffff;
	private boolean solid = false;
	
	public Selection(int x, int y, String label) {
		this.x = x;
		this.y = y;
		
		this.label = label;
		
		w = label.length() * 8 + 2;
		h = 12;
	}
	
	public Selection(int x, int y, int col, String label) {
		this(x, y, label);
		this.col = col;
	}
	
	public Selection(int x, int y, String label, boolean solid) {
		this(x, y, label);
		this.solid = solid;
	}
	
	public Selection(int x, int y, int col, String label, boolean solid) {
		this(x, y, col, label);
		this.solid = solid;
	}
	
	public void update() {
	}
	
	public void render(Screen screen) {
		
		if(solid == false)
			screen.drawRect(x, y, w, h, col);
		else
			screen.fillRect(x, y, w, h, col);
		
		screen.renderString8x8(x + 1, y + 2, col, label);
	}
}