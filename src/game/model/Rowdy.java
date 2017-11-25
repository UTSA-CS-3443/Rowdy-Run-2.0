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
	protected Rectangle hitBox;
	protected Image rowdySprite;
	protected int jumpTime;
	protected boolean onGround,canMoveLeft,canMoveRight;
	private int coinCount;
	
	//TODO add foot + headbox for platform to check against and to help with last tile
	public Rowdy(){
		this.position = new Point(200,400);
		this.xVelocity = 0;
		this.yVelocity = 0;
		this.jumpTime = 0;
		this.onGround = false;
		this.canMoveLeft = true;
		this.canMoveRight = true;
		this.hitBox = new Rectangle(position.x,position.y,75,150);
		this.coinCount = 0;
	}

	public void adjustRowdy(int x,int y) {
		this.position.setLocation(x, y);
		this.hitBox.setLocation(this.position);
	}
	public void moveRowdy() {
		this.position.translate(this.xVelocity, this.yVelocity);
		this.hitBox.translate(this.xVelocity, this.yVelocity);
	}

	public void moveLeft() {
		if(this.canMoveLeft)
			this.xVelocity = -10;
	}

	public void moveRight() {
		if(this.canMoveRight)
			this.xVelocity = 10;
	}

	public void jump() {
		if(this.onGround)
			this.yVelocity = 30;
	}

	protected void land() {
		this.yVelocity = 0;
	}

	public void fall() {
		if(!this.onGround)
			this.yVelocity -= -10;
	}
	
	public int getCoinCount() {
		return this.coinCount;
	}

	//TODO check last box and add platform management + coin management
	public int hitBoxChecker(ArrayList<Tile[]> Level) {
		Tile curr = null;
		int  rowdyX = (int) Math.floor((double)this.position.x/100.0);
		int rowdyY = (int) Math.floor((double)this.position.y/100.0);
		Point[] list = {
				new Point(rowdyX-1,rowdyY),
				new Point(rowdyX-1,rowdyY-1),
				new Point(rowdyX,rowdyY-2),
				new Point(rowdyX,rowdyY-1),
				new Point(rowdyX,rowdyY),
				new Point(rowdyX,rowdyY+1),
				new Point(rowdyX+1,rowdyY),
				new Point(rowdyX+1,rowdyY-1)};
		this.onGround = false;
		
		for(int a = 0; a <2;a++) {
			curr = Level.get(list[a].x)[list[a].y];
			switch(curr.getTileType()) {
				case 'c':
					Level.get(list[a].x)[list[a].y] = new Air(list[a].x * 100, list[a].y * 100);
					this.coinCount++;
					break;
				case 'a': 
					break;
				case 'g':
					if(this.hitBox.intersects(curr.getHitBox())) { 
						canMoveLeft = false;
						this.adjustRowdy(curr.getX()+100,this.position.y);
					}
					else
						canMoveLeft = true;
					break;
				case 'x':
				case '?':
				default:
					return 1;
			}
		}
			
		for(int b = 6; b <list.length;b++) {
			curr = Level.get(list[b].x)[list[b].y];
			switch(curr.getTileType()) {
				case 'c':
					Level.get(list[b].x)[list[b].y] = new Air(list[b].x * 100, list[b].y * 100);
					this.coinCount++;
					break;
				case 'a': 
					break;
				case 'g':
					if(this.hitBox.intersects(curr.getHitBox())) { 
						canMoveRight = false;
						this.adjustRowdy(curr.getX(),this.position.y);
					}
					else
						canMoveRight = true;
					break;
				case 'x':
				case '?':
				default:
					return 1;
			}
		}
		
		curr = Level.get(list[2].x)[list[2].y];
		switch(curr.getTileType()) {
			case 'c':
				Level.get(list[2].x)[list[2].y] = new Air(list[2].x * 100, list[2].y * 100);
				this.coinCount++;
				break;
			case 'a': 
				break;
			case 'g':
				if(this.hitBox.intersects(curr.getHitBox())) {
					onGround = true;
					this.land();
					this.adjustRowdy(this.position.x,curr.getY()+100);
				}
				else
					onGround = false;
				break;
			case 'x':
			case '?':
			default:
				return 1;
		}
		
		curr = Level.get(list[3].x)[list[3].y];
		switch(curr.getTileType()) {
			case 'c':
				Level.get(list[3].x)[list[3].y] = new Air(list[3].x * 100, list[3].y * 100);
				this.coinCount++;
				break;
			case 'a': 
				break;
			case 'g':
				if(this.hitBox.intersects(curr.getHitBox())) {
					onGround = true;
					this.land();
					this.adjustRowdy(this.position.x,curr.getY()+100);
				}
				else
					onGround = false;
				break;
			case 'x':
			case '?':
			default:
				return 1;
		}
		
		curr = Level.get(list[5].x)[list[5].y];
		switch(curr.getTileType()) {
			case 'c':
				Level.get(list[5].x)[list[5].y] = new Air(list[5].x * 100, list[5].y * 100);
				this.coinCount++;
				break;
			case 'a': 
				break;
			case 'g':
				if(this.hitBox.intersects(curr.getHitBox())) {
					 this.land();
					 this.fall();
					this.adjustRowdy(this.position.x,curr.getY()-100);
				}
				break;
			case 'x':
			case '?':
			default:
				return 1;
		}
		
		curr = Level.get(list[4].x)[list[4].y];
		switch(curr.getTileType()) {
			case 'c':
				Level.get(list[4].x)[list[4].y] = new Air(list[4].x * 100, list[4].y * 100);
				this.coinCount++;
				break;
			case 'a': 
				break;
			case 'g':
				if(this.hitBox.intersects(curr.getHitBox())) {
					this.land();
					this.fall();
					this.adjustRowdy(this.position.x,curr.getY()-100);
				}
				break;
			case 'x':
			case '?':
			default:
				return 1;
		}
		
		return 0;
	}
	

}
