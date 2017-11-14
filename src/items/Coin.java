package items;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import com.sun.javafx.scene.traversal.Direction;


public class Coin extends Item {
	
	public static String[] imgFile = {"Coin1.gif", "Coin2.gif", "Coin3.gif"};
	
	public int start;
	public static int start_vel_x = 0;
	public static int start_vel_y = 0;
	
	public static int x_vel;
	
	public static final int WIDTH = 20;
	public static final int HEIGHT = 20;
	
	public static final int start_height = 100;
	
	private BufferedImage[] image;
	
	//TODO giving me errors
    public Coin() {
        super();
        
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
	
	public void move() {
		if (x_vel > 0) dir = Direction.RIGHT;
		if (x_vel < 0) dir = Direction.LEFT;
		x_pos += x_vel;
			
		offScreen();
	}
	
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
	
	public void offScreen() {
		if (y_pos < 0) y_pos = 0;
		else if (y_pos > y_max) y_pos= y_max;
	}
}