package game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import tiles.Tile;

public class GameController {

	public static ArrayList<Tile[]> readLevel(File lvlFile) throws IOException {
		ArrayList<Tile[]> Level = new ArrayList<Tile[]>();
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
			levelColumn = processLevelColumn(line.toCharArray(), line.length());
			Level.add(levelColumn);
		}
		return Level;
	}

	private static Tile[] processLevelColumn(char[] tileList, int length) {
		// TODO add tile objects as you create them
		Tile[] levelColumn = new Tile[5]; // Once air tile is created will just be initialized as five of those
		for (int i = 0; i < length; i++) {
			Tile currTile = null;
			switch (tileList[i]) {
			case 'F':
				currTile = new Flame();
				break;
			case 'W':
				currTile = new Water();
				break;
			case 'C':
				currTile = new Cactus();
				break;
			case 'G':
				currTile = new Ground();
				break;
			case ' ':
			default:
				currTile = new Air();
				break;
			}
			levelColumn[i] = currTile;
		}
		return levelColumn;
	}
}
