package day13;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import util.AdventUtils;
import util.IntCodeComputer;
import util.Touple;

public class Day13Task1Main {

	private static Map<Touple<Long, Long>, Long> board = new HashMap<>();

	public static void main(String[] args) {
		try {
			List<Long> code = Arrays.asList(AdventUtils.getStringInput(13).get(0).split(",")).stream()
					.map(Long::valueOf).collect(Collectors.toList());

			List<Long> results = new ArrayList<>();

			IntCodeComputer computer = new IntCodeComputer(code);
			
			while (computer.isRunning()) {
				results.add(computer.run());

				if (results.size() % 3 == 0) {
					Long x = results.get(results.size() - 3);
					Long y = results.get(results.size() - 2);
					Long id = results.get(results.size() - 1);

					board.put(new Touple<Long, Long>(y, x), id);
				}
			}

			AdventUtils.publishResult(13, 1, board.values().stream().filter(id -> id == 2).count());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
