package com.structural.FlyweightDesignPattern;

public class Circle implements Shape{
	private String color;
	private int x;
	private int y;
	private int radius;
	
	public Circle(String color) {
		this.color=color;
	}
	
	public void setCoordinates(int x, int y) {
		this.x=x;
		this.y=y;
	}
	
	public void setRadius(int radius) {
		this.radius=radius;
	}
	
	@Override
	public void draw() {
		System.out.println("Drawing Circle: Color: "+color+", x: "+x+", y: "+", Radius: "+radius);
	}
}
