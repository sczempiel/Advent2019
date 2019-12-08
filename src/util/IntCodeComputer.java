package util;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class IntCodeComputer {

	private final int[] code;

	private int codePointer = 0;
	private int paramPointer = 0;
	private boolean running = true;

	private Integer lastOutput;

	public IntCodeComputer(int[] code) {
		this.code = Arrays.copyOf(code, code.length);
	}

	public Integer run(Consumer<Integer> output, List<Integer> params) {
		return run(output, params.stream().mapToInt(Integer::intValue).toArray());
	}

	public Integer run(Consumer<Integer> output, int... params) {

		while (running) {

			String inst = String.valueOf(code[codePointer++]);

			String optcode = inst.length() > 1 ? inst.substring(inst.length() - 2) : inst.substring(inst.length() - 1);

			String modeParam1 = inst.length() > 2 ? inst.substring(inst.length() - 3, inst.length() - 2) : "0";
			String modeParam2 = inst.length() > 3 ? inst.substring(inst.length() - 4, inst.length() - 3) : "0";

			int value1;
			int value2;
			int target;

			switch (optcode) {
			case "99":
				running = false;
				break;
			case "1":
			case "01":
				value1 = getValue(code, codePointer++, modeParam1);
				value2 = getValue(code, codePointer++, modeParam2);
				target = code[codePointer++];

				code[target] = value1 + value2;
				break;
			case "2":
			case "02":
				value1 = getValue(code, codePointer++, modeParam1);
				value2 = getValue(code, codePointer++, modeParam2);
				target = code[codePointer++];

				code[target] = value1 * value2;
				break;
			case "3":
			case "03":
				target = code[codePointer++];
				code[target] = params[paramPointer++];
				break;
			case "4":
			case "04":
				lastOutput = getValue(code, codePointer++, modeParam1);
				if (output != null) {
					output.accept(lastOutput);
				}
				break;
			case "5":
			case "05":
				value1 = getValue(code, codePointer++, modeParam1);
				value2 = getValue(code, codePointer++, modeParam2);

				if (value1 != 0) {
					codePointer = value2;
				}
				break;
			case "6":
			case "06":
				value1 = getValue(code, codePointer++, modeParam1);
				value2 = getValue(code, codePointer++, modeParam2);

				if (value1 == 0) {
					codePointer = value2;
				}
				break;
			case "7":
			case "07":
				value1 = getValue(code, codePointer++, modeParam1);
				value2 = getValue(code, codePointer++, modeParam2);
				target = code[codePointer++];

				if (value1 < value2) {
					code[target] = 1;
				} else {
					code[target] = 0;
				}
				break;
			case "8":
			case "08":
				value1 = getValue(code, codePointer++, modeParam1);
				value2 = getValue(code, codePointer++, modeParam2);
				target = code[codePointer++];

				if (value1 == value2) {
					code[target] = 1;
				} else {
					code[target] = 0;
				}
				break;
			}
		}

		return lastOutput;
	}

	private int getValue(int[] code, int pointer, String mode) {
		if (mode.equals("0")) {
			return code[code[pointer]];
		}

		return code[pointer];
	}

}
