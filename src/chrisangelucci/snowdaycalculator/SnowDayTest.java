package chrisangelucci.snowdaycalculator;

import java.io.IOException;

import chrisangelucci.snowdaycalculator.SnowDayCalculator.SchoolType;

public class SnowDayTest {

	public static void main(String[] args){
		SnowDayCalculator calculator = new SnowDayCalculator("07871", 1, SchoolType.PUBLIC);
		try {
			System.out.println(calculator.getPrediction());
		} catch (IOException e) {
			System.out.println("Something went wrong!");
		}
	}
	// I hate you
}
