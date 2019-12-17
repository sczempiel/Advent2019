package day17;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import util.AdventUtils;
import util.IntCodeComputer;

// 46 w
public class Day17Task2Main {

	private static final String MAIN_MOVES = "A,A,B,C,B,C,B,C,C,A";
	private static final String A_MOVES = "L,10,R,8,R,8";
	private static final String B_MOVES = "L,10,L,12,R,8,R,10";
	private static final String C_MOVES = "R,10,L,12,R,10";

	public static void main(String[] args) {
		try {
			List<Long> code = Arrays.asList(AdventUtils.getStringInput(17).get(0).split(",")).stream()
					.map(Long::valueOf).collect(Collectors.toList());

			code.set(0, 2l);

			IntCodeComputer computer = new IntCodeComputer(code);

			for (int i = 0; i <= 41 * 50; i++) {
				Long output = computer.run();
				if (output != null) {
					System.out.print((char) output.intValue());
				} else {
					System.out.print("?");
				}
			}
			System.out.print((char) computer.run().intValue());
			System.out.print((char) computer.run().intValue());
			System.out.print((char) computer.run().intValue());
			System.out.print((char) computer.run().intValue());
			System.out.print((char) computer.run().intValue());
			System.out.print((char) computer.run().intValue());
			System.out.print((char) computer.run(asInstruction(MAIN_MOVES)).intValue());

			System.out.print((char) computer.run().intValue());
			System.out.print((char) computer.run().intValue());
			System.out.print((char) computer.run().intValue());
			System.out.print((char) computer.run().intValue());
			System.out.print((char) computer.run().intValue());
			System.out.print((char) computer.run().intValue());
			System.out.print((char) computer.run().intValue());
			System.out.print((char) computer.run().intValue());
			System.out.print((char) computer.run().intValue());
			System.out.print((char) computer.run().intValue());
			System.out.print((char) computer.run().intValue());
			System.out.print((char) computer.run(asInstruction(A_MOVES)).intValue());

			System.out.print((char) computer.run().intValue());
			System.out.print((char) computer.run().intValue());
			System.out.print((char) computer.run().intValue());
			System.out.print((char) computer.run().intValue());
			System.out.print((char) computer.run().intValue());
			System.out.print((char) computer.run().intValue());
			System.out.print((char) computer.run().intValue());
			System.out.print((char) computer.run().intValue());
			System.out.print((char) computer.run().intValue());
			System.out.print((char) computer.run().intValue());
			System.out.print((char) computer.run().intValue());
			System.out.print((char) computer.run(asInstruction(B_MOVES)).intValue());

			System.out.print((char) computer.run().intValue());
			System.out.print((char) computer.run().intValue());
			System.out.print((char) computer.run().intValue());
			System.out.print((char) computer.run().intValue());
			System.out.print((char) computer.run().intValue());
			System.out.print((char) computer.run().intValue());
			System.out.print((char) computer.run().intValue());
			System.out.print((char) computer.run().intValue());
			System.out.print((char) computer.run().intValue());
			System.out.print((char) computer.run().intValue());
			System.out.print((char) computer.run().intValue());
			System.out.print((char) computer.run(asInstruction(C_MOVES)).intValue());

			System.out.print((char) computer.run().intValue());
			System.out.print((char) computer.run().intValue());
			System.out.print((char) computer.run().intValue());
			System.out.print((char) computer.run().intValue());
			System.out.print((char) computer.run().intValue());
			System.out.print((char) computer.run().intValue());
			System.out.print((char) computer.run().intValue());
			System.out.print((char) computer.run().intValue());
			System.out.print((char) computer.run().intValue());
			System.out.print((char) computer.run().intValue());
			System.out.print((char) computer.run().intValue());
			System.out.print((char) computer.run().intValue());
			System.out.print((char) computer.run().intValue());
			System.out.print((char) computer.run().intValue());
			System.out.print((char) computer.run().intValue());
			System.out.print((char) computer.run().intValue());
			System.out.print((char) computer.run().intValue());
			System.out.print((char) computer.run().intValue());
			System.out.print((char) computer.run().intValue());
			System.out.print((char) computer.run().intValue());
			System.out.print((char) computer.run().intValue());
			System.out.print((char) computer.run().intValue());
			System.out.print((char) computer.run(asInstruction("n")).intValue());

			while (computer.isRunning()) {
				Long output = computer.run();

				if (output != null) {
					if (output < 100) {
						System.out.print((char) output.intValue());
					} else {
						AdventUtils.publishResult(17, 2, output);
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static long[] asInstruction(String moves) {
		long[] inst = new long[moves.length() + 1];

		for (int i = 0; i < moves.length(); i++) {
			inst[i] = (int) moves.charAt(i);
		}

		inst[inst.length - 1] = (int) '\n';

		return inst;
	}

}
