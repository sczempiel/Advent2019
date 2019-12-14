package day14;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.AdventUtils;
import util.Touple;

public class Day14Task1Main {
	private static Map<String, Touple<Double, List<Touple<String, Double>>>> map = new HashMap<>();
	private static Map<String, Double> overMap = new HashMap<>();

	public static void main(String[] args) {
		try {
			List<String> startValue = AdventUtils.getStringInput(14);

			for (String row : startValue) {
				String[] splitted = row.split(" => ");

				String[] outputSplit = splitted[1].split(" ");

				splitted = splitted[0].split(", ");

				List<Touple<String, Double>> materials = new ArrayList<>();

				for (String material : splitted) {
					String[] matSplit = material.split(" ");

					materials.add(new Touple<>(matSplit[1], Double.valueOf(matSplit[0])));
				}

				map.put(outputSplit[1], new Touple<>(Double.valueOf(outputSplit[0]), materials));

			}

			AdventUtils.publishResult(14, 1, Double.valueOf(calcNeed("FUEL", 1d)).intValue());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static double calcNeed(String output, double amount) {
		if (output.equals("ORE")) {
			return amount;
		}

		Touple<Double, List<Touple<String, Double>>> mats = map.get(output);

		Double over = overMap.get(output);

		if (over == null) {
			over = 0d;
		}

		if (amount < over) {
			overMap.put(output, over - amount);
			return 0d;
		} else {
			double needed = amount - over;

			double times = Math.ceil(needed / mats.getLeft());

			over = times * mats.getLeft() - needed;
			overMap.put(output, over);

			double ores = 0;
			for (Touple<String, Double> mat : mats.getRight()) {
				ores += calcNeed(mat.getLeft(), mat.getRight() * times);
			}
			return ores;
		}
	}

}
