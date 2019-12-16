package day16;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import util.AdventUtils;

public class Day16Task2Main {

	private static final int[] PATTERN = { 0, 1, 0, -1 };
	private static List<int[]> patterns = new ArrayList<>();

	public static void main(String[] args) {
		try {
			char[] startValue = AdventUtils.getStringInput(16).get(0).toCharArray();
			System.out.println(startValue.length);
			int[] digits = new int[startValue.length];

			for (int i = 0; i < startValue.length; i++) {
				digits[i] = Character.getNumericValue(startValue[i]);

				int[] pattern = new int[4 * (i + 1)];

				int j = 0;

				for (int k = 0; k <= i; k++) {
					pattern[j] = PATTERN[0];
					j++;
				}

				for (int k = 0; k <= i; k++) {
					pattern[j] = PATTERN[1];
					j++;
				}

				for (int k = 0; k <= i; k++) {
					pattern[j] = PATTERN[2];
					j++;
				}

				for (int k = 0; k <= i; k++) {
					pattern[j] = PATTERN[3];
					j++;
				}

				patterns.add(pattern);
			}

			System.out.println(Arrays.toString(digits));

			for (int round = 1; round <= 100; round++) {
				int[] newDigits = new int[digits.length];

				for (int i = 0; i < digits.length; i++) {
					int[] pattern = patterns.get(i);

					long sum = 0;
					for (int j = 0; j < digits.length; j++) {
						sum += (digits[j] * pattern[(j + 1) % pattern.length]);
					}

					String sumAsString = String.valueOf(sum);
					newDigits[i] = Character.getNumericValue(sumAsString.charAt(sumAsString.length() - 1));
				}
				digits = newDigits;
				System.out.println(Arrays.toString(digits));
			}

			System.out.println("----------------------------");

			StringBuilder sb = new StringBuilder();

			for (int i = 0; i < 8; i++) {
				sb.append(digits[i]);
			}

			AdventUtils.publishResult(16, 2, sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
