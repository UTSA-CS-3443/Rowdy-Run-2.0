package items;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import com.sun.javafx.scene.traversal.Direction;

public abstract class Item {
	
	protected BufferedImage img;
	public Direction dir;
	
	/**
	 * Current Position of the Object
	 * 
	 * 0 <= x_pos <= x_max
	 * 0 <= y_pos <= y_max
	 */
	public int x_pos;
	public int y_pos;
	
	/**
	 * Pixels to Move Each Time move() is Called
	 */
	public int x_vel;
	public int y_vel;
	
	/**
	 * Bounds Area, Items Position
	 * Maximum x and y Positions
	 */
	public int x_max;
	public int y_max;
	
	/**
	 * Size of Item, in Pixels
	 */
	public int width;
	public int height;
	
	public Item(Direction dir, int x_pos, int y_pos, int x_vel, int y_vel, int x_max,
			int y_max, int width, int height, int boxWidth, int boxHeight) {
		this.dir = dir;
		this.x_pos = x_pos;
		this.y_pos = y_pos;
		this.x_vel = x_vel;
		this.y_vel = y_vel;
		this.x_max = x_max;
		this.y_max = y_max;
		this.width = width;
		this.height = height;
	}

	/**
	 * Move Item By Velocity
	 */
	public abstract void move();
	public abstract void offScreen();
	
	public boolean intersect(Item coin) {
		return (intersectRight(coin) || intersectTop(coin) || intersectBottom(coin));
	}
	
	public boolean intersectRight(Item coin) {
		return (coin.x_pos <= x_pos + width && coin.x_pos >= x_pos + ((double) width / 2)
				&& coin.y_pos + coin.height >= y_pos && coin.y_pos <= y_pos + height);
	}
	
	public boolean intersectTop(Item coin) {
		return (coin.y_pos + coin.height >= y_pos && coin.y_pos + coin.height <=y_pos + ((double) height / 2)
				&& coin.x_pos + coin.width >= x_pos && coin.x_pos <= x_pos + width);
	}
	
	public boolean intersectBottom(Item coin) {
		return (coin.y_pos <= y_pos + height && coin.y_pos > y_pos + ((double) height / 2)
				&& coin.x_pos + coin.width >= x_pos && coin.x_pos <= x_pos + width);
	}
	
	public boolean collision(Item coin) {
		return (collisionRight(coin) || collisionTop(coin) || collisionBottom(coin));
	}
	
	public boolean collisionRight(Item coin) {
		return (coin.x_pos == x_pos + width && coin.y_pos + coin.height >= y_pos
				&& coin.y_pos <= y_pos + height);
	}
	
	public boolean collisionTop(Item coin) {
		return (coin.y_pos +coin.height == y_pos && coin.x_pos + coin.width >= x_pos
				&& coin.x_pos <= x_pos + width);
	}
	
	public boolean collisionBottom(Item coin) {
		return (coin.y_pos == y_pos + height && coin.x_pos + coin.width >= x_pos
				&& coin.x_pos <= x_pos + width);
	}
	
	public void draw(Graphics g) {
		g.drawImage(img, x_pos, y_pos, width, height, null);
	}
}