package day01;

import java.io.IOException;

import util.AdventUtils;

public class Day1Task1Main {

	public static void main(String[] args) {
		try {
			int startValue = AdventUtils.getIntegerInput(1).stream().mapToInt(raw -> {
				int rawToInt = Integer.valueOf(raw);

				return new Double(Math.floor(rawToInt / 3) - 2).intValue();
			}).sum();
			AdventUtils.publishResult(1, 1, startValue);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
