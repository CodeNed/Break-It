package com.noah.breakit.util;

public class Vector2d {
	private double x;
	private double y;

	public Vector2d() {
		set(0, 0);
	}

	public Vector2d(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public Vector2d(Vector2d v) {
		this(v.x, v.y);
	}

	public Vector2d add(double x, double y) {
		this.x += x;
		this.y += y;
		return this;
	}

	public Vector2d add(Vector2d v) {
		return add(v.x, v.y);
	}

	public Vector2d sub(double x, double y) {
		this.x -= x;
		this.y -= y;
		return this;
	}

	public Vector2d sub(Vector2d v) {
		return sub(v.x, v.y);
	}

	public boolean equals(Object o) {
		if(!(o instanceof Vector2d))
			return false;
		Vector2d v = (Vector2d)o;
		if(v.x == x && v.y == y)
			return true;
		return false;
	}

	public static double getDistance(Vector2d v0, Vector2d v1) {
		double dx = v0.x - v1.x;
		double dy = v0.y - v1.y;
		return Math.sqrt(dx * dx + dy * dy);
	}

	public double getDistance(double x, double y) {
		double dx = Math.abs(this.x - x);
		double dy = Math.abs(this.y - y);
		return Math.sqrt(dx * dx + dy * dy);
	}

	public double getDistance(Vector2d v) {
		return getDistance(v.x, v.y);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public Vector2d setX(double x) {
		this.x = x;
		return this;
	}

	public Vector2d setY(double y) {
		this.y = y;
		return this;
	}

	public void set(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public void set(Vector2d v) {
		set(v.x, v.y);
	}
}