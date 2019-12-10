package day10;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import util.AdventUtils;
import util.Touple;

public class Day10Task2Main {

	private static final Touple<Integer, Integer> BASE = new Touple<>(22, 17);
	private static Touple<Integer, Integer> destroyedNr200;
	private static Map<Double, List<Touple<Touple<Double, Double>, Touple<Integer, Integer>>>> map = new TreeMap<>();
	private static int totalAstroidCount = 0;
	private static int totalAstroidsDestroyed = 0;

	public static void main(String[] args) {
		try {
			List<String> startValue = AdventUtils.getStringInput(10);

			for (int y = 0; y < startValue.size(); y++) {

				char[] fields = startValue.get(y).toCharArray();

				for (int x = 0; x < fields.length; x++) {

					if (fields[x] == '#' && (y != BASE.getLeft() || x != BASE.getRight())) {
						Touple<Double, Double> polar = calcPolar(y, x);
						List<Touple<Touple<Double, Double>, Touple<Integer, Integer>>> astroids = map
								.get(polar.getRight());
						if (astroids == null) {
							astroids = new ArrayList<>();
						}

						Touple<Touple<Double, Double>, Touple<Integer, Integer>> data = new Touple<>();

						data.setLeft(polar);
						data.setRight(new Touple<Integer, Integer>(y, x));

						astroids.add(data);
						astroids.sort(Comparator.comparing(d -> d.getLeft().getLeft()));

						map.put(polar.getRight(), astroids);
						totalAstroidCount++;
					}
				}
			}

			for (Entry<Double, List<Touple<Touple<Double, Double>, Touple<Integer, Integer>>>> entry : map.entrySet()) {
				System.out.println("degree: " + entry.getKey() + "; count: " + entry.getValue().size());
			}

			System.out.println("total: " + totalAstroidCount);

			System.out.println("-------------------------------------");

			firingMyLaser();

			System.out.println("-------------------------------------");
			AdventUtils.publishResult(10, 2, (destroyedNr200.getRight() * 100 + destroyedNr200.getLeft()));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void firingMyLaser() {
		// move from -90 to 180
		for (Entry<Double, List<Touple<Touple<Double, Double>, Touple<Integer, Integer>>>> entry : map.entrySet()) {
			if (entry.getKey() < -90) {
				continue;
			}

			destroy(entry);

			if (totalAstroidCount == totalAstroidsDestroyed) {
				return;
			}
		}

		while (totalAstroidCount > totalAstroidsDestroyed) {
			for (Entry<Double, List<Touple<Touple<Double, Double>, Touple<Integer, Integer>>>> entry : map.entrySet()) {
				destroy(entry);

				if (totalAstroidCount == totalAstroidsDestroyed) {
					return;
				}
			}
		}
	}

	private static void destroy(Entry<Double, List<Touple<Touple<Double, Double>, Touple<Integer, Integer>>>> entry) {
		if (!entry.getValue().isEmpty()) {
			totalAstroidsDestroyed++;
			Touple<Touple<Double, Double>, Touple<Integer, Integer>> destroyed = entry.getValue().remove(0);

			if (totalAstroidsDestroyed == 200) {
				destroyedNr200 = destroyed.getRight();
			}

			System.out.println("Destroyed (" + totalAstroidsDestroyed + "): y=" + destroyed.getRight().getLeft()
					+ ", x=" + destroyed.getRight().getRight() + ", degree=" + entry.getKey());
		}
	}

	private static Touple<Double, Double> calcPolar(int yMap, int xMap) {
		// relative to the base
		int y = yMap - BASE.getLeft();
		int x = xMap - BASE.getRight();

		Touple<Double, Double> polar = new Touple<>();

		// radius
		polar.setLeft(Math.sqrt(y * y + x * x));

		// angle
		polar.setRight(Math.toDegrees(Math.atan2(y, x)));

		return polar;
	}

}
