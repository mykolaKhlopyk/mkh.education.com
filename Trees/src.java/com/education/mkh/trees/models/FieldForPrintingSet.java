package com.education.mkh.trees.models;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javafx.scene.canvas.Canvas;
import javafx.scene.control.TextField;

public class FieldForPrintingSet implements Observer {
	private TextField mainField;
	private TreeFunctionable tree;
	private volatile static FieldForPrintingSet instance;

	private FieldForPrintingSet() {
	}

	private FieldForPrintingSet(TextField mainField) {
		this.mainField = mainField;
	}

	public static FieldForPrintingSet getInstance() {
		if (instance == null) {
			synchronized (CanvasForTree.class) {
				if (instance == null) {
					instance = new FieldForPrintingSet();
				}
			}
		}
		return instance;
	}

	public void setTextField(TextField textField) {
		this.mainField = textField;

	}

	public void setTree(TreeFunctionable tree) {
		this.tree = tree;
	}

	@Override
	public void update(TreeFunctionable tree) {
		setTree(tree);
		if (tree == null) {
			mainField.setText("Set is empty");
			return;
		}
		String result = "";
		List list = tree.toList();
		if (list == null || list.isEmpty()) {
			result = "Set is empty";
		} else {
			result = list.toString();
		}

		mainField.setText(result);

		
	}

	void setEmpty() {
		this.mainField.setText("");
	}
}
