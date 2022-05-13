package com.education.mkh.trees.models;

import javafx.scene.canvas.Canvas;

public class CanvasForTree {
	Canvas canvas;
	TreeFunctionable tree;
	private volatile static CanvasForTree instance;
	private CanvasForTree() {}
	private CanvasForTree(Canvas canvas) {
		this.canvas=canvas;
	}
	public static CanvasForTree getInstance() {
		if (instance==null) {
			synchronized (CanvasForTree.class) {
				if (instance==null) {
					instance= new CanvasForTree();
				}
			}
		}
		return instance;
	}
	public void setCanvas(Canvas canvas) {
		this.canvas=canvas;
		
	}
	public void setTree(TreeFunctionable tree) {
		this.tree=tree;
	}
	
	
}
