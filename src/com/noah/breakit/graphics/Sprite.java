package com.noah.breakit.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class Sprite {

	private int x = 0;
	private int y = 0;

	public final int width;
	public final int height;

	public int[] pixels = null;

	private Sprite(int x, int y, int width, int height) {

		this.x = x;
		this.y = y;

		this.width = width;
		this.height = height;

		pixels = new int[width * height];
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

		for (int yy = 0; yy < height; yy++) {
			for (int xx = 0; xx < width; xx++)
				pixels[xx + yy * width] = image.getRGB(xx, yy);
		}
	}

	public void render(Screen screen) {
		screen.renderSprite(x, y, width, height, pixels);
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