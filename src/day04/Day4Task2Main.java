package day04;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import util.AdventUtils;

public class Day4Task2Main {

	public static void main(String[] args) {
		try {
			int start = 256310;
			int end = 732736;

			int total = 0;

			outer: for (int current = start; current <= end; current++) {
				char[] digits = String.valueOf(current).toCharArray();

				char lastDigit = digits[0];
				int lastDigitValue = Integer.valueOf(String.valueOf(lastDigit));

				List<Integer> counts = new ArrayList<>();
				counts.add(1);

				for (int i = 1; i < digits.length; i++) {
					char digit = digits[i];

					if (digit == lastDigit) {
						int index = counts.size() - 1;
						counts.set(index, counts.get(index) + 1);
					} else {
						counts.add(1);
					}

					int currentValue = Integer.valueOf(String.valueOf(digit));

					if (currentValue < lastDigitValue) {
						continue outer;
					}

					lastDigit = digit;
					lastDigitValue = currentValue;
				}

				for (Integer count : counts) {

					if (count == 2) {
						total++;
						break;
					}
				}
			}

			AdventUtils.publishResult(4, 2, total);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
