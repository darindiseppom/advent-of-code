package mdrd.advent.of.code.commons;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Set;

public class Dijkstra {

	public static <T> Graph<T> calculateShortestPathFromSource(Graph<T> graph, Node2<T> source) {
		source.setDistance(0);

		Set<Node2<T>> settledNodes = new HashSet<>();
		Set<Node2<T>> unsettledNodes = new HashSet<>();

		unsettledNodes.add(source);

		while (unsettledNodes.size() != 0) {
			Node2<T> currentNode = getLowestDistanceNode(unsettledNodes);
			unsettledNodes.remove(currentNode);
			for (Entry<Node2<T>, Integer> adjacencyPair : currentNode.getAdjacentNodes().entrySet()) {
				Node2<T> adjacentNode = adjacencyPair.getKey();
				Integer edgeWeight = adjacencyPair.getValue();
				if (!settledNodes.contains(adjacentNode)) {
					calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
					unsettledNodes.add(adjacentNode);
				}
			}
			settledNodes.add(currentNode);
		}
		return graph;
	}

	private static <T> Node2<T> getLowestDistanceNode(Set<Node2<T>> unsettledNodes) {
		Node2<T> lowestDistanceNode = null;
		int lowestDistance = Integer.MAX_VALUE;
		for (Node2<T> node : unsettledNodes) {
			int nodeDistance = node.getDistance();
			if (nodeDistance < lowestDistance) {
				lowestDistance = nodeDistance;
				lowestDistanceNode = node;
			}
		}
		return lowestDistanceNode;
	}

	private static <T> void calculateMinimumDistance(Node2<T> evaluationNode, Integer edgeWeigh, Node2<T> sourceNode) {
		Integer sourceDistance = sourceNode.getDistance();
		if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
			evaluationNode.setDistance(sourceDistance + edgeWeigh);
			LinkedList<Node2<T>> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
			shortestPath.add(sourceNode);
			evaluationNode.setShortestPath(shortestPath);
		}
	}
}
