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
	private Rectangle hitBox, footBox, headBox, rightBox, leftBox;
	protected Image rowdySprite;
	private int jumpTime, fallTime;
	private boolean onGround, canMoveLeft, canMoveRight;
	private int coinCount;
	public static final int HEIGHT = 15;
	public static final int WIDTH = 7;

	public Rowdy() {
		this.position = new Point(20, 40);
		this.xVelocity = 0;
		this.yVelocity = 0;
		this.jumpTime = 0;
		this.onGround = false;
		this.canMoveLeft = true;
		this.canMoveRight = true;
		this.hitBox = new Rectangle(this.position.x, this.position.y, Rowdy.WIDTH, Rowdy.HEIGHT);
		this.headBox = new Rectangle(this.position.x, this.position.y, Rowdy.WIDTH, 3);
		this.footBox = new Rectangle(this.position.x + 1, this.position.y - (Rowdy.HEIGHT - 3), Rowdy.WIDTH - 2, 3);
		this.rightBox = new Rectangle(this.position.x + (Rowdy.WIDTH - 2), this.position.y + 2, 2, Rowdy.HEIGHT - 2);
		this.leftBox = new Rectangle(this.position.x, this.position.y + 2, 2, Rowdy.HEIGHT - 2);
		this.coinCount = 0;
	}

	public void adjustRowdy(int x, int y) {
		this.position.setLocation(x, y);
		Point foot = new Point(this.position);
		Point head = new Point(this.position);
		Point right = new Point(this.position);
		foot.translate(0, -(Rowdy.HEIGHT - 1));
		head.translate(0, 2);
		right.translate((Rowdy.WIDTH - 2), 0);

		this.hitBox.setLocation(this.position);
		this.leftBox.setLocation(this.position);
		this.headBox.setLocation(head);
		this.footBox.setLocation(foot);
		this.rightBox.setLocation(right);
	}

	public void moveRowdy() {
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
			this.jumpTime = 10;
			this.yVelocity = 3;
			this.onGround = false;
		}
	}

	protected void land() {
		this.yVelocity = 0;
	}

	public void fall() {
		if (this.onGround && this.jumpTime == 0) {
			this.yVelocity = 0;
			return;
		}
		if (this.jumpTime == 0) {
			if (this.fallTime > 0) {
				this.fallTime -= 1;
				return;
			}
			this.yVelocity -= 1;
			if (this.yVelocity < -3)
				this.yVelocity = -3;
			this.fallTime = 10;
		} else
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

	public int hitBoxChecker(ArrayList<Tile[]> Level) {
		this.onGround = false;
		this.canMoveLeft = true;
		this.canMoveRight = true;
		Tile curr = null;
		int rowdyX = (int) Math.floor((double) this.position.x / Tile.WIDTH);
		int rowdyY = (int) Math.floor((double) this.position.y / Tile.HEIGHT);

		Point[] footlist = { new Point(rowdyX + 1, rowdyY - 1), new Point(rowdyX, rowdyY - 1),
				new Point(rowdyX + 1, rowdyY - 2), new Point(rowdyX, rowdyY - 2) };

		for (int i = 0; i < footlist.length; i++) {
			if (footlist[i].y < 0 || footlist[i].y > Level.get(0).length)
				return 1;
			curr = Level.get(footlist[i].x)[footlist[i].y];
			switch (curr.getTileType()) {
			case 'C':
				if (this.hitBox.intersects(curr.getHitBox())) {
					Level.get(footlist[i].x)[footlist[i].y] = new Air(footlist[i].x * 10, footlist[i].y * 10);
					this.coinCount++;
				}
				break;
			case 'A':
				break;
			case 'G':
				if (this.footBox.intersectsLine(curr.getPosition().getX() + 1, curr.getPosition().getY() + 1, curr.getPosition().getX() - 1 + curr.WIDTH, curr.getPosition().getY() + 1)) {
					this.onGround = true;
					if (this.jumpTime == 0)
						this.adjustRowdy(this.position.x, (int) curr.getPosition().getY() + Rowdy.HEIGHT);
				}
				break;
			case 'X':
				if (this.hitBox.intersects(curr.getHitBox()))
					return 1;
				break;
			case '?':
			default:
				return 1;
			}
		}

		Point[] headlist = { new Point(rowdyX + 1, rowdyY), new Point(rowdyX, rowdyY),
				new Point(rowdyX + 1, rowdyY + 1), new Point(rowdyX, rowdyY + 1) };

		for (int i = 0; i < headlist.length; i++) {
			if (headlist[i].y < 0 || headlist[i].y > Level.get(0).length)
				return 1;
			curr = Level.get(headlist[i].x)[headlist[i].y];
			switch (curr.getTileType()) {
			case 'C':
				if (this.hitBox.intersects(curr.getHitBox())) {
					Level.get(headlist[i].x)[headlist[i].y] = new Air(headlist[i].x * 10, headlist[i].y * 10);
					this.coinCount++;
				}
				break;
			case 'A':
				break;
			case 'G':
				if (this.headBox.intersectsLine(curr.getPosition().getX(), curr.getPosition().getY() + Tile.HEIGHT, curr.getPosition().getX() + Tile.WIDTH, curr.getPosition().getY() + Tile.HEIGHT)) {
					System.out.println("touching bottom side");
					this.jumpTime = 0;
					this.yVelocity = 0;
					//this.land();
					this.adjustRowdy(this.position.x, (int) curr.getPosition().getY() - Tile.HEIGHT);
				}
				break;
			case 'X':
				if (this.hitBox.intersects(curr.getHitBox()))
					return 1;
				break;
			case '?':
			default:
				return 1;
			}
		}

		Point[] leftlist = { new Point(rowdyX, rowdyY), new Point(rowdyX, rowdyY - 1) };

		if (rowdyX <= 0) {
			canMoveLeft = false;
			this.adjustRowdy(10, this.position.y);
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
						//this.xVelocity = 0;
						this.adjustRowdy(this.position.x + 1, this.position.y);
					}
					break;
				case 'X':
					if (this.hitBox.intersects(curr.getHitBox()))
						return 1;
					break;
				case '?':
				default:
					return 1;
				}
			}
		}
		Point[] rightlist = { new Point(rowdyX + 1, rowdyY), new Point(rowdyX + 1, rowdyY - 1),
				new Point(rowdyX + 1, rowdyY - 2), new Point(rowdyX, rowdyY), new Point(rowdyX, rowdyY - 1) };
		if (rowdyX >= Level.size() - 2) {
			canMoveRight = false;
			this.adjustRowdy(((Level.size() - 3) * Tile.WIDTH) + 9, this.position.y);
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
						//this.xVelocity = 0;
						this.adjustRowdy(this.position.x - 1, this.position.y);
					}
					break;
				case 'X':
					if (this.hitBox.intersects(curr.getHitBox()))
						return 1;
					break;
				case '?':
				default:
					return 1;
				}
			}
		}

		return 0;
	}

}