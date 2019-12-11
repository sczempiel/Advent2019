package day08;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import util.AdventUtils;

public class Day8Task2Main {

	private static final int WIDE = 25;
	private static final int TALL = 6;

	private static List<int[][]> layers = new ArrayList<>();

	public static void main(String[] args) {
		try {
			String startValue = AdventUtils.getStringInput(8).get(0);

			List<Integer> pixels = new ArrayList<>(startValue.length());
			for (char c : startValue.toCharArray()) {
				pixels.add(Integer.valueOf(String.valueOf(c)));
			}

			int row = 0;
			int column = 0;
			int[][] layer = new int[TALL][WIDE];
			layers.add(layer);

			for (Integer pixel : pixels) {

				layer[row][column] = pixel;

				column++;
				if (column >= WIDE) {
					column = 0;
					row++;
				}

				if (row >= TALL) {
					row = 0;
					layer = new int[TALL][WIDE];
					layers.add(layer);
				}
			}

			StringBuilder sb = new StringBuilder();

			for (int y = 0; y < TALL; y++) {
				for (int x = 0; x < WIDE; x++) {

					for (int[][] currentLayer : layers) {
						int pixel = currentLayer[y][x];

						if (pixel == 0) {
							sb.append(".");
							break;
						}
						if (pixel == 1) {
							sb.append("#");
							break;
						}

					}
				}
				if (y < TALL - 1) {
					sb.append("\n");
				}
			}

			AdventUtils.publishResult(8, 2, sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
