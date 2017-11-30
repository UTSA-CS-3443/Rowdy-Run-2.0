package game.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
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

	private Canvas canvas = null;
	private GraphicsContext gc = null;
	
	public Model() {
		indefiniteTimeline = new Timeline();
		indefiniteTimeline.setCycleCount(Timeline.INDEFINITE);
		
		canvas = new Canvas();
		gc = canvas.getGraphicsContext2D();

		timelineHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				gameTick();
			}
		};

		kf = new KeyFrame(Duration.seconds(.0017), timelineHandler);

		indefiniteTimeline.getKeyFrames().add(kf);
	}
	
	@Override
	public void run() {
		indefiniteTimeline.play();
	}

	public void gameTick() {
		//TODO Fix this stuff, it ai'nt workin'
		/**int playerState = 1;
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
		player.fall();**/
		drawCanvas(gc);
	}
	
	public ArrayList<Tile[]> getCurrentLevel() {
		return currentLevel;
	}

	public void setCurrentLevel(ArrayList<Tile[]> currentLevel) {
		this.currentLevel = currentLevel;
	}

	public Rowdy getPlayer() {
		return player;
	}

	public void setPlayer(Rowdy player) {
		this.player = player;
	}

	public Timeline getIndefiniteTimeline() {
		return indefiniteTimeline;
	}

	public void setIndefiniteTimeline(Timeline indefiniteTimeline) {
		this.indefiniteTimeline = indefiniteTimeline;
	}

	public Canvas getCanvas() {
		return canvas;
	}

	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}

	public ArrayList<Tile[]> readLevel(File lvlFile) throws IOException {
		
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
	private Tile[] processLevelColumn(char[] tileList, int column) {
		// TODO add tile objects as you create them
		System.out.println("generating air in column " + column);
		Tile[] levelColumn = { new Air(column, 1), new Air(column, 2), new Air(column, 3), new Air(column, 4),
				new Air(column, 5), new Air(column, 6), new Air(column, 7), new Air(column, 8), new Air(column, 9),
				new Air(column, 10), new Air(column, 11), new Air(column, 12), new Air(column, 13), new Air(column, 14),
				new Air(column, 15), new Air(column, 16), new Air(column, 17), new Air(column, 18), new Air(column, 19),
				new Air(column, 20), new Air(column, 21), new Air(column, 22), new Air(column, 23), new Air(column, 24),
				new Air(column, 25) }; // Once air tile is created will just be initialized as twenty-five of those
		System.out.println("generating level from tileList[" + column + "]");
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

	public void drawCanvas(GraphicsContext gc)
	{
		System.out.println("drawing on canvas");
		Tile[] temp = null;
		for(int x = 0; x < currentLevel.size();x++)
		{
			temp = this.currentLevel.get(x);
			for(int y = 0; y < 25; y++)
			{
				gc.drawImage(temp[y].getImg(), temp[y].getPosition().getX(), temp[y].getPosition().getY());
				//gc.fillRect(arg0, arg1, arg2, arg3);
			}
		}
		//gc.drawImage(this.player.getImg(), this.player.getPosition().getX(), this.player.getPosition().getY());
	}

}
