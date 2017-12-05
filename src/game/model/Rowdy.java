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
	private Rectangle hitBox, footBox, headBox,rightBox,leftBox;
	protected Image rowdySprite;
	protected int jumpTime,fallTime;
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
		this.headBox = new Rectangle(this.position.x, this.position.y + 2, this.WIDTH, 2);
		this.footBox = new Rectangle(this.position.x, this.position.y - (this.HEIGHT - 3), this.WIDTH, 1);
		this.rightBox = new Rectangle(this.position.x+(this.WIDTH-2), this.position.y, 2, this.HEIGHT);
		this.leftBox = new Rectangle(this.position.x, this.position.y, 2, this.HEIGHT);
		this.coinCount = 0;
	}

	public void adjustRowdy(int x, int y) {
		this.position.setLocation(x, y);
		Point foot = new Point(this.position);
		Point head = new Point(this.position);
		Point right = new Point(this.position);
		foot.translate(0, -(this.HEIGHT - 1));
		head.translate(0, -2);
		right.translate((this.WIDTH-2), 0);
		
		this.hitBox.setLocation(this.position);
		this.leftBox.setLocation(this.position);
		this.headBox.setLocation(head);
		this.footBox.setLocation(foot);
		this.rightBox.setLocation(right);
	}

	public void moveRowdy() {
		
		System.out.println(this.yVelocity);
		this.position.translate(this.xVelocity, yVelocity);
		this.hitBox.translate(this.xVelocity, yVelocity);
		this.headBox.translate(this.xVelocity, yVelocity);
		this.footBox.translate(this.xVelocity, yVelocity);
		this.leftBox.translate(this.xVelocity, yVelocity);
		this.rightBox.translate(this.xVelocity, yVelocity);
	}

	public void moveLeft() {
		if (this.canMoveLeft)
			this.xVelocity = -1;
	}

	public void moveRight() {
		if (this.canMoveRight)
			this.xVelocity = 1;
	}
	
	public void stopHorizontalMotion() {
		this.xVelocity = 0;
	}

	public void jump() {
		if (this.onGround) {
			this.jumpTime = 20;
			this.yVelocity = 3;
			this.onGround = false; 
		}
	}
	public void stop() {

		this.xVelocity = 0;
	}

	protected void land() {
		this.yVelocity = 0;
	}

	public void fall() {
		if (this.onGround && this.jumpTime == 0) {
			this.yVelocity = 0;
			return;
		}
		if(this.jumpTime == 0) {
			this.yVelocity -= 1;
			if (this.yVelocity < -5)
				this.yVelocity = -5;
		}
		else 
			this.jumpTime -= 1;
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
		Point[] list = { new Point(rowdyX - 1, rowdyY - 1), new Point(rowdyX, rowdyY - 1),
				new Point(rowdyX - 1, rowdyY - 2), new Point(rowdyX, rowdyY - 2) };

		if (rowdyX == 0) {
			canMoveLeft = false;
			for (int i = 1; i < list.length; i += 2) {
				if(list[i].y < 0 ||list[i].y>25)
					return -1;
				curr = Level.get(list[i].x)[list[i].y];
				switch (curr.getTileType()) {
				case 'C':
					if (this.hitBox.intersects(curr.getHitBox())) {
						Level.get(list[i].x)[list[i].y] = new Air(list[i].x * 10, list[i].y * 10);
						this.coinCount++;
					}
					break;
				case 'A':
					break;
				case 'G':
					if (this.footBox.intersects(curr.getHitBox())) {
						this.onGround = true;
						if(this.jumpTime == 0)
							this.adjustRowdy(this.position.x, (int) curr.getPosition().getY() + Rowdy.HEIGHT + 1);
					}
					break;
				case 'X':
				case '?':
				default:
					return 1;
				}
			}
		} else {
			for (int i = 0; i < list.length; i++) {
				curr = Level.get(list[i].x)[list[i].y];
				switch (curr.getTileType()) {
				case 'C':
					if (this.hitBox.intersects(curr.getHitBox())) {
						Level.get(list[i].x)[list[i].y] = new Air(list[i].x * 10, list[i].y * 10);
						this.coinCount++;
					}
					break;
				case 'A':
					break;
				case 'G':
					if (this.footBox.intersects(curr.getHitBox())) {
						this.onGround = true;
						if(this.jumpTime == 0)
							this.adjustRowdy(this.position.x, (int) curr.getPosition().getY() + Rowdy.HEIGHT);
					}
					break;
				case 'X':
				case '?':
				default:
					return 1;
				}
			}
		}
		Point[] leftlist = { new Point(rowdyX - 1, rowdyY), new Point(rowdyX - 1, rowdyY - 1),
				new Point(rowdyX - 1, rowdyY - 2), new Point(rowdyX, rowdyY), new Point(rowdyX, rowdyY - 1) };

		if (rowdyX == 0) {
			canMoveLeft = false;
		} else {
			for (int i = 0; i < leftlist.length; i++) {
				curr = Level.get(leftlist[i].x)[leftlist[i].y];
				switch (curr.getTileType()) {
				case 'C':
					if (this.hitBox.intersects(curr.getHitBox())) {
						Level.get(leftlist[i].x)[leftlist[i].y] = new Air(leftlist[i].x * 10, leftlist[i].y * 10);
						this.coinCount++;
					}
					break;
				case 'A':
					break;
				case 'G':
					if (this.leftBox.intersects(curr.getHitBox())) {
						this.canMoveLeft = false;
						this.adjustRowdy((int) curr.getPosition().getX() + Tile.WIDTH, this.position.y);
					}
					break;
				case 'X':
				case '?':
				default:
					return 1;
				}
			}
		}
		Point[] rightlist = { new Point(rowdyX + 1, rowdyY), new Point(rowdyX + 1, rowdyY - 1),
				new Point(rowdyX + 1, rowdyY - 2), new Point(rowdyX, rowdyY), new Point(rowdyX, rowdyY - 1) };
		if (rowdyX == Level.size() - 1) {
			canMoveRight = false;
		} else {
			for (int i = 0; i < rightlist.length; i++) {
				curr = Level.get(rightlist[i].x)[rightlist[i].y];
				switch (curr.getTileType()) {
				case 'C':
					if (this.hitBox.intersects(curr.getHitBox())) {
						Level.get(rightlist[i].x)[rightlist[i].y] = new Air(rightlist[i].x * 10, rightlist[i].y * 10);
						this.coinCount++;
					}
					break;
				case 'A':
					break;
				case 'G':
					if (this.rightBox.intersects(curr.getHitBox())) {
						this.canMoveRight = false;
						this.adjustRowdy((int) curr.getPosition().getX() - Rowdy.WIDTH, this.position.y);
					}
					break;
				case 'X':
				case '?':
				default:
					return 1;
				}
			}
		}

		return 0;
	}

}
