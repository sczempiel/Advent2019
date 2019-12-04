package day04;

import java.io.IOException;

import util.AdventUtils;

public class Day4Task1Main {

	public static void main(String[] args) {
		try {
			int start = 256310;
			int end = 732736;

			int total = 0;

			outer: for (int current = start; current <= end; current++) {
				char[] digits = String.valueOf(current).toCharArray();

				char lastDigit = digits[0];
				int lastDigitValue = Integer.valueOf(String.valueOf(lastDigit));

				boolean isSame = false;
				for (int i = 1; i < digits.length; i++) {
					char digit = digits[i];

					if (digit == lastDigit) {
						isSame = true;
					}

					int currentValue = Integer.valueOf(String.valueOf(digit));

					if (currentValue < lastDigitValue) {
						continue outer;
					}

					lastDigit = digit;
					lastDigitValue = currentValue;
				}

				if (isSame) {
					total++;
				}
			}

			AdventUtils.publishResult(4, 1, total);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
