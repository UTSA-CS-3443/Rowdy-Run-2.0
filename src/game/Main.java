package game;
	
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import game.model.Model;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	private static Stage stage = null;
	private static Scene mainMenu = null;
	private static Scene levelSelection = null;
	private static Scene game = null;
	private static Model model = null;
	private static ExecutorService application = null;
	private static final int x = 500, y = 500;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("view/MainMenuView.fxml"));
			//BorderPane root = new BorderPane(); //not needed when loading from fxml since the fxml already has the main pane
			mainMenu = new Scene(root,x,y);
			mainMenu.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			root = FXMLLoader.load(getClass().getResource("view/LevelSelectionView.fxml"));
			levelSelection = new Scene(root,x,y);
			levelSelection.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			root = FXMLLoader.load(getClass().getResource("view/GameView.fxml"));
			game = new Scene(root,x,y);
			
			model = new Model();
			
			primaryStage.setScene(mainMenu);
			primaryStage.setTitle("RowdyRun-2.0");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		this.stage = primaryStage;
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

	public static Model getModel() {
		return model;
	}

	public static void setModel(Model model) {
		Main.model = model;
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
