package game.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import tiles.*;

/**
 * Reads in data from a level file (lvlFile) to generate a level consisting of 
 * Tile objects stored in an ArrayList.
 * 
 * @author Jared???
 *
 */
public class Model {
	
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
		Tile[] levelColumn = new Tile[5]; // Once air tile is created will just be initialized as five of those
		for (int i = 0; i < tileList.length; i++) {
			Tile currTile = null;
			switch (tileList[i]) {
			case 'F':
				currTile = new Flame(column,i);
				break;
			case 'W':
				currTile = new Water(column,i);
				break;
			case 'C':
				currTile = new Cactus(column,i);
				break;
			case 'G':
				currTile = new Ground(column,i);
				break;
			case ' ':
			default:
				currTile = new Air(column,i);
				break;
			}
			levelColumn[i] = currTile;
		}
		return levelColumn;
	}
}
