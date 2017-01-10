package com.noah.breakit.gamestate.outro;

import com.noah.breakit.gamestate.BreakitGameState;
import com.noah.breakit.graphics.Screen;
import com.noah.breakit.util.Util;
public abstract class Outro extends BreakitGameState {
	
	int col = 0x000000;
	boolean finished = false;
	
	Outro(int col, BreakitGameState ngs) {
		this.col = col;
		this.ngs = ngs;
	}
	
	boolean fadeToBlack(int rate, int[] pixels) {
		for (int i = 0; i < pixels.length; i++) {
			int r = (pixels[i] & 0xff0000) >> 16;
			int g = (pixels[i] & 0xff00) >> 8;
			int b = pixels[i] & 0xff;

			r -= rate;
			g -= rate;
			b -= rate;

			r = Util.clamp(r, 0, 255);
			g = Util.clamp(g, 0, 255);
			b = Util.clamp(b, 0, 255);

			pixels[i] = (r << 16) | (g << 8) | b;
		}

		for (int i = 0; i < pixels.length; i++) {
			if (pixels[i] > 0x000000) return false;
		}
		
		return true;
	}
	
	void fadeToWhite(int rate, int[] pixels) {
		for (int i = 0; i < pixels.length; i++) {
			int r = (pixels[i] & 0xff0000) >> 16;
			int g = (pixels[i] & 0xff00) >> 8;
			int b = pixels[i] & 0xff;

			r += rate;
			g += rate;
			b += rate;

			r = Util.clamp(r, 0, 255);
			g = Util.clamp(g, 0, 255);
			b = Util.clamp(b, 0, 255);

			pixels[i] = (r << 16) | (g << 8) | b;
		}

		for (int i = 0; i < pixels.length; i++)
			if (pixels[i] < 0xffffff) return;

		finished = true;
	}
	
	public void render(Screen screen) {
		for (int i = 0; i < pixels.length; i++) {
			screen.renderPixel(pixels[i], i);
		}
	}
	
	public boolean isFinished() {
		return finished;
	}
	
	public abstract void update();
}