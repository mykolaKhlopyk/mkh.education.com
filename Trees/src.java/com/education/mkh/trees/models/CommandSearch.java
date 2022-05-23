package com.education.mkh.trees.models;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class CommandSearch <T>  implements Command<T>{
	private TreeFunctionable tree;
	public CommandSearch(TreeFunctionable<T> tree) {
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
			//int number = Integer.parseInt(key.trim());
			RationalNumber rn=new RationalNumber(key);
			
			if (!tree.search(rn)) {
				alertError.setHeaderText("Tree hasn't contained this value yet");
				alertError.showAndWait();
			}else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Searching");
				alert.setHeaderText("Contains");
			}
			
		}catch (Exception e) {
			alertError.setHeaderText("Input is incorrect");
			alertError.showAndWait();
		}
		
	}
}
