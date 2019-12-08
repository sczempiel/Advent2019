package day07;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import util.AdventUtils;
import util.IntCodeComputer;

public class Day7Task2Main {

	private static final int MIN_PHASE = 5;
	private static final int MAX_PHASE = 9;

	public static void main(String[] args) {
		try {
			int[] code = Arrays.asList(AdventUtils.getStringInput(7).get(0).split(",")).stream()
					.mapToInt(Integer::valueOf).toArray();

			Integer highestThrust = null;
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
								IntCodeComputer[] acss = new IntCodeComputer[5];
								acss[0] = new IntCodeComputer(code);
								acss[1] = new IntCodeComputer(code);
								acss[2] = new IntCodeComputer(code);
								acss[3] = new IntCodeComputer(code);
								acss[4] = new IntCodeComputer(code);

								List<List<Integer>> params = new ArrayList<>();
								List<Integer> params1 = new ArrayList<>();
								params1.add(p1);
								params1.add(0);
								params.add(params1);

								List<Integer> params2 = new ArrayList<>();
								params2.add(p2);
								params.add(params2);

								List<Integer> params3 = new ArrayList<>();
								params3.add(p3);
								params.add(params3);

								List<Integer> params4 = new ArrayList<>();
								params4.add(p4);
								params.add(params4);

								List<Integer> params5 = new ArrayList<>();
								params5.add(p5);
								params.add(params5);

								compute(0, acss, params);

								String phaseSetting = "(" + p1 + ", " + p2 + ", " + p3 + ", " + p4 + ", " + p5 + ")";
								if (highestThrust == null || highestThrust < params1.get(params1.size() - 1)) {
									highestThrust = params1.get(params1.size() - 1);
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

	private static void compute(int acssUnit, IntCodeComputer[] acss, List<List<Integer>> params) {
		acss[acssUnit].run((output) -> {
			int nextUnit = acssUnit + 1;

			if (nextUnit > 4) {
				nextUnit = 0;
			}
			params.get(nextUnit).add(output);
			compute(nextUnit, acss, params);
		}, params.get(acssUnit));
	}

}
