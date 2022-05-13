package com.education.mkh.trees.models;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class CommandDelete<T>  implements Command<T>{
	TreeFunctionable tree;
	public CommandDelete(TreeFunctionable<T> tree) {
		this.tree=tree;
	}
	@Override
	public void execute(String key) {
		Alert alertError = new Alert(AlertType.ERROR);
		alertError.setTitle("Error alert");
		if (key.trim().equals("")) {	
			alertError.setHeaderText("Field is empty");
			alertError.showAndWait();
			return;
		}
		try {	
			int number = Integer.parseInt(key.trim());
			if (!tree.delete(number)) {
				alertError.setHeaderText("Tree hasn't contained this value yet");
				alertError.showAndWait();
			}
			
		}catch (Exception e) {
			alertError.setHeaderText("Input is incorrect");
			alertError.showAndWait();
		}
		
	}
	
}