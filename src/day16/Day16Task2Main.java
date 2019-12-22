package day16;

import java.io.IOException;

import util.AdventUtils;

public class Day16Task2Main {

	public static void main(String[] args) {
		try {
			String startValue = AdventUtils.getStringInput(16).get(0);

			int offset = Integer.valueOf(startValue.substring(0, 7));

			System.out.println(startValue.length() * 10000);
			System.out.println(offset);
			System.out.println("----------------------------");
			long[] digits = new long[startValue.length() * 10000 - offset];

			int d = digits.length - 1;

			outer: while (d >= 0) {

				for (int i = startValue.length() - 1; i >= 0; i--) {

					digits[d] = Character.getNumericValue(startValue.charAt(i));

					d--;
					if (d < 0) {
						break outer;
					}
				}
			}

			long start = System.currentTimeMillis();

			for (int round = 1; round <= 100; round++) {
				System.out.print("Round: " + round);

				long[] newDigits = new long[digits.length];

				long sum = 0;

				for (int i = digits.length - 1; i >= 0; i--) {
					sum += digits[i];

					String sumAsString = String.valueOf(sum);
					newDigits[i] = Character.getNumericValue(sumAsString.charAt(sumAsString.length() - 1));
				}

				digits = newDigits;

				System.out.println(
						" -> finished after: " + AdventUtils.getPrettyTimeElapsed(start, System.currentTimeMillis()));
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
