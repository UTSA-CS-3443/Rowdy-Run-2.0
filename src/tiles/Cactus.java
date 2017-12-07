package tiles;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import javafx.scene.image.Image;



public class Cactus extends Obstacle {

	/**
	 * Set Cactus Image
	 */
	// Image cactusSprite = new Image("/src/images/cactus.png");
	
	// Constructor
	public Cactus(int x, int y) {
		super(x, y);
		this.tileType = 'X';

	    try {                
	        this.img = new Image("images/cactus.png", Tile.WIDTH, Tile.HEIGHT, false, false);//ImageIO.read(getClass().getResourceAsStream("/src/images/cactus.png"));
	    }
	    catch(Exception e) {
	        e.printStackTrace();
	        }
	}

	public Rectangle getHitBox() {
		return hitBox;
	}
}
