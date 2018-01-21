
public class Node {

	State myState;
	Node leftNeighbor;
	Node rightNeighbor;
	Node parent;
	boolean visited;
	
	public Node() {
		this.visited = false;
	}
	
	public Node(Node parent) {
		this();
		this.parent = parent;
	}
	
	public Node(Node parent, Node leftNeighbor, Node rightNeighbor) {
		this(parent);
		this.leftNeighbor = leftNeighbor;
		this.rightNeighbor = rightNeighbor;
	}
}
