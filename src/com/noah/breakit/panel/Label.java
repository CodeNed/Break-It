package com.noah.breakit.panel;

import com.noah.breakit.graphics.Screen;
import com.noah.breakit.util.ColorFlasher;

public class Label extends Component 
{	
	public Label(int x, int y, String name) {
		super(x, y, name);
	}

	public void update() {
	}
	
	public void render(Screen screen) {
		if(solid == false)
			screen.drawRect(x, y, w, h, ColorFlasher.col);
		else
			screen.fillRect(x, y, w, h, ColorFlasher.col);
		
		screen.renderString8x8(x + 1, y + 2, ColorFlasher.col, name);
	}
}