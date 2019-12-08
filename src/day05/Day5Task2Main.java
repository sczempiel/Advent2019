package day05;

import java.io.IOException;
import java.util.Arrays;

import util.AdventUtils;
import util.IntCodeComputer;

public class Day5Task2Main {

	private static final int ID = 5;

	public static void main(String[] args) {
		try {
			int[] code = Arrays.asList(AdventUtils.getStringInput(5).get(0).split(",")).stream()
					.mapToInt(Integer::valueOf).toArray();
			
			int result = new IntCodeComputer(code).run((output) -> System.out.println(output) ,ID);

			System.out.println("-----------------------------");
			AdventUtils.publishResult(5, 2, result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
