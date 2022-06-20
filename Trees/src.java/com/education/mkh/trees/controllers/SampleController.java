package com.education.mkh.trees.controllers;

import java.awt.event.MouseEvent;
import java.util.List;

import com.education.mkh.trees.models.CanvasForTree;
import com.education.mkh.trees.models.Command;
import com.education.mkh.trees.models.CommandChangeTree;
import com.education.mkh.trees.models.CommandDelete;
import com.education.mkh.trees.models.CommandForList;
import com.education.mkh.trees.models.CommandInsert;
import com.education.mkh.trees.models.CommandMoveLeft;
import com.education.mkh.trees.models.CommandMoveRight;
import com.education.mkh.trees.models.CommandSearch;
import com.education.mkh.trees.models.ElementsForViewing;
import com.education.mkh.trees.models.FieldForPrintingSet;
import com.education.mkh.trees.models.Observer;
import com.education.mkh.trees.models.PersistentRBTree;
import com.education.mkh.trees.models.PersistentRBTreeAdapter;
import com.education.mkh.trees.models.RBTree;
import com.education.mkh.trees.models.RationalNumber;
import com.education.mkh.trees.models.TREES_TYPE;
import com.education.mkh.trees.models.TreeFactory;
import com.education.mkh.trees.models.TreeFunctionable;
import com.education.mkh.trees.models.WritingInFile;

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

	private TreeFunctionable<RationalNumber> tree = null;
	private TreeFactory<RationalNumber> factory = new TreeFactory<RationalNumber>();
	private Command command = null;
	private Alert alertError = new Alert(AlertType.ERROR);
	private ElementsForViewing elementsForViewing = new ElementsForViewing();
	private WritingInFile writingInFile= new WritingInFile();
	
	@FXML
	void initialize() {

		CanvasForTree.getInstance().setCanvas(canvas);
		FieldForPrintingSet.getInstance().setTextField(fieldForPrintingSet);
		elementsForViewing.registerObserver(CanvasForTree.getInstance());
		elementsForViewing.registerObserver(writingInFile);
		//elementsForViewing.registerObserver(FieldForPrintingSet.getInstance());

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
			command = new CommandInsert(tree);
			command.execute(fieldInsert.getText());
			fieldInsert.clear();
			elementsForViewing.notifyObservers();
		});
		fieldInsert.setOnAction(event -> {
			command = new CommandInsert(tree);
			command.execute(fieldInsert.getText());
			fieldInsert.clear();
			elementsForViewing.notifyObservers();
		});

		buttonDelete.setOnAction(event -> {
			command = new CommandDelete(tree);
			command.execute(fieldDelete.getText());
			fieldDelete.clear();
			elementsForViewing.notifyObservers();
		});
		fieldDelete.setOnAction(event1 -> {
			command = new CommandDelete(tree);
			command.execute(fieldDelete.getText());
			fieldDelete.clear();
			elementsForViewing.notifyObservers();
		});

		buttonSearch.setOnAction(event -> {
			command = new CommandSearch(tree);
			command.execute(fieldSearch.getText());
			fieldSearch.clear();
		});

		fieldSearch.setOnAction(event -> {
			command = new CommandSearch(tree);
			command.execute(fieldSearch.getText());
			fieldSearch.clear();
		});

		buttonMakeListOutOfTree.setOnAction(event -> {
			command = new CommandForList(tree, buttonMakeListOutOfTree, elementsForViewing);
			command.execute(fieldSearch.getText());
			//this.fieldForPrintingSet.setText(list.toString());
		});

		buttonRight.setOnAction(event -> {
			PersistentRBTreeAdapter treePer = (PersistentRBTreeAdapter) tree;
			command = new CommandMoveRight(treePer);
			command.execute(null);
			elementsForViewing.notifyObservers();
		});
		buttonLeft.setOnAction(event -> {
			PersistentRBTreeAdapter treePer = (PersistentRBTreeAdapter) tree;
			command = new CommandMoveLeft(treePer);
			command.execute(null);
			elementsForViewing.notifyObservers();
		});
	}

	private void gotoTreesTasksMenu_Binary() {
		tree = factory.createTree(TREES_TYPE.BINARY_TREE);
		command = new CommandChangeTree(tree, elementsForViewing, menuTitle, menuWithTreeTasks,
				menuChoosingInkTreeInTasksPane, buttonLeft, buttonRight, CanvasForTree.getInstance());
		command.execute(null);
		
	}

	private void gotoTreesTasksMenu_RB() {
		tree = factory.createTree(TREES_TYPE.RB_TREE);
		command = new CommandChangeTree(tree, elementsForViewing, menuTitle, menuWithTreeTasks,
				menuChoosingInkTreeInTasksPane, buttonLeft, buttonRight, CanvasForTree.getInstance());
		command.execute(null);
		
	}

	private void gotoTreesTasksMenu_Persistent() {
		tree = factory.createTree(TREES_TYPE.PERSISTENT_RB_TREE);
		command = new CommandChangeTree(tree, elementsForViewing, menuTitle, menuWithTreeTasks,
				menuChoosingInkTreeInTasksPane, buttonLeft, buttonRight, CanvasForTree.getInstance());
		command.execute(null);
		
	}

}
