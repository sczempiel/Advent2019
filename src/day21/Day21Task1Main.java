package day21;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import util.AdventUtils;
import util.IntCodeComputer;

public class Day21Task1Main {

	private static final String[] INSTRUCTIONS = new String[] { "NOT C J", "AND D J", "NOT A T", "OR T J", "WALK" };

	public static void main(String[] args) {
		try {
			List<Long> code = Arrays.asList(AdventUtils.getStringInput(21).get(0).split(",")).stream()
					.map(Long::valueOf).collect(Collectors.toList());

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

			AdventUtils.publishResult(21, 1, lastOutput);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
