package com.noah.breakit.util;

public class ColorFlasher {
	public static int col = 0x0000ff;
	public static int fade = 1;
	
	private ColorFlasher() {
	}
	
	public static void update() {
		int r = (col & 0xff0000) >> 16;
		int g = (col & 0xff00) >> 8;
		int b = col & 0xff;

		if (r >= 0 && r <= 255) r += 5 * fade;
		b += 5 * fade * -1;

		col = (r << 16) | (g << 8) | b;

		if (r >= 255) {
			fade = -1;
			r = 254;
		}

		if (r <= 0) {
			fade = 1;
			r = 1;
		}
	}
}
