/**
 * 	Subclass of Ship.java. Governs the enemy ship object
 * 
 * @author wolfyCSA
 */

package application.model;

import java.util.ArrayList;

import application.Main;

public class EnemyShip extends Ship {

	private int pointValue;
	private int moneyValue;
	private String name;
	private String enemyID;
	
	
	public EnemyShip(String enemyGameID) {
		ArrayList<String> enemyShipData = new ArrayList<String>();
		// store data grabbed from the Cortex entry
		enemyShipData = Main.cortex.getEnemy().get(enemyGameID);
		
		setEnemyID(enemyShipData.get(0));
		setName(enemyShipData.get(1));
		super.setHullPoints(enemyShipData.get(2));
		//super.setShieldPoints(enemyShipData.get(3)); // Not using this in this version
		setPointValue(enemyShipData.get(4));
		setMoneyValue(enemyShipData.get(5));
		super.setSpriteLink(enemyShipData.get(6));
		super.setNumWeapons(enemyShipData.get(7));
		
		for (int i = 0; i < getNumWeapons(); i++)
			addWeapons(enemyShipData.get(8+i));
		
	}

	
	/**
	 * @return the pointValue
	 */
	public int getPointValue() {
		return pointValue;
	}
	/**
	 * @return the moneyValue
	 */
	public int getMoneyValue() {
		return moneyValue;
	}
	/**
	 * @return the numWeapons
	 */
	public int getNumWeapons() {
		return numWeapons;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return the enemyID
	 */
	public String getEnemyID() {
		return enemyID;
	}
	/**
	 * @param pointValue the pointValue to set
	 */
	public void setPointValue(String pointValue) {
		this.pointValue = Integer.parseInt(pointValue);
	}
	/**
	 * @param moneyValue the moneyValue to set
	 */
	public void setMoneyValue(String moneyValue) {
		this.moneyValue = Integer.parseInt(moneyValue);
	}
	/**
	 * @param numWeapons the numWeapons to set
	 */
	public void setNumWeapons(String numWeapons) {
		this.numWeapons = Integer.parseInt(numWeapons);
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @param enemyID the enemyID to set
	 */
	public void setEnemyID(String enemyID) {
		this.enemyID = enemyID;
	}
	
}//end class EnemyShip