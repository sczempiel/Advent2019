package day23;

import java.io.IOException;
import java.util.List;

import util.AdventUtils;

public class Day23Task2Main {

	public static void main(String[] args) {
		try {
			List<Integer> startValue = AdventUtils.getIntegerInput(14);
			
			AdventUtils.publishResult(15, 2, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
