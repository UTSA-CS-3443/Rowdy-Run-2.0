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
	protected Rectangle hitBox, footBox, headBox;
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
		this.footBox = new Rectangle(this.position.x + 2, this.position.y - (this.HEIGHT - 1), 3, 1);
		this.coinCount = 0;
	}

	public void adjustRowdy(int x, int y) {
		this.position.setLocation(x, y);
		Point foot = new Point(this.position);
		Point head = new Point(this.position);
		foot.translate(2, -(this.HEIGHT - 1));
		head.translate(2, 2);
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
	
	public void stopHorizontalMotion() {
		this.xVelocity = 0;
	}

	public void jump() {
		if (this.onGround)
			this.yVelocity = 20;
	}

	protected void land() {
		this.yVelocity = 0;
	}

	public void fall() {
		if (this.onGround) {
			this.yVelocity = 0;
			return;
		}

		//this.yVelocity = 0;
		this.yVelocity -= 1;
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
		Point[] list = { new Point(rowdyX - 1, rowdyY - 1), new Point(rowdyX, rowdyY - 1),
				new Point(rowdyX - 1, rowdyY - 2), new Point(rowdyX, rowdyY - 2) };

		if (rowdyX == 0) {
			canMoveLeft = false;
			for (int i = 1; i < list.length; i += 2) {
				curr = Level.get(list[i].x)[list[i].y];
				switch (curr.getTileType()) {
				case 'C':
					// Level.get(list[a].x)[list[a].y] = new Air(list[a].x * 10, list[a].y * 10);
					// this.coinCount++;
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
		} else {
			for (int i = 0; i < list.length; i++) {
				curr = Level.get(list[i].x)[list[i].y];
				switch (curr.getTileType()) {
				case 'C':
					Level.get(list[i].x)[list[i].y] = new Air(list[i].x * 10, list[i].y * 10);
					this.coinCount++;
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
		Point[] leftlist = { new Point(rowdyX - 1, rowdyY), new Point(rowdyX - 1, rowdyY - 1),
				new Point(rowdyX - 1, rowdyY - 2) };

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
					if (this.hitBox.intersects(curr.getHitBox())) {
						canMoveRight = false;
						this.adjustRowdy((int) curr.getPosition().getX() - this.WIDTH, this.position.y);
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
