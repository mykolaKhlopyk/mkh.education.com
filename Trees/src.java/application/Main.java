package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		
		try {
			Parent root;
			root = FXMLLoader.load(getClass().getResource("/fxml/Sample.fxml"));
		
	        primaryStage.setTitle("Trees");
	        primaryStage.setScene(new Scene(root, 300, 275));
	        primaryStage.show();
	       } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
