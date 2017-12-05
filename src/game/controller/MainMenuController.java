package game.controller;

import java.io.File;
import java.io.IOException;
import game.Main;
import game.model.Model;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 * Handles selection in the menus
 * 
 * @author Michael Diep
 *
 */
public class MainMenuController implements EventHandler<ActionEvent> {

	private Model model = null;
	private File lvlFile = null;
	private Boolean run;

	public MainMenuController() {

		super();
		this.model = Main.getModel();
		run = false;

	}

	@Override
	public void handle(ActionEvent click) {

		Button b = (Button) click.getSource();

		switch (b.getText()) {

		case "Run":
			Main.changeScene(Main.getLevelSelection());
			break;

		case "Level 1":
			lvlFile = new File("level1.txt");
			try {
				System.out.println("reading level1.txt");
				this.model.setCurrentLevel(this.model.readLevel(lvlFile));
				System.out.println("read-in successfull");
			} catch (IOException e) {
				System.err.println("failed to open level1.txt");
				e.printStackTrace();
				break;
			}
			run = true;
			break;

		case "Level 2":
			lvlFile = new File("level2.txt");
			try {
				System.out.println("reading level2.txt");
				this.model.setCurrentLevel(this.model.readLevel(lvlFile));
			} catch (IOException e) {
				System.err.println("failed to open level2.txt");
				e.printStackTrace();
				break;
			}
			run = true;
			break;

		case "Level 3":
			lvlFile = new File("level3.txt");
			try {
				System.out.println("reading level3.txt");
				this.model.setCurrentLevel(this.model.readLevel(lvlFile));
			} catch (IOException e) {
				System.err.println("failed to open level3.txt");
				e.printStackTrace();
				break;
			}
			run = true;
			break;

		case "Main Menu":
			Main.changeScene(Main.getMainMenu());
			break;

		case "Quit":
			System.exit(0);// exit game
		}

		if (run == true) {
			System.out.println("Starting game");
			Main.changeScene(Main.getGame());
			Main.getExecutorService().execute(this.model);

		}
	}
}
