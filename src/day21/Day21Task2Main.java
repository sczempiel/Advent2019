package day21;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import util.AdventUtils;
import util.IntCodeComputer;

public class Day21Task2Main {

	private static final String[] INSTRUCTIONS = new String[] { "OR E J", "OR H J", "AND D J", "OR A T",
			"AND B T", "AND C T", "NOT T T", "AND T J", "RUN" };

	public static void main(String[] args) {
		try {
			List<Long> code = Arrays.asList(AdventUtils.getStringInput(21).get(0).split(",")).stream()
					.map(Long::valueOf).collect(Collectors.toList());

			System.out.println("Instructions: " + INSTRUCTIONS.length);
			IntCodeComputer computer = new IntCodeComputer(code);

			Long lastOutput = null;

			while (computer.isRunning()) {
				Long rawOutput = computer.run();

				if (rawOutput != null) {
					lastOutput = rawOutput;

					if (rawOutput < 127) {
						char output = (char) rawOutput.longValue();
						System.out.print(output);

						if (output == ':') {
							// after the : one space will be rendered
							System.out.print((char) computer.run().intValue());
							// enter program
							System.out
									.print((char) computer.run(IntCodeComputer.asInstruction(INSTRUCTIONS)).intValue());
						}
					}
				}
			}

			System.out.println("----------------------------");

			AdventUtils.publishResult(21, 2, lastOutput);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
