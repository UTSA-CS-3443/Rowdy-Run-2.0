package tiles;

import java.awt.Rectangle;
import javafx.scene.image.Image;

/**
 * 
 * @author Lynn Miguel
 *
 */

public class Flame extends Obstacle {
	
	/**
	 * Set Location of Flame Sprite
	 * Draw Image to Canvas
	 * @param x 
	 * @param y
	 */
	public Flame(int x, int y) {
		super(x, y);
		this.tileType = 'F';
		
	    try {                
	        this.img = new Image("images/Flame1.gif", Tile.WIDTH, Tile.HEIGHT, false, false);
	    }
	    catch(Exception e) {
	        e.printStackTrace();
	        }
	}
	
	public Rectangle getHitBox() {
		return hitBox;
	}

}