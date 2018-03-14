/********************************************************************************************
 * File Name : MazeDriver.java

 * Author : Sreeram Pulavarthi

 * Date: 02-26-2018

 * Compiler Used: Java 1.8
 
 * Description of File: Reads the data from the file and constructs the MazeNode and runs the logic
 * 			
 *********************************************************************************************
 */

import java.util.*;
import java.io.*;

public class MazeDriver {

	public Integer rows = 0, cols = 0, startcol=0,startrow=0;
	public ArrayList<String> rows_cols = new ArrayList<String>();
	public String[][] splitted;
	public MazeNode[][] MN;
	public ArrayList<String> Path = new ArrayList<String>();
	static File input_file = new File("C:\\Users\\WavicleDataSP\\eclipse-workspace\\Maze\\src\\Input_File.txt");

	public MazeDriver() {

		// Opens file constructs the required matrix and passes output to next
		// function....

		try {

			// Reads the data from file in line by line and adds to String Array List

			Scanner sc = new Scanner(input_file);

			while (sc.hasNext()) {

				String row = sc.nextLine();

				rows_cols.addAll(Arrays.asList(row));
			}
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

		// Initializes the 2 D empty array and fetches the data from ArrayList

		splitted = new String[rows_cols.size()][];

		MN = new MazeNode[rows_cols.size()][];

		for (int i = 0; i < splitted.length; i++) {
			rows += 1;
			splitted[i] = new String[rows_cols.get(i).length()];
			MN[i] = new MazeNode[rows_cols.get(i).length()];

			String[] neww = rows_cols.get(i).split("");

			// Fills the data into the columns of 2D array

			for (int j = 0; j < splitted[i].length; j++) {

				splitted[i][j] = neww[j];

				MN[i][j] = new MazeNode(i, j, neww[j]);

				// Set Next Node
				if (j + 1 == splitted[i].length) {
					// System.out.format("\n%s->,%d,%d","Next",i,0);

					MN[i][j].NextNode(i, 0);
				} else {
					// System.out.format("\n%s->,%d,%d","Next",i,j+1);

					MN[i][j].NextNode(i, j + 1);
				}

				// Set Previous node
				if (j - 1 < 0) {
					// System.out.format("\n%s->,%d,%d","Previous",i,splitted[i].length-1);

					MN[i][j].PreviousNode(i, splitted[i].length - 1);
				} else {
					// System.out.format("\n%s->,%d,%d","Previous",i,j-1);

					MN[i][j].PreviousNode(i, j - 1);
				}

				// Set Top node
				if (i - 1 < 0) {
					// System.out.format("\n%s->,%d,%d","Top",splitted.length-1,j);

					MN[i][j].TopNode(splitted.length - 1, j);
				} else {
					// System.out.format("\n%s->,%d,%d","Top",i-1,j);

					MN[i][j].TopNode(i - 1, j);
				}

				// Set Bottom node
				if (i + 1 == splitted.length) {
					// System.out.format("\n%s->,%d,%d","Bottom",0,j);

					MN[i][j].BottomNode(0, j);
				} else {
					// System.out.format("\n%s->,%d,%d","Bottom",i+1,j);

					MN[i][j].BottomNode(i + 1, j);
				}

			}
		}
		
		
		for (int i= 0;i<MN.length;i++) {
			for (int j=0;j<MN[i].length;j++) {
				
				if (MN[i][j].equals("o")) {
					startcol = i;
					startrow = j;
				}
			}
		}
		
		StartTraverse(startrow, startcol);

	}

	private void StartTraverse(Integer i, Integer j) {
		// TODO Auto-generated method stub

		Path.add(i.toString()+"," +j.toString());

		//Initialize all the variables of current, Next previous and top vertices  
		Integer[] curr, nexx, pree, topp, bott;

		Integer curro, currc, nexr, nexc, prer, prec, topr, topc, botr, botc;

		nexx = MN[i][j].NN();

		nexr = nexx[0];
		nexc = nexx[1];

		//////////////////////////

		pree = MN[i][j].PN();

		prer = pree[0];
		prec = pree[1];

		//////////////////////////

		topp = MN[i][j].TN();

		topr = topp[0];
		topc = topp[1];

		//////////////////////////

		bott = MN[i][j].BN();

		botr = bott[0];
		botc = bott[1];

		//////////////////////////

		// Start Traversing

		// If not visited change status and then call the main function with
		// new values

		// To call which node is to be decided now
		// check to traverse to next node or prev or top or bottom
		// Is visited or not, Is blocked or not

		// Check Next node
		if (!(MN[nexr][nexc].Status.equals("x")) && !(MN[nexr][nexc].Status.equals("V"))) {

			if (MN[nexr][nexc].Status.equals("f")) {
				System.out.format("\n%s -> %d,%d", "\nGame finished at ", nexr, nexc);
				ShowPath();
			}

			else {

				MN[i][j].SetStatus("V");

				SendValues(nexr, nexc);
			}
		} 
		// Check Top Node
		else if (!(MN[topr][topc].Status.equals("x")) && !(MN[topr][topc].Status.equals("V"))) {
			if (MN[topr][topc].Status.equals("f")) {
				System.out.format("\n%s -> %d,%d", "\nGame finished at ", topr, topr);
				ShowPath();
			} else {

				MN[i][j].SetStatus("V");
				SendValues(topr, topc);
			}
		} 
		// Check previous Node
		else if ((!(MN[prer][prec].Status.equals("x")) && !(MN[prer][prec].Status.equals("V")))) {

			if (MN[prer][prec].Status.equals("f")) {
				System.out.format("\n%s -> %d,%d", "\nGame finished at ", prer, prec);
				ShowPath();
			} else {
				MN[i][j].SetStatus("V");
				SendValues(prer, prec);
			}
		} 
		
		// Check Bottom Node
		else if ((!(MN[botr][botc].Status.equals("x")) && !(MN[botr][botc].Status.equals("V")))) {
			// MN[nexr][nexc].SetStatus("o");

			if (MN[botr][botc].Status.equals("f")) {
				System.out.format("\n%s -> %d,%d", "\nGame finished at ", botr, botc);
				
				ShowPath();
			} else {

				MN[i][j].SetStatus("V");
				SendValues(botr, botc);
			}
		}
		// If there is not end point then it is un-solvable
		else {
			System.out.println("\nUnsolvable");
		}

	}

	private void ShowPath() {
		// TODO Auto-generated method stub
		
		System.out.println("\n\n----------- Path followed ------------");
		for (int i=0;i<Path.size();i++)
			System.out.println(Path.get(i));
		
	}

	public void SendValues(int rr, int cc) {
		StartTraverse(rr, cc);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		MazeDriver md = new MazeDriver();

	}
}
