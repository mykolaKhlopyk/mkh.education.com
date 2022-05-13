package com.education.mkh.trees.controllers;

import java.awt.event.MouseEvent;
import java.util.List;

import com.education.mkh.trees.models.CanvasForTree;
import com.education.mkh.trees.models.Command;
import com.education.mkh.trees.models.CommandDelete;
import com.education.mkh.trees.models.CommandInsert;
import com.education.mkh.trees.models.CommandMoveLeft;
import com.education.mkh.trees.models.CommandMoveRight;
import com.education.mkh.trees.models.CommandSearch;
import com.education.mkh.trees.models.PersistentRBTree;
import com.education.mkh.trees.models.PersistentRBTreeAdapter;
import com.education.mkh.trees.models.RBTree;
import com.education.mkh.trees.models.TREES_TYPE;
import com.education.mkh.trees.models.TreeFactory;
import com.education.mkh.trees.models.TreeFunctionable;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class SampleController {

	@FXML
	private Button buttonChooseBinaryTree;
	@FXML
	private Button buttonChoosePersistentRBTree;
	@FXML
	private Button buttonChooseRRBTree;
	@FXML
	private Button buttonChooseBinaryTree1;
	@FXML
	private Button buttonChoosePersistentRBTree1;
	@FXML
	private Button buttonChooseRRBTree1;
	@FXML
	private Button buttonChooseTree2;
	@FXML
	private Button buttonChooseTreeInStartMenu;
	@FXML
	private Button buttonMakeListOutOfTree;
	@FXML
	private Canvas canvas;
	@FXML
	private ImageView clickChooseTreeStartMenu;
	@FXML
	private Button buttonDelete;
	@FXML
	private Button buttonInsert;
	@FXML
	private Button clickMakeListOutOfTrees;
	@FXML
	Button buttonLeft;
	@FXML
	Button buttonRight;
	@FXML
	private Button buttonSearch;
	@FXML
	private TextField fieldDelete;
	@FXML
	public TextField fieldForPrintingSet;
	@FXML
	private TextField fieldInsert;
	@FXML
	private TextField fieldSearch;
	@FXML
	private Pane menuChoosingInkTreeInTasksPane;
	@FXML
	private Pane menuChoosingTreesStartMenu;
	@FXML
	private Label menuTitle;
	@FXML
	private Pane menuWithTreeTasks;
	@FXML
	private Pane startedMenuForChosingTypeOfTree;

	TreeFunctionable<Integer> tree = null;
	TreeFactory<Integer> factory = new TreeFactory<Integer>();
	Command command=null;
	Alert alertError = new Alert(AlertType.ERROR);

	@FXML
	void initialize() {

		CanvasForTree treeCanvas = CanvasForTree.getInstance();
		treeCanvas.setCanvas(canvas);

		buttonChooseTreeInStartMenu.setOnAction(event -> {
			if (menuChoosingTreesStartMenu.isVisible())
				menuChoosingTreesStartMenu.setVisible(false);
			else
				menuChoosingTreesStartMenu.setVisible(true);
		});
		buttonChooseTree2.setOnAction(event -> {
			if (menuChoosingInkTreeInTasksPane.isVisible())
				menuChoosingInkTreeInTasksPane.setVisible(false);
			else
				menuChoosingInkTreeInTasksPane.setVisible(true);
		});
		buttonChooseBinaryTree.setOnAction(event -> gotoTreesTasksMenu_Binary());
		buttonChooseRRBTree.setOnAction(event -> gotoTreesTasksMenu_RB());
		buttonChoosePersistentRBTree.setOnAction(event -> gotoTreesTasksMenu_Persistent());
		buttonChooseBinaryTree1.setOnAction(event -> gotoTreesTasksMenu_Binary());
		buttonChooseRRBTree1.setOnAction(event -> gotoTreesTasksMenu_RB());
		buttonChoosePersistentRBTree1.setOnAction(event -> gotoTreesTasksMenu_Persistent());

		buttonInsert.setOnAction(event -> {
			command=new CommandInsert(tree);
			command.execute(fieldInsert.getText());
			fieldInsert.clear();
		});
		fieldInsert.setOnAction(event -> {
			command=new CommandInsert(tree);
			command.execute(fieldInsert.getText());
			fieldInsert.clear();
		});
		
		buttonDelete.setOnAction(event -> {
			command=new CommandDelete(tree);
			command.execute(fieldDelete.getText());
			fieldDelete.clear();
		});
		fieldDelete.setOnAction(event1 -> {
			command=new CommandDelete(tree);
			command.execute(fieldDelete.getText());
			fieldDelete.clear();
		});
		
		buttonSearch.setOnAction(event -> {
			command=new CommandSearch(tree);
			command.execute(fieldSearch.getText());
			fieldSearch.clear();
		});
		
		fieldSearch.setOnAction(event -> {
			command=new CommandSearch(tree);
			command.execute(fieldSearch.getText());
			fieldSearch.clear();
		});
		
		buttonMakeListOutOfTree.setOnAction(event ->{
			if (tree instanceof PersistentRBTreeAdapter) {
				((PersistentRBTreeAdapter) tree).correctionParent();
			}
			List list = tree.toList();
			this.fieldForPrintingSet.setText(list.toString());
		});
		
		buttonRight.setOnAction(event->{
			PersistentRBTreeAdapter treePer=(PersistentRBTreeAdapter)tree;
			command=new CommandMoveRight(treePer);
			command.execute(null);
		});
		buttonLeft.setOnAction(event->{
			PersistentRBTreeAdapter treePer=(PersistentRBTreeAdapter)tree;
			command=new CommandMoveLeft(treePer);
			command.execute(null);
		});
	}

	private void gotoTreesTasksMenu_Binary() {
		tree = factory.createTree(TREES_TYPE.BINARY_TREE);
		menuTitle.setText("Бінарне д.");
		menuWithTreeTasks.toFront();
		menuChoosingInkTreeInTasksPane.setVisible(false);
		buttonRight.setVisible(false);		
		buttonLeft.setVisible(false);
		
	}

	private void gotoTreesTasksMenu_RB() {
		tree = factory.createTree(TREES_TYPE.RB_TREE);
		menuTitle.setText("Червоно-чорне д.");
		menuWithTreeTasks.toFront();
		menuChoosingInkTreeInTasksPane.setVisible(false);
		buttonRight.setVisible(false);		
		buttonLeft.setVisible(false);
	}

	private void gotoTreesTasksMenu_Persistent() {
		tree = factory.createTree(TREES_TYPE.PERSISTENT_RB_TREE);
		menuTitle.setText("Персистентне ч-ч д.");
		menuWithTreeTasks.toFront();
		menuChoosingInkTreeInTasksPane.setVisible(false);
		buttonRight.setVisible(true);		
		buttonLeft.setVisible(true);
		
	}

	
}
