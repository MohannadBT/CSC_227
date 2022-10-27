package CSC227;

public class Node {
	public PCB data;
	public Node next;
	
	public Node () { 
		data = null; 
		next = null;
	}
	
	public Node (PCB val) { 
		data = val;
		next = null;
	}

}
