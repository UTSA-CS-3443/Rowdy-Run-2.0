package tiles;

/**
 * Abstract class that denotes an object as an obstacle which kills Rowdy on
 * contact
 *
 *@author Jared Polwort
 */
public class Obstacle extends Tile {

	protected char tileType = 'X';

	protected Obstacle(int x, int y) {
		super(x, y);
	}

}
