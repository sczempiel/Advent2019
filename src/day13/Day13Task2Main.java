package day13;

import java.io.IOException;
import java.util.List;

import util.AdventUtils;

public class Day13Task2Main {

	public static void main(String[] args) {
		try {
			List<Integer> startValue = AdventUtils.getIntegerInput(13);
			
			AdventUtils.publishResult(13, 2, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
