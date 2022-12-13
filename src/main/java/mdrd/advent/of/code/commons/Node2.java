package mdrd.advent.of.code.commons;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * <b>TODO</b>:
 * <li><a href="https://github.com/darindiseppom/advent-of-code/issues/18">#18</a> - Unire le due classi Node in una sola</li>
 */
public class Node2<T> {

	private T name;
	private List<Node2<T>> shortestPath = new LinkedList<>();
	private Integer distance = Integer.MAX_VALUE;
	private Map<Node2<T>, Integer> adjacentNodes = new HashMap<>();

	public Node2(T name) {
		this.name = name;
	}

	public void addDestination(Node2<T> destination, int distance) {
		adjacentNodes.put(destination, distance);
	}

	public T getName() {
		return name;
	}

	public void setName(T name) {
		this.name = name;
	}

	public List<Node2<T>> getShortestPath() {
		return shortestPath;
	}

	public void setShortestPath(List<Node2<T>> shortestPath) {
		this.shortestPath = shortestPath;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	public Map<Node2<T>, Integer> getAdjacentNodes() {
		return adjacentNodes;
	}

	public void setAdjacentNodes(Map<Node2<T>, Integer> adjacentNodes) {
		this.adjacentNodes = adjacentNodes;
	}

	@Override
	public String toString() {
		return name + "";
	}

}