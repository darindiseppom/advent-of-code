package commons;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class Node<T extends Comparable<T>> {

	// TODO: Cambiare il tipo di {@code children} in modo che non permetta
	// l'inserimento di figli con lo stesso value

	private T data = null;
	private List<Node<T>> children = new ArrayList<>();
	private Node<T> parent = null;
	private int depth = 0;
	private int weight = 0;

	public Node() {
	}

	public Node(T data) {
		this.data = data;
	}

	public Node(T data, int weight) {
		this.data = data;
		this.weight = weight;
	}

	public void addChild(T data) {
		Node<T> newChild = new Node<>(data);
		this.addChild(newChild);
	}

	public void addChild(T data, int weight) {
		Node<T> newChild = new Node<>(data, weight);
		this.addChild(newChild);
	}

	public void addChild(Node<T> child) {
		child.setParent(this);
		child.setDepth(this.getDepth() + 1);
		child.getChildren().forEach(c -> c.setDepth(this.getDepth() + 2));
		this.children.add(child);
	}

	public Node<T> getChild(T data) {
		for (Node<T> child : children) {
			if (child.getData().compareTo(data) == 0) {
				return child;
			}
		}
		throw new NoSuchElementException("Child not found: " + data);
	}

	public void addChildren(List<Node<T>> children) {
		for (Node<T> t : children) {
			t.setParent(this);
			t.setDepth(this.getDepth() + 1);
		}
		this.children.addAll(children);
	}

	public List<Node<T>> getChildren() {
		return children;
	}

	public boolean hasChildren() {
		return !children.isEmpty();
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	private void setParent(Node<T> parent) {
		this.parent = parent;
	}

	public boolean hasParent() {
		return parent != null;
	}

	public Node<T> getParent() {
		return parent;
	}

	public int getDepth() {
		return depth;
	}

	private void setDepth(int depth) {
		this.depth = depth;
	}

	public int getWeight() {
		return weight;
	}

	public int getTreeWeight() {
		int result = weight;
		for (Node<T> node : children) {
			result += node.getTreeWeight();
		}
		return result;
	}

	@SuppressWarnings("rawtypes")
	public static Comparator<Node> getWeightComparator() {
		return new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return Integer.compare(o1.getTreeWeight(), o2.getTreeWeight());
			}
		};
	}

	@Override
	public String toString() {
		if (children.isEmpty()) {
			return data + ", " + weight;
		} else {
			StringBuilder sb = new StringBuilder();
			for (Node<T> node : children) {
				sb.append("\n");
				for (int i = 0; i < node.getDepth(); i++) {
					sb.append("  ");
				}
				sb.append(node);
			}
			return data + ", " + getTreeWeight() + " -> " + sb;
		}
	}

}