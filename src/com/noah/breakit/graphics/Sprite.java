package com.noah.breakit.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class Sprite {

	private int x;
	private int y;

	public final int WIDTH;
	public final int HEIGHT;

	public int[] pixels;

	private Sprite(int x, int y, int width, int height) {

		this.x = x;
		this.y = y;

		WIDTH = width;
		HEIGHT = height;

		pixels = new int[WIDTH * HEIGHT];
	}

	public Sprite(int x, int y, int width, int height, int[] pixels) {
		this(x, y, width, height);

		for (int i = 0; i < pixels.length; i++) {
			this.pixels[i] = pixels[i];
		}
	}

	public Sprite(int x, int y, int width, int height, String filename) {
		this(x, y, width, height);
		URL url = this.getClass().getClassLoader().getResource(filename);
		BufferedImage image = null;
		try {
			image = ImageIO.read(url);
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (int yy = 0; yy < HEIGHT; yy++) {
			for (int xx = 0; xx < WIDTH; xx++)
				pixels[xx + yy * WIDTH] = image.getRGB(xx, yy);
		}
	}

	public void render(Screen screen) {
		screen.renderSprite(x, y, WIDTH, HEIGHT, pixels);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
}
