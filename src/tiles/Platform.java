package tiles;

import java.awt.Rectangle;

/**
 * Represents the Platform Tile which Rowdy can jump through but also stand on
 *
 * @author Jared Polwort
 */
public class Platform extends Tile {

	public Platform(int x, int y) {
		super(x, y);
		this.tileType = 'P';
		this.hitBox = new Rectangle(x, y - 4, 10, 2);
	}

}
