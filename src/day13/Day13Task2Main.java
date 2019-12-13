package day13;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import util.AdventUtils;
import util.IntCodeComputer;
import util.Touple;

public class Day13Task2Main {

	public static void main(String[] args) {
		try (Scanner in = new Scanner(System.in)) {
			List<Long> code = Arrays.asList(AdventUtils.getStringInput(13).get(0).split(",")).stream()
					.map(Long::valueOf).collect(Collectors.toList());

			code.set(0, 2l);

			Map<Touple<Integer, Integer>, Integer> board = new HashMap<>();
			long score = 0;

			List<Long> results = new ArrayList<>();
			IntCodeComputer computer = new IntCodeComputer(code);

			Touple<Integer, Integer> ballPos = new Touple<>(0, 0);
			Touple<Integer, Integer> paddlePos = new Touple<>(0, 0);

			while (computer.isRunning()) {

				int position = 0;

				if (ballPos.getRight() > paddlePos.getRight()) {
					position = 1;
				}
				
				if (ballPos.getRight() < paddlePos.getRight()) {
					position = -1;
				}

				results.add(computer.run(Long.valueOf(position)));

				if (results.size() % 3 == 0) {
					Long x = results.get(results.size() - 3);
					Long y = results.get(results.size() - 2);
					Long id = results.get(results.size() - 1);

					results.clear();

					if (x == -1 && y == 0) {
						score = id;
					} else {

						Touple<Integer, Integer> pos = new Touple<>(y.intValue(), x.intValue());

						if (id == 4) {
							ballPos = pos;
						}
						if (id == 3) {
							paddlePos = pos;
						}

						board.put(pos, id.intValue());
					}
				}
			}

			AdventUtils.publishResult(13, 2, score);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
