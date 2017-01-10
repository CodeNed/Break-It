package com.noah.breakit.gamestate.menu;

import com.noah.breakit.component.Label;
import com.noah.breakit.component.Panel;
import com.noah.breakit.component.PushButton;
import com.noah.breakit.component.RotaryButton;
import com.noah.breakit.gamestate.BreakitGameState;
import com.noah.breakit.gamestate.GameOver;
import com.noah.breakit.gamestate.outro.Outro;
import com.noah.breakit.gamestate.outro.PixelSpatter;
import com.noah.breakit.graphics.Screen;
import com.noah.breakit.input.Keyboard;
import com.noah.breakit.sound.SoundFX;
import com.noah.breakit.util.Config;
import com.noah.breakit.util.FuzzRenderer;

public class InitialsMenu extends BreakitGameState {

	private Keyboard key = null;
	private Panel panel = null;
	
	private int rank = -1;
	
	private String chars = "abcdefghijklmnopqrstuvwxyz0123456789:-!?<>@ ";
	
	private boolean soundPlayed = false;
	
	public InitialsMenu(Keyboard key, int rank) {
		this.key = key;
		
		this.rank = rank;
		
		int lw = "enter initials!".length() * 8;
		
		int pw = lw + 16; // the width of the label plus 2 characters for margins
		int ph = 75;
		int px = Config.WINDOW_WIDTH / 2 - pw / 2;
		int py = Config.WINDOW_HEIGHT / 2 - ph / 2;
		
		int lx = px + pw / 2 - lw / 2;
		int ly = py + 4;
		
		int bw = 12;
		int bh = 12;
		
		int bxCenter = px + pw / 2 - bw / 2;
		int byCenter = py + ph / 2 - bh / 2;
		
		int bx0 = bxCenter - bw;
		int by0 = byCenter - 4;
		
		int bx1 = bxCenter;
		int by1 = by0;
		
		int bx2 = bxCenter + bw;
		int by2 = by0;
		
		int pbw = "confirm".length() * 8;
		int pbx = px + pw / 2 - pbw / 2;
		int pby = byCenter + 16; 

		panel = new Panel(px, py, pw, ph, key, new Label(lx, ly, "enter initials!"),
				new RotaryButton(bx0, by0, new Label(bx0, by0, " "), chars),
				new RotaryButton(bx1, by1, new Label(bx1, by1, " "), chars),
				new RotaryButton(bx2, by2, new Label(bx2, by2, " "), chars),
				new PushButton(pbx, pby, new Label(pbx, pby, "confirm"), () -> confirm()));
	}
	
	public void update() {
		key.update();
		panel.update();
		if(!soundPlayed) {
			SoundFX.HI_SCORE.play();
			soundPlayed = true;
		}
	}

	public void render(Screen screen) {
		FuzzRenderer.render(screen, 128);
		panel.render(screen);
	}
	
	private void confirm() {
		String result = "";
		for(int i = 0; i < panel.getRotaryButtons().size(); i++)
			result += panel.getRotaryButtonValue(i);
		
		Config.HI_SCORES.get(rank).setInitials(result);
		loadNextGameState();
	}
	
	public void loadNextGameState() {
		Outro o = new PixelSpatter(0x00ffff, new GameOver(key, rank));
		o.captureScreen();
		ngs = o;
		finished = true;
	}
}