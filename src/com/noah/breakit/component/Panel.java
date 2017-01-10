package com.noah.breakit.component;

import java.util.ArrayList;
import java.util.List;

import com.noah.breakit.graphics.Screen;
import com.noah.breakit.input.Keyboard;
import com.noah.breakit.sound.SoundFX;
import com.noah.breakit.util.ColorFlasher;

public class Panel extends Component {
	
	private Keyboard key = null;

	private Label label = null;
	
	private List<Button> buttons = new ArrayList<>();
	private List<RotaryButton> rotaryButtons = new ArrayList<>(); // NOTE: rotary buttons are members of both buttons and rotaryButtons!
	private Button activeButton = null;
	private int index = 0;

	private Panel(int x, int y, int w, int h, int col, Keyboard key, Label label) {
		super(x, y, w, h, col);
		this.key = key;
		this.label = label;
	}

	public Panel(int x, int y, int w, int h, int col, Keyboard key, Label label, Button...newButtons) {
		this(x, y, w, h, col, key, label);
		
		for(Button b : newButtons) {
			buttons.add(b);
			if(b instanceof RotaryButton)
				rotaryButtons.add((RotaryButton) b);
		}
		
		activeButton = buttons.get(0);
		activeButton.setActive(true);
	}
	
	public Panel(int x, int y, int w, int h, Keyboard key, Label label, Button...newButtons ) {
		this(x, y, w, h, 0x000000, key, label, newButtons);
	}
	
	public void update() {
			
		label.update();
		
		if(key.up && !key.upLast) {
			if(activeButton instanceof PushButton)
				activeButton = getPrevButton();
			else if(activeButton instanceof RotaryButton) {
				RotaryButton r = (RotaryButton) activeButton;
				r.setNextChar();
			}
			SoundFX.MENU_1.play();
		}
		
		if(key.down && !key.downLast) {
			if(activeButton instanceof PushButton)
				activeButton = getNextButton();
			else if(activeButton instanceof RotaryButton) {				
				RotaryButton r = (RotaryButton) activeButton;
				r.setPrevChar();
			}
			SoundFX.MENU_1.play();
		}
		
		if(key.left && !key.leftLast)
				activeButton = getPrevButton();
		
		if(key.right && !key.rightLast)
				activeButton = getNextButton();
		
		if(key.enter && !key.enterLast) {
			if(activeButton instanceof PushButton) {
				PushButton p = (PushButton) activeButton;
				p.getAction().perform();
				SoundFX.MENU_2.play();
			}
			else if(activeButton instanceof RotaryButton) {
				activeButton = getNextButton();
				SoundFX.MENU_2.play();
			}
		}
	}

	public void render(Screen screen) {
		//render panel
		screen.fillRect(x, y, w, h, col);
		screen.drawRect(x, y, w, h, ColorFlasher.col);
		
		//render label
		label.render(screen, true, false);
		
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
	
	public List<RotaryButton> getRotaryButtons() {
		return rotaryButtons;
	}
	
	public String getRotaryButtonValue(int index) {
		return rotaryButtons.get(index).getChar().toString();
	}
}