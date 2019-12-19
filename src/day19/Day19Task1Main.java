package day19;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import util.AdventUtils;
import util.IntCodeComputer;

public class Day19Task1Main {

	public static void main(String[] args) {
		try {
			List<Long> code = Arrays.asList(AdventUtils.getStringInput(19).get(0).split(",")).stream()
					.map(Long::valueOf).collect(Collectors.toList());

			int hits = 0;
			for (int y = 0; y < 50; y++) {
				for (int x = 0; x < 50; x++) {
					Long output = new IntCodeComputer(code).run(x, y);
					if(output != null) {
						hits += output;
					}
				}
			}

			AdventUtils.publishResult(19, 1, hits);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
