package CSC227;

public class Test {

	public static void main(String[] args) {
		Load l = new Load();
		String path = "D:\\testdata2.txt";
		  l.loadFromFile(path);
		  System.out.println("---------------------------------- FCFS");
		  FCFS f = new FCFS(path);
		  
	     System.out.println("----------------------------------SJF");
	     
	     SJF s = new SJF(path);
	     
//	     System.out.println("----------------------------list");
//	     list.findfirst();
//	     while(!list.last()) {
//	    	      System.out.println("id: "+list.retrieve().id+" burstTime: "+
//	    			 list.retrieve().burstTime+" wating time: "+list.retrieve().watingTime+
//	    			 " turnAroundtime: "+list.retrieve().turnAroundTime+"\n"); 
//	    	        list.findnext();
//	                        }
//
//	     System.out.println("id "+list.retrieve().id+" burstTime: "+
//  			 list.retrieve().burstTime+" wating time: "+list.retrieve().watingTime+
//  			 " turnAroundtime: "+list.retrieve().turnAroundTime+"\n");
	     System.out.println("----------------------------------RR3");
  	 
	     RR3 r3 = new RR3(l.nb);		  
	     LinkedList list1 =	r3.processPCB(l.pc,l.nb);
	     
	     System.out.println("----------------------------------RR5");
	     
	     RR5 r5 = new RR5(l.nb);		  
	     LinkedList list2 =	r5.processPCB(l.pc,l.nb);
	     
	    
		System.out.println("---------------------------- avg ");
		
		System.out.println(" \t avg WT  \t avg TR \n");
		System.out.println("FCFS: \t "+String.format("%.3f", f.AverageWating)+" \t "+String.format("%.3f", f.AverageTurnAround));
//		System.out.println("\t FCFS: "+String.format("%.3f", f.AverageTurnAround));
		System.out.println("SJF:  \t "+String.format("%.3f", s.avrWating)+" \t " + String.format("%.3f", s.avrTurnAround));
//		System.out.println("avg turnAroud time SJF: "+String.format("%.3f", s.avrTurnAround));
		System.out.println("RR3:  \t "+String.format("%.3f", r3.avgWatingTime())+" \t "+String.format("%.3f", r3.avgTurnAroundTime()));
//		System.out.println("avg turnAroud time RR3: "+String.format("%.3f", r3.avgTurnAroundTime()));
		System.out.println("RR3:  \t "+String.format("%.3f", r5.avgWatingTime())+" \t "+String.format("%.3f", r5.avgTurnAroundTime()));
//		System.out.println("avg turnAroud time RR3: "+String.format("%.3f", r5.avgTurnAroundTime()));
	 }
	 


	}


