package com.noah.breakit.util;

public class Vector2f {
	private float x;
	private float y;
	
	public Vector2f() {
		set(0, 0);
	}

	public Vector2f(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public Vector2f(Vector2f v) {
		this(v.x, v.y);
	}

	public Vector2f add(float x, float y) {
		this.x += x;
		this.y += y;
		return this;
	}

	public Vector2f add(Vector2f v) {
		return add(v.x, v.y);
	}

	public Vector2f sub(float x, float y) {
		this.x -= x;
		this.y -= y;
		return this;
	}

	public Vector2f sub(Vector2f v) {
		return sub(v.x, v.y);
	}

	public boolean equals(Object o) {
		if(!(o instanceof Vector2f))
			return false;
		Vector2f v = (Vector2f)o;
		if(v.x == x && v.y == y)
			return true;
		return false;
	}

	public static float getDistance(Vector2f v0, Vector2f v1) {
		float dx = v0.x - v1.x;
		float  dy = v0.y - v1.y;
		return (float)Math.sqrt(dx * dx + dy * dy);
	}

	public float getDistance(float x, float y) {
		float dx = Math.abs(this.x - x);
		float dy = Math.abs(this.y - y);
		return (float)Math.sqrt(dx * dx + dy * dy);
	}

	public float getDistance(Vector2f v) {
		return getDistance(v.x, v.y);
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public Vector2f setX(float x) {
		this.x = x;
		return this;
	}

	public Vector2f setY(float y) {
		this.y = y;
		return this;
	}

	public void set(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public void set(Vector2f v) {
		set(v.x, v.y);
	}
}