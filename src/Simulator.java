import java.util.Scanner;

public class Simulator {

	final static int NUM_ROWS = 30;
	final static int NUM_COLS = 30;
	final static int MIN_COORD = 1;
	final static int MAX_XCOORD = NUM_COLS - 2;
	final static int MAX_YCOORD = NUM_ROWS - 2;
	static Scanner user = new Scanner(System.in);
	
	
	/**
	 * Class Comment
	 * @param args
	 */
	public static void main(String[] args)
	{
		boolean[][] aliveCells = getCoordsForAliveCells();
		
		System.out.println("How many generations would you like to simulate?");
		int numGenerations = user.nextInt();
		
		performSimulation(aliveCells, numGenerations);
	}
	
	public static boolean[][] getCoordsForAliveCells()
	{	
		System.out.println("Enter in coordinates in the form x y. Enter x values between 1 "
				+ "and " + MAX_XCOORD + " and y values between 1 and " + MAX_YCOORD);
		
		boolean[][] aliveCells = fillCells();
		
		return aliveCells;
	}
	
	public static boolean[][] fillCells()
	{
		boolean[][] aliveCells = new boolean[NUM_ROWS][NUM_COLS];
		boolean isDone = false;
		int xCoord;
		int yCoord;
		
		while (!isDone)
		{
			xCoord = user.nextInt();
			
			// Check to see if it is a valid x coord
			if (xCoord < MIN_COORD || xCoord > MAX_XCOORD)
			{
				isDone = true;
			}
			else
			{
				yCoord = user.nextInt();
				
				// Check to see if it is a valid y coord
				if (yCoord < MIN_COORD || yCoord > MAX_YCOORD)
				{
					isDone = true;
				}
				else
				{
					aliveCells[yCoord][xCoord] = true;
				}
			}
		}
		
		return aliveCells;
	}

	public static void performSimulation(boolean[][] aliveCells, int numGenerations)
	{
		int [][] numNeighbors = new int[NUM_ROWS][NUM_COLS];
		for (int gen = 0; gen < numGenerations; gen++)
		{
			// Show the state of the board
			printGrid(aliveCells);

			// Simulation Logic
			numNeighbors = getNumNeighbors(aliveCells);

			for (int row = 1; row < NUM_ROWS - 1; row++)
			{
				for (int col = 1; col < NUM_COLS - 1; col++)
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
//			repaint();
		}
	}

	public static void printGrid(boolean[][] aliveCells)
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
		System.out.println();
	}

	public static int[][] getNumNeighbors(boolean[][] aliveCells)
	{
		int[][] numNeighbors = new int[NUM_ROWS][NUM_COLS];
		for (int row = 1; row < NUM_ROWS - 1; row++)
		{
			for (int col = 1; col < NUM_COLS - 1; col++)
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
		
		if (aliveCells[row][col + 1])
		{
			count++;
		}
		
		return count;
	}

	public static int numNeighborsLeft(int row, int col, boolean[][] aliveCells)
	{
		int count = 0;
		
		if (aliveCells[row][col - 1])
		{
			count++;
		}
		
		return count;
	}
}
