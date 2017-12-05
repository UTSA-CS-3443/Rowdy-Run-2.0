package items;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import tiles.Tile;

/**
 * 
 * @author Lynn Miguel
 *
 */
public class Coin extends Tile {
	
	/**
	 * Hold Image Files
	 */
	public static String[] imgFile = {"Coin1.gif", "Coin2.gif", "Coin3.gif", 
			"Coin1.gif", "Coin2.gif", "Coin3.gif", "Coin1.gif", "Coin2.gif", "Coin3.gif"};
	
	public static final int WIDTH = 20;
	public static final int HEIGHT = 20;
	private BufferedImage[] image;
	
    public Coin(int x,int y) {
        super(x,y);
        //hitBox = new Rectangle(x + 30, y + 30, WIDTH, HEIGHT);
        this.tileType = 'C';
		/*try {
			image = new BufferedImage[imgFile.length];
			for (int i = 0; i < imgFile.length; i++) {
				image[i] = ImageIO.read(new File(imgFile[i]));
			}
			if (img == null) img = image[0];
		}	catch(IOException e) {
			System.out.println("Error");
		}*/
	}
    
    public Rectangle getHitBox() {
    	return hitBox;
    }
    
	/**
	 * Loops Images Files and will Spin Coin
	 */
    /*
	public void spinCoin() {
		for (int i = 0; i < image.length; i++) {
			if (i == image.length - 1 && img.equals(image[i])) {
				img = image[0];
				break;
			} else if (img.equals(image[i])) {
				break;
			}
		}
		
	}
*/
}