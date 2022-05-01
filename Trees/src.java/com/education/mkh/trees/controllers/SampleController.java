package com.education.mkh.trees.controllers;
import java.awt.event.MouseEvent;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


public class SampleController {


    
    @FXML private Button buttonChooseBinaryTree;
    @FXML private Button buttonChoosePersistentRBTree;
    @FXML private Button buttonChooseRRBTree;
    @FXML private Button buttonChooseBinaryTree1;
    @FXML private Button buttonChoosePersistentRBTree1;
    @FXML private Button buttonChooseRRBTree1;
    @FXML private Button buttonChooseTree2;
    @FXML private Button buttonChooseTreeInStartMenu;
    @FXML private Button buttonMakeListOutOfTree;
    @FXML private Canvas canvas;
    @FXML private ImageView clickChooseTreeStartMenu;
    @FXML private ImageView clickDelete;
    @FXML private ImageView clickInsert;
    @FXML private ImageView clickLeft;
    @FXML private ImageView clickMakeListOutOfTrees;
    @FXML private ImageView clickRight;
    @FXML private ImageView clickSearch;
    @FXML private TextField fieldDelete;
    @FXML private TextField fieldForPrintingSet;
    @FXML private TextField fieldInsert;
    @FXML private TextField fieldSearch;
    @FXML private Pane menuChoosingInkTreeInTasksPane;

    @FXML private Pane menuChoosingTreesStartMenu;
    @FXML private Label menuTitle;
    @FXML private Pane menuWithTreeTasks;
    @FXML private Pane startedMenuForChosingTypeOfTree;
 
    @FXML
    void initialize() {
    	buttonChooseTreeInStartMenu.setOnAction(event->{
    		if (menuChoosingTreesStartMenu.isVisible())
    			menuChoosingTreesStartMenu.setVisible(false);
			else
				menuChoosingTreesStartMenu.setVisible(true);
    	});
    	buttonChooseTree2.setOnAction(event->{
    		if (menuChoosingInkTreeInTasksPane.isVisible())
    			menuChoosingInkTreeInTasksPane.setVisible(false);
			else
				menuChoosingInkTreeInTasksPane.setVisible(true);
    	});
    	buttonChooseBinaryTree.setOnAction(event->gotoTreesTasksMenu_Binary());
    	buttonChooseRRBTree.setOnAction(event->gotoTreesTasksMenu_RB());
    	buttonChoosePersistentRBTree.setOnAction(event->gotoTreesTasksMenu_Persistent());
    	buttonChooseBinaryTree1.setOnAction(event->gotoTreesTasksMenu_Binary());
    	buttonChooseRRBTree1.setOnAction(event->gotoTreesTasksMenu_RB());
    	buttonChoosePersistentRBTree1.setOnAction(event->gotoTreesTasksMenu_Persistent());
    }
    private void gotoTreesTasksMenu_Binary() {
    	menuTitle.setText("Бінарне д.");
    	menuWithTreeTasks.toFront();
    	menuChoosingInkTreeInTasksPane.setVisible(false);
    }
    private void gotoTreesTasksMenu_RB() {
    	menuTitle.setText("Червоно-чорне д.");
    	menuWithTreeTasks.toFront();
    	menuChoosingInkTreeInTasksPane.setVisible(false);
    }
    private void gotoTreesTasksMenu_Persistent() {
    	menuTitle.setText("Персистентне ч-ч д.");
    	menuWithTreeTasks.toFront();
    	menuChoosingInkTreeInTasksPane.setVisible(false);
    }
}
