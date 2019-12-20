package day21;

import java.io.IOException;
import java.util.List;

import util.AdventUtils;

public class Day15Task1Main {

	public static void main(String[] args) {
		try {
			List<Integer> startValue = AdventUtils.getIntegerInput(14);
			
			AdventUtils.publishResult(15, 1, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
