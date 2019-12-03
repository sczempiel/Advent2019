package day03;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.AdventUtils;
import util.Touple;

public class Day3Task2Main {

	private static Map<Touple<Integer, Integer>, Integer> walked1 = new HashMap<>();
	private static Map<Touple<Integer, Integer>, Integer> walked2 = new HashMap<>();

	private static List<Integer> crossings = new ArrayList<>();

	public static void main(String[] args) {
		try {
			List<String> startValue = AdventUtils.getStringInput(3);

			String[] path1 = startValue.get(0).split(",");
			String[] path2 = startValue.get(1).split(",");

			walkPath(path1, walked1, walked2);
			walkPath(path2, walked2, walked1);

			Integer shortestSteps = null;

			for (Integer steps : crossings) {

				if (shortestSteps == null || steps < shortestSteps) {
					shortestSteps = steps;
				}
			}

			AdventUtils.publishResult(3, 2, shortestSteps);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void walkPath(String[] path, Map<Touple<Integer, Integer>, Integer> ownWalk,
			Map<Touple<Integer, Integer>, Integer> otherWalk) {
		int y = 0;
		int x = 0;

		int totalSteps = 0;

		for (String part : path) {
			String instruction = part.substring(0, 1);
			int steps = Integer.valueOf(part.substring(1));

			for (int i = 0; i < steps; i++) {
				totalSteps++;
				switch (instruction) {
				case "R":
					x++;
					break;
				case "U":
					y++;
					break;
				case "L":
					x--;
					break;
				case "D":
					y--;
					break;
				}

				Touple<Integer, Integer> position = new Touple<>(y, x);
				ownWalk.put(new Touple<>(y, x), totalSteps);

				Integer otherSteps = otherWalk.get(position);
				if (otherSteps != null) {
					crossings.add(otherSteps + totalSteps);
				}

			}
		}
	}

}
