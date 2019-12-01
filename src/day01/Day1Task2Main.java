package day01;

import java.io.IOException;

import util.AdventUtils;

public class Day1Task2Main {

	public static void main(String[] args) {
		try {
			int startValue = AdventUtils.getIntegerInput(1).stream()
					.mapToInt(raw -> calculateFuel(Integer.valueOf(raw))).sum();

			AdventUtils.publishResult(1, 2, startValue);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static int calculateFuel(int mass) {

		int fuel = new Double(Math.floor(mass / 3) - 2).intValue();

		if (fuel > 0) {
			return fuel += calculateFuel(fuel);
		} else {
			return 0;
		}
	}

}
