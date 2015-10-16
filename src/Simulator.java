import java.util.Scanner;

public class Simulator {

	final static int NUM_ROWS = 10;
	final static int NUM_COLS = 10;
	/*final static int START_ROW = 1;
	final static int END_ROW = NUM_ROWS - 1;
	final static int START_COL = 1;
	final static int END_COL = NUM_COLS - 1;*/
	static Scanner user = new Scanner(System.in);

	/**
	 * Class Comment
	 * @param args
	 */
	public static void main(String[] args) {

		boolean isDone = false;
		String input = "";
		int xCoord;
		int yCoord;
		int[][] grid = new int[NUM_ROWS][NUM_COLS];
		boolean[][] aliveCells = new boolean[NUM_ROWS][NUM_COLS];
		int numGenerations;

		while (!isDone)
		{
			System.out.println("Enter in a coordinate in the form x,y between (1,1) and (9,9)");
			input = user.nextLine();
			xCoord = getXCoord(input);
			if (xCoord < 0)
			{
				isDone = true;
			}
			else
			{
				yCoord = getYCoord(input);
				aliveCells[xCoord][yCoord] = true;
			}
		}
		System.out.println("How many generations would you like to simulate?");
		numGenerations = Integer.parseInt(user.nextLine());

		performSimulation(grid, aliveCells, numGenerations);
	}

	public static int getXCoord(String input)
	{
		int xCoord;
		String xCoordStr;
		if (input.charAt(0) == '-')
		{
			xCoordStr = input.substring(0, 2);
			xCoord = Integer.parseInt(xCoordStr);
		}
		else
		{
			xCoordStr = input.substring(0, 1);
			xCoord = Integer.parseInt(xCoordStr);
		}


		System.out.println("Entered xCoord: " + xCoord);

		return xCoord;
	}

	public static int getYCoord(String input)
	{
		char yCoordChar = input.charAt(2);
		int yCoord = Integer.parseInt("" + yCoordChar);

		System.out.println("Entered yCoord: " + yCoord);

		return yCoord;

	}

	public static void performSimulation(int[][] grid, boolean[][] aliveCells,
			int numGenerations)
	{
		int [][] numNeighbors = new int[NUM_ROWS][NUM_COLS];
		for (int gen = 0; gen < numGenerations; gen++)
		{
			// Show the state of the board
			printGrid(grid, aliveCells);
			System.out.println();
			
			// Simulation Logic
			numNeighbors = getNumNeighbors(numNeighbors, aliveCells);
			
			for (int row = 1; row < NUM_ROWS; row++)
			{
				for (int col = 1; col < NUM_COLS; col++)
				{
					if (numNeighbors[row][col] == 3)
					{
						// Cell Becomes Alive
						aliveCells[row][col] = true;
					}
					else if (numNeighbors[row][col] == 2)
					{
						// Remains the Same
						aliveCells[row][col] = aliveCells[row][col];
					}
					else
					{
						// Cell Dies
						aliveCells[row][col] = false;
					}
				}
			}
		}
	}

	public static void printGrid(int[][] grid, boolean[][] aliveCells)
	{
		for (int row = 0; row < NUM_ROWS; row++)
		{
			for (int col = 0; col < NUM_COLS; col++)
			{
				if (aliveCells[row][col])
				{
					// Cell is alive
					if (col == NUM_COLS - 1)
					{
						System.out.println("*");
					}
					else
					{
						System.out.print("*");
					}
				}
				else
				{
					// Cell is dead
					if (row != 0 && row != NUM_ROWS -1 && col != 0 && col != NUM_COLS - 1)
					{
						if (col == NUM_COLS - 1)
						{
							System.out.println("-");
						}
						else
						{
							System.out.print("-");
						}
					}
					else
					{
						if (col == NUM_COLS - 1)
						{
							System.out.println("X");
						}
						else
						{
							System.out.print("X");
						}
					}
				}
			}
		}
	}
	
	public static int[][] getNumNeighbors(int[][] numNeighbors, boolean[][] aliveCells)
	{
		for (int row = 1; row < NUM_ROWS; row++)
		{
			for (int col = 1; col < NUM_COLS; col++)
			{
				int count = 0;
				count += numNeighborsAbove(row, col, aliveCells);
				count += numNeighborsBelow(row, col, aliveCells);
				count += numNeighborsRight(row, col, aliveCells);
				count += numNeighborsLeft(row, col, aliveCells);
				
				numNeighbors[row][col] = count;
			}
		}
		
		return numNeighbors;
	}
	
	public static int numNeighborsAbove(int row, int col, boolean[][] aliveCells)
	{
		int count = 0;
		for (int cell = col - 1; cell <= col + 1; cell++)
		{
			if (aliveCells[row - 1][cell])
			{
				count++;
			}
		}
		return count;
	}
	
	public static int numNeighborsBelow(int row, int col, boolean[][] aliveCells)
	{
		int count = 0;
		for (int cell = col - 1; cell <= col + 1; cell++)
		{
			if (aliveCells[row + 1][cell])
			{
				count++;
			}
		}
		return count;
	}
	
	public static int numNeighborsRight(int row, int col, boolean[][] aliveCells)
	{
		int count = 0;
		for (int cell = row - 1; cell <= row + 1; cell++)
		{
			if (aliveCells[cell][col + 1])
			{
				count++;
			}
		}
		return count;
	}
	
	public static int numNeighborsLeft(int row, int col, boolean[][] aliveCells)
	{
		int count = 0;
		for (int cell = row - 1; cell <= row + 1; cell++)
		{
			if (aliveCells[cell][col - 1])
			{
				count++;
			}
		}
		return count;
	}

}
