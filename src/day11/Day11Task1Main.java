package day11;

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

public class Day11Task1Main {

	private static List<Long> outputs = new ArrayList<>();

	private static Map<Touple<Integer, Integer>, Long> positions = new HashMap<>();
	private static Touple<Integer, Integer> currentPos = new Touple<>(0, 0);
	private static IntCodeComputer computer;
	// 0 up 1 right 2 down 3 left
	private static int facing = 0;

	public static void main(String[] args) {
		try {
			List<Long> code = Arrays.asList(AdventUtils.getStringInput(11).get(0).split(",")).stream()
					.map(Long::valueOf).collect(Collectors.toList());

			computer = new IntCodeComputer(code, output -> onOutput(output));

			computer.run(0);
			AdventUtils.publishResult(11, 1, positions.size());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void onOutput(Long output) {
		outputs.add(output);
		if (outputs.size() % 2 == 1) {
			positions.put(currentPos, output);
		}

		if (outputs.size() % 2 == 0) {

			if (output == 0) {
				facing--;
			} else if (output == 1) {
				facing++;
			}

			if (facing < 0) {
				facing = 3;
			}

			if (facing > 3) {
				facing = 0;
			}

			if (facing == 0) {
				currentPos = new Touple<>(currentPos.getLeft() - 1, currentPos.getRight());
			}

			if (facing == 1) {
				currentPos = new Touple<>(currentPos.getLeft(), currentPos.getRight() + 1);
			}

			if (facing == 2) {
				currentPos = new Touple<>(currentPos.getLeft() + 1, currentPos.getRight());
			}

			if (facing == 3) {
				currentPos = new Touple<>(currentPos.getLeft(), currentPos.getRight() - 1);
			}

			Long color = positions.get(currentPos);

			if (color == null) {
				color = 0l;
			}

			computer.run(color);
		}
	}

}
