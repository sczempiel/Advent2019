package day07;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import util.AdventUtils;
import util.IntCodeComputer;

public class Day7Task1Main {

	private static final int MIN_PHASE = 0;
	private static final int MAX_PHASE = 4;

	public static void main(String[] args) {
		try {
			List<Long> code = Arrays.asList(AdventUtils.getStringInput(7).get(0).split(",")).stream().map(Long::valueOf)
					.collect(Collectors.toList());

			Long highestThrust = null;
			String highestThrustPhaseSetting = null;

			for (int p1 = MIN_PHASE; p1 <= MAX_PHASE; p1++) {

				long r1 = new IntCodeComputer(code).run(p1, 0);

				for (int p2 = MIN_PHASE; p2 <= MAX_PHASE; p2++) {

					if (p2 == p1) {
						continue;
					}

					long r2 = new IntCodeComputer(code).run(p2, r1);

					for (int p3 = MIN_PHASE; p3 <= MAX_PHASE; p3++) {

						if (p3 == p1 || p3 == p2) {
							continue;
						}

						long r3 = new IntCodeComputer(code).run(p3, r2);

						for (int p4 = MIN_PHASE; p4 <= MAX_PHASE; p4++) {

							if (p4 == p1 || p4 == p2 || p4 == p3) {
								continue;
							}

							long r4 = new IntCodeComputer(code).run(p4, r3);

							for (int p5 = MIN_PHASE; p5 <= MAX_PHASE; p5++) {

								if (p5 == p1 || p5 == p2 || p5 == p3 || p5 == p4) {
									continue;
								}

								long r5 = new IntCodeComputer(code).run(p5, r4);

								String phaseSetting = "(" + p1 + ", " + p2 + ", " + p3 + ", " + p4 + ", " + p5 + ")";

								System.out.println(phaseSetting + " -> " + r5);

								if (highestThrust == null || highestThrust < r5) {
									highestThrust = r5;
									highestThrustPhaseSetting = phaseSetting;
								}
							}
						}
					}
				}
			}

			System.out.println("-------------------------");
			System.out.println("Highest thrust phase setting: " + highestThrustPhaseSetting);
			AdventUtils.publishResult(7, 1, highestThrust);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
