package tiles;

import javafx.scene.image.Image;

public class Flame extends Obstacle {
	
	/**
	 * Set Flame Image
	 * 
	 */
	Image flame = new Image("/src/images/Flame1.gif");

	public Flame(int x, int y) {
		super(x, y);
		
		//super.setImg(cactusImage);
	}


}
