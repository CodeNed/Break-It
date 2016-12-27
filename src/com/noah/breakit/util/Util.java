package com.noah.breakit.util;

import java.util.Random;

public class Util {

	public static Random random = new Random();
	
	private Util() {
	}
	
	public static int min(int n, int min) {
		return n < min ? min : n;
	}

	public static int max(int n, int max) {
		return n > max ? max : n;
	}

	public static int clamp(int n, int min, int max) {
		if (n < min) return min;
		if (n > max) return max;
		return n;
	}
	
	public static float min(float n, float min) {
		return n < min ? min : n;
	}

	public static float max(float n, float max) {
		return n > max ? max : n;
	}

	public static float clamp(float n, float min, float max) {
		if (n < min) return min;
		if (n > max) return max;
		return n;
	}
}