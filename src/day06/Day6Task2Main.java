package day06;

import java.io.IOException;
import java.util.List;

import util.AdventUtils;
import util.pathfinding.Graph;

public class Day6Task2Main {
	private static Graph<String, StringNode> graph = new Graph<>();

	public static void main(String[] args) {
		try {
			List<String> startValue = AdventUtils.getStringInput(6);

			for (String line : startValue) {
				String[] raw = line.split("\\)");

				String central = raw[0];
				String orbiter = raw[1];

				StringNode centralNode = graph.getNode(central);

				if (centralNode == null) {
					centralNode = new StringNode(central);
					graph.addNode(centralNode);
				}

				StringNode orbiterNode = graph.getNode(orbiter);

				if (orbiterNode == null) {
					orbiterNode = new StringNode(orbiter);
					graph.addNode(orbiterNode);
				}

				centralNode.addDestination(orbiterNode, 1);
				orbiterNode.addDestination(centralNode, 1);

			}

			graph.calculateShortestPathFromSource(graph.getNode("YOU"));

			List<StringNode> path = graph.getNode("SAN").getShortestPath();

			AdventUtils.publishResult(6, 1, path.size() - 2);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
