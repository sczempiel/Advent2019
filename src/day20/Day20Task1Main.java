package day20;

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

public class Day20Task1Main {

	private static Set<Touple<Integer, Integer>> freeTiles = new HashSet<>();
	private static Map<String, List<Touple<Integer, Integer>>> portals = new HashMap<>();

	public static void main(String[] args) {
		try {
			List<String> startValue = AdventUtils.getStringInput(20);

			for (int y = 0; y < startValue.size(); y++) {
				char[] row = startValue.get(y).toCharArray();

				for (int x = 0; x < row.length; x++) {
					char field = row[x];

					if (field == '#') {
						continue;
					}

					Touple<Integer, Integer> coord = new Touple<>(y, x);

					if (field == '.') {
						freeTiles.add(coord);
					} else if (Character.isUpperCase(field)) {
						handlePortal(field, x, y - 1, x, y + 1, startValue);
						handlePortal(field, x, y + 1, x, y - 1, startValue);
						handlePortal(field, x - 1, y, x + 1, y, startValue);
						handlePortal(field, x + 1, y, x - 1, y, startValue);
					}

				}
			}

			Touple<Integer, Integer> currentPos = null;
			Touple<Integer, Integer> targetPos = null;

			CoordingateGraph graph = new CoordingateGraph();

			for (Touple<Integer, Integer> field : freeTiles) {
				graph.addNode(field);
			}

			for (Entry<String, List<Touple<Integer, Integer>>> portal : portals.entrySet()) {
				String key = portal.getKey();
				List<Touple<Integer, Integer>> list = portal.getValue();

				if (key.equals("AA")) {
					currentPos = list.get(0);
				} else if (key.equals("ZZ")) {
					targetPos = list.get(0);
				} else {
					CoordingateNode node1 = graph.getNode(list.get(0));
					CoordingateNode node2 = graph.getNode(list.get(1));

					node1.addDestination(node2, 1);
					node2.addDestination(node1, 1);
				}
			}

			graph.calculateShortestPathFromSource(currentPos);

			AdventUtils.publishResult(20, 1, graph.getNode(targetPos).getDistance());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void handlePortal(char field, int xO, int yO, int xF, int yF, List<String> startValue) {
		try {
			char otherPortalField = startValue.get(yO).charAt(xO);
			char emptyField = startValue.get(yF).charAt(xF);

			if (Character.isUpperCase(otherPortalField) && emptyField == '.') {
				String key;

				if (field > otherPortalField) {
					key = String.valueOf(new char[] { field, otherPortalField });
				} else {
					key = String.valueOf(new char[] { otherPortalField, field });
				}

				List<Touple<Integer, Integer>> list = portals.get(key);
				if (list == null) {
					list = new ArrayList<>();
				}

				list.add(new Touple<>(yF, xF));
				portals.put(key, list);

			}
		} catch (IndexOutOfBoundsException e) {
			return;
		}
	}

}
