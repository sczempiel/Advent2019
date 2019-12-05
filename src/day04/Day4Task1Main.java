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

				boolean isSame = false;
				for (int i = 1; i < digits.length; i++) {
					char digit = digits[i];

					if (digit == lastDigit) {
						isSame = true;
					}

					if (digit < lastDigit) {
						continue outer;
					}

					lastDigit = digit;
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
