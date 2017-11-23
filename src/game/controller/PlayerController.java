package game.controller;

//import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import game.model.Rowdy;
//import javafx.scene.input.KeyCode;

public class PlayerController implements EventHandler<KeyEvent> {
	Rowdy player;
	
	public PlayerController(Rowdy player) {
		super();
		this.player = player;
	}
	
	boolean isPaused = false;
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
					// Model.pause();
					// Rowdy.pause(); or something
					// display pause menu
					isPaused = true;
					break;
				default:
					break;
			}
		}
		else {
			switch (event.getCode()) {
				case UP:
					// navigate up in menu
					break;
				case DOWN:
					// navigate down in menu
					break;
				case P:
					// Model.resume();
					// Rowdy.resume() or something
					// hide pause menu
					isPaused = false;
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
