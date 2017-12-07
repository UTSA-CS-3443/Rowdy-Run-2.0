package tiles;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import javafx.scene.image.Image;

/**
 * 
 * @author Lynn Miguel
 *
 */
public class Cactus extends Obstacle {

	/**
	 * Set Location of Cactus Sprite
	 * Draw Image to Canvas
	 * @param x 
	 * @param y
	 */
	public Cactus(int x, int y) {
		super(x, y);
		this.tileType = 'X';
		
	    try {                
	        this.img = new Image("images/cactus.png", Tile.WIDTH, Tile.HEIGHT, false, false);
	    }
	    catch(Exception e) {
	        e.printStackTrace();
	        }
	}

	public Rectangle getHitBox() {
		return hitBox;
	}
}