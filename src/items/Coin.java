package items;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import com.sun.javafx.scene.traversal.Direction;


public class Coin extends Item {
	
	public static String[] imgFile = {"Coin1.gif", "Coin2.gif", "Coin3.gif"};
	
	public static final int WIDTH = 20;
	public static final int HEIGHT = 20;
	
	private BufferedImage[] image;
	
	private Rectangle hitBox;
	private Point position;

    public Coin(Direction dir, int x_pos, int y_pos, int x_max,
			int y_max, int width, int height, int boxWidth, int boxHeight) {
        super(dir, x_pos, y_pos, x_max, y_max, width, height, boxHeight, boxHeight);
        hitBox = new Rectangle(x_pos, y_pos, WIDTH, HEIGHT);
		this.position.x = x_pos;
		this.position.y = y_pos;

		try {
			image = new BufferedImage[imgFile.length];
			for (int i = 0; i < imgFile.length; i++) {
				image[i] = ImageIO.read(new File(imgFile[i]));
			}
			if (img == null) img = image[0];
		}	catch(IOException e) {
			System.out.println("Error");
		}
	}

    public Rectangle getHitBox() {
    	return hitBox;
    }
    
    public void setX(int x_pos) {
    	this.x_pos = x_pos;
    }
    
    public void setY(int y_pos) {
    	this.y_pos = y_pos;
    	hitBox.setLocation(new Point(x_pos, y_pos));
    }
    
	/**
	 * Loops Images Files and will Spin Coin
	 */
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
}