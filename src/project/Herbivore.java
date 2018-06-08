package project;

import java.awt.Color;
import java.util.ArrayList;

import game.Direction;
import naturesimulator.Action;
import naturesimulator.LocalInformation;
import ui.GridPanel;
/**
 * A class for the herbivores
 * @author Ömer
 *
 */
public class Herbivore extends Creature {

	static final double MAX_HEALTH = 20.0;
	/**
	 * Constructor of herbivore
	 * @param x x coordinate
	 * @param y y coordinate
	 * @param health health
	 */
	public Herbivore(int x, int y, double health) {
		super(x, y, health);
		// TODO Auto-generated constructor stub
	}
	/**
	 * Standart constructor of herbivore
	 * @param x x coordinate
	 * @param y y coordinate
	 */
	public Herbivore(int x, int y) {
		super(x, y, 10.0);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void stay() {
		//reducing the health by 0.1
		setHealth(getHealth() - 0.1);

	}
	@Override
	public Creature reproduce(Direction direction) {
		//Creates the child according to the direction and sets the health
		Herbivore herbivore;
		if(direction == Direction.LEFT) {			
			herbivore = new Herbivore(getX() - 1, getY(), getHealth()/5.0);
			
		} else if(direction == Direction.RIGHT) {			
			herbivore = new Herbivore(getX() + 1, getY(), getHealth()/5.0);
			
		} else if(direction == Direction.UP) {			
			herbivore = new Herbivore(getX() , getY() - 1, getHealth()/5.0);
			
		} else {			
			herbivore = new Herbivore(getX() , getY() + 1, getHealth()/5.0);
			
		}
		setHealth(getHealth() * 0.4);
		return herbivore;
	}
	public void attack(Creature attackedCreature) {
		//Adds the health of the attackedCreature to the herbivore and if the health
		//is more than Max_Health, make it equal to this value
		setHealth(getHealth() + attackedCreature.getHealth());
		if(getHealth() > MAX_HEALTH) {
			setHealth(MAX_HEALTH);
		}
		//changes the herbivores location to the attackedCreature's location
		int x = attackedCreature.getX();
		int y = attackedCreature.getY();
		setX(x);
		setY(y);
		attackedCreature.setHealth(0.0);
		
	}
	
	public void move(Direction direction) {
		//reduces the health by 1.0
		setHealth(getHealth() - 1.0);
		//sets the new location according to direction
		if(direction == Direction.LEFT) {			
			setX(getX() - 1);
			
		} else if(direction == Direction.RIGHT) {			
			setX(getX() + 1);
			
		} else if(direction == Direction.UP) {			
			setY(getY() - 1);
			
		} else {
			setY(getY() + 1);
		}
			
	}

	@Override
	public Action chooseAction(LocalInformation localInfo) {
		//A random free direction
		Direction direction = LocalInformation.getRandomDirection(localInfo.getFreeDirections());
		//An if statement to choose reproduce
		if(getHealth() == MAX_HEALTH) {
			if(direction != null)				
			return new Action(Action.Type.REPRODUCE, direction);
			
		}
		//determines the directions with Plant
		ArrayList<Direction> directionsAttack = new ArrayList<Direction>();
		if(localInfo.getCreatureUp() instanceof Plant)
			directionsAttack.add(Direction.UP);
		
		if(localInfo.getCreatureLeft() instanceof Plant)
			directionsAttack.add(Direction.LEFT);
		
		if(localInfo.getCreatureDown() instanceof Plant)
			directionsAttack.add(Direction.DOWN);
		
		if(localInfo.getCreatureRight() instanceof Plant)
			directionsAttack.add(Direction.RIGHT);
		//if there is a direction with Plant, chooses one of this randomly
		if(directionsAttack.size() != 0) {
			int randomIndex = (int)(Math.random() * directionsAttack.size());
	        return new Action(Action.Type.ATTACK, directionsAttack.get(randomIndex));
		}
		//If there is not enough health, stays, otherwise moves randomly to a free direction
		if(getHealth() <= 1.0)
			return new Action(Action.Type.STAY);
		
		return new Action(Action.Type.MOVE, direction);
		
		
	}
	@Override
	public void draw(GridPanel panel) {
		//Determines the color of herbivore according to its health
		if(getHealth() == MAX_HEALTH * 1.0)
			panel.drawSquare(getX(), getY(), new Color(102,0,0));
		else if(getHealth() > MAX_HEALTH * 0.75)
			panel.drawSquare(getX(), getY(), new Color(153,0,0));
		else if(getHealth() > MAX_HEALTH * 0.5)
			panel.drawSquare(getX(), getY(), new Color(204,0,0));
		else if(getHealth() > MAX_HEALTH * 0.25)
			panel.drawSquare(getX(), getY(), new Color(255,0,0));
		else
			panel.drawSquare(getX(), getY(), new Color(255,51,51));
		
	}

}
