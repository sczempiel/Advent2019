package day12;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import util.AdventUtils;

public class Day12Task2Main {

	private static List<Moon> moons;
	private static List<List<Moon>> previous = new ArrayList<>();

	public static void main(String[] args) {
		try {
			moons = AdventUtils.getStringInput(12).stream().map(raw -> {
				Moon moon = new Moon();

				String parsing = raw.substring(3, raw.length() - 1);

				String[] splitted = parsing.split(", y=");
				moon.setX(Integer.valueOf(splitted[0]));

				splitted = splitted[1].split(", z=");
				moon.setY(Integer.valueOf(splitted[0]));
				moon.setZ(Integer.valueOf(splitted[1]));

				return moon;
			}).collect(Collectors.toList());

			for (Moon moon : moons) {
			}

			System.out.println("After 0 steps");
			for (Moon moon : moons) {
				moon.move();

				System.out.println(moon);
			}

			System.out.println("---------------------------");

			long i = 0;
			while (!onSamePos() || i == 0) {
				i++;

				applyGravity();

				if (i % 100 == 0) {
					System.out.println("After " + NumberFormat.getInstance().format(i) + " steps");
				}
				for (Moon moon : moons) {
					moon.move();

					if (i % 100 == 0) {
						System.out.println(moon);
					}
				}

				if (i % 100 == 0) {
					System.out.println("---------------------------");
				}
			}

			AdventUtils.publishResult(12, 2, i);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static boolean onSamePos() {
		outer: for (List<Moon> prev : previous) {

			for (int i = 0; i < moons.size(); i++) {
				if (!prev.get(i).sameState(moons.get(i))) {
					continue outer;
				}
			}

			return true;
		}

		return false;
	}

	private static void applyGravity() {
		List<Moon> prev = new ArrayList<>();
		previous.add(prev);

		for (Moon moon : moons) {

			prev.add(new Moon(moon));

			int vX = 0;
			int vY = 0;
			int vZ = 0;

			for (Moon oMoon : moons) {
				if (oMoon == moon) {
					continue;
				}

				if (moon.getX() < oMoon.getX()) {
					vX++;
				} else if (moon.getX() > oMoon.getX()) {
					vX--;
				}

				if (moon.getY() < oMoon.getY()) {
					vY++;
				} else if (moon.getY() > oMoon.getY()) {
					vY--;
				}

				if (moon.getZ() < oMoon.getZ()) {
					vZ++;
				} else if (moon.getZ() > oMoon.getZ()) {
					vZ--;
				}
			}

			moon.setvX(moon.getvX() + vX);
			moon.setvY(moon.getvY() + vY);
			moon.setvZ(moon.getvZ() + vZ);

		}
	}

}
