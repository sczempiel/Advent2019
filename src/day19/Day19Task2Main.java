package day19;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import util.AdventUtils;
import util.IntCodeComputer;
import util.Touple;
import util.pathfinding.Polar;

// 3381134 l
public class Day19Task2Main {

	public static void main(String[] args) {
		try {
			List<Long> code = Arrays.asList(AdventUtils.getStringInput(19).get(0).split(",")).stream()
					.map(Long::valueOf).collect(Collectors.toList());

			List<Touple<Integer, Integer>> hits = new ArrayList<>();

			boolean found = false;

			int y = 100;
			int lastXStart = 0;
			while (!found) {
				while();
				for (int x = lastXStart; x < 1000; x++) {
					Long output = new IntCodeComputer(code).run(x, y);

					if (output == 1) {
						Long output2 = new IntCodeComputer(code).run(x, y + 100);
						if(ou)
					}
				}
				y++;
			}

			Touple<Integer, Integer> last = hits.get(hits.size() - 1);
			Polar pol1 = new Polar(last.getLeft(), last.getRight());

			Polar pol2 = new Polar(1216.151, pol1.getAngle());

			System.out.println(pol2);

			for (int y = pol2.getY() - 10; y < pol2.getY() + 110; y++) {
				for (int x = pol2.getX() - 200; x < pol2.getX() + 400; x++) {
					Long output = new IntCodeComputer(code).run(x, y);
					if (output == 1) {
						System.out.print("#");
					} else {
						System.out.print(".");
					}
				}
				System.out.print("\n");
			}

			AdventUtils.publishResult(19, 1, (pol2.getX() - 100) * 10000 + pol2.getY());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main2(String[] args) {
		try {
			List<Long> code = Arrays.asList(AdventUtils.getStringInput(19).get(0).split(",")).stream()
					.map(Long::valueOf).collect(Collectors.toList());

			List<Touple<Integer, Integer>> hits = new ArrayList<>();

			for (int x = 0; x < 1000; x++) {
				Long output = new IntCodeComputer(code).run(x, 1000);
				if (output != null && output == 1) {
					hits.add(new Touple<>(1000, x));
				}
			}

			Touple<Integer, Integer> last = hits.get(hits.size() - 1);
			Polar pol1 = new Polar(last.getLeft(), last.getRight());

			Polar pol2 = new Polar(1216.151, pol1.getAngle());

			System.out.println(pol2);

			for (int y = pol2.getY() - 10; y < pol2.getY() + 110; y++) {
				for (int x = pol2.getX() - 200; x < pol2.getX() + 400; x++) {
					Long output = new IntCodeComputer(code).run(x, y);
					if (output == 1) {
						System.out.print("#");
					} else {
						System.out.print(".");
					}
				}
				System.out.print("\n");
			}

			AdventUtils.publishResult(19, 1, (pol2.getX() - 100) * 10000 + pol2.getY());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
