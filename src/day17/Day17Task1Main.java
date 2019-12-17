package day17;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import util.AdventUtils;
import util.IntCodeComputer;

public class Day17Task1Main {

	public static void main(String[] args) {
		try {
			List<Long> code = Arrays.asList(AdventUtils.getStringInput(17).get(0).split(",")).stream()
					.map(Long::valueOf).collect(Collectors.toList());

			IntCodeComputer computer = new IntCodeComputer(code);

			List<List<Integer>> map = new ArrayList<>();
			map.add(new ArrayList<>());

			while (computer.isRunning()) {
				Long output = computer.run();

				if (output == null) {
					break;
				}

				if (output == 10) {
					map.add(new ArrayList<>());
				} else {
					map.get(map.size() - 1).add(output.intValue());
				}
			}

			int interCount = 0;

			for (int y = 1; y < map.size() - 1; y++) {
				for (int x = 1; x < map.get(y).size() - 1; x++) {

					if (map.get(y + 1).isEmpty() || map.get(y).get(x) != 35) {
						continue;
					}

					if (map.get(y - 1).get(x) == 35 && map.get(y).get(x + 1) == 35 && map.get(y + 1).get(x) == 35
							&& map.get(y).get(x - 1) == 35) {
						interCount += (x * y);
					}
				}
			}

			StringBuilder sb = new StringBuilder();
			for (List<Integer> row : map) {

				if (row.isEmpty()) {
					continue;
				}

				for (Integer field : row) {
					sb.append((char) field.intValue());
				}
				sb.append("\n");
			}

			AdventUtils.publishExtra(17, 1, sb.toString(), "grid");

			AdventUtils.publishResult(17, 1, interCount);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
