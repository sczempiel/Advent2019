package day07;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import util.AdventUtils;
import util.IntCodeComputer;

public class Day7Task2Main {

	private static final int MIN_PHASE = 5;
	private static final int MAX_PHASE = 9;

	public static void main(String[] args) {
		try {
			List<Long> code = Arrays.asList(AdventUtils.getStringInput(7).get(0).split(",")).stream().map(Long::valueOf)
					.collect(Collectors.toList());

			Long highestThrust = null;
			String highestThrustPhaseSetting = null;

			for (int p1 = MIN_PHASE; p1 <= MAX_PHASE; p1++) {

				for (int p2 = MIN_PHASE; p2 <= MAX_PHASE; p2++) {

					if (p2 == p1) {
						continue;
					}

					for (int p3 = MIN_PHASE; p3 <= MAX_PHASE; p3++) {

						if (p3 == p1 || p3 == p2) {
							continue;
						}

						for (int p4 = MIN_PHASE; p4 <= MAX_PHASE; p4++) {

							if (p4 == p1 || p4 == p2 || p4 == p3) {
								continue;
							}

							for (int p5 = MIN_PHASE; p5 <= MAX_PHASE; p5++) {

								if (p5 == p1 || p5 == p2 || p5 == p3 || p5 == p4) {
									continue;
								}

								List<Long> thrusts = new ArrayList<>();

								IntCodeComputer[] acss = new IntCodeComputer[5];

								acss[0] = new IntCodeComputer(code, (output) -> acss[1].run(output), p1, 0);
								acss[1] = new IntCodeComputer(code, (output) -> acss[2].run(output), p2);
								acss[2] = new IntCodeComputer(code, (output) -> acss[3].run(output), p3);
								acss[3] = new IntCodeComputer(code, (output) -> acss[4].run(output), p4);
								acss[4] = new IntCodeComputer(code, (output) -> {
									thrusts.add(output);
									acss[0].run(output);
								}, p5);

								acss[0].run(0);

								String phaseSetting = "(" + p1 + ", " + p2 + ", " + p3 + ", " + p4 + ", " + p5 + ")";
								if (highestThrust == null || highestThrust < thrusts.get(thrusts.size() - 1)) {
									highestThrust = thrusts.get(thrusts.size() - 1);
									highestThrustPhaseSetting = phaseSetting;
								}

							}
						}
					}
				}
			}

			System.out.println("Highest thrust phase setting: " + highestThrustPhaseSetting);
			AdventUtils.publishResult(7, 2, highestThrustPhaseSetting + highestThrust);

		} catch (

		IOException e) {
			e.printStackTrace();
		}
	}
}
