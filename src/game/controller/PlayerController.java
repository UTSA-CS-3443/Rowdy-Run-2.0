package game.controller;

import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import game.Main;
import game.model.Model;
import game.model.Rowdy;

/**
 * 
 * @author Michael Diep
 *
 */
public class PlayerController implements EventHandler<KeyEvent> {
	
	@FXML
	private Canvas canvas = null;
	@FXML
	private GridPane pauseMenu = null;
	private Model model = null;
	private Rowdy player = null;
	private boolean isPaused = false;
	private Timeline timeline = null;
	
	public PlayerController() {
		super();
		this.model = Main.getModel();
		this.player = this.model.getPlayer();
		this.canvas = this.model.getCanvas();
		this.timeline = this.model.getIndefiniteTimeline();
	}
	
	@Override
	public void handle(KeyEvent event) {
		System.out.println("KeyEvent!");
		this.timeline.pause();
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
	
	public void setControllable(Rowdy player) {
		this.player = player;
	}
}
