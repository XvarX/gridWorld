import java.awt.Color;

import info.gridworld.actor.Actor;

import info.gridworld.actor.Flower;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;

public class Jumper extends Actor {
    
    public Jumper() {
        setColor(Color.RED);
    }
    
    public Jumper(Color jumperColor) {
        setColor(jumperColor);
    }
    
    public void turn() {
        setDirection(getDirection() + Location.HALF_RIGHT);
    }
    
    public void move() {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return;
        }
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        Location next1 = next.getAdjacentLocation(getDirection());
        if (gr.isValid(next1)) {
            moveTo(next1);
        } else {
            removeSelfFromGrid();
        }
    }
    
    public boolean canMove() {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return false;
        }
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        Location next1 = next.getAdjacentLocation(getDirection());
        if (!gr.isValid(next1)) {
            return false;
        }
        Actor neighbor = gr.get(next1);
        return (neighbor == null) || (neighbor instanceof Flower);
    }
    
    public void act() {
        if (canMove()) {
            move();
        } else {
            turn();
            
        }
    }

}
