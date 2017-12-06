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
	protected static final int HEIGHT = 15;
	protected static final int WIDTH = 7;

	/**
	 * Basic initialization method to make sure no field remains unassigned
	 */
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

	/**
	 * This method manually moves Rowdy and his hitboxes to a specified x,y
	 * coordinate
	 * 
	 * @param x
	 * @param y
	 */
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

	/**
	 * This method is used directly by the model to use his derived velocities to
	 * move him across the level
	 */
	public void moveRowdy() {
		this.position.translate(this.xVelocity, yVelocity);
		this.hitBox.translate(this.xVelocity, yVelocity);
		this.headBox.translate(this.xVelocity, yVelocity);
		this.footBox.translate(this.xVelocity, yVelocity);
		this.leftBox.translate(this.xVelocity, yVelocity);
		this.rightBox.translate(this.xVelocity, yVelocity);
	}

	/**
	 * Called by the controller to give Rowdy velocity to the left
	 */
	public void moveLeft() {
		if (this.canMoveLeft)
			this.xVelocity = -1;
	}

	/**
	 * Called by the controller to give Rowdy velocity to the right
	 */
	public void moveRight() {
		if (this.canMoveRight)
			this.xVelocity = 1;
	}

	/**
	 * Used by the controller on key release to stop Rowdy's horizontal momentum
	 */
	public void stopHorizontalMotion() {
		this.xVelocity = 0;
	}

	/**
	 * This method used by the controller makes Rowdy simulate a jump by giving him
	 * an initial velocity and a buffer for "gravity" to wear down before it
	 * decelerates his jump
	 */
	public void jump() {
		if (this.onGround) {
			this.jumpTime = 15;
			this.yVelocity = 3;
			this.onGround = false;
		}
	}

	/**
	 * Used within the model to stop Rowdy's vertical momentum as a helper method
	 */
	protected void land() {
		this.yVelocity = 0;
	}

	/**
	 * Simulates gravity within the model by decelerating Rowdy whenever he isn't in
	 * contact with ground through a buffer to limit the rate of deceleration
	 */
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

	/**
	 * returns the amount of coins left to be drawn to the view
	 * 
	 * @return
	 */
	public int getCoinCount() {
		return this.coinCount;
	}

	/**
	 * returns Rowdy's position
	 * 
	 * @return Point where Rowdy currently is
	 */
	public Point getPosition() {
		return position;
	}

	/**
	 * Sets Rowdy's current position without care for his hitboxes
	 * 
	 * @param position
	 */
	public void setPosition(Point position) {
		this.position = position;
	}

	/**
	 * returns Rowdy's stored sprite for drawing
	 * 
	 * @return
	 */
	public Image getImg() {
		return rowdySprite;
	}

	/**
	 * Sets Rowdy's current sprite
	 * 
	 * @param rowdySprite
	 */
	public void setImg(Image rowdySprite) {
		this.rowdySprite = rowdySprite;
	}

	/**
	 * This method handles all of the collision of Rowdy by checking which blocks he
	 * is interacting with and determining how to react to them depending on what
	 * block type they are
	 * 
	 * @param level
	 *            the currentlevel that Rowdy is navigating
	 * @return 0 if everything is going fine 1 if rowdy died 2 if rowdy has won the
	 *         game
	 */
	public int hitBoxChecker(Level level) {
		this.onGround = false;
		this.canMoveLeft = true;
		this.canMoveRight = true;
		Tile curr = null;
		int rowdyX = (int) Math.floor((double) this.position.x / Tile.WIDTH);
		int rowdyY = (int) Math.floor((double) this.position.y / Tile.HEIGHT);

		Point[] footlist = { new Point(rowdyX + 1, rowdyY - 1), new Point(rowdyX, rowdyY - 1),
				new Point(rowdyX + 1, rowdyY - 2), new Point(rowdyX, rowdyY - 2) };

		for (int i = 0; i < footlist.length; i++) {
			if (footlist[i].y < 0 || footlist[i].y > level.HEIGHT)
				return 1;
			curr = level.access(footlist[i].x, footlist[i].y);
			switch (curr.getTileType()) {
			case 'C':
				if (this.hitBox.intersects(curr.getHitBox()))
					level.collectCoin(footlist[i].x, footlist[i].y);
				break;
			case 'A':
				break;
			case 'P':
				if (this.footBox.intersects(curr.getHitBox())) {
					this.onGround = true;
					if (this.jumpTime == 0)
						this.adjustRowdy(this.position.x, (int) curr.getPosition().getY() + (Rowdy.HEIGHT - 4));
				}
				break;
			case 'G':
				if (this.footBox.intersectsLine(curr.getPosition().getX() + 1, curr.getPosition().getY() + 1, curr.getPosition().getX() - 1 + curr.WIDTH, curr.getPosition().getY() + 1)) {
					this.onGround = true;
					if (this.jumpTime == 0)
						this.adjustRowdy(this.position.x, (int) curr.getPosition().getY() + Rowdy.HEIGHT);
				}
				break;
			case 'F':
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
			if (headlist[i].y < 0 || headlist[i].y > level.HEIGHT)
				return 1;
			curr = level.access(headlist[i].x, headlist[i].y);
			switch (curr.getTileType()) {
			case 'C':
				if (this.hitBox.intersects(curr.getHitBox()))
					level.access(headlist[i].x, headlist[i].y);
				break;
			case 'P':
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
			case 'F':
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
				curr = level.access(leftlist[i].x, leftlist[i].y);
				switch (curr.getTileType()) {
				case 'C':
					if (this.hitBox.intersects(curr.getHitBox()))
						level.access(leftlist[i].x, leftlist[i].y);
					break;
				case 'P':
				case 'A':
					break;
				case 'G':
					if (this.leftBox.intersects(curr.getHitBox())) {
						this.canMoveLeft = false;
						//this.xVelocity = 0;
						this.adjustRowdy(this.position.x + 1, this.position.y);
					}
					break;
				case 'F':
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
		if (rowdyX >= level.WIDTH - 2) {
			canMoveRight = false;
			this.adjustRowdy(((level.WIDTH - 3) * Tile.WIDTH) + 9, this.position.y);
		} else {
			for (int i = 0; i < rightlist.length; i++) {
				curr = level.access(rightlist[i].x, rightlist[i].y);
				switch (curr.getTileType()) {
				case 'C':
					if (this.hitBox.intersects(curr.getHitBox()))
						level.collectCoin(rightlist[i].x, rightlist[i].y);
					break;
				case 'P':
				case 'A':
					break;
				case 'G':
					if (this.rightBox.intersects(curr.getHitBox())) {
						this.canMoveRight = false;
						//this.xVelocity = 0;
						this.adjustRowdy(this.position.x - 1, this.position.y);
					}
					break;
				case 'F':
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
		if (level.isLevelBeat())
			return 2;
		return 0;
	}

}