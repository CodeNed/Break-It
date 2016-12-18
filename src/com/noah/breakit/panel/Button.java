package com.noah.breakit.panel;

import com.noah.breakit.graphics.Screen;

public class Button extends Component{
	
	private Label label;

	private boolean active = false;
	
	public Button(int x, int y, int col, Label label) {
		super(x, y, col);
		this.label = label;
		w = label.w;
		h = label.h;
	}
	
	public Button(int x, int y, Label label) {
		this(x, y, 0xffffff, label);
	}
	
	public void update() {
	}
	
	public void render(Screen screen) {
		if(isActive()) {
				label.render(screen, true, true);
		} else {
			label.render(screen);
		}
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public boolean isActive() {
		return active;
	}
}