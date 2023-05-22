package com.structural.FlyweightDesignPattern;

import java.util.HashMap;
import java.util.Map;

public class ShapeFactory {
	private static final Map<String, Shape> shapeCache = new HashMap<>();
	
	public static Shape getShape(String color) {
		Shape shape = shapeCache.get(color);
		if(shape==null) {
			shape = new Circle(color);
			shapeCache.put(color, shape);
			System.out.println("Creating "+color+" Circle");
		}
		return shape;
	}
}
