package day08;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import util.AdventUtils;

public class Day8Task1Main {

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

			Integer fewestZeros = null;
			int result = 0;

			for (int[][] currentLayer : layers) {
				int zeros = 0;
				int ones = 0;
				int twos = 0;

				for (int y = 0; y < currentLayer.length; y++) {
					for (int x = 0; x < currentLayer[y].length; x++) {
						switch (currentLayer[y][x]) {
						case 0:
							zeros++;
							break;
						case 1:
							ones++;
							break;
						case 2:
							twos++;
							break;
						}
					}
				}

				if (fewestZeros == null || zeros < fewestZeros) {
					fewestZeros = zeros;
					result = ones * twos;
				}
			}

			AdventUtils.publishResult(8, 1, result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
