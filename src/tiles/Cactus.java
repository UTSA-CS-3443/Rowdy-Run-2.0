package tiles;

import java.awt.Rectangle;

import javafx.scene.image.Image;

public class Cactus extends Obstacle {

	/**
	 * Set Cactus Image
	 */
	// Image cactusSprite = new Image("/src/images/cactus.png");

	// Constructor
	public Cactus(int x, int y) {
		super(x, y);
		this.tileType = 'T';
	}

	public Rectangle getHitBox() {
		return hitBox;
	}
}
