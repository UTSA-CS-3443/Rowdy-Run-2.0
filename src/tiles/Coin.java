package tiles;

import java.awt.Rectangle;

public class Coin extends Item {
	
	private static int coins = 0;		// Counts Coins
	private Rectangle hitBox;			// Collision Detection
	
	public static int getCoinCount() {
		return coins;
	}
	
	public Coin(int x, int y) {
		super (x, y);
	}
	
	
}
	
	
