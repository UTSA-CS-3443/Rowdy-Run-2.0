package tiles;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;



public class Cactus extends Obstacle {

	/**
	 * Set Cactus Image
	 */
	// Image cactusSprite = new Image("/src/images/cactus.png");

	BufferedImage img;
	
	// Constructor
	public Cactus(int x, int y) {
		super(x, y);
		this.tileType = 'X';

	    try {                
	        img = ImageIO.read(getClass().getResourceAsStream("/src/images/cactus.png"));
	    }
	    catch(Exception e) {
	        e.printStackTrace();
	        }
	}

	public Rectangle getHitBox() {
		return hitBox;
	}
}
