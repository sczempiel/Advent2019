package day12;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import util.AdventUtils;

public class Day12Task1Main {

	private static List<Moon> moons;

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

			System.out.println("After 0 steps");
			for (Moon moon : moons) {
				moon.move();

				System.out.println(moon);
			}

			System.out.println("---------------------------");

			for (int i = 1; i <= 1000; i++) {
				applyGravity();

				if (i < 11 || i % 10 == 0) {
					System.out.println("After " + i + " steps");
				}
				for (Moon moon : moons) {
					moon.move();

					if (i < 11 || i % 10 == 0) {
						System.out.println(moon);
					}
				}

				if (i < 11 || i % 10 == 0) {
					System.out.println("---------------------------");
				}
			}

			long totalEnergy = moons.stream().mapToLong(Moon::getTotalEnergy).sum();

			AdventUtils.publishResult(12, 1, totalEnergy);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void applyGravity() {
		for (Moon moon : moons) {
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
