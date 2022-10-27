package CSC227;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class Load 
{
	public  PCB pc[] = new PCB[30];
	public int nb = 0;
	public int nbStr = 0;

	public Load() 
	{
		for(int i = 0 ; i<30 ; i++) 
		{
			pc[i] = new PCB();
		}
	}
	
	public void addPCB(String s ,int i) 
	{
	
		if(nb >= pc.length) 
		{
		System.out.println("no space");
		return;
		}
		else 
		{
			if(s.toLowerCase().contains("job")) 
			{
				pc[nb].name = s;
		
			}
			else 
			{
				String[] res = s.split("[,]", 0);
//				for(String myStr: res) {
//					pc[nb].burstTime = Integer.parseInt(res[0]);
//			       }
				pc[nb].id = Integer.parseInt(res[0]);
				pc[nb].burstTime = Integer.parseInt(res[1].trim());
				pc[nb].memory = Integer.parseInt(res[2].trim());
			
			}
		if(i%2!=0 && i!=0)
			nb++;
		}
		 
	 }

	public PCB[] loadFromFile(String filePath) 
	{
		String arr[] = new String[100];
	 
		try 
		{
			File myObj = new File(filePath);
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine())
			{
				String data = myReader.nextLine();
//	        	System.out.println(data);
	      
				arr[nbStr] = data;
				nbStr++;

			}  // end while
			myReader.close();
		} catch (FileNotFoundException e) 
			{
	    		System.out.println("An error occurred.");
	    		e.printStackTrace();
			}
		for(int i= 0 ;i<nbStr ; i++) 
		{   	
			addPCB(arr[i],i);
	  	}
	   
		return pc;
	}	
}
