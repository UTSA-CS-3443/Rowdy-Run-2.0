package items;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import com.sun.javafx.scene.traversal.Direction;

public abstract class Item {
	
	protected BufferedImage img;
	public Direction dir;
	
	/**
	 * Current Position of the Object
	 * 
	 * 0 <= x_pos <= x_max
	 * 0 <= y_pos <= y_max
	 */
	public int x_pos;
	public int y_pos;
	
	/**
	 * Bounds Area, Items Position
	 * Maximum x and y Positions
	 */
	public int x_max;
	public int y_max;
	
	/**
	 * Size of Item, in Pixels
	 */
	public int width;
	public int height;
	
	public Item(Direction dir, int x_pos, int y_pos, int x_max, int y_max, 
			int width, int height, int boxWidth, int boxHeight) {
		this.dir = dir;
		this.x_pos = x_pos;
		this.y_pos = y_pos;
		this.x_max = x_max;
		this.y_max = y_max;
		this.width = width;
		this.height = height;
	}
	
	public void draw(Graphics g) {
		g.drawImage(img, x_pos, y_pos, width, height, null);
	}
}