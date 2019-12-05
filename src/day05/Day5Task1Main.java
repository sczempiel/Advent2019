package day05;

import java.io.IOException;

import util.AdventUtils;

public class Day5Task1Main {
	
	private static final String ID = "1";

	public static void main(String[] args) {
		try {
			Integer lastOutput = -1;
			String[] code = AdventUtils.getStringInput(5).get(0).split(",");

			boolean running = true;
			int p = 0;

			while (running) {

				char[] inst = code[p++].toCharArray();

				int optcode = Integer.valueOf(
						inst.length > 1 ? String.valueOf(inst[inst.length - 2]) + String.valueOf(inst[inst.length - 1])
								: String.valueOf(inst[inst.length - 1]));

				char modeParam1 = inst.length > 2 ? inst[inst.length - 3] : '0';
				char modeParam2 = inst.length > 3 ? inst[inst.length - 4] : '0';

				int value1;
				int value2;
				int target;

				switch (optcode) {
				case 99:
					running = false;
					break;
				case 1:
					value1 = getValue(code, p++, modeParam1);
					value2 = getValue(code, p++, modeParam2);
					target = Integer.valueOf(code[p++]);

					code[target] = String.valueOf(value1 + value2);
					break;
				case 2:
					value1 = getValue(code, p++, modeParam1);
					value2 = getValue(code, p++, modeParam2);
					target = Integer.valueOf(code[p++]);

					code[target] = String.valueOf(value1 * value2);
					break;
				case 3:
					target = Integer.valueOf(code[p++]);
					code[target] = ID;
					break;
				case 4:
					lastOutput = getValue(code, p++, modeParam1);
					System.out.println(lastOutput);
					break;
				}
			}

			System.out.println("-----------------------------");
			AdventUtils.publishResult(5, 1, lastOutput);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static int getValue(String[] code, int pointer, char mode) {
		if (mode == '0') {
			return Integer.valueOf(code[Integer.valueOf(code[pointer])]);
		}

		return Integer.valueOf(code[pointer]);
	}

}
