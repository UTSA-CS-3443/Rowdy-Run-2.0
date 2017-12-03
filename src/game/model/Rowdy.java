package game.model;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import javafx.scene.image.Image;
import tiles.Air;
import tiles.Tile;

/**
 * Handles all of the main character's movement and hitbox detection as well as
 * storing his sprite
 * 
 * @author Jared Polwort
 *
 */
public class Rowdy {

	protected Point position;
	private int xVelocity, yVelocity;
	protected Rectangle hitBox,footBox,headBox;
	protected Image rowdySprite;
	protected int jumpTime;
	protected boolean onGround, canMoveLeft, canMoveRight;
	private int coinCount;
	public static final int HEIGHT = 15;
	public static final int WIDTH = 7;

	// TODO add foot + headbox for platform to check against and to help with last
	// tile
	public Rowdy() {
		this.position = new Point(200, 80);
		this.xVelocity = 0;
		this.yVelocity = 0;
		this.jumpTime = 0;
		this.onGround = false;
		this.canMoveLeft = true;
		this.canMoveRight = true;
		this.hitBox = new Rectangle(this.position.x, this.position.y, this.WIDTH, this.HEIGHT);
		this.headBox = new Rectangle(this.position.x + 2, this.position.y + 2, 5, 2);
		this.footBox = new Rectangle(this.position.x + 2, this.position.y - (this.HEIGHT-1), 3, 1);
		this.coinCount = 0;
	}

	public void adjustRowdy(int x, int y) {
		this.position.setLocation(x, y);
		Point foot = new Point(this.position);
		Point head = new Point(this.position);
		foot.translate(2, -(this.HEIGHT-1 ));
		head.translate(2,2);
		this.hitBox.setLocation(this.position);
		this.headBox.setLocation(head);
		this.footBox.setLocation(foot);
	}

	public void moveRowdy() {
		this.position.translate(this.xVelocity, this.yVelocity);
		this.hitBox.translate(this.xVelocity, this.yVelocity);
		this.headBox.translate(this.xVelocity, this.yVelocity);
		this.footBox.translate(this.xVelocity, this.yVelocity);
	}

	public void moveLeft() {
		if (this.canMoveLeft)
			this.xVelocity = -2;
	}

	public void moveRight() {
		if (this.canMoveRight)
			this.xVelocity = 2;
	}

	public void jump() {
		if (this.onGround)
			this.yVelocity = 30;
	}

	protected void land() {
		this.yVelocity = 0;
	}

	public void fall() {
		if (this.onGround) {
			System.out.println("YUP!");
			this.yVelocity = 0;
			return;
		}
	
		System.out.println("NOPE");
		this.yVelocity = 0;
		this.yVelocity -= 2;
		if (this.yVelocity < -10)
			this.yVelocity = -10;

	}

	public int getCoinCount() {
		return this.coinCount;
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public Image getImg() {
		return rowdySprite;
	}

	public void setImg(Image rowdySprite) {
		this.rowdySprite = rowdySprite;
	}

	// TODO check last box and add platform management + coin management
	public int hitBoxChecker(ArrayList<Tile[]> Level) {
		this.onGround = false;
		this.canMoveLeft = true;
		this.canMoveRight = true;
		Tile curr = null;
		int rowdyX = (int) Math.floor((double) this.position.x / Tile.WIDTH);
		int rowdyY = (int) Math.floor((double) this.position.y / Tile.HEIGHT);
		Point[] list =  { new Point(rowdyX - 1, rowdyY - 1), 
				new Point(rowdyX, rowdyY - 1),
				new Point(rowdyX - 1, rowdyY - 2),
				new Point(rowdyX , rowdyY - 2) };
		
		if(rowdyX == 0) {
			canMoveLeft = false;
			for(int i = 1;i< list.length; i+=2 ) {
				curr = Level.get(list[i].x)[list[i].y];
				switch (curr.getTileType()) {
				case 'C':
					//Level.get(list[a].x)[list[a].y] = new Air(list[a].x * 10, list[a].y * 10);
					//this.coinCount++;
					break;
				case 'A':
					break;
				case 'G':
					if (this.footBox.intersects(curr.getHitBox())) {
						this.onGround = true;
						this.land();
						this.adjustRowdy(this.position.x, (int) curr.getPosition().getY() + this.HEIGHT);
					}
					break;
				case 'X':
				case '?':
				default:
					return 1;
				}
			}
		}
		else{
			for(int i =0;i< list.length;i++) {
				curr = Level.get(list[i].x)[list[i].y];
				switch (curr.getTileType()) {
				case 'C':
					//Level.get(list[a].x)[list[a].y] = new Air(list[a].x * 10, list[a].y * 10);
					//this.coinCount++;
					break;
				case 'A':
					break;
				case 'G':
					if (this.footBox.intersects(curr.getHitBox())) {
						this.onGround = true;
						this.land();
						this.adjustRowdy(this.position.x, (int) curr.getPosition().getY() + this.HEIGHT);
					}
					break;
				case 'X':
				case '?':
				default:
					return 1;
				}
			}
		}
		
		/*
		Point[] list = { new Point(rowdyX - 1, rowdyY), new Point(rowdyX - 1, rowdyY - 1),
				new Point(rowdyX, rowdyY - 3), new Point(rowdyX, rowdyY - 1), new Point(rowdyX, rowdyY),
				new Point(rowdyX, rowdyY + 1), new Point(rowdyX + 1, rowdyY), new Point(rowdyX + 1, rowdyY - 1) };
		this.onGround = false;
		this.canMoveLeft = true;
		this.canMoveRight = true;
		System.out.println("Rowdy C "+ rowdyX +" "+ rowdyY);
		for (int a = 0; a < 2; a++) {
			
			curr = Level.get(list[a].x)[list[a].y];
			switch (curr.getTileType()) {
			case 'C':
				Level.get(list[a].x)[list[a].y] = new Air(list[a].x * 10, list[a].y * 10);
				this.coinCount++;
				break;
			case 'A':
				break;
			case 'G':
				if (this.hitBox.intersects(curr.getHitBox())) {
					canMoveLeft = false;
					this.adjustRowdy((int) curr.getPosition().getX() + 10, this.position.y);
				}
				break;
			case 'X':
			case '?':
			default:
				return 1;
			}
		}

		for (int b = 6; b < list.length; b++) {
			curr = Level.get(list[b].x)[list[b].y];
			switch (curr.getTileType()) {
			case 'C':
				Level.get(list[b].x)[list[b].y] = new Air(list[b].x * 10, list[b].y * 10);
				this.coinCount++;
				break;
			case 'A':
				break;
			case 'G':
				if (this.hitBox.intersects(curr.getHitBox())) {
					this.canMoveRight = false;
					this.adjustRowdy((int) curr.getPosition().getX()-7, this.position.y);
				}
				break;
			case 'X':
			case '?':
			default:
				return 1;
			}
		}


		curr = Level.get(list[5].x)[list[5].y];
		switch (curr.getTileType()) {
		case 'C':
			Level.get(list[5].x)[list[5].y] = new Air(list[5].x * 10, list[5].y * 10);
			this.coinCount++;
			break;
		case 'A':
			break;
		case 'G':
			if (this.hitBox.intersects(curr.getHitBox())) {
				this.land();
				this.adjustRowdy(this.position.x, (int) curr.getPosition().getY()-15);
			}
			break;
		case 'X':
		case '?':
		default:
			return 1;
		}

		curr = Level.get(list[4].x)[list[4].y];
		switch (curr.getTileType()) {
		case 'C':
			Level.get(list[4].x)[list[4].y] = new Air(list[4].x * 10, list[4].y * 10);
			this.coinCount++;
			break;
		case 'A':
			break;
		case 'G':
			if (this.hitBox.intersects(curr.getHitBox())) {
				this.land();
				this.adjustRowdy(this.position.x, (int) curr.getPosition().getY()-15);
			}
			break;
		case 'X':
		case '?':
		default:
			return 1;
		}
		
		curr = Level.get(list[2].x)[list[2].y];
		switch (curr.getTileType()) {
		case 'C':
			Level.get(list[2].x)[list[2].y] = new Air(list[2].x * 10, list[2].y * 10);
			this.coinCount++;
			break;
		case 'A':
			break;
		case 'G':
			if (this.hitBox.intersects(curr.getHitBox())) {
				System.out.println(2);
				this.onGround = true;
				this.land();
				this.adjustRowdy(this.position.x, (int) curr.getPosition().getY()+15);
			}
			break;
		case 'X':
		case '?':
		default:
			return 1;
		}
		
		curr = Level.get(list[3].x)[list[3].y];
		switch (curr.getTileType()) {
		case 'C':
			Level.get(list[3].x)[list[3].y] = new Air(list[3].x * 10, list[3].y * 10);
			this.coinCount++;
			break;
		case 'A':
			break;
		case 'G':
			if (this.hitBox.intersects(curr.getHitBox())) {
				System.out.println(3);
				this.onGround = true;
				this.land();
				//this.adjustRowdy(this.position.x, (int) curr.getPosition().getY()+15);
			}
			break;
		case 'X':
		case '?':
		default:
			return 1;
		}
		System.out.println(this.onGround);
		*/
		return 0;
	}
	

}
