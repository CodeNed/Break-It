package com.noah.breakit.component;

import com.noah.breakit.graphics.Screen;
import com.noah.breakit.util.ColorFlasher;

public class Label extends Component 
{	
	private String name = null;
	
	private Label(int x, int y, int col, int numChars) {
		super (x, y, col);
		w = numChars * 8 + 2;
		h = 12;
	}
	
	public Label(int x, int y, String name) {
		this(x, y, 0xffffff, name.length());
		this.name = name;
	}
	
	public Label(int x, int y, int col, String name) {
		this(x, y, col, name.length());
		this.name = name;
	}

	public void update() {
	}
	
	public void render(Screen screen) {
		screen.drawRect(x,  y,  w,  h,  col);
		screen.renderString8x8(x + 1, y + 2, col, name);
	}
	
	public void render(Screen screen, boolean flashing, boolean opposite) {
		if(flashing) {
			int c = ColorFlasher.col;
			if(opposite)
				c = ~c;
			screen.drawRect(x, y, w, h, c);
			screen.renderString8x8(x + 1,  y + 2,  c, name);
		}
		else
			render(screen);
	}
	
	void setName(String s) {
		name = s;
	}
}