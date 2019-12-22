package day18;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import util.AdventUtils;
import util.Touple;
import util.pathfinding.CoordingateGraph;
import util.pathfinding.CoordingateNode;

// 2946 correct
public class Day18Task1Main {

	private static Integer shortestFinished = Integer.MAX_VALUE;

	public static void main(String[] args) {
		try {
			List<String> startValue = AdventUtils.getStringInput(18);

			Set<Touple<Integer, Integer>> tiles = new HashSet<>();
			Map<Touple<Integer, Integer>, Character> keys = new HashMap<>();
			Map<Touple<Integer, Integer>, Character> doors = new HashMap<>();

			Map<String, Integer> distance = new HashMap<>();
			Map<Character, Set<Character>> neededDoors = new HashMap<>();

			Touple<Integer, Integer> currentPos = null;

			for (int y = 0; y < startValue.size(); y++) {
				char[] row = startValue.get(y).toCharArray();

				for (int x = 0; x < row.length; x++) {
					char field = row[x];

					if (field == '#') {
						continue;
					}

					Touple<Integer, Integer> coord = new Touple<>(y, x);

					tiles.add(coord);
					if (field == '@') {
						currentPos = coord;
						keys.put(coord, field);
					} else if (Character.isLowerCase(field)) {
						keys.put(coord, field);
					} else if (Character.isUpperCase(field)) {
						doors.put(coord, Character.toLowerCase(field));
					}
				}
			}

			// create graph
			CoordingateGraph graph = new CoordingateGraph();

			for (Touple<Integer, Integer> field : tiles) {
				graph.addNode(field);
			}

			// get tile to walk on
			graph.calculateShortestPathFromSource(currentPos);

			for (Entry<Touple<Integer, Integer>, Character> entry : keys.entrySet()) {
				CoordingateNode node = graph.getNode(entry.getKey());

				Set<Character> needed = new HashSet<>();
				for (CoordingateNode path : node.getShortestPath()) {
					Character door = doors.get(path.getCoordinate());
					if (door != null) {
						needed.add(door);
					}
				}

				neededDoors.put(entry.getValue(), needed);
			}

			Map<Touple<Integer, Integer>, Character> _keys = new HashMap<>(keys);
			for (Entry<Touple<Integer, Integer>, Character> entry : keys.entrySet()) {
				// create graph
				graph = new CoordingateGraph();

				for (Touple<Integer, Integer> field : tiles) {
					graph.addNode(field);
				}

				graph.calculateShortestPathFromSource(entry.getKey());
				_keys.remove(entry.getKey());

				for (Entry<Touple<Integer, Integer>, Character> _entry : _keys.entrySet()) {

					String distKey;
					if (entry.getValue() > _entry.getValue()) {
						distKey = String.valueOf(_entry.getValue()) + String.valueOf(entry.getValue());
					} else {
						distKey = String.valueOf(entry.getValue()) + String.valueOf(_entry.getValue());
					}

					distance.put(distKey, graph.getNode(_entry.getKey()).getDistance());
				}
			}

			// calc steps
			long start = System.currentTimeMillis();

			Set<Character> keysLeft = new HashSet<>(keys.values());
			List<Character> openedDoors = new ArrayList<>();

			char key = '@';

			keysLeft.remove(key);

			List<Integer> results = new ArrayList<>();
			List<Thread> threads = new ArrayList<>();
			for (Character left : keysLeft) {
				if (openedDoors.containsAll(neededDoors.get(left))) {

					Runnable run = new Runnable() {

						@Override
						public void run() {
							String distKey;
							if (key > left) {
								distKey = String.valueOf(left) + String.valueOf(key);
							} else {
								distKey = String.valueOf(key) + String.valueOf(left);
							}

							int steps = calcSteps(left, distance.get(distKey), distance, neededDoors, openedDoors,
									keysLeft);
							results.add(steps);
						}
					};
					Thread thread = new Thread(run);
					threads.add(thread);
					thread.start();
					;
				}
			}

			for (Thread thread : threads) {
				try {
					thread.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			Integer min = null;

			for (Integer steps : results) {
				if (min == null || min > steps) {
					min = steps;
				}
			}

			AdventUtils.publishNewExtraLine(18, 1, "---------------------------------------------------------------",
					"minHist");
			AdventUtils.publishNewExtraLine(18, 1,
					"Finished in " + AdventUtils.getPrettyTimeElapsed(start, System.currentTimeMillis()), "minHist");

			System.out.println("---------------");

			AdventUtils.publishResult(18, 1, min);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static int calcSteps(char key, int steps, Map<String, Integer> distance,
			Map<Character, Set<Character>> neededDoorsMap, List<Character> _openedDoors, Set<Character> _keysLeft) {

		if (steps > shortestFinished) {
			return Integer.MAX_VALUE;
		}

		List<Character> openedDoors = new ArrayList<>(_openedDoors);
		Set<Character> keysLeft = new HashSet<>(_keysLeft);

		keysLeft.remove(key);
		openedDoors.add(key);

		if (keysLeft.isEmpty()) {
			if (steps < shortestFinished) {
				shortestFinished = steps;
				try {
					AdventUtils.publishNewExtraLine(18, 1, openedDoors + " -> " + steps, "minHist");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return steps;
		}

		Integer minSteps = null;
		for (Character left : keysLeft) {
			if (openedDoors.containsAll(neededDoorsMap.get(left))) {
				String distKey;
				if (key > left) {
					distKey = String.valueOf(left) + String.valueOf(key);
				} else {
					distKey = String.valueOf(key) + String.valueOf(left);
				}

				int toalSteps = calcSteps(left, steps + distance.get(distKey), distance, neededDoorsMap, openedDoors,
						keysLeft);
				if (minSteps == null || minSteps > toalSteps) {
					minSteps = toalSteps;
				}
			}
		}

		return minSteps;
	}

//	private static int playManually(Set<Touple<Integer, Integer>> freeTiles,
//			Map<Touple<Integer, Integer>, Character> keys, Map<Character, Touple<Integer, Integer>> doors,
//			Touple<Integer, Integer> currentPos) {
//
//		List<Character> keyOrder = new ArrayList<>();
//		int totalSteps = 0;
//
//		Scanner s = new Scanner(System.in);
//		while (true) {
//			// create graph
//			CoordingateGraph graph = new CoordingateGraph();
//
//			List<CoordingateNode> keyNodes = new ArrayList<>();
//			for (Touple<Integer, Integer> field : keys.keySet()) {
//				CoordingateNode node = new CoordingateNode(field);
//				graph.addNode(node);
//				keyNodes.add(node);
//			}
//
//			for (Touple<Integer, Integer> field : freeTiles) {
//				graph.addNode(field);
//			}
//
//			// get tile to walk on
//			graph.calculateShortestPathFromSource(currentPos);
//
//			keyNodes.sort(Comparator.comparing(CoordingateNode::getDistance));
//
//			for (Iterator<CoordingateNode> it = keyNodes.iterator(); it.hasNext();) {
//				CoordingateNode node = it.next();
//				if (!node.getShortestPath().isEmpty()) {
//					System.out.print(keys.get(node.getCoordinate()) + " " + node.getDistance() + "; ");
//				} else {
//					it.remove();
//				}
//			}
//			System.out.println("total steps: " + totalSteps);
//
//			CoordingateNode keyNode = null;
//			if (keyNodes.size() != 1) {
//
//				while (keyNode == null) {
//					char nextKey = s.next().charAt(0);
//
//					Touple<Integer, Integer> keyPos = null;
//
//					for (Entry<Touple<Integer, Integer>, Character> entry : keys.entrySet()) {
//						if (entry.getValue() == nextKey) {
//							keyPos = entry.getKey();
//							break;
//						}
//					}
//
//					keyNode = graph.getNode(keyPos);
//				}
//
//			} else {
//				keyNode = keyNodes.get(0);
//			}
//
//			totalSteps += keyNode.getDistance();
//			currentPos = keyNode.getCoordinate();
//
//			// walk
//			Character key = keys.remove(currentPos);
//
//			if (key != null) {
//				Touple<Integer, Integer> doorCord = doors.remove(key);
//
//				if (doorCord != null) {
//					freeTiles.add(doorCord);
//				}
//				freeTiles.add(currentPos);
//				keyOrder.add(key);
//			}
//
//			if (keys.isEmpty()) {
//				break;
//			}
//
//		}
//		s.close();
//		System.out.println("---------------");
//		System.out.print(keyOrder + " -> ");
//
//		return totalSteps;
//	}

}
