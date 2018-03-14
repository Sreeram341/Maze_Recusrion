/********************************************************************************************
 * File Name : MazeNode.java

 * Author : Sreeram Pulavarthi

 * Date: 02-26-2018

 * Compiler Used: Java 1.8
 
 * Description of File: Creates the base holder of each pont to each Maze Node class 			
 * 
 * 
 *********************************************************************************************
 */

public class MazeNode {

	public Integer row,col;
	public String Status,Front,Side,Top,Bottom;
	public boolean IsVIsited;
	public Integer NR, NC, PR, PC, TR, TC, BR, BC;
	
// Every point converted into a class object which holds status, column, row position
	public MazeNode(int x,int y, String stat)
	{
		
		this.row=x;
		this.col=y;
		this.Status =stat;
		
	}

	// Returns the current node position
	public Integer[] CN()
	{
		Integer[] CN = null;
		
		CN[0] = this.row;
		CN[1] = this.col;
		
		return CN;
	}
	
	//Checks if the current node is visited or not
	public boolean visit()
	{
		
		if (this.Status.equals("V"))
			{
				return true;
			}
		else {
		
			return false;
		}
	}
	
	// Check if the position is blocked or not
	public boolean blocked()
	{
		if(this.Status.equals("x"))
		{
			return true;
		}
		else {
			return false;
		}
	}
	
	//  Check if the node reached the final path
	public boolean finish()
	{
		if(this.Status.equals("f"))
		{
			return true;
		}
		else {
			return false;
		}
		
	}
	
	// Next row position
	public void NextNode(int row, int col)

	{
		this.NR = row;
		this.NC = col;
	}
	
	public Integer[] NN()
	{
		
		Integer[] NN;
		
		NN = new Integer[2];
			
		NN[0] = this.NR;
		NN[1] = this.NC;
		
		return NN;
	}

	public void PreviousNode(int row, int col)

	{
		this.PR = row;
		this.PC = col;
	}
	
	public Integer[] PN()
	{
		Integer[] PN = new Integer[2];
		
		PN[0] = this.PR;
		PN[1] = this.PC;
		
		return PN;
	}

	public void TopNode(int row, int col)

	{
		this.TR = row;
		this.TC = col;
	}

	public Integer[] TN()
	{
		
		Integer[] TN = new Integer[2];
		
		TN[0] = this.TR;
		TN[1] = this.TC;
		
		return TN;
		
	}
	
	public void BottomNode(int row, int col)

	{
		this.BR = row;
		this.BC = col;
	}
	
	public Integer[] BN()
	{
		Integer[] BN = new Integer[2];
		
		BN[0] = this.BR;
		BN[1] = this.BC;
		
		return BN;
		
	}
	// Set the current node status
	public void SetStatus(String s)
	{
		this.Status =s;
		
		//return this.Status;
	}
	// Check the current node status
	public String CheckStats()
	{
		return this.Status;
	}
}
