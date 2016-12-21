package com.noah.breakit.component;

import com.noah.breakit.graphics.Screen;

public class Button extends Component {

	private Label label = null;
	private Action action = null;

	private boolean active = false;

	public Button(int x, int y, int col, Label label, Action action) {
		super(x, y, col);
		this.label = label;
		this.action = action;
		w = label.w;
		h = label.h;
	}

	public Button(int x, int y, Label label, Action action) {
		this(x, y, 0xffffff, label, action);
	}
	
	public void update() {
	}

	public void render(Screen screen) {
		if (isActive()) {
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
	
	public Action getAction() {
		return action;
	}
}