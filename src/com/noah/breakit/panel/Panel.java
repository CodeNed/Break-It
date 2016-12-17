package com.noah.breakit.panel;

import java.util.ArrayList;
import java.util.List;

import com.noah.breakit.graphics.Screen;
import com.noah.breakit.input.Keyboard;
import com.noah.breakit.util.ColorFlasher;

public class Panel {
	private int x, y;
	private int w, h;
	private int col = 0x000000;
	
	private Keyboard key;

	private Label label;
	
	private List<Button> buttons = new ArrayList<>();
	private Button activeButton;
	private int index = 0;

	private Panel(int x, int y, int w, int h, Keyboard key, Label label) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.key = key;
		this.label = label;
	}

	public Panel(int x, int y, int w, int h, Keyboard key, Label label, Button...newButtons ) {
		this(x, y, w, h, key, label);
		
		for(Button b : newButtons) {
			buttons.add(b);
		}
		
		activeButton = buttons.get(0);
		activeButton.setActive(true);
	}
	
	public Panel(int x, int y, int w, int h, int col, Keyboard key, Label label, Button...newButtons) {
		this(x, y, w, h, key, label, newButtons);
		this.col = col;
	}

	public void update() {
			
		label.update();
		
		if(key.up && !key.upLast)
			activeButton = getPrevButton();
		else if(key.down && !key.downLast)
			activeButton = getNextButton();
		
		activeButton.update();
	}

	public void render(Screen screen) {
		//render panel
		screen.fillRect(x, y, w, h, col);
		screen.drawRect(x, y, w, h, ColorFlasher.col);
		
		//render label
		label.render(screen);
		
		//render buttons
		for(Button b : buttons)
			b.render(screen);
	}
	
	private Button getPrevButton() {
		
		buttons.get(index).setActive(false);
		
		if(--index < 0)
			index = buttons.size() - 1;
		
		buttons.get(index).setActive(true);
		
		return buttons.get(index);
	}
	
	private Button getNextButton() {
		
		buttons.get(index).setActive(false);
		
		if(++index > buttons.size() - 1)
			index = 0;
		
		buttons.get(index).setActive(true);
		
		return buttons.get(index);
	}
}