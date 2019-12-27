package day24;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import util.AdventUtils;

public class Day24Task1Main {

	private static Set<Integer> biodiversityHist = new HashSet<>();

	public static void main(String[] args) {
		try {
			List<String> startValue = AdventUtils.getStringInput(24);

			int[][] map = new int[startValue.size()][startValue.get(0).length()];
			int biodiversity = 0;

			// init
			for (int y = 0; y < startValue.size(); y++) {

				char[] row = startValue.get(y).toCharArray();
				for (int x = 0; x < row.length; x++) {
					boolean isBug = row[x] == '#';

					if (isBug) {
						biodiversity += Math.pow(2, y * row.length + x);
					}

					map[y][x] = isBug ? 1 : 0;
				}
			}

			// repeat
			while (biodiversityHist.size() == 0 || !biodiversityHist.contains(biodiversity)) {
				biodiversityHist.add(biodiversity);

				biodiversity = 0;
				int[][] newMap = new int[map.length][map[0].length];

				for (int y = 0; y < map.length; y++) {
					for (int x = 0; x < map[y].length; x++) {

						int neighbourBugCount = 0;
						neighbourBugCount += bugCount(x, y - 1, map);
						neighbourBugCount += bugCount(x + 1, y, map);
						neighbourBugCount += bugCount(x, y + 1, map);
						neighbourBugCount += bugCount(x - 1, y, map);

						if (neighbourBugCount == 1 || (map[y][x] == 0 && neighbourBugCount == 2)) {
							newMap[y][x] = 1;
							biodiversity += Math.pow(2, y * map[y].length + x);
						} else {
							newMap[y][x] = 0;
						}

					}
				}

				map = newMap;

			}

			StringBuilder sb = new StringBuilder();
			for (int y = 0; y < map.length; y++) {
				for (int x = 0; x < map[y].length; x++) {
					sb.append(map[y][x] == 1 ? '#' : '.');
				}
				sb.append('\n');
			}

			System.out.println(sb.toString());

			AdventUtils.publishResult(15, 1, biodiversity);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static int bugCount(int x, int y, int[][] map) {
		if (y < 0 || y >= map.length || x < 0 || x >= map[y].length) {
			return 0;
		}

		return map[y][x];

	}

}
