package project;

import game.Direction;
import game.Drawable;
import naturesimulator.Action;
import naturesimulator.LocalInformation;
import ui.GridPanel;

/**
 * An abstract class for the creatures in the game and implements "Drawable" interface
 * to be drawn
 * @author Ömer
 *
 */
public abstract class Creature implements Drawable {
	/**
	 * Every creature has x,y coordinates and health
	 */
	private int x;
	private int y;
	private double health;
	/**
	 * Constructor for creatures
	 * @param x x coordinate	
	 * @param y y coordinate
	 * @param health health
	 */
	public Creature(int x, int y, double health) {
		this.x = x;
		this.y = y;
		this.health = health;
	}
	/**
	 * 
	 * @return x coordinate
	 */
	public int getX() {
		return x;
	}
	/**
	 * 
	 * @return y coordinate
	 */
	public int getY() {
		return y;
	}
	/**
	 * 
	 * @return health
	 */
	public double getHealth() {
		return health;
	}
	/**
	 * Sets the health
	 * @param health health
	 */
	public void setHealth(double health) {
		this.health = health;
	}
	/**
	 * Sets the x coordinate
	 * @param x x coordinate
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * Sets the y coordinate
	 * @param y y coordinate
	 */
	public void setY(int y) {
		this.y = y;
	}
	/**
	 * Move of Creature
	 * @param direction direction
	 */
	public void move(Direction direction) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Attack of Creature
	 * @param attackedCreature attacked Creature
	 */
	public void attack(Creature attackedCreature) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Abstract method to stay
	 */
	public abstract void stay();
	/**
	 * Abstract method to reproduce
	 * @param direction direction
	 * @return child Creature
	 */
	public abstract Creature reproduce(Direction direction);
	/**
	 * Abstract method to choose action
	 * @param localInfo information of the creature
	 * @return	action
	 */
	public abstract Action chooseAction(LocalInformation localInfo);
	
	/**
	 * Abstract method to decide the color of the creature
	 */
	public abstract void draw(GridPanel panel);











}
