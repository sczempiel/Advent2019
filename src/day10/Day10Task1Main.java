package day10;

import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import util.AdventUtils;
import util.Touple;

public class Day10Task1Main {

	public static void main(String[] args) {
		try {
			List<String> startValue = AdventUtils.getStringInput(10);

			Map<Touple<Integer, Integer>, Integer> map = new HashMap<>();
			int totalAstroidCount = 0;

			Touple<Integer, Integer> highestAstroid = null;
			Integer highestAstroidCount = null;

			int yBound = startValue.size();
			int xBound = startValue.get(0).length();

			for (int y = 0; y < yBound; y++) {
				char[] fields = startValue.get(y).toCharArray();
				for (int x = 0; x < xBound; x++) {
					if (fields[x] == '#') {
						map.put(new Touple<>(y, x), 0);
						totalAstroidCount++;
					}
				}
			}

			for (Entry<Touple<Integer, Integer>, Integer> entry : map.entrySet()) {

				Set<Touple<Integer, Integer>> blocked = new HashSet<>();

				Touple<Integer, Integer> coord = entry.getKey();

				for (Entry<Touple<Integer, Integer>, Integer> other : map.entrySet()) {

					Touple<Integer, Integer> oCoord = other.getKey();

					if (coord.equals(oCoord) || blocked.contains(oCoord)) {
						continue;
					}

					int y = oCoord.getLeft();
					int x = oCoord.getRight();

					int ySteps = y - coord.getLeft();
					int xSteps = x - coord.getRight();

					int gcd = gcd(ySteps, xSteps);

					ySteps = ySteps / gcd;
					xSteps = xSteps / gcd;

					y = y + ySteps;
					x = x + xSteps;

					while (x >= 0 && y >= 0 && x < xBound && y < yBound) {
						Touple<Integer, Integer> blockedField = new Touple<>(y, x);

						if (map.containsKey(blockedField)) {
							blocked.add(blockedField);
						}

						y = y + ySteps;
						x = x + xSteps;
					}

				}

				int astroidCount = totalAstroidCount - (blocked.size() + 1);
				map.put(coord, astroidCount);

				if (highestAstroidCount == null || astroidCount > highestAstroidCount) {
					highestAstroidCount = astroidCount;
					highestAstroid = coord;
				}
			}

			System.out.println(highestAstroid);
			System.out.println("-----------------------------");

			AdventUtils.publishResult(10, 1, highestAstroidCount);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static int gcd(int a, int b) {
		BigInteger b1 = BigInteger.valueOf(a);
		BigInteger b2 = BigInteger.valueOf(b);
		BigInteger gcd = b1.gcd(b2);
		return gcd.intValue();
	}

}
