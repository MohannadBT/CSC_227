package CSC227;

public class FCFS {
	static double AverageWating;
	static double AverageTurnAround;

	FCFS(Load l) {

//		Load l = new Load();
//		PCB[] data = l.loadFromFile(path);

		// FCFS
		String[] ArrayOfFCFSNAMES = new String[l.nb];
		int[] ArrayOFFCFSID = new int[l.nb];
		int[] ArrayOFFCFSBurstTime = new int[l.nb];
		int[] ArrayOfFCFSMemory = new int[l.nb];
		int MemorySize = 8192;
		FormalPrint(ArrayOfFCFSNAMES, ArrayOFFCFSID, ArrayOFFCFSBurstTime, l.nb, l, MemorySize, ArrayOfFCFSMemory);
	}

	public static void FCFS(String NAMES[], int id[], int burstTime[], int length, Load nq, int memorySize,
			int memory[]) // nq= new queue
	{
		for (int i = 0; i < length; i++) {

			NAMES[i] = nq.pc[i].name;
			burstTime[i] = nq.pc[i].burstTime;
			id[i] = nq.pc[i].id;
			System.out.println(NAMES[i]);
			System.out.println(id[i]);
			System.out.println(burstTime[i]);
		}
	}

	public static void FormalPrint(String NAMES[], int id[], int burstTime[], int length, Load nq, int memorySize,
			int memory[]) // nq= new queue
	{

		if (memorySize < nq.pc[0].memory) {
			System.out.println("Error the memory size is not enough");
		} else {
			System.out.println("Name" + "\t " + "ID" + "\t" + "BTime" + "\t" + "Start" + "\t" + "Stop");
			int start = 0;
			int end = 0;
			int Holder = 0;

			Load rq = new Load();
			int counter = 0;

			for (int i = 0; i < length; i++) {
				memory[i] = nq.pc[i].memory;

				if (memorySize > memory[i]) {
					memorySize -= memory[i];
					rq.pc[i] = nq.pc[i];
					rq.pc[i].ArrivelTime = end;
					//System.out.print("job"+(i+1));
					//System.out.println(" ArrivelTime: "+rq.pc[i].ArrivelTime);
					rq.nb++;
					if (i + 1 != length)
						continue;
				}
				if (i + 1 == length) {
					while (counter != rq.nb) {
						//System.out.println("counter: " + (counter+1) + " rq.nb: "+rq.nb);
						NAMES[counter] = rq.pc[counter].name;
						id[counter] = rq.pc[counter].id;
						burstTime[counter] = rq.pc[counter].burstTime;

						if (i != 0) {
							start += Holder;
							rq.pc[counter].watingTime = start - rq.pc[counter].ArrivelTime;
						}
						
						Holder = burstTime[counter];// to save the old value Remark
						end += burstTime[counter];
						rq.pc[counter].turnAroundTime = end - rq.pc[counter].ArrivelTime;

						System.out.println(NAMES[counter] + "\t " + id[counter] + "\t " + burstTime[counter] + "\t "
								+ start + "\t" + end);
						memorySize += memory[counter];
						counter++;
					}
				} else {
					NAMES[counter] = rq.pc[counter].name;
					id[counter] = rq.pc[counter].id;
					burstTime[counter] = rq.pc[counter].burstTime;

					if (i != 0) {
						start += Holder;
						rq.pc[counter].watingTime = start - rq.pc[counter].ArrivelTime;
					}
					
					Holder = burstTime[counter];// to save the old value Remark
					end += burstTime[counter];
					rq.pc[counter].turnAroundTime = end - rq.pc[counter].ArrivelTime;
					System.out.println(NAMES[counter] + "\t " + id[counter] + "\t " + burstTime[counter] + "\t " + start
							+ "\t" + end);
					memorySize += memory[counter];
					i--;
					counter++;
				}
			}
			double totWating = 0.0;
			for (int i = 0; i < rq.nb; i++) {
				totWating += rq.pc[i].watingTime;
				//System.out.print("job"+(i+1));
				//System.out.println(" totWating: "+totWating);
			}
			AverageWating = totWating / rq.nb;

			//System.out.println("rq.nb: "+rq.nb);
			//System.out.println("AverageWating.nb: "+AverageWating);

			
			double TurnAround = 0.0;
			for (int i = 0; i < rq.nb; i++) {
				TurnAround += rq.pc[i].turnAroundTime;
//				System.out.print("job"+(i+1));
//				System.out.println(" TurnAround: "+TurnAround);
			}
			AverageTurnAround = TurnAround / rq.nb;
			
//			System.out.println("rq.nb: "+rq.nb);
//			System.out.println("AverageTurnAround: "+AverageTurnAround);
		}
//		System.out.println("The Average waiting time: "+AverageWating);
//		
//		System.out.println("The Average TurnAround time: "+AverageTurnAround);	
	}
}
