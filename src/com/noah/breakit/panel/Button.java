package com.noah.breakit.panel;

import com.noah.breakit.graphics.Screen;
import com.noah.breakit.util.ColorFlasher;

public class Button extends Component{
	
	private boolean active = false;
	
	public Button(int x, int y, int col, String label) {
		super(x, y, col, label);
	}
	
	public Button(int x, int y, String label) {
		super(x, y, label);
	}
	
	public void update() {
	}
	
	public void render(Screen screen) {
		if(isActive()) {
			if(solid == false)
				screen.drawRect(x, y, w, h, ~ColorFlasher.col);
			else
				screen.fillRect(x, y, w, h, col);
			
			screen.renderString8x8(x + 1, y + 2, ~ColorFlasher.col, name);
		} else super.render(screen);
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public boolean isActive() {
		return active;
	}
}