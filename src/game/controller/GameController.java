package game.controller;

import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

import game.Main;
import game.model.Model;
import game.model.Rowdy;

/**
 * The controller for the GameView.fxml
 * <p>
 * Handles player movement and actions taken in the pause menu.
 * 
 * @author Michael Diep
 *
 */
public class GameController implements EventHandler<KeyEvent>, Initializable {

	@FXML private Canvas canvas;
	@FXML private GridPane pauseMenu;
	private Model model = null;
	private Rowdy player = null;
	private boolean isPaused = false;
	private Timeline timeline = null;
	private Boolean selection = false;

	/**
	 * Initializes the model from Main and the player and timeline from model
	 */
	public GameController() {
		super();
		this.model = Main.getModel();
		this.player = this.model.getPlayer();
		this.timeline = this.model.getIndefiniteTimeline();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.model.setCanvas(canvas);
		this.model.setGraphicsContext(this.canvas.getGraphicsContext2D());
	}

	@Override
	public void handle(KeyEvent event) {

		if (isPaused == false) {

			switch (event.getCode()) {
			case SPACE:
			case W:
			case UP: // KeyCode.UP
				player.jump();
				break;
			case A:
			case LEFT:
				player.moveLeft();
				break;
			case D:
			case RIGHT:
				player.moveRight();
				break;

			case P:
				pause();
				break;

			default:
				break;
			}
		} else {
			switch (event.getCode()) {

			case P:
				resume();
				break;
			case W:
			case S:
			case UP: // KeyCode.UP
			case DOWN:
				this.selection = !this.selection;
				break;
			case SPACE:
			case ENTER:
				if (!this.selection) {
					resume();
					this.selection = false;
				} else {
					quit();
				}
				break;
			default:
				break;
			}
		}
	}

	public void handleKeyReleased(KeyEvent event) {
		switch (event.getCode()) {
		case A:
		case D:
		case LEFT:
		case RIGHT:
			player.setVelocity(0, player.getYVelocity());
			break;
		}
	}
	
	public void handleMouseClicked(ActionEvent event) {
		System.out.println("mouse clicked");
		Button b = (Button) event.getSource();
		switch (b.getText()) {
		case "Continue":
			resume();
			break;
		case "Quit":
			quit();
			resume();
			break;
		default:
			break;
		}
	}
	
	/**
	 * pauses the timeline of the game and displays the pause menu
	 */
	public void pause() {
		System.out.println("Paused game");
		this.timeline.pause();
		isPaused = true;
		pauseMenu.setVisible(true);
	}
	
	/**
	 * plays the timeline of the game and hides the pause menu
	 */
	public void resume() {
		System.out.println("Resumed Game");
		this.timeline.play();
		isPaused = false;
		pauseMenu.setVisible(false);
	}
	
	/**
	 * returns the user to the main menu
	 */
	public void quit() {
		System.out.println("Quitting Game");
		Main.setModel(new Model());
		Main.changeScene(Main.getMainMenu());
	}

	/**
	 * allows for character swapping
	 * @param player
	 */
	public void setControllable(Rowdy player) {
		this.player = player;
	}

	public Canvas getCanvas() {
		return canvas;
	}

	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}

}
