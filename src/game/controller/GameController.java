package game.controller;

import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

import game.Main;
import game.model.Model;
import game.model.Rowdy;

/**
 * 
 * @author Michael Diep
 *
 */
public class GameController implements EventHandler<KeyEvent>, Initializable{
	
	@FXML private Canvas canvas;
	@FXML private GridPane pauseMenu;
	private Model model = null;
	private Rowdy player = null;
	private boolean isPaused = false;
	private Timeline timeline = null;
	
	public GameController() {
		super();
		this.model = Main.getModel();
		this.player = this.model.getPlayer();
		this.timeline = this.model.getIndefiniteTimeline();

	}
	
	@Override
	public void handle(KeyEvent event) {
		
		if (isPaused == false) {
			
			switch (event.getCode()) {
				case UP: // KeyCode.UP
					player.jump();
					break;
					
				case LEFT:
					player.moveLeft();
					break;
					
				case RIGHT:
					player.moveRight();
					break;
					
				case P:
					System.out.println("Paused game");
					this.timeline.pause();
					isPaused = true;
					pauseMenu.setVisible(true);
					break;
					
				default:
					break;
			}
		}
		else {
			switch (event.getCode()) {
					
				case P:
					System.out.println("Resumed Game");
					this.timeline.play();
					isPaused = false;
					pauseMenu.setVisible(false);
					break;
					
				default:
					break;
			}
		}		
	}
	
	public void handleKeyReleased(KeyEvent event) {
		switch (event.getCode()) {
			case LEFT:
			case RIGHT:
				player.stopHorizontalMotion();
				break;
		}
	}
	
	public void setControllable(Rowdy player) {
		this.player = player;
	}

	public Canvas getCanvas() {
		return canvas;
	}

	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.model.setCanvas(canvas);
		this.model.setGraphicsContext(this.canvas.getGraphicsContext2D());
		//this.pauseMenu.setVisible(true);
	}
	
}
