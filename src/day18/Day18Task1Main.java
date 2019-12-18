package day18;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import util.AdventUtils;
import util.Touple;
import util.pathfinding.CoordingateGraph;
import util.pathfinding.CoordingateNode;

public class Day18Task1Main {

	private static Integer shortestFinished;

	public static void main(String[] args) {
		try {
			List<String> startValue = AdventUtils.getStringInput(18);

			Set<Touple<Integer, Integer>> freeTiles = new HashSet<>();
			Map<Touple<Integer, Integer>, Character> keys = new HashMap<>();
			Map<Character, Touple<Integer, Integer>> doors = new HashMap<>();

			Touple<Integer, Integer> currentPos = null;

			for (int y = 0; y < startValue.size(); y++) {
				char[] row = startValue.get(y).toCharArray();

				for (int x = 0; x < row.length; x++) {
					char field = row[x];

					if (field == '#') {
						continue;
					}

					Touple<Integer, Integer> coord = new Touple<>(y, x);

					if (field == '@') {
						freeTiles.add(coord);
						currentPos = coord;
					} else if (field == '.') {
						freeTiles.add(coord);
					} else if (Character.isLowerCase(field)) {
						keys.put(coord, field);
					} else {
						doors.put(Character.toLowerCase(field), coord);
					}
				}
			}

			int totalSteps = collectNextKey(freeTiles, keys, doors, currentPos, 0, new ArrayList<>());

			AdventUtils.publishResult(18, 1, totalSteps);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static int collectNextKey(Set<Touple<Integer, Integer>> freeTiles,
			Map<Touple<Integer, Integer>, Character> keys, Map<Character, Touple<Integer, Integer>> doors,
			Touple<Integer, Integer> currentPos, int steps, List<Character> keyOrder) {

		if (shortestFinished != null && shortestFinished <= steps) {
			return Integer.MAX_VALUE;
		}

		freeTiles = new HashSet<>(freeTiles);
		keys = new HashMap<>(keys);
		doors = new HashMap<>(doors);
		keyOrder = new ArrayList<>(keyOrder);

		// walk
		Character key = keys.remove(currentPos);

		if (key != null) {
			Touple<Integer, Integer> doorCord = doors.remove(key);

			if (doorCord != null) {
				freeTiles.add(doorCord);
			}
			freeTiles.add(currentPos);
			keyOrder.add(key);
		}

		if (keys.isEmpty()) {
			shortestFinished = steps;
			System.out.println(steps + " -> " + keyOrder);
			return steps;
		}

		// create graph
		CoordingateGraph graph = new CoordingateGraph();

		List<CoordingateNode> keyNodes = new ArrayList<>();
		for (Touple<Integer, Integer> field : keys.keySet()) {
			CoordingateNode node = new CoordingateNode(field);
			graph.addNode(node);
			keyNodes.add(node);
		}

		for (Touple<Integer, Integer> field : freeTiles) {
			graph.addNode(field);
		}

		// get tile to walk on
		graph.calculateShortestPathFromSource(currentPos);

		keyNodes.sort(Comparator.comparing(CoordingateNode::getDistance));

		Integer shortestStep = null;
		for (CoordingateNode field : keyNodes) {
			if (field.getShortestPath().isEmpty()) {
				continue;
			}

			int stepsAfter = collectNextKey(freeTiles, keys, doors, field.getCoordinate(), steps + field.getDistance(),
					keyOrder);
			if (shortestStep == null || stepsAfter < shortestStep) {
				shortestStep = stepsAfter;
			}
		}

		return shortestStep;
	}

}
