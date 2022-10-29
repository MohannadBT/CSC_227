package CSC227;

public class RR5 {
	int quantum = 5;
	int wt[];//waiting time
	int tt[];//turn around time
	static double avgWatingTime;
	static double avgTurnAroundTime;

	public RR5(Load l) {
		wt = new int[l.nb];
		tt = new int[l.nb];
//		String[] ArrayOfRR3NAMES = new String[l.nb];
//		int[] ArrayOfRR3ID = new int[l.nb];
		int[] ArrayOfRR3Memory = new int[l.nb];
		int MemorySize = 8192;
		processPCB(l, l.nb, MemorySize, ArrayOfRR3Memory);
	}

	public void processPCB(Load nq, int length, int memorySize, int memory[]) {
		if (memorySize < nq.pc[0].memory) {
			System.out.println("Error the memory size is not enough");
		} else {
			System.out.println("Name" + "\t " + "ID" + "\t" + "Time" + "\t" + "Start" + "\t" + "Stop");

			int Remaining[] = new int[length];
			for (int i = 0; i < length; i++)
				Remaining[i] = nq.pc[i].burstTime;

			int time = 0;
			boolean done;
			boolean full = false;
			Load rq = new Load();
			// int counter = 0;
			int rqHolder = 0;//ready queue holder
			int j = 0;
			int i;
			int[] loopHolder = new int[2];
			// int Remaining;

			// for (int j = 0; j < length; j++) {}

			while (true) {
				done = true;

				if (!full)
					for (j = rqHolder; j < length; j++) {
						memory[j] = nq.pc[j].memory;
						if (memorySize > memory[j]) {
							memorySize -= memory[j];
							rq.pc[j] = nq.pc[j];
							rq.pc[j].ArrivelTime = time;
							System.out.println("time:"+time);
							rq.nb++;
						} else {
							full = true;
//							System.out.println("j:"+j+ " holder:"+holder);
							break;
						}
						rqHolder++;
					}

					
				for (i = 0; i < rq.nb; i++) {

					if(loopHolder[0]!=0) {
						System.out.println("inside loopHolder[0]:"+loopHolder[0]+" inside loopHolder[1]:"+loopHolder[1]);
						i = loopHolder[0];
						loopHolder[0] = 0;
					}
					// Remaining[i] = rq.pc[i].burstTime;

					// if(rq.pc[i].burstTime > 0) {}
					if (Remaining[i] > 0) // as long as we have a remianing go inside
					{
						done = false; // if all remainings are zero it will not go inside this

						if (Remaining[i] > quantum) {
							System.out.println(rq.pc[i].name + "\t " + rq.pc[i].id + "\t " + time + "\t " + Remaining[i]
									+ "\t" + (Remaining[i] - quantum));
							time += quantum;
							Remaining[i] -= quantum;
							
//							if(i+1 == loopHolder[1]) {
//								loopHolder[1] = 0;
//								break;
//							}
							
						} else // burst time is smaller
						{
							System.out.println(rq.pc[i].name + "\t " + rq.pc[i].id + "\t " + time + "\t " + Remaining[i]
									+ "\t" + 0);
							time += Remaining[i];
							this.wt[i] = time - rq.pc[i].burstTime - rq.pc[i].ArrivelTime;
							System.out.println("i="+(i+1)+" wt:"+this.wt[i]);
							Remaining[i] = 0; // process smaller than quantum so its out
							memorySize += memory[i];
							full = false;
							
//							if(i+1 == loopHolder[1]) {
//								loopHolder[1] = 0;
//								break;
//							}
							
							System.out.println("rq.nb:"+rq.nb+" length:"+length+ " i:"+i);
							if(j<length && rq.nb!=length) {
								if(memorySize > memory[j])
									loopHolder[0] = i+1;
									loopHolder[1] = rq.nb;
									break;
							}		
						}// else
					} // if
				} // for

				// If all processes are done
				if (done == true)
					break;
			} // while

			for (i = 0; i < length; i++)
				this.tt[i] = rq.pc[i].burstTime + this.wt[i];

			double totalWT = 0;
			for (i = 0; i < this.wt.length; i++) {
				totalWT += this.wt[i];
			}
			avgWatingTime = totalWT / this.wt.length;

			double totalTT = 0;
			for (i = 0; i < this.tt.length; i++) {
				totalTT += this.tt[i];
			}
			avgTurnAroundTime = totalTT / this.tt.length;
		}
	}
}

//j = holder;
//while(j < length) {
//	memory[j] = nq.pc[j].memory;
//	if (memorySize > memory[j]) {
//		memorySize -= memory[j];
//		rq.pc[j] = nq.pc[j];
//		rq.nb++;
//	} else {
//		full = true;
//		break;
//	}
//	holder ++;
//	j++;
//}