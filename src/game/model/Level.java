package game.model;

import java.awt.Point;
import java.util.ArrayList;
import tiles.*;

public class Level {
	private ArrayList<Tile[]> layout;
	public Point playerStart;
	public int WIDTH = 0;
	public int HEIGHT = 0;
	private int coinCount = 0;
	
	public Level(ArrayList<Tile[]> curr,Point rowdyStart) {
		this.layout = curr;
		this.playerStart = rowdyStart;
		this.WIDTH = layout.size();
		this.HEIGHT = layout.get(0).length;
		this.countCoins();
	}
	
	public ArrayList<Tile[]> getLayout(){
		return layout;
	}
	
	public Point getPlayerStart() {
		return this.playerStart;
	}
	public Tile access(int x, int y) {
		return layout.get(x)[y];
	}

	public void addColumn(Tile[] Column) {
		layout.add(Column);
		this.WIDTH = layout.size();
		this.HEIGHT = Column.length;
		this.countCoins();
	}

	public char accessType(int x, int y) {
		return this.access(x, y).getTileType();
	}

	public void collectCoin(int x, int y) {
		layout.get(x)[y] = new Air(x * Tile.WIDTH, y * Tile.HEIGHT);
		this.coinCount--;
	}

	public void countCoins() {
		this.coinCount = 0;
		for (int x = 0; x < WIDTH; x++) {
			for (int y = 0; y < HEIGHT; y++) {
				if (this.accessType(x, y) == 'C')
					this.coinCount++;
			}
		}
	}
	public Boolean isLevelBeat() {
		if(this.coinCount == 0)
			return true;
		return false;
	}

}
