package game.controller;

import game.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Handles selection in the menus
 * @author Michael Diep
 *
 */
public class MenuController implements EventHandler<ActionEvent> {
	Stage stage = null;
	
	public MenuController() {
		super();
		stage = Main.getStage();
	}

	@Override
	public void handle(ActionEvent buttonPress) {
		Button b = (Button)buttonPress.getSource();
		if (b.getText().equals("Run")) {
			stage.setScene(Main.getLevelSelection());
		} 
		else {
			//exit game
		}
		
	}
}
