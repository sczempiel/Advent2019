package day15;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import util.AdventUtils;
import util.IntCodeComputer;
import util.Touple;
import util.pathfinding.CoordingateGraph;
import util.pathfinding.CoordingateNode;

public class Day15Task2Main {

	private static CoordingateGraph graph = new CoordingateGraph();

	private static Touple<Integer, Integer> currentPos = new Touple<>(0, 0);
	private static Touple<Integer, Integer> lastPos = new Touple<>(0, 0);
	private static Touple<Integer, Integer> oxiPos;

	private static Set<Touple<Integer, Integer>> walls = new HashSet<>();
	private static Set<Touple<Integer, Integer>> emptyTiles = new HashSet<>();

	public static void main(String[] args) {
		try {
			List<Long> code = Arrays.asList(AdventUtils.getStringInput(15).get(0).split(",")).stream()
					.map(Long::valueOf).collect(Collectors.toList());

			IntCodeComputer computer = new IntCodeComputer(code);

			emptyTiles.add(new Touple<>(0, 0));

			while (computer.isRunning()) {

				Long output;
				Touple<Integer, Integer> walkedPos;

				Touple<Integer, Integer> northPos = new Touple<>(currentPos.getLeft() - 1, currentPos.getRight());
				Touple<Integer, Integer> eastPos = new Touple<>(currentPos.getLeft(), currentPos.getRight() + 1);
				Touple<Integer, Integer> southPos = new Touple<>(currentPos.getLeft() + 1, currentPos.getRight());
				Touple<Integer, Integer> westPos = new Touple<>(currentPos.getLeft(), currentPos.getRight() - 1);

				int wallCount = countWalls();

				if (!walls.contains(northPos) && (!northPos.equals(lastPos) || wallCount == 3)) {

					output = computer.run(1);
					walkedPos = northPos;

				} else if (!walls.contains(eastPos) && (!eastPos.equals(lastPos) || wallCount == 3)) {

					output = computer.run(4);
					walkedPos = eastPos;

				} else if (!walls.contains(southPos) && (!southPos.equals(lastPos) || wallCount == 3)) {

					output = computer.run(2);
					walkedPos = southPos;

				} else {

					output = computer.run(3);
					walkedPos = westPos;

				}

				if (output == null) {
					continue;
				}

				if (output == 0) {
					walls.add(walkedPos);
				} else if (output == 1) {

					if (countWalls() == 3) {
						walls.add(currentPos);
					}

					lastPos = currentPos;
					currentPos = walkedPos;
					emptyTiles.add(walkedPos);
				} else if (output == 2) {
					oxiPos = walkedPos;
					emptyTiles.add(walkedPos);
					break;
				}
			}

			printGrid();

			for (Touple<Integer, Integer> field : emptyTiles) {
				graph.addNode(field.getLeft(), field.getRight());
			}

			graph.calculateShortestPathFromSource(oxiPos.getLeft(), oxiPos.getRight());

			int longestPath = 0;

			for (CoordingateNode node : graph.getNodes().values()) {
				if (longestPath < node.getDistance()) {
					longestPath = node.getDistance();
				}
			}

			AdventUtils.publishResult(15, 2, longestPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void printGrid() throws IOException {
		Map<Touple<Integer, Integer>, String> map = new HashMap<>();

		for (Touple<Integer, Integer> field : walls) {
			map.put(field, "#");
		}
		for (Touple<Integer, Integer> field : emptyTiles) {
			map.put(field, ".");
		}
		map.put(oxiPos, "O");

		String grid = AdventUtils.printMap(map, field -> field != null ? field : "?");
		AdventUtils.publishExtra(15, 2, grid, "grid");

	}

	private static int countWalls() {
		int countWalls = 0;

		if (walls.contains(new Touple<>(currentPos.getLeft() - 1, currentPos.getRight()))) {
			countWalls++;
		}
		if (walls.contains(new Touple<>(currentPos.getLeft(), currentPos.getRight() + 1))) {
			countWalls++;
		}
		if (walls.contains(new Touple<>(currentPos.getLeft() + 1, currentPos.getRight()))) {
			countWalls++;
		}
		if (walls.contains(new Touple<>(currentPos.getLeft(), currentPos.getRight() - 1))) {
			countWalls++;
		}

		return countWalls;
	}

}
