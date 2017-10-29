package tiles;

import java.awt.Rectangle;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * The Tile class is designed to represent a single tile in a game.
 * Each instance of Tile will have a fixed size of 100 * 100 pixels and 
 * should have its own (x,y) coordinates in the model. 
 * <p>
 * Tile is also designed to accommodate an image to be determined in its 
 * subclasses. Subclasses can load their respective images by calling the 
 * Image constructor in their own constructor.
 * <p>
 * Each instance of Tile also has a 100*100 Rectangle object serving as its
 * hit-box. The reason for this is that Rectangle has the intersects(Rectangle)
 * method which can be used for collision detection.
 * 
 * @author Michael Diep
 *
 */
public abstract class Tile {
	protected final int WIDTH = 100, HEIGHT = 100;
	protected int x, y;
	protected Rectangle hitBox;
	protected Image img;
	
	/**
	 * Class constructor initializes its position (x,y) and graphical size 
	 * in the model, along with its hitBox (just a rectangle object).
	 * It is up to the controller to determine where the Tile should be placed.
	 * 
	 * @param x
	 * @param y
	 */
	protected Tile(int x, int y){
		hitBox = new Rectangle(x, y, WIDTH, HEIGHT);
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Rectangle getHitBox() {
		return hitBox;
	}

	public void setHitBox(Rectangle hitBox) {
		this.hitBox = hitBox;
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}	
	
	/**
	 * Draws the image stored in the Tile object to the game's Canvas.
	 * 
	 * @see javafx.scene.canvas
	 * @see javafx.scene.canvas.GraphicsContext
	 * @param c - The GraphicsContext used by all draw methods
	 */
	public void drawTile(GraphicsContext c) {
		c.drawImage(img, x, y);
	}
}
