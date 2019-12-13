package day14;

import java.io.IOException;
import java.util.List;

import util.AdventUtils;

public class Day14Task1Main {

	public static void main(String[] args) {
		try {
			List<Integer> startValue = AdventUtils.getIntegerInput(14);
			
			AdventUtils.publishResult(14, 1, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
