package day10;

import java.io.IOException;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import util.AdventUtils;
import util.Touple;

public class Day10Task2Main {

	// private static final Touple<Integer, Integer> BASE = new Touple<>(22, 17);
	private static final Touple<Integer, Integer> BASE = new Touple<>(13, 11);
	private static Touple<Integer, Integer> lastDestroyed;

	private static Set<Touple<Integer, Integer>> map = new HashSet<>();
	private static int totalAstroidCount = 0;
	private static int yBound;
	private static int xBound;

	private static Set<Touple<Integer, Integer>> blocked;

	public static void main(String[] args) {
		try {
			List<String> startValue = AdventUtils.getStringInput(10);

			yBound = startValue.size();
			xBound = startValue.get(0).length();

			for (int y = 0; y < yBound; y++) {
				char[] fields = startValue.get(y).toCharArray();
				for (int x = 0; x < xBound; x++) {
					if (fields[x] == '#') {
						map.add(new Touple<>(y, x));
						totalAstroidCount++;
					}
				}
			}

			while (map.size() > totalAstroidCount - 200) {
				calcBlocked();

				// up
				for (int y = BASE.getLeft() - 1; y >= 0; y--) {
					Touple<Integer, Integer> coord = new Touple<>(y, x);
					if (!coord.equals(BASE) && map.contains(coord) && !blocked.contains(coord)) {
						map.remove(coord);
					}
				}

				if (map.size() <= totalAstroidCount - 200) {
					break;
				}

				// up-right
				int y = 0;
				int x = BASE.getRight() + 1;
				for (int yO = BASE.getLeft() - 1; y >= 0; y--) {
					Touple<Integer, Integer> coord = new Touple<>(y, x);
					if (map.contains(coord) && !blocked.contains(coord)) {
						map.remove(coord);
					}
				}

				if (map.size() <= totalAstroidCount - 200) {
					break;
				}
				// right
				for (int y = BASE.getLeft() - 1; y >= 0; y--) {
					Touple<Integer, Integer> coord = new Touple<>(y, x);
					if (map.contains(coord) && !blocked.contains(coord)) {
						map.remove(coord);
					}
				}

				if (map.size() <= totalAstroidCount - 200) {
					break;
				}

				// right-bottom
				for (int y = BASE.getLeft() - 1; y >= 0; y--) {
					Touple<Integer, Integer> coord = new Touple<>(y, x);
					if (map.contains(coord) && !blocked.contains(coord)) {
						map.remove(coord);
					}
				}

				if (map.size() <= totalAstroidCount - 200) {
					break;
				}

				// bottom
				for (int y = BASE.getLeft() - 1; y >= 0; y--) {
					Touple<Integer, Integer> coord = new Touple<>(y, x);
					if (map.contains(coord) && !blocked.contains(coord)) {
						map.remove(coord);
					}
				}

				if (map.size() <= totalAstroidCount - 200) {
					break;
				}

				// bottom-left
				for (int y = BASE.getLeft() - 1; y >= 0; y--) {
					Touple<Integer, Integer> coord = new Touple<>(y, x);
					if (map.contains(coord) && !blocked.contains(coord)) {
						map.remove(coord);
					}
				}

				if (map.size() <= totalAstroidCount - 200) {
					break;
				}

				// left
				for (int y = BASE.getLeft() - 1; y >= 0; y--) {
					Touple<Integer, Integer> coord = new Touple<>(y, x);
					if (map.contains(coord) && !blocked.contains(coord)) {
						map.remove(coord);
					}
				}

				if (map.size() <= totalAstroidCount - 200) {
					break;
				}

				// left-up
				for (int y = BASE.getLeft() - 1; y >= 0; y--) {
					Touple<Integer, Integer> coord = new Touple<>(y, x);
					if (map.contains(coord) && !blocked.contains(coord)) {
						map.remove(coord);
					}
				}

				if (map.size() <= totalAstroidCount - 200) {
					break;
				}
			}

			System.out.println("-------------------------------------");
			AdventUtils.publishResult(10, 2, (lastDestroyed.getRight() * 100 + lastDestroyed.getLeft()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void calcBlocked() {

		blocked = new HashSet<>();

		for (Touple<Integer, Integer> coord : map) {

			if (BASE.equals(coord) || blocked.contains(coord)) {
				continue;
			}

			int y = coord.getLeft();
			int x = coord.getRight();

			int ySteps = y - BASE.getLeft();
			int xSteps = x - BASE.getRight();

			int gcd = gcd(ySteps, xSteps);

			ySteps = ySteps / gcd;
			xSteps = xSteps / gcd;

			y = y + ySteps;
			x = x + xSteps;

			while (x >= 0 && y >= 0 && x < xBound && y < yBound) {
				Touple<Integer, Integer> blockedField = new Touple<>(y, x);

				if (map.contains(blockedField)) {
					blocked.add(blockedField);
				}

				y = y + ySteps;
				x = x + xSteps;
			}
		}

	}

	private static void fireLaser(int y, int x) {
		int ySteps = BASE.getLeft() - y;
		int xSteps = BASE.getRight() - x;

		int gcd = gcd(ySteps, xSteps);

		ySteps = ySteps / gcd;
		xSteps = xSteps / gcd;

		Touple<Integer, Integer> astroid = null;

		while (y != BASE.getLeft() || x != BASE.getRight()) {

			Touple<Integer, Integer> coord = new Touple<>(y, x);
			if (map.contains(coord)) {
				astroid = coord;
			}

			y = y + ySteps;
			x = x + xSteps;
		}

		if (astroid != null) {
			map.remove(astroid);
			lastDestroyed = astroid;
			System.out.println("Destroyed (" + astroid.getLeft() + ", " + astroid.getRight() + ") as number "
					+ (totalAstroidCount - map.size()));
		}
	}

	private static int gcd(int a, int b) {
		BigInteger b1 = BigInteger.valueOf(a);
		BigInteger b2 = BigInteger.valueOf(b);
		BigInteger gcd = b1.gcd(b2);
		return gcd.intValue();
	}

}
