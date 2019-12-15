package util.pathfinding;

import util.Touple;

public class CoordingateGraph extends Graph<Touple<Integer, Integer>, CoordingateNode> {

	public CoordingateNode getNode(int y, int x) {
		return getNode(new Touple<>(y, x));
	}

	public CoordingateNode addNode(int y, int x) {
		CoordingateNode node = new CoordingateNode(y, x);

		addNode(node);

		addDestination(node, y - 1, x);
		addDestination(node, y, x + 1);
		addDestination(node, y + 1, x);
		addDestination(node, y, x - 1);

		return node;
	}

	private void addDestination(CoordingateNode node, int y, int x) {
		CoordingateNode other = getNode(y, x);

		if (other == null) {
			return;
		}

		node.addDestination(other, 1);
		other.addDestination(node, 1);
	}

	public Graph<Touple<Integer, Integer>, CoordingateNode> calculateShortestPathFromSource(int y, int x) {
		return calculateShortestPathFromSource(getNode(y, x));
	}
}
