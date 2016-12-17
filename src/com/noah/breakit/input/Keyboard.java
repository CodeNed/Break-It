package com.noah.breakit.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

	public boolean[] keys = new boolean[120];

	public boolean up, down, left, right, enter, space, esc;
	public boolean upLast, downLast, leftLast, rightLast, enterLast, spaceLast, escLast;

	public void update() {
		
		upLast    = up;
		downLast  = down;
		leftLast  = left;
		rightLast = right;
		enterLast = enter;
		spaceLast = space;
		escLast   = esc;
		
		up    = keys[KeyEvent.VK_W] || keys[KeyEvent.VK_UP];
		down  = keys[KeyEvent.VK_S] || keys[KeyEvent.VK_DOWN];
		left  = keys[KeyEvent.VK_A] || keys[KeyEvent.VK_LEFT];
		right = keys[KeyEvent.VK_D] || keys[KeyEvent.VK_RIGHT];
		enter = keys[KeyEvent.VK_ENTER];
		space = keys[KeyEvent.VK_SPACE];
		esc   = keys[KeyEvent.VK_ESCAPE];
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}
}