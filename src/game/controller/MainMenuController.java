package game.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import game.Main;
import game.model.Model;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Handles selection in the menus
 * 
 * @author Michael Diep
 *
 */
public class MainMenuController implements EventHandler<ActionEvent> {
	private File lvlFile = null;

	public MainMenuController() {
		super();
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
					Main.getModel().readLevel(lvlFile);
				} catch (IOException e) {
					System.err.println("failed to open level1.txt");
					e.printStackTrace();
					break;
				}
				Main.getExecutorService().execute(Main.getModel());
				Main.changeScene(Main.getGame());
				break;
			case "Level 2":
				lvlFile = new File("level2.txt");
				try {
					System.out.println("reading level2.txt");
					Main.getModel().readLevel(lvlFile);
				} catch (IOException e) {
					System.err.println("failed to open level2.txt");
					e.printStackTrace();
					break;
				}
				Main.getExecutorService().execute(Main.getModel());
				Main.changeScene(Main.getGame());
				break;
			case "Level 3":
				lvlFile = new File("level3.txt");
				try {
					System.out.println("reading level3.txt");
					Main.getModel().readLevel(lvlFile);
				} catch (IOException e) {
					System.err.println("failed to open level3.txt");
					e.printStackTrace();
					break;
				}
				Main.getExecutorService().execute(Main.getModel());
				Main.changeScene(Main.getGame());
				break;
			case "Main Menu":
				Main.changeScene(Main.getMainMenu());
				break;
			case "Quit":
				System.exit(0);// exit game
		}
	}
}
