package CSC227;

import java.io.Serializable;

public class PCB implements Serializable {
 public String name;
 public int id;
 public int burstTime;
 public int memory;
 public String processState;
 public int watingTime;
 public int turnAroundTime;
 
 
 public PCB() {}
 
  public PCB(String name , int br, int memory, int id) {
	  this.name = name;
	  this.burstTime = br;
	  this.memory = memory;
	  this.id = id;
  }
	public static void main(String[] args) {
		

	}

}
