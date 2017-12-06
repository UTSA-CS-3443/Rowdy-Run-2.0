package tiles;

import java.awt.Rectangle;

//TODO make platform with different sized hitboxes 
public class Platform extends Tile {

	public Platform(int x, int y) {
		super(x, y);
		this.tileType = 'P';
		this.hitBox = new Rectangle(x,y-4,10,2);
	}

}
