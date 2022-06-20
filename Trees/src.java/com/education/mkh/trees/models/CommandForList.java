package com.education.mkh.trees.models;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

public class CommandForList<T> implements Command<T> {
	private TreeFunctionable tree;
	private Button but;
	private ElementsForViewing elem;

	public CommandForList(TreeFunctionable<T> tree, Button but, ElementsForViewing elem) {
		this.tree = tree;
		this.but = but;
		this.elem = elem;
	}

	@Override
	public void execute(String key) {
		if (but.getText().equals("У список")) {
			if (tree instanceof PersistentRBTreeAdapter) {
				((PersistentRBTreeAdapter) tree).correctionParent();
			}
			List list = tree.toList();
			elem.registerObserver(FieldForPrintingSet.getInstance());
			elem.notifyObservers();
			
			but.setText("Без списку");
		} else {
			
			

			elem.removeObserver(FieldForPrintingSet.getInstance());
			FieldForPrintingSet.getInstance().setEmpty();
			but.setText("У список");
		}
	}

}
