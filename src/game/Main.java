package game;
	
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import game.controller.MainMenuController;
import game.model.Model;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;


public class Main extends Application {
	
	private static Stage stage = null;
	private static Scene mainMenu = null;
	private static Scene levelSelection = null;
	private static Scene game = null;
	private static ExecutorService application = null;
	public static final int WIDTH = 500, HEIGHT = 500;
	//private MainMenuController mc = null;
	private static Model model = null;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			model = new Model();
			Parent root = FXMLLoader.load(getClass().getResource("view/MainMenuView.fxml"));
			mainMenu = new Scene(root,WIDTH,HEIGHT);
			mainMenu.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			root = FXMLLoader.load(getClass().getResource("view/LevelSelectionView.fxml"));
			levelSelection = new Scene(root,WIDTH,HEIGHT);
			levelSelection.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			root = FXMLLoader.load(getClass().getResource("view/GameView.fxml"));
			game = new Scene(root,WIDTH,HEIGHT);
			game.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			primaryStage.setScene(mainMenu);
			primaryStage.setTitle("RowdyRun-2.0");
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		/**try {
		    Font customFont = Font.loadFont("mexcellent rg.ttf", 20);
		} catch (Exception e) {
		    e.printStackTrace();
		}**/
		Main.stage = primaryStage;
	}

	public static Model getModel() {
		return model;
	}

	public static void setModel(Model model) {
		Main.model = model;
	}

	public static void main(String[] args) {
		application = Executors.newCachedThreadPool();
		
		launch(args);
	}
	
	/**
	 * 
	 * @return the application's stage
	 */
	public static Stage getStage() {
		return stage;
	}
	
	public static void setStage(Stage stage) {
		Main.stage = stage;
	}

	public static ExecutorService getExecutorService() {
		return application;
	}
	
	public static void changeScene(Scene scene) {
		Main.stage.setScene(scene);
	}
	
	public static Scene getMainMenu() {
		return mainMenu;
	}

	public static void setMainMenu(Scene mainMenu) {
		Main.mainMenu = mainMenu;
	}

	public static Scene getLevelSelection() {
		return levelSelection;
	}

	public static void setLevelSelection(Scene levelSelection) {
		Main.levelSelection = levelSelection;
	}

	public static Scene getGame() {
		return game;
	}

	public static void setGame(Scene game) {
		Main.game = game;
	}
	
}
