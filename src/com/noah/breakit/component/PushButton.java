package com.noah.breakit.component;

public class PushButton extends Button {
	
	private Action action = null;
	
	public PushButton(int x, int y, int col, Label label, Action action) {
		super(x, y, col, label);
		this.action = action;
	}
	
	public PushButton(int x, int y, Label label, Action action) {
		this(x, y, 0xffffff, label, action);
	}
	
	public Action getAction() {
		return action;
	}
}
