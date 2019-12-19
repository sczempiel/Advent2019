package day19;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import util.AdventUtils;
import util.IntCodeComputer;

public class Day19Task2Main {

	public static void main(String[] args) {
		try {
			List<Long> code = Arrays.asList(AdventUtils.getStringInput(19).get(0).split(",")).stream()
					.map(Long::valueOf).collect(Collectors.toList());

			Integer foundY = null;
			Integer foundX = null;

			int y = 100;
			int lastXStart = 0;
			while (foundY == null) {
				boolean tractorStarted = false;
				boolean tractorEnded = false;

				int x = lastXStart;
				while (!tractorEnded) {
					Long output = new IntCodeComputer(code).run(x, y);
					if (output == 1) {

						if (!tractorStarted) {
							tractorStarted = true;
							lastXStart = x;
						}
						Long output2 = new IntCodeComputer(code).run(x + 99, y);

						if (output2 == 0) {
							tractorEnded = true;
						}

						Long output3 = new IntCodeComputer(code).run(x, y + 99);

						if (output2 == 1 && output3 == 1) {
							foundX = x;
							foundY = y;
						}
					} else if (tractorStarted) {
						tractorEnded = true;
					}

					x++;
				}
				y++;
			}

			AdventUtils.publishResult(19, 2, foundX * 10000 + foundY);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
