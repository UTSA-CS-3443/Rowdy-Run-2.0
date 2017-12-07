package game.model;

import java.awt.Point;
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
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.event.ActionEvent;

import tiles.*;
import game.Main;
import game.controller.GameController;
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

	protected Level currentLevel;
	protected Rowdy player = new Rowdy();
	private int playerState;
	// private Timer timer;
	// private TimerListener timerListener;

	private Timeline indefiniteTimeline;
	private KeyFrame kf;
	private EventHandler<ActionEvent> timelineHandler;

	// Canvas is initialized by the GameView.FXML
	private Canvas canvas = null;
	private GraphicsContext gc = null;

	public Model() {

		indefiniteTimeline = new Timeline();
		indefiniteTimeline.setCycleCount(Timeline.INDEFINITE);

		timelineHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				gameTick();
			}
		};

		// kf = new KeyFrame(Duration.seconds(.0017), timelineHandler); //60 fps
		kf = new KeyFrame(Duration.seconds(.0066), timelineHandler); // 15 fps
		indefiniteTimeline.getKeyFrames().add(kf);

	}

	@Override
	public void run() {
		if (gc != null)
			indefiniteTimeline.play();
		else
			System.out.println(" ");

	}

	public void gameTick() {
		// TODO Fix this stuff, it ai'nt workin'
		int playerState;
		// player.moveRowdy();
		playerState = player.hitBoxChecker(currentLevel);
		player.fall();
		player.moveRowdy();
		// player.stop();
		if (playerState == 1) {
			// TODO handle rowdy dying
			System.err.println("You died");
			player.adjustRowdy(currentLevel.playerStart.x, currentLevel.playerStart.y);
		} else if (playerState == 2) {
			// TODO handle rowdy winning
			System.err.println("YOU WIN!");
			playerState = 0;
			player.setVelocity(0, 0);
			System.out.println("Ending Game");
			this.indefiniteTimeline.pause();
			//Main.setModel(new Model());
			Main.changeScene(Main.getMainMenu());
			//delete this model somehow
		}
		// player.fall();
		try {
			drawCanvas(gc);
		} catch (NullPointerException e) {
			System.out.println("No GraphicsContext present");
		}
	}

	public int getPlayerState() {
		return this.playerState;
	}
	
	public Level getCurrentLevel() {
		return currentLevel;
	}

	public void setCurrentLevel(Level currentLevel) {
		this.currentLevel = currentLevel;
		player.adjustRowdy(currentLevel.playerStart.x, currentLevel.playerStart.y);
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

	public GraphicsContext getGraphicsContext() {
		return gc;
	}

	public void setGraphicsContext(GraphicsContext gc) {
		this.gc = gc;
	}

	/**
	 * Reads in the txt file corresponding to the level and gives a nicely formatted
	 * level object
	 * 
	 * @param lvlFile
	 *            - text file that has the structure of the level
	 * @return Level - an object that has the format of the level in a easily read
	 *         and manipulatable format
	 * @throws IOException
	 */
	public Level readLevel(File lvlFile) throws IOException {

		ArrayList<Tile[]> Level = new ArrayList<Tile[]>();
		Point rowdyPosition = new Point(20, 40);
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
			levelColumn = processLevelColumn(line.toCharArray(), x, rowdyPosition);
			Level.add(levelColumn);
			x++;
		}
		Level finalLevel = new Level(Level, rowdyPosition);
		return finalLevel;
	}

	/**
	 * Helper method to readLevel which parses a column to be added to the arrayList
	 * of tile arrays
	 * 
	 * @param tileList
	 * @param column
	 * @return
	 */
	private Tile[] processLevelColumn(char[] tileList, int column, Point start) {
		// TODO add tile objects as you create them
		// System.out.println("generating air in column " + column);
		Tile[] levelColumn = new Tile[200];// Once air tile is created will just be initialized as twenty-five of those
		for (int i = 0; i < levelColumn.length; i++)
			levelColumn[i] = new Air(column * Tile.WIDTH, Main.HEIGHT - i * Tile.HEIGHT);

		// System.out.println("generating level from tileList[" + column + "]");
		for (int i = 0; i < tileList.length; i++) {
			Tile currTile = null;
			switch (tileList[i]) {
			case 'F':
				currTile = new Flame(column * Tile.WIDTH, (i + 1) * Tile.HEIGHT);
				break;
			case 'W':
				currTile = new Water(column * Tile.WIDTH, (i + 1) * Tile.HEIGHT);
				break;
			case 'X':
				currTile = new Cactus(column * Tile.WIDTH, (i + 1) * Tile.HEIGHT);
				break;
			case 'G':
				currTile = new Ground(column * Tile.WIDTH, (i + 1) * Tile.HEIGHT);
				break;
			case 'P':
				currTile = new Platform(column * Tile.WIDTH, (i + 1) * Tile.HEIGHT);
				break;
			case 'C':
				currTile = new Coin(column * Tile.WIDTH, (i + 1) * Tile.HEIGHT);
				break;
			case 'R':
				start.setLocation(column * Tile.WIDTH, (i + 1) * Tile.HEIGHT);
			case ' ':
				currTile = new Air(column * Tile.WIDTH, (i + 1) * Tile.HEIGHT);
				break;
			default:
				break;
			}
			levelColumn[i] = currTile;
		}
		return levelColumn;
	}

	public void drawCanvas(GraphicsContext gc) {
		// gc.fillRect(100, 100, 100, 100);
		gc.clearRect(0, 0, 500, 500);
		// gc.translate(1, 0);
		for (int x = 0; x < currentLevel.WIDTH; x++) {
			for (int y = 0; y < currentLevel.HEIGHT; y++) {
				// System.out.println("drawing on canvas at " + temp[y].getPosition().getX() +
				// ", " + temp[y].getPosition().getY());
				Tile temp[] = currentLevel.getLayout().get(x);
				gc.drawImage(temp[y].getImg(), temp[y].getPosition().getX(), Main.HEIGHT - temp[y].getPosition().getY());
				//temp[y].drawTile(gc);
				
				// temp[y].getPosition().getY());
				if (currentLevel.accessType(x, y) == 'G') {
					gc.setFill(Color.BLACK);
					gc.fillRect(currentLevel.access(x, y).getPosition().getX(),
							Main.HEIGHT - currentLevel.access(x, y).getPosition().getY(), Tile.WIDTH, Tile.HEIGHT);
					// run this if you want severe lag
				} else if (currentLevel.accessType(x, y) == 'A') {
					gc.setFill(Color.AQUAMARINE);
					// gc.fillRect(currentLevel.access.getPosition().getX(),
					// currentLevel.access.getPosition().getY(), 10,
					// 10);

				} else if (currentLevel.accessType(x, y) == 'C') {
					gc.setFill(Color.ORANGE);
					gc.fillRect(currentLevel.access(x, y).getPosition().getX(),
							Main.HEIGHT - currentLevel.access(x, y).getPosition().getY(), Tile.WIDTH, Tile.HEIGHT);
					// run this if you want severe lag
				} else if (currentLevel.accessType(x, y) == 'P') {
					gc.setFill(Color.BROWN);
					gc.fillRect(currentLevel.access(x, y).getPosition().getX(),
							Main.HEIGHT - (currentLevel.access(x, y).getPosition().getY() - 4), Tile.WIDTH,
							Tile.HEIGHT - 8);
				}
			}
		}
		gc.setFill(Color.BLUE);
		gc.fillRect(this.player.getPosition().getX(), Main.HEIGHT - this.player.getPosition().getY(), Rowdy.WIDTH,
				Rowdy.HEIGHT);
		// gc.fillRect(this.player.getPosition().getX()+2, Main.HEIGHT -
		// (this.player.getPosition().getY() - Rowdy.HEIGHT), 3, 1);
		// gc.drawImage(this.player.getImg(), this.player.getPosition().getX(),
		// this.player.getPosition().getY());
	}

}
