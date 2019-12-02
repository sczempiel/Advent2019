package day02;

import java.io.IOException;
import java.util.Arrays;

import util.AdventUtils;

public class Day2Task1Main {

	public static void main(String[] args) {
		try {
			int noun = 0;
			int verb = 0;

			outer: for (int i = 0; i < 100; i++) {
				for (int j = 0; j < 100; j++) {
					int[] code = Arrays.asList(AdventUtils.getStringInput(2).get(0).split(",")).stream()
							.mapToInt(Integer::valueOf).toArray();

					boolean running = true;
					int p = 0;

					noun = i;
					verb = j;

					code[1] = noun;
					code[2] = verb;

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

					System.out.println(code[0]);
					if (code[0] == 19690720) {
						break outer;
					}
				}
			}
			System.out.println("-----------------------");
			AdventUtils.publishResult(2, 1, 100 * noun + verb);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
