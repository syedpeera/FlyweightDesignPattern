package com.structural.FlyweightDesignPattern;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FlyweightDesignPatternApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlyweightDesignPatternApplication.class, args);
		Circle redCircle = (Circle)ShapeFactory.getShape("Red");
		redCircle.setCoordinates(10, 20);
		redCircle.setRadius(25);
		redCircle.draw();
		
		Circle blueCircle = (Circle)ShapeFactory.getShape("Blue");
		blueCircle.setCoordinates(11, 22);
		blueCircle.setRadius(26);
		blueCircle.draw();
		
		Circle greenCircle = (Circle)ShapeFactory.getShape("Green");
		greenCircle.setCoordinates(12, 22);
		greenCircle.setRadius(27);
		greenCircle.draw();
	}
}
