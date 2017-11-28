package items;

import java.awt.image.BufferedImage;
import com.sun.javafx.scene.traversal.Direction;
import tiles.Tile;

/**
 * 
 * @author Lynn Miguel
 *
 */

public abstract class Item extends Tile{
	
	protected char tileType = 'c';
	protected BufferedImage img;
	public Direction dir;
	
	/**
	 * Size of Item, in Pixels
	 */
	public int width;
	public int height;
	
	public Item(int x,int y) {
		super(x,y);
	}
	
}