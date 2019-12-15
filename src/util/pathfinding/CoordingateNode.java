package util.pathfinding;

import util.Touple;

public class CoordingateNode extends Node<Touple<Integer, Integer>, CoordingateNode> {

	public CoordingateNode(Touple<Integer, Integer> coordinate) {
		super(coordinate);
	}

	public CoordingateNode(int y, int x) {
		super(new Touple<>(y, x));
	}

}
