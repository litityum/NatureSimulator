package project;

import java.awt.Color;

import game.Direction;
import naturesimulator.Action;
import naturesimulator.LocalInformation;
import ui.GridPanel;

/**
 * A class for the Plants
 * @author Ömer
 *
 */
public class Plant extends Creature {
	
	private static final double MAX_HEALTH = 1.0;
	/**
	 * Standard constructor of plant
	 * @param x x coordinate
	 * @param y y coordinate
	 * 
	 */
	public Plant(int x, int y) {
		
		super(x, y, 0.5);
	}
	/**
	 * Constructor of plant
	 * @param x x coordinate
	 * @param y y coordinate
	 * @param health health
	 */
	public Plant(int x, int y, double health) {
		
		super(x, y, health);
	}
	
	@Override
	public void draw(GridPanel panel) {
		//Determines the color of herbivore according to its health
		if(getHealth() == MAX_HEALTH * 1.0)
			panel.drawSquare(getX(), getY(), new Color(0,102,0));
		else if(getHealth() > MAX_HEALTH * 0.75)
			panel.drawSquare(getX(), getY(), new Color(0,153,0));
		else if(getHealth() > MAX_HEALTH * 0.5)
			panel.drawSquare(getX(), getY(), new Color(0,204,0));
		else if(getHealth() > MAX_HEALTH * 0.25)
			panel.drawSquare(getX(), getY(), new Color(0,255,51));
		else
			panel.drawSquare(getX(), getY(), new Color(102,255,102));
	
	}
	
	public void stay(){
		//increases the health by 0.05 and if it exceeds the maximum health, sets it to the max health
		setHealth(getHealth() + 0.05);
		if(getHealth() > MAX_HEALTH) {
			setHealth(MAX_HEALTH);
		}
	}
	@Override
	public Action chooseAction(LocalInformation localInfo) {
		//An if statement to choose reproduce
		if(getHealth() >= MAX_HEALTH * 0.75) {
			//A random free direction
			Direction direction = LocalInformation.getRandomDirection(localInfo.getFreeDirections());
			if(direction != null)				
				return new Action(Action.Type.REPRODUCE, direction);
			
		}
		//if cannot reproduce then stays
		return new Action(Action.Type.STAY);
	}
	
	@Override
	public Creature reproduce(Direction direction) {
		//Creates the child according to the direction and sets the health
		Plant plant;
		if(direction == Direction.LEFT) {			
			plant = new Plant(getX() - 1, getY(), this.getHealth()/10.0);
			
		} else if(direction == Direction.RIGHT) {			
			plant = new Plant(getX() + 1, getY(), this.getHealth()/10.0);
			
		} else if(direction == Direction.UP) {			
			plant = new Plant(getX() , getY() - 1, this.getHealth()/10.0);
			
		} else {			
			plant = new Plant(getX() , getY() + 1, this.getHealth()/10.0);
			
		}
		setHealth(getHealth() * 0.7);
		return plant;
		
	}

	

	

}
