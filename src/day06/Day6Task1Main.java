package day06;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.AdventUtils;

public class Day6Task1Main {

	private static Map<String, List<String>> tree = new HashMap<>();

	public static void main(String[] args) {
		try {
			List<String> startValue = AdventUtils.getStringInput(6);

			for (String line : startValue) {
				String[] raw = line.split("\\)");

				String central = raw[0];
				String orbiter = raw[1];

				List<String> orbiters = tree.get(central);

				if (orbiters == null) {
					orbiters = new ArrayList<>();
				}

				orbiters.add(orbiter);
				tree.put(central, orbiters);

			}

			AdventUtils.publishResult(6, 1, countOrbits("COM", 0));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static int countOrbits(String current, int depth) {
		List<String> childs = tree.get(current);

		if (childs == null) {
			return depth;
		}

		int count = depth;
		
		for (String child : childs) {
			count += countOrbits(child, depth + 1);
		}

		return count;
	}

}
