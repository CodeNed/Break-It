package com.noah.breakit.component;

import java.util.ArrayList;
import java.util.List;

import com.noah.breakit.graphics.Screen;
import com.noah.breakit.util.ColorFlasher;

public class RotaryButton extends Button {

	private List<Character> chars = new ArrayList<>();
	private Character currChar = ' ';
	private int index = 0;
	
	public RotaryButton(int x, int y, int col, Label label, String chars) {
		super(x, y, col, label);		
		for(int i = 0; i < chars.length(); i++)
			this.chars.add(chars.charAt(i));
		currChar = this.chars.get(index);
	}
	
	public RotaryButton(int x, int y, Label label, String chars){
		this(x, y, 0xffffff, label, chars);
	}
	
	public void render(Screen screen) {
		super.render(screen);
		if(isActive())
			screen.renderString8x8(x + 1, y + 2, ~ColorFlasher.col, currChar.toString());
		else
			screen.renderString8x8(x + 1, y + 2, 0xffffff, currChar.toString());
	}
	
	public void setNextChar() {
		if(++index > chars.size() - 1)
			index = 0;
		currChar = chars.get(index);
	}
	
	public void setPrevChar() {
		if(--index < 0)
			index = chars.size() - 1;
		currChar = chars.get(index);
	}
	
	public Character getChar() {
		return currChar;
	}
}
