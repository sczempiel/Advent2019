package day03;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import util.AdventUtils;
import util.Touple;

public class Day3Task1Main {

	private static Set<Touple<Integer, Integer>> walked1 = new HashSet<>();
	private static Set<Touple<Integer, Integer>> walked2 = new HashSet<>();

	private static Set<Touple<Integer, Integer>> crossings = new HashSet<>();

	public static void main(String[] args) {
		try {
			List<String> startValue = AdventUtils.getStringInput(3);

			String[] path1 = startValue.get(0).split(",");
			String[] path2 = startValue.get(1).split(",");

			walkPath(path1, walked1, walked2);
			walkPath(path2, walked2, walked1);

			Integer shortestDist = null;

			for (Touple<Integer, Integer> crossing : crossings) {

				int dist = Math.abs(crossing.getLeft()) + Math.abs(crossing.getRight());

				if (shortestDist == null || dist < shortestDist) {
					shortestDist = dist;
				}
			}

			AdventUtils.publishResult(3, 2, shortestDist);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void walkPath(String[] path, Set<Touple<Integer, Integer>> ownWalk,
			Set<Touple<Integer, Integer>> otherWalk) {
		int y = 0;
		int x = 0;

		for (String part : path) {
			String instruction = part.substring(0, 1);
			int steps = Integer.valueOf(part.substring(1));

			for (int i = 0; i < steps; i++) {
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
				ownWalk.add(new Touple<>(y, x));

				if (otherWalk.contains(position)) {
					crossings.add(position);
				}

			}
		}
	}

}
