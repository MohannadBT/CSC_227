package CSC227;

public class RR3   
{
	int quantum = 3;
	int wt[];
	int tt[];
	static double avgWatingTime;
	static double avgTurnAroundTime;
	int MemorySize = 8192;
	
	public RR3(int size) {
		wt = new int [size];
		tt = new int [size];
	}
	public void processPCB(PCB arrP[], int n) 
	{ 
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
//						System.out.println("selected:"+arrP[i].id+" time: "+time+" starting br: "+copyRemaining[i]+" stopping br:"+(copyRemaining[i]-quantum));
						System.out.println(arrP[i].id+"\t "+time+"\t "+Remaining[i]+"\t"+(Remaining[i]-quantum));
						time += quantum;
						Remaining[i] -= quantum; 
					}
  
            
					else // burst time is smaller 
					{
//						System.out.println("selected:"+arrP[i].id+" time: "+time+" starting br: "+copyRemaining[i]+" stopping br:0");
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
    
		for (int i = 0; i < n; i++)
			this.tt[i] = arrP[i].burstTime + this.wt[i];
    
		double totalAT = 0;
		for(int i = 0 ; i<this.wt.length;i++) {
			totalAT+= this.wt[i];
		}
		avgWatingTime = totalAT/this.wt.length;
    
		double totalTT = 0;
		for(int i = 0 ; i<this.tt.length;i++) {
			totalTT+= this.tt[i];
		}
		avgTurnAroundTime = totalTT/this.tt.length;
	
	}
}
