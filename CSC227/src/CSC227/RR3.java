package CSC227;

public class RR3   {
	
	int quantum = 3;
	int wt[];
	int tt[];
	
	public RR3(int size) {
		wt = new int [size];
		tt = new int [size];
	}
	 public LinkedList processPCB(PCB arrP[], int n) {
           
    int Remaining[] = new int[n];
    for (int i = 0 ; i < n ; i++)
    	Remaining[i] =  arrP[i].burstTime;
  
    int time = 0; 
  System.out.println("process "+"time"+"  start br "+"stop br");
    while(true)
    {
        boolean done = true;
  
        for (int i = 0 ; i < n; i++)
        {
           
            if (Remaining[i] > 0) // as long as we have a remianing go inside
            {
                done = false;  // if all remainings are zero it will not go inside this
  
                if (Remaining[i] > quantum)
                {
//                	System.out.println("selected:"+arrP[i].id+" time: "+time+" starting br: "+copyRemaining[i]+" stopping br:"+(copyRemaining[i]-quantum));
                	System.out.println(arrP[i].id+"\t "+time+"\t "+Remaining[i]+"\t"+(Remaining[i]-quantum));
                    time += quantum;
                    Remaining[i] -= quantum; 
                }
  
            
                else // burst time is smaller 
                {
//                	System.out.println("selected:"+arrP[i].id+" time: "+time+" starting br: "+copyRemaining[i]+" stopping br:0");
                	System.out.println(arrP[i].id+"\t "+time+"\t "+Remaining[i]+"\t"+0);
                    time += Remaining[i];
                    this.wt[i] = time - arrP[i].burstTime;
                    Remaining[i] = 0; // process smaller than quantum so its out 
                }
            } // if
        } // for
  
        // If all processes are done
        if (done == true)
          break;
    } // while
    
    findTurnAroundTime(arrP,n);
    LinkedList temp = copy(arrP,n);
    return temp;
}

public void findTurnAroundTime(PCB arrP[], int n) {
// calculating turnaround time by adding
// bt[i] + wt[i]
	for (int i = 0; i < n; i++)
		this.tt[i] = arrP[i].burstTime + this.wt[i];
}

public float avgWatingTime() {
	float total = 0;
	for(int i = 0 ; i<this.wt.length;i++) {
		total+= this.wt[i];
	}
	return total/this.wt.length;
}

public float avgTurnAroundTime() {
	float total = 0;
	for(int i = 0 ; i<this.tt.length;i++) {
		total+= this.tt[i];
	}
	return total/this.tt.length;
}


//	 public void copyWatingTimeToPCB(PCB arrP[]) {
//		 for(int i = 0 ; i<this.wt.length ; i++) {
//			 arrP[i].watingTime = this.wt[i];
//			 arrP[i].turnAroundTime = this.tt[i];
//		 }
//	 }
	 
	 public LinkedList copy (PCB arrP[],int n) {
		 LinkedList l = new LinkedList();
		 for(int i = 0 ; i<n ; i++) {
			 l.insert(arrP[i]);
			 l.retrieve().id = arrP[i].id;
			 l.retrieve().burstTime = arrP[i].burstTime;
			 l.retrieve().watingTime = this.wt[i];
			 l.retrieve().turnAroundTime = this.tt[i];
		 }
		 return l;
	 }
	 public static void main (String args[]) {
		 
		 Load l = new Load();
		  l.loadFromFile("C:\\Users\\hossa\\Desktop\\227\\testdata2.txt");
		  RR3 r = new RR3(l.nb);
//		  int arr[] = new int[l.nb];
		  
		  
	     LinkedList list =	r.processPCB(l.pc,l.nb);
	     System.out.println("----------------------------list");
	     list.findfirst();
	     while(!list.last()) {
	    	      System.out.println("id: "+list.retrieve().id+" burstTime: "+
	    			 list.retrieve().burstTime+" wating time: "+list.retrieve().watingTime+
	    			 " turnAroundtime: "+list.retrieve().turnAroundTime+"\n"); 
	    	        list.findnext();
	                        }

	     System.out.println("id "+list.retrieve().id+" burstTime: "+
    			 list.retrieve().burstTime+" wating time: "+list.retrieve().watingTime+
    			 " turnAroundtime: "+list.retrieve().turnAroundTime+"\n");
    	 
    	 
//		System.out.println("---------------------------- wating");
//		for(int i = 0 ; i<r.wt.length; i++) {
//			System.out.println(r.wt[i]);
//		}
//		r.findTurnAroundTime(l.pc,l.nb);
//		System.out.println("---------------------------- tt");
//		for(int i = 0 ; i<r.tt.length; i++) {
//			System.out.println(r.tt[i]);
//		}
		System.out.println("---------------------------- avg");
		System.out.println("avg wating time: "+r.avgWatingTime());
		System.out.println("avg turnAroud time: "+r.avgTurnAroundTime());
	 }
	 

}
