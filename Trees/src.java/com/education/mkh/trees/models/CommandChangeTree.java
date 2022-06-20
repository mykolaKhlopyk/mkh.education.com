package com.education.mkh.trees.models;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class CommandChangeTree<T> implements Command<T> {

	private TreeFunctionable tree;
	private ElementsForViewing elementsForViewing;
	private Label menuTitle;
	private Pane menuWithTreeTasks;
	private Pane menuChoosingInkTreeInTasksPane;
	private Button buttonRight;
	private Button buttonLeft;
	private CanvasForTree canvas;
	
	public CommandChangeTree(TreeFunctionable<T> tree, ElementsForViewing elementsForViewing, 
			Label menuTitle, Pane menuWithTreeTasks, Pane menuChoosingInkTreeInTasksPane,
			Button buttonLeft, Button buttonRight, CanvasForTree canvas) {
		this.tree = tree;
		this.elementsForViewing=elementsForViewing;
		this.menuTitle=menuTitle;
		this.menuWithTreeTasks=menuWithTreeTasks;
		this.menuChoosingInkTreeInTasksPane=menuChoosingInkTreeInTasksPane;
		this.buttonRight=buttonRight;
		this.buttonLeft=buttonLeft;
		this.canvas=canvas;
	}

	@Override
	public void execute(String key) {
		elementsForViewing.setTree(tree);
		if (tree instanceof BinaryTree) {
			menuTitle.setText("Бінарне д.");
			buttonRight.setVisible(false);
			buttonLeft.setVisible(false);
		}else if(tree instanceof PersistentRBTreeAdapter) {
			menuTitle.setText("Персистентне ч-ч д.");
			buttonRight.setVisible(true);
			buttonLeft.setVisible(true);
		}else if(tree instanceof RBTree) {
			menuTitle.setText("Червоно-чорне д.");
			buttonRight.setVisible(false);
			buttonLeft.setVisible(false);
		}
		menuWithTreeTasks.toFront();
		menuChoosingInkTreeInTasksPane.setVisible(false);
		canvas.update(tree);
		elementsForViewing.notifyObservers();

	}
}
