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
			System.out.println("Name" + "\t " + "ID" + "\t" + "Time" + "\t" + "Start" + "\t" + "Stop");
			int start = 0;
			int end = 0;
			int q = 0;

			Load rq = new Load();
			int counter = 0;

			for (int i = 0; i < length; i++) {
				memory[i] = nq.pc[i].memory;

				if (memorySize > memory[i]) {
					memorySize -= memory[i];
					rq.pc[i] = nq.pc[i];
					rq.nb++;
					if (i + 1 != length)
						continue;
				}
				if (i + 1 == length) {
					while (counter != rq.nb) {
						NAMES[counter] = rq.pc[counter].name;
						id[counter] = rq.pc[counter].id;
						burstTime[counter] = rq.pc[counter].burstTime;

						if (i != 0) {
							start += q;
							rq.pc[counter].watingTime = start;
						}
						q = burstTime[counter];// to save the old value Remark
						end += burstTime[counter];
						rq.pc[i].turnAroundTime = end;

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
						start += q;
						rq.pc[counter].watingTime = start;
					}
					q = burstTime[counter];// to save the old value Remark
					end += burstTime[counter];
					rq.pc[i].turnAroundTime = end;
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
			}
			AverageWating = totWating / rq.nb;

			double TurnAround = 0.0;
			for (int i = 0; i < rq.nb; i++) {
				TurnAround += rq.pc[i].turnAroundTime;
			}
			AverageTurnAround = TurnAround / rq.nb;

		}
//		System.out.println("The Average waiting time: "+AverageWating);
//		
//		System.out.println("The Average TurnAround time: "+AverageTurnAround);	
	}
}
