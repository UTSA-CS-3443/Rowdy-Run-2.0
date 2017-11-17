package game.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;

public class PlayerController implements EventHandler<KeyEvent> {

	boolean isPaused = false;
	@Override
	public void handle(KeyEvent event) {
		if (isPaused == false) {
			switch (event.getCode()) {
				case UP: // KeyCode.UP
					// Rowdy.jump();
					break;
				case LEFT:
					// Rowdy.AccelerateLeft();
					break;
				case RIGHT:
					// Rowdy.AccelerateRight();
					break;
				case P:
					// Model.pause();
					// Rowdy.pause(); or something
					// display pause menu
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
					break;
				default:
					break;
			}
		}		
	}
}
