package day02;

import java.io.IOException;
import java.util.Arrays;

import util.AdventUtils;

public class Day2Task1Main {

	public static void main(String[] args) {
		try {
			int[] code = Arrays.asList(AdventUtils.getStringInput(2).get(0).split(",")).stream()
					.mapToInt(Integer::valueOf).toArray();

			boolean running = true;
			int p = 0;

			code[1] = 12;
			code[2] = 2;

			while (running) {
				int optcode = code[p++];
				int read1 = code[p++];
				int read2 = code[p++];
				int target = code[p++];

				int value1 = code[read1];
				int value2 = code[read2];

				switch (optcode) {
				case 99:
					running = false;
					break;
				case 1:
					code[target] = value1 + value2;
					break;
				case 2:
					code[target] = value1 * value2;
					break;

				}
			}

			AdventUtils.publishResult(2, 1, code[0]);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
