package com.education.mkh.trees.models;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class CommandInsert<T>  implements Command<T>{
	private TreeFunctionable tree;
	public CommandInsert(TreeFunctionable<T> tree) {
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
			
			if (!tree.insert(rn)) {
				alertError.setHeaderText("Tree has contained this value yet");
				alertError.showAndWait();
			}
			
		}catch (Exception e) {
			alertError.setHeaderText("Input is incorrect");
			alertError.showAndWait();
		}
	}
	
}
