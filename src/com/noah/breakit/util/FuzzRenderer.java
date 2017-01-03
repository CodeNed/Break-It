package com.noah.breakit.util;

import com.noah.breakit.graphics.Screen;

public class FuzzRenderer {
	
	private FuzzRenderer() {
	}
	
	public static void render(Screen screen, int numPixels) {
		for (int i = 0; i < numPixels; i++) {
			screen.fillRect(Util.random.nextInt(screen.getWidth()), Util.random.nextInt(screen.getHeight()), 1, 1,
					Util.random.nextInt(0xffffff));
		}
	}
}
