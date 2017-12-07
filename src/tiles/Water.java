package tiles;

import java.awt.Rectangle;

import javafx.scene.image.Image;

/**
 * Represents the Water tile and stores its Image
 * 
 * @author Lynn Miguel
 *
 */
public class Water extends Obstacle {

	/**
	 * Set Location of Water Sprite Draw Image to Canvas
	 * 
	 * @param x
	 * @param y
	 */
	public Water(int x, int y) {
		super(x, y);
		this.tileType = 'W';

		try {
			this.img = new Image("images/Water.png", Tile.WIDTH, Tile.HEIGHT, false, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Rectangle getHitBox() {
		return hitBox;
	}

}
