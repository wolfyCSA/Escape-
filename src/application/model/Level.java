/**
 * 	This class governs the dynamically-rendered level
 * 
 * @author wolfyCSA
 */

package application.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import application.Main;

public class Level {

	private static final int numRows = 7;
	private static final int numCols = 10;
	private String [][] level;
	private int currentLevel;
	private int currentRow;
	private int currentCol;
	private ArrayList<String> enemyList = new ArrayList<String>();

	public Level( String levelNum ) {
		// TODO: add getters and setters for this kind of stuff in Cortex.java
		//totalEnemies = Integer.parseInt( Main.cortex.level.get("L_" + levelNum).get(9).get(0) );
		//System.out.println(totalEnemies);
		this.currentLevel = Integer.parseInt( levelNum );
		this.currentRow = 4;
		this.currentCol = 1;
		
		for (int i = 0; i < level.length ; i++) 
	    { 
	        for (int j=0; j<level[i].length; j++)
	        {
	        	if ( level[i][j].equals("H1") )
	        		enemyList.add("H1");
	        	if ( level[i][j].equals("H2") )
	        		enemyList.add("H2");
	        }//end inner for
	    }//end outer for
	}
	
	// deprecated, level data is already stored in the cortex entry
	/*
	public static Level parseLevel( String fileName ) {
		
		// open the file
		Scanner scan = null;
		Level l = null;
		ArrayList<String> eachRow = new ArrayList<String>();
		
		//TODO: refactor this to work with arrays parsed from txt file
		try {
			// do the reading
			scan = new Scanner( new File( fileName ) );
			while( scan.hasNextLine() ) {
				eachRow.add( scan.nextLine() );
			}
			
			int numberOfRows = eachRow.size();
			int numberOfCols = eachRow.get(0).length();
			
			l = new Level( numberOfRows, numberOfCols );
			// copy the file contents to the Level model object
			for( int r=0; r<numberOfRows; r++ ) {
				for( int c=0; c<numberOfCols; c++ ) {
					char letter = eachRow.get(r).charAt(c);
					l.level[r][c] = letter;
					
					if( letter=='c' )
						l.updateCurrentLocation(r,c);
				}
			}
			
		}catch(IOException e) {
			//TODO: handle this exception!
			e.printStackTrace();
		}finally {
			if( scan!=null )
				scan.close();
		}
			
		return l;
	}//end parseLevel()
	*/
	
	public void updateCurrentLocation(int r, int c) {
		this.currentCol = c;
		this.currentRow = r;
	}
	
}
