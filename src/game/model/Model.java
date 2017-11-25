package game.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.fxml.FXML;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.event.ActionEvent;

import tiles.*;
import game.controller.PlayerController;
import items.Coin;

/**
 * Reads in data from a level file (lvlFile) to generate a level consisting of
 * Tile objects stored in an ArrayList.
 * 
 * @author Jared Polwort
 * @author Michael Diep
 *
 */
public class Model implements Runnable {

	protected ArrayList<Tile[]> currentLevel;
	protected Rowdy player;
	// private Timer timer;
	// private TimerListener timerListener;

	private Timeline indefiniteTimeline;
	private KeyFrame kf;
	private EventHandler<ActionEvent> timelineHandler;

	private PlayerController pController;

	public Model() {

	}

	public void gameTick() {

		int playerState = 1;
		player.moveRowdy();
		playerState = player.hitBoxChecker(currentLevel);
		if (playerState == 1) {
			// TODO handle rowdy dying
			// wait, so rowdy starts the game dead?
			// nah thats a failsafe in case something goes horribly wrong with hitboxChecker
			// hitboxChecker normally sets it back to 0
		} else if (playerState == 2) {
			// TODO handle rowdy winning
		}
		player.fall();

	}

	@Override
	public void run() {
		pController = new PlayerController(player);

		indefiniteTimeline = new Timeline();
		indefiniteTimeline.setCycleCount(Timeline.INDEFINITE);

		timelineHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				gameTick();
			}
		};

		kf = new KeyFrame(Duration.seconds(.0017), timelineHandler);

		indefiniteTimeline.getKeyFrames().add(kf);
		indefiniteTimeline.play();

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
				new Air(column, 5), new Air(column, 6), new Air(column, 7), new Air(column, 8), new Air(column, 9),
				new Air(column, 10), new Air(column, 11), new Air(column, 12), new Air(column, 13), new Air(column, 14),
				new Air(column, 15), new Air(column, 16), new Air(column, 17), new Air(column, 18), new Air(column, 19),
				new Air(column, 20), new Air(column, 21), new Air(column, 22), new Air(column, 23), new Air(column, 24),
				new Air(column, 25) }; // Once air tile is created will just be initialized as twenty-five of those
		for (int i = 0; i < tileList.length; i++) {
			Tile currTile = null;
			switch (tileList[i]) {
			case 'F':
				currTile = new Flame(column * 100, i * 100);
				break;
			case 'W':
				currTile = new Water(column * 100, i * 100);
				break;
			case 'X':
				currTile = new Cactus(column * 100, i * 100);
				break;
			case 'G':
				currTile = new Ground(column * 100, i * 100);
				break;
			case 'C':
				currTile = new Coin(column * 100, i * 100);
				break;
			case ' ':
			default:
				currTile = new Air(column * 100, i * 100);
				break;
			}
			levelColumn[i] = currTile;
		}
		return levelColumn;
	}

}
