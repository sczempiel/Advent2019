package day09;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import util.AdventUtils;
import util.IntCodeComputer;

public class Day9Task2Main {

	public static void main(String[] args) {
		try {
			List<Long> code = Arrays.asList(AdventUtils.getStringInput(9).get(0).split(",")).stream().map(Long::valueOf)
					.collect(Collectors.toList());

			IntCodeComputer computer = new IntCodeComputer(code);
			long result = computer.run(2);

			AdventUtils.publishResult(9, 2, result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
