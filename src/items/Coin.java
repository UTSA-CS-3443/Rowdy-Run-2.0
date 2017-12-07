package items;

import java.awt.*;
import javafx.scene.image.Image;
import tiles.Tile;

/**
 * 
 * @author Lynn Miguel
 *
 */
public class Coin extends Tile {

	/**
	 * Set Location of Water Sprite
	 * Draw Image to Canvas
	 * @param x
	 * @param y
	 */
    public Coin(int x,int y) {
        super(x,y);
        this.tileType = 'C';
        
	    try {                
	        this.img = new Image("images/Coin1.gif", Tile.WIDTH, Tile.HEIGHT, false, false);
	    }
	    catch(Exception e) {
	        e.printStackTrace();
	        }
	}
    
    public Rectangle getHitBox() {
    	return hitBox;
    }
}