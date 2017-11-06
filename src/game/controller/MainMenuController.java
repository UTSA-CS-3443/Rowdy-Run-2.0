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
public class MainMenuController implements EventHandler<ActionEvent> {
	Stage stage = null;
	
	public MainMenuController() {
		super();
		stage = Main.getStage();
	}

	@Override
	public void handle(ActionEvent buttonPress) {
		Button b = (Button)buttonPress.getSource();
		switch (b.getText()) {
			case "Run":
				Main.changeScene(Main.getLevelSelection());
				break;
			case "Main Menu":
				Main.changeScene(Main.getMainMenu());
				break;
			case "Quit":
				System.exit(0);//exit game
		}
	}
}
