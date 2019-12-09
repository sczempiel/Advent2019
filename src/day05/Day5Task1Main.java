package day05;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import util.AdventUtils;
import util.IntCodeComputer;

public class Day5Task1Main {

	private static final int ID = 1;

	public static void main(String[] args) {
		try {
			List<Long> code = Arrays.asList(AdventUtils.getStringInput(5).get(0).split(",")).stream().map(Long::valueOf)
					.collect(Collectors.toList());

			long result = new IntCodeComputer(code, (output) -> System.out.println(output)).run(ID);

			System.out.println("-----------------------------");
			AdventUtils.publishResult(5, 1, result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
