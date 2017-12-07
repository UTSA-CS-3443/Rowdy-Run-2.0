package game.model;

import java.awt.Point;
import java.util.ArrayList;
import tiles.*;

/**
 * The Level Class is a data structure that handles storing the information
 * needed for the game's logic
 * 
 * @author jared
 *
 */
public class Level {
	private ArrayList<Tile[]> layout;
	public Point playerStart;
	public int WIDTH = 0;
	public int HEIGHT = 0;
	private int coinCount = 0;

	/**
	 * Constructor to be used by the model's read in methods to initialize all the
	 * information
	 * 
	 * @param curr
	 * @param rowdyStart
	 */
	public Level(ArrayList<Tile[]> curr, Point rowdyStart) {
		this.layout = curr;
		this.playerStart = rowdyStart;
		this.WIDTH = layout.size();
		this.HEIGHT = layout.get(0).length;
		this.countCoins();
	}

	/**
	 * Returns how many uncollected coins remain in the level
	 * 
	 * @return remaining coins
	 */
	public int getCoinCount() {
		return this.coinCount;
	}

	/**
	 * Returns the level as a ArrayList of Tile arrays
	 * 
	 * @return Level structure in ArrayList format
	 */
	public ArrayList<Tile[]> getLayout() {
		return layout;
	}

	/**
	 * Gives the coordinates where the player is to be spawned
	 * 
	 * @return Point where player spawns
	 */
	public Point getPlayerStart() {
		return this.playerStart;
	}

	/**
	 * Returns the tile at a specific x y spot in our Level structure
	 * 
	 * @param x
	 *            coordinate/Tile.Width
	 * @param y
	 *            coordinate/Tile.Height
	 * @return The specified Tile
	 */
	public Tile access(int x, int y) {
		return layout.get(x)[y];
	}

	/**
	 * Adds a new Column to our Level Structure
	 *
	 * @param Column
	 *            Column to be added
	 */
	public void addColumn(Tile[] Column) {
		layout.add(Column);
		this.WIDTH = layout.size();
		this.HEIGHT = Column.length;
		this.countCoins();
	}

	/**
	 * Gives the TileType of the specified Tile
	 * 
	 * @param x
	 *            coordinate/Tile.Width
	 * @param y
	 *            coordinate/Tile.Height
	 * @return Char denoting TileType
	 */
	public char accessType(int x, int y) {
		return this.access(x, y).getTileType();
	}

	/**
	 * Removes specified coin from the level and decrements the coinCount
	 * 
	 * @param x
	 *            coordinate/Tile.Width
	 * @param y
	 *            coordinate/Tile.Height
	 */
	public void collectCoin(int x, int y) {
		layout.get(x)[y] = new Air(x * Tile.WIDTH, y * Tile.HEIGHT);
		this.coinCount--;
	}

	/**
	 * Counts the total number of coins remaining in the Level Structure used in
	 * initialization and when adding to the level
	 */
	public void countCoins() {
		this.coinCount = 0;
		for (int x = 0; x < WIDTH; x++) {
			for (int y = 0; y < HEIGHT; y++) {
				if (this.accessType(x, y) == 'C')
					this.coinCount++;
			}
		}
	}

	/**
	 * Checks whether the current level has been completed
	 * 
	 * @return true if no coins remain in the level - false if otherwise
	 */
	public Boolean isLevelBeat() {
		if (this.coinCount == 0)
			return true;
		return false;
	}

}
