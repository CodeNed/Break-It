package com.noah.breakit.graphics;

public class Screen {

	private int width, height;

	public static int[] pixels;

	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
	}

	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0x000000;
		}
	}

	public void render() {
		for (int i = 0; i < pixels.length; i++) {
		}
	}

	public void drawRect(int x, int y, int width, int height, int col) {
		for (int yy = y; yy < y + height; yy++) {
			for (int xx = x; xx < x + width; xx++) {

				if (xx == x || xx == x + width - 1 || yy == y || yy == y + height - 1)
					if (xx > 0 && xx < this.width && yy > 0 && yy < this.height) pixels[xx + yy * this.width] = col;
			}
		}
	}

	public void fillRect(int x, int y, int width, int height, int col) {
		for (int yy = y; yy < y + height; yy++) {
			for (int xx = x; xx < x + width; xx++) {
				if (xx > 0 && xx < this.width && yy > 0 && yy < this.height) pixels[xx + yy * this.width] = col;
			}
		}
	}

	public void renderChar(int x, int y, int col, int[] character) {

		for (int yy = 0; yy < 8; yy++) {
			for (int xx = 0; xx < 8; xx++) {
				if (character[xx + (yy << 3)] == '#') {
					drawRect(x + xx, y + yy, 1, 1, col);
				}
			}
		}
	}

	public void renderString(int x, int y, int col, String string) {

		for (int i = 0; i < string.length(); i++) {
			renderChar(x + (i << 3), y, col, Font.getChar(string.charAt(i)));
		}
	}

	public void renderPixel(int pixel, int index) {
		pixels[index] = pixel;
	}

	public void renderSprite(Sprite sprite) {
		for (int y = 0; y < sprite.HEIGHT; y++) {
			for (int x = 0; x < sprite.WIDTH; x++) {
				if (x + sprite.getX() < this.width && y + sprite.getY() < this.height)
					if (sprite.pixels[x + y * sprite.WIDTH] != 0xffff00ff)
						Screen.pixels[x + sprite.getX() + (y + sprite.getY()) * width] = sprite.pixels[x + y * sprite.WIDTH];
			}
		}
	}

	public void renderSprite(int x, int y, int width, int height, int[] pixels) {
		
		for (int yy = 0; yy < height; yy++) {
			for (int xx = 0; xx < width; xx++) {
				if (xx + x < this.width && yy + y < this.height) {
					if (pixels[xx + yy * width] != 0xffff00ff)
						Screen.pixels[xx + x + (yy + y) * this.width] = pixels[xx + yy * width];
				}
			}
		}
	}
	
	public void renderCol(int col){
		for(int i = 0; i < pixels.length; i++){
			pixels[i] = col;
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}