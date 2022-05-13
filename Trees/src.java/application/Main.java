package application;
	
import java.io.IOException;
import java.util.Iterator;

import com.education.mkh.trees.models.NodeWithTwoChilds;
import com.education.mkh.trees.models.PersistentRBTree;
import com.education.mkh.trees.models.RBTree;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws IOException {

			Parent root = FXMLLoader.load(getClass().getResource("/fxml/Sample.fxml"));
	        primaryStage.setTitle("Trees");
	        primaryStage.setScene(new Scene(root, 1550, 790));
	        primaryStage.setMaximized(true);
	        primaryStage.show();
	              
	}
	
	public static void main(String[] args) {
		
		launch(args);	
	}
}
