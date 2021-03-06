/**
 * This class governs the dynamically-rendered level
 * 
 * @author wolfyCSA
 */
package application.model;

import java.util.ArrayList;
import java.util.Iterator;

import application.Main;

public class Level {

	private static final int numRows = 7;
	private static final int numCols = 10;
	private String[][] level;
	private int currentLevel;
	private int currentRow;
	private int currentCol;
	private int previousRow;
	private int previousCol;
	private int longestRow = 0;
	private ArrayList<EnemyShip> enemyShips = new ArrayList<EnemyShip>();

	private boolean crash = false;

	public Level() {};

	/**
	 * Overloaded constructor for level objects. Parses level to fill level 
	 * 	String array, and then synchronizes starting player position with 
	 * 	level starting position.
	 * 
	 * @param levelNum Designates which "level.txt" will be loaded.
	 */
	public Level(int levelNum) {
		this.currentLevel = levelNum;
		this.level = parseLevel(this.currentLevel);
		this.currentRow = Main.player.getCurrentRow();
		this.currentCol = Main.player.getCurrentCol();	
	}//end Level overloaded constructor

	/** 
	 * The collectEnemies method reads in data from level.txt and populates 
	 * 	an ArrayList of enemy ships based on characters that are not walls, 
	 * 	boundaries, empty space, or players. 
	 * 
	 * @return ArrayList of EnemyShips containing data on all the enemies in the level
	 */
	public ArrayList<EnemyShip> collectEnemies() {
		for (int r = 0; r < 7 ; r++) { 
			for (int c = 0; c < longestRow; c++) {
				switch(level[r][c]) {
				case "b" :  break;
				case "-1" : break;
				case " " :  break;
				case "p" :  break;
				default: enemyShips.add( new EnemyShip(level[r][c].toString(), r, c) );
				}//end switch
			}//end inner for
		}//end outer for

		return enemyShips;
	}//end collectEnemies()

	/**
	 * This method gets the longest row, for use in looping through the level
	 * 
	 * @param eachRow ArrayList of strings containing all the level data
	 * @return int length of the longest row
	 */
	public int getLongestRow(ArrayList<String> eachRow) {
		int length = 0;
		for (int i = 1; i < 7; i++)
		{
			String line = eachRow.get(i-1).toString();
			String[] tokens = line.split(",");
			String line2 = eachRow.get(i).toString();
			String[] tokens2 = line2.split(",");

			length = Math.max(tokens.length, tokens2.length);
		}
		this.longestRow = length;
		return length;
	}

	/**
	 * This method takes the raw level data and converts it into a 2D
	 * 	array. It then loops through the array, replacing all the null
	 * 	elements with -1.
	 * 
	 * @param levelNum number of the level to be loaded
	 * @return a 2D String array containing all objects in the level
	 */
	public String[][] parseLevel(int levelNum) {

		ArrayList<String> eachRow = new ArrayList<String>();
		eachRow = Main.cortex.getLevel().get("L_"+levelNum);
		int longestRow = getLongestRow(eachRow)+1;

		String[][] matrix = new String[7][longestRow];

		for (int i = 0; i < 7; i++)
		{
			String line = eachRow.get(i).toString();
			String[] tokens = line.split(",");
			// fill the array
			for (int j = 0; j < tokens.length; j++)
				matrix[i][j] = tokens[j];
			for ( int j = tokens.length - 1; j < matrix[i].length; j++)
				if ( matrix[i][j] == null ) 
					matrix[i][j] = String.valueOf(-1);

			//System.out.println();
		}//end outer for

		// print level array to console
		printLevel(levelNum);

		return matrix;
	}//end parseLevel()

	/**
	 * Prints level to console for debugging purposes.
	 * 
	 * @param levelNum the number that determines what "level.txt" will be read.
	 */
	public void printLevel(int levelNum) {
		ArrayList<String> eachRow = new ArrayList<String>();
		eachRow 				  = Main.cortex.getLevel().get("L_"+levelNum);
		int longestRow    		  = getLongestRow(eachRow);
		String[][] matrix 		  = new String[7][longestRow];


		for (int i = 0; i < 7; i++)
		{
			String line = eachRow.get(i).toString();
			String[] tokens = line.split(",");

			// fill the array
			for (int j = 0; j < tokens.length; j++)
				matrix[i][j] = tokens[j];
			for ( int j = tokens.length - 1; j < matrix[i].length; j++)
				if ( matrix[i][j] == null ) 
					 matrix[i][j] = String.valueOf(-1);
		}//end outer for

		System.out.println("");
		System.out.println("Level Array: ");
		for( int i = 0; i < 7; i++ ) {
			for( int j = 0; j < matrix[i].length; j++ ) {
				System.out.printf("%s,",matrix[i][j]);
			}//end inner for
			System.out.println("");
		}//end outer for

		System.out.println("");
		for( int r = 0; r < 7; r++ ) {
			for( int c = 0; c < matrix[r].length; c++ ) {
				String code = "";
				code = matrix[r][c];

				if( code == null )
					continue;
				else if ( code.equals("p") )
					updateCurrentLocation(r,c);
			}//end inner for
		}//end outer for

	}//end printLevel();

	/**
	 * The move methods takes the key pressed ID and passes it to 
	 * 	a switch statement.Switch statement handles which move 
	 * 	method will be called.
	 * 
	 * @param letter keystroke
	 */
	public void move( char letter ) {
		switch( letter ) {
		case 'W': moveUp(); break;
		case 'A': moveLeft(); break;
		case 'S': moveDown(); break;
		case 'D': moveRight(); break;
		}//end switch
	}//end move()

	/**
	 * Moves the current position up one row.
	 */
	public void moveUp() {
		// check if the row above is a wall
		if( !(this.level[this.currentRow-1][this.currentCol].equals("b")) ) {
			this.updateCurrentLocation( this.currentRow-1, this.currentCol );
			System.out.println(Main.player.toString());
		}
	}//end moveUp()

	/**
	 * Moves the current position down one row.
	 */	
	public void moveDown() {
		// check if the row below is a wall
		if( !(this.level[this.currentRow+1][this.currentCol].equals("b")) ) {
			this.updateCurrentLocation( this.currentRow+1, this.currentCol );
			System.out.println(Main.player.toString());
		}
	}//end moveDown()

	/**
	 * Moves the current position right one column.
	 */
	public void moveRight() {
		// check if the column to right is a wall
		if( !(this.level[this.currentRow][this.currentCol+1].equals("b")) ) {
			this.updateCurrentLocation( this.currentRow, this.currentCol+1 );
			System.out.println(Main.player.toString());
		}
	}//end moveRight()

	/**
	 * Moves the current position left one column.
	 */
	public void moveLeft() {
		// check if the column to left is a wall
		if( this.currentCol >= 0 ) {
			this.updateCurrentLocation( this.currentRow, this.currentCol-1 );
			System.out.println(Main.player.toString());
		}
	}//end moveLeft()

	/**
	 * The updateCurrentLocation method updates the level current position
	 * 	and the player current position.
	 * 
	 * @param r current row
	 * @param c current column
	 */
	public void updateCurrentLocation(int r, int c) {	
		Main.model.previousCol = Main.model.currentCol;
		Main.model.previousRow = Main.model.currentRow;
		Main.model.currentCol  = c;
		Main.model.currentRow  = r;

		Main.player.setPreviousLocation(Main.model.previousRow, Main.model.previousCol);
		Main.player.setCurrentLocation(Main.model.currentRow, Main.model.currentCol);
	}//end updateCurrentLocation()

	/**
	 * The updateEnemyLocation takes an enemy ship and moves it one to the left
	 * 
	 * @param e enemy ship to be moved
	 */
	public void updateEnemyLocation(EnemyShip e) {
		this.currentCol = e.getCurrentCol() - 1;
		System.out.println(e.getName() + " moved to: " + e.getCurrentRow() + ", " + e.getCurrentCol());
	}//end updateEnemyLocation()

	/**
	 * The enemyJumpBarrier method moves an enemy over a barrier and into visible
	 * space.
	 * 
	 * @param e enemy ship to be spawed over barrier
	 */
	public void enemyJumpBarrier(EnemyShip e) {
		this.currentCol = e.getCurrentCol() - 2;
		System.out.println(e.getName() + " jumped the barrier to: " + e.getCurrentRow() + ", " + e.getCurrentCol());
	}//end enemyJumpBarrier()

	/**
	 * The isLevelOver method ends a level when all enemies are dispatched.
	 * 
	 * @return true if level is over, false if not.
	 */
	public boolean isLevelOver() {
		if (Main.enemies.size() == 0) {
			System.out.println("Level complete!");
			return true;
		}
		return false;
	}//end isLevelOver()

	//checks for collisions [WORKING]
	public boolean crash(int r, int c)
	{
		if(r == this.getCurrentRow() && c == this.getCurrentColumn())
			crash = true;
		return crash;
	}//end crash()

	//checks for collisions [WORKING]
	public boolean shot()
	{
		boolean shot = false;
		Iterator<EnemyShip>    e = Main.enemies.iterator();
		Iterator<PlayerBullet> b = Main.playerBullets.iterator();

		while (b.hasNext()) {
			PlayerBullet pb = b.next();
			while (e.hasNext()) {
				EnemyShip es = e.next();
				if ( es.getCurrentCol() == pb.getCurrentCol()-1 && es.getCurrentRow() == pb.getCurrentRow() ) {
					System.out.println("Hit!");
					es.setDead(true);
					b.remove();
					Main.profile.addPoints(100);
					shot = true;
				}//end if
			}//end inner while
		}//end outer while

		return shot;
	}//end shot()

	/**
	 * @return previous row coordinate.
	 */
	public int getPreviousRow() {
		return Main.model.previousRow;
	}
	/**
	 * @return previous column coordinate.
	 */
	public int getPreviousColumn() {
		return Main.model.previousCol;
	}
	/**
	 * @return the current row coordinate.
	 */
	public int getCurrentRow() {
		return Main.model.currentRow;
	}
	/**
	 * @return the current column position.
	 */
	public int getCurrentColumn() {
		return Main.model.currentCol;
	}
	/**
	 * @return number of rows.
	 */
	public int getRows() {
		return numRows;
	}
	/**
	 * @return number of columns.
	 */
	public int getCols() {
		return numCols;
	}
	/**
	 * Gets code ("p", " ", "b", "H1", etc.) at given position.
	 * 
	 * @param row coordinate of row.
	 * @param col coordinate of column.
	 * @return String character at position
	 */
	public String getLevelLocation( int row, int col ) {
		return this.level[row][col];
	}//end getLevelLocation()
	/**
	 * @return int the score
	 */
	public int getScore() {
		return 100;
	}//end getScore()

}//end class Level