package CSC227;

public class SJF {
	Load lo;
	double avrWating;
	double avrTurnAround;
	SJF(String path) {

		lo = new Load();
		lo.loadFromFile(path);


		int[] timpA = new int[lo.nb + 1];
		for (int i = 0; i < lo.nb; i++) {

			timpA[i] = lo.pc[i].burstTime;

		}

		int[] timpB = new int[lo.nb];
		int min = timpA[0];
		int index = 0;
		for (int i = 0; i < lo.nb; i++) {

			for (int j = 0; j < lo.nb; j++) {

				if (min >= timpA[j] && timpA[j] != 0) {
					min = timpA[j];
					index = j;

				}

			}
			timpB[i] = index;
			timpA[index] = 0;
			min = 2147483647;
		}


		PCB newPCB[] = new PCB[30];

		for (int i = 0; i < lo.nb; i++) {

			newPCB[i] = lo.pc[timpB[i]];

		}

		lo.pc = newPCB;


//		System.out.println("________________________");
		int totWating = 0;
		int totTurnAround = 0;
		System.out.println("process "+"time"+"  start br "+"stop br");
		for (int i = 0; i < lo.nb; i++) {

			lo.pc[i].watingTime = totWating;
			totWating += lo.pc[i].burstTime;
			if (i != 0)
				lo.pc[i].turnAroundTime = lo.pc[i - 1].turnAroundTime + lo.pc[i].burstTime;
			else
				lo.pc[i].turnAroundTime = lo.pc[i].burstTime;
			totTurnAround += lo.pc[i].turnAroundTime;
			System.out.println(lo.pc[i].id+" 	 "+lo.pc[i].burstTime+"	" + lo.pc[i].watingTime + "   	  " + lo.pc[i].turnAroundTime);

		}
		totWating = 0;
		for (int i = 0; i < lo.nb; i++) {
			totWating += lo.pc[i].watingTime;
		}
		
		avrWating = (double) totWating / lo.nb;
		avrTurnAround = (double) totTurnAround / lo.nb;
		
//		System.out.println("average wating time is: " + avrWating);
//		System.out.println("average turn around time is: " + avrTurnAround);

	}

}