package CSC227;

public class RR3 {
	int quantum = 3;
	int wt[];
	int tt[];
	static double avgWatingTime;
	static double avgTurnAroundTime;

	public RR3(Load l) {
		wt = new int[l.nb];
		tt = new int[l.nb];
		String[] ArrayOfRR3NAMES = new String[l.nb];
		int[] ArrayOfRR3ID = new int[l.nb];
		int[] ArrayOfRR3Memory = new int[l.nb];
		int MemorySize = 8192;
		processPCB(ArrayOfRR3NAMES, ArrayOfRR3ID, l, l.nb, MemorySize, ArrayOfRR3Memory);
	}

	public void processPCB(String NAMES[], int id[], Load nq, int length, int memorySize, int memory[]) {
		if (memorySize < nq.pc[0].memory) {
			System.out.println("Error the memory size is not enough");
		} else {
			System.out.println("Name" + "\t " + "ID" + "\t" + "Time" + "\t" + "Start" + "\t" + "Stop");

			int Remaining[] = new int[length];
			for (int i = 0; i < length; i++)
				Remaining[i] = nq.pc[i].burstTime;

			int time = 0;
			boolean done;
			boolean full;
			Load rq = new Load();
			int counter = 0;
			
			for (int j = 0; j < length; j++) {}
			
			while (true) {
				done = true;

				for (int i = 0; i < length; i++) {

					if (Remaining[i] > 0) // as long as we have a remianing go inside
					{
						done = false; // if all remainings are zero it will not go inside this

						if (Remaining[i] > quantum) {
//						System.out.println("selected:"+arrP[i].id+" time: "+time+" starting br: "+copyRemaining[i]+" stopping br:"+(copyRemaining[i]-quantum));
							System.out.println(nq.pc[i].name + "\t " + time + "\t " + Remaining[i] + "\t"
									+ (Remaining[i] - quantum));
							time += quantum;
							Remaining[i] -= quantum;
						}

						else // burst time is smaller
						{
//						System.out.println("selected:"+arrP[i].id+" time: "+time+" starting br: "+copyRemaining[i]+" stopping br:0");
							System.out.println(nq.pc[i].id + "\t " + time + "\t " + Remaining[i] + "\t" + 0);
							time += Remaining[i];
							this.wt[i] = time - nq.pc[i].burstTime;
							Remaining[i] = 0; // process smaller than quantum so its out
						}
					} // if
				} // for

				// If all processes are done
				if (done == true)
					break;
			} // while

			for (int i = 0; i < length; i++)
				this.tt[i] = nq.pc[i].burstTime + this.wt[i];

			double totalAT = 0;
			for (int i = 0; i < this.wt.length; i++) {
				totalAT += this.wt[i];
			}
			avgWatingTime = totalAT / this.wt.length;

			double totalTT = 0;
			for (int i = 0; i < this.tt.length; i++) {
				totalTT += this.tt[i];
			}
			avgTurnAroundTime = totalTT / this.tt.length;
		}
	}
}