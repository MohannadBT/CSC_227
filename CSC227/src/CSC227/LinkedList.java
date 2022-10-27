package CSC227;

public class LinkedList {
	private Node head;
	private Node current;
	
	public LinkedList() 
	{
		head = null;
		current = null;
		}
	public boolean empty () 
	{ 
		return head == null;
		}
	
	public boolean last () 
	{
		if(current==null) 
		{
			return true;
			}
		else
		return current.next == null;
		}
	
	public boolean full () 
	{ 
		return false;
		}
	
	public void findfirst () 
	{ 
		current = head;
		}
	
	public void findnext () 
	{ 
		current = current.next;
		}
	
	public PCB retrieve () 
	{ 
		return current.data;
		}
	
	public void update (PCB val) 
	{ 
		current.data = val;
		}
	
	public void insert (PCB val) 
	{ 
		Node tmp;
		if (empty()) 
		{ 
			current = head = new Node(val);
			}
		else 
		{
	      tmp = current.next; 
	      current.next = new Node(val);
	      current = current.next;
	      current.next = tmp;
			}
	 
		}

}
