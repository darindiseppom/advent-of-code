package mdrd.advent.of.code.commons;

import java.util.HashSet;
import java.util.Set;

public class Graph<T> {

	private Set<Node2<T>> nodes = new HashSet<>();

	public void addNode(Node2<T> nodeA) {
		nodes.add(nodeA);
	}

	public Set<Node2<T>> getNodes() {
		return nodes;
	}

	public void setNodes(Set<Node2<T>> nodes) {
		this.nodes = nodes;
	}
	

}