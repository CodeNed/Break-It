package com.noah.breakit.transition;

import java.util.Random;

import com.noah.breakit.util.Util;
abstract class Transition {
	
	static Random random = new Random();
	boolean finished = false;
	
	void fadeToBlack(int rate, int[] pixels) {
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
			if (pixels[i] > 0x000000) return;
		}
		
		finished = true;
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
	
	abstract boolean isFinished();
}