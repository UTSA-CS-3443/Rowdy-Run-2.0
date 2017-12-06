package tiles;

import java.awt.Rectangle;

import javafx.scene.image.Image;

public class Water extends Obstacle {

	// Image waterSprite = new Image("/src/images/Water.gif");
	
	public Water(int x, int y) {
		super(x, y);
		this.tileType = 'W';
	}
	
	public Rectangle getHitBox() {
		return hitBox;
	}	

}
