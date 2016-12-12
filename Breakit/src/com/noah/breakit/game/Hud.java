package com.noah.breakit.game;

import com.noah.breakit.graphics.Screen;

public class Hud {

	private Hud(){	
	}

	public static String parseScore(int score) {
		int rem = 0;

		int mil = score / 1000000;
		rem = score % 1000000;

		int hunThou = rem / 100000;
		rem = rem % 100000;

		int tenThou = rem / 10000;
		rem = rem % 10000;

		int thou = rem / 1000;
		rem = rem % 1000;

		int hun = rem / 100;
		rem = rem % 100;

		int ten = rem / 10;

		int one = rem % 10;

		return Integer.toString(mil) + Integer.toString(hunThou) + Integer.toString(tenThou)
				+ Integer.toString(thou) + Integer.toString(hun) + Integer.toString(ten) + Integer.toString(one);
	}

	public static void renderScore(Screen screen, int x, int y, int col, String scoreStr) {
		screen.renderString(x, y, col, scoreStr);
	}

	public static void renderLives(Screen screen, int lives) {
		screen.renderString(Game.width - (8 << 3), 4, 0xffffff, "rest: " + (lives - 1));
	}
}