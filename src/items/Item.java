package items;

import java.awt.Rectangle;

import tiles.Tile;

public class Item extends Tile {
	
	private Rectangle hitBox;	// Collision Detection
	
	/**
	 * Current Position of the Object
	 * 
	 * Coordinates Given By Upper-Left Hand Corner of Item
	 * Position Must Be In Bounds
	 * 
	 * 0 <= position_x <= maximum_x
	 * 0 <= position_y <= maximum_y
	 * 
	 */
	public int position_x;
	public int position_y;
	
	/**
	 * Size of Item, in Pixels
	 */
	public int item_width;
	public int item_height;
	
	/**
	 * Upper Bound area in which the Item can be placed
	 * Maximum x and y positions for Upper-Left Hand Corner of Item
	 * 
	 */
	public int maximum_x;
	public int maximum_y;
	
	
	public Item(int x, int y) {
		super (x, y);
					
	}	
}
