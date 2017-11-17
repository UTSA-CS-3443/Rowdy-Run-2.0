package game.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import game.Rowdy;
import tiles.*;

/**
 * Reads in data from a level file (lvlFile) to generate a level consisting of
 * Tile objects stored in an ArrayList.
 * 
 * @author Jared Polwort
 *
 */
public class Model {

	protected ArrayList<Tile[]> currentLevel;
	protected Rowdy player;
	
	public void gameTick() {
		int playerState = 1;
		player.moveRowdy();
		playerState = player.hitBoxChecker(currentLevel); 
		if(playerState == 1) {
			//TODO handle rowdy dying
		}
		else if (playerState == 2){
			//TODO handle rowdy winning
		}
		player.fall();
		
	}

	public static ArrayList<Tile[]> readLevel(File lvlFile) throws IOException {
		ArrayList<Tile[]> Level = new ArrayList<Tile[]>();
		int x = 0;
		Scanner in = null;
		try {
			in = new Scanner(lvlFile);
		} catch (FileNotFoundException exception) {
			System.err.println("Did not find a level of that name");
			System.exit(1);
		}
		while (in.hasNextLine()) {
			Tile[] levelColumn = null;
			String line = in.nextLine();
			levelColumn = processLevelColumn(line.toCharArray(), x);
			Level.add(levelColumn);
			x++;
		}
		return Level;
	}

	/**
	 * 
	 * @param tileList
	 * @param column
	 * @return
	 */
	private static Tile[] processLevelColumn(char[] tileList, int column) {
		// TODO add tile objects as you create them
		Tile[] levelColumn = { new Air(column, 1), new Air(column, 2), new Air(column, 3), new Air(column, 4),
				new Air(column, 5) }; // Once air tile is created will just be initialized as five of those
		for (int i = 0; i < tileList.length; i++) {
			Tile currTile = null;
			switch (tileList[i]) {
			case 'F':
				currTile = new Flame(column*100, i*100);
				break;
			case 'W':
				currTile = new Water(column*100, i*100);
				break;
			case 'C':
				currTile = new Cactus(column*100, i*100);
				break;
			case 'G':
				currTile = new Ground(column*100, i*100);
				break;
			case ' ':
			default:
				currTile = new Air(column*100, i*100);
				break;
			}
			levelColumn[i] = currTile;
		}
		return levelColumn;
	}
}