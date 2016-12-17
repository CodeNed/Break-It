package com.noah.breakit.panel;

import java.util.ArrayList;
import java.util.List;

import com.noah.breakit.graphics.Screen;
import com.noah.breakit.input.Keyboard;

public class Panel {
	int x, y;
	int w, h;
	int col = 0x000000;
	int borderCol = 0xffffff;

	private List<Selection> selections = new ArrayList<>();

	public Panel(int x, int y, int w, int h, Keyboard key) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}

	public Panel(int x, int y, int w, int h, Keyboard key, Selection...newSelections ) {
		this(x, y, w, h, key);
		
		for(Selection s : newSelections) {
			selections.add(s);
		}
	}
	
	public Panel(int x, int y, int w, int h, int col, Keyboard key, Selection...newSelections) {
		this(x, y, w, h, key, newSelections);
		this.col = col;
	}

	public void update() {

	}

	public void render(Screen screen) {
		screen.fillRect(x, y, w, h, col);
		screen.drawRect(x, y, w, h, borderCol);
		
		for(Selection s : selections)
			s.render(screen);
	}
}