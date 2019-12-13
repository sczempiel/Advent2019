package day07;

import java.io.IOException;
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

								IntCodeComputer acs1 = new IntCodeComputer(code, p1, 0);
								IntCodeComputer acs2 = new IntCodeComputer(code, p2);
								IntCodeComputer acs3 = new IntCodeComputer(code, p3);
								IntCodeComputer acs4 = new IntCodeComputer(code, p4);
								IntCodeComputer acs5 = new IntCodeComputer(code, p5);

								Long output = 0l;

								while (acs1.isRunning()) {
									output = acs1.run(output);

									if (output == null) {
										break;
									}
									output = acs2.run(output);

									if (output == null) {
										break;
									}
									output = acs3.run(output);

									if (output == null) {
										break;
									}
									output = acs4.run(output);

									if (output == null) {
										break;
									}
									output = acs5.run(output);

									if (output == null) {
										break;
									}

									if (highestThrust == null || highestThrust < output) {
										highestThrust = output;
										highestThrustPhaseSetting = "(" + p1 + ", " + p2 + ", " + p3 + ", " + p4 + ", "
												+ p5 + ")";
									}
								}

							}
						}
					}
				}
			}

			System.out.println("Highest thrust phase setting: " + highestThrustPhaseSetting);
			AdventUtils.publishResult(7, 2, highestThrust);

		} catch (

		IOException e) {
			e.printStackTrace();
		}
	}
}
