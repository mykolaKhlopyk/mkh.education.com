
package com.education.mkh.trees.models;

import java.util.ArrayList;
import java.util.Iterator;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class CanvasForTree implements Observer {
	final int nodeSize = 50;
	final int sizeBetweenNodesInY = 25;
	final int upperBorder = 25;
	final int fontSize = 20;
	double preScale=1;
	Color peach = Color.rgb(245, 145, 117);
	Canvas canvas;
	ArrayList<Double> scales=new ArrayList<Double>();
	TreeFunctionable tree;
	private volatile static CanvasForTree instance;

	private CanvasForTree() {
	}

	private CanvasForTree(Canvas canvas) {
		this.canvas = canvas;
	}

	public static CanvasForTree getInstance() {
		if (instance == null) {
			synchronized (CanvasForTree.class) {
				if (instance == null) {
					instance = new CanvasForTree();
				}
			}
		}
		return instance;
	}

	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
		double scale=1;
		for (int i = 0; i < 15; i++) {
			scale=((this.canvas.getWidth()*1.0) / Math.pow(2, i))/nodeSize;
			if (scale>1) {
				scales.add(1.0);
			}else {
			scales.add(scale);
			}
		}

	}

	public void setTree(TreeFunctionable tree) {
		this.tree = tree;
	}

	private void drawTreeVersion1() {
		GraphicsContext gc = this.canvas.getGraphicsContext2D();
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		
		int height = getMaxHeight(tree.getRoot(), 0)-1;
		System.out.println(this.canvas.getWidth()+"width");
		System.out.println(height+"height"+tree.getRoot().isLeaf);
		double newSize = (this.canvas.getWidth()*1.0 / Math.pow(2, height));
		double scale=newSize/nodeSize;
		paintNode(tree.getRoot(), gc, 0, 0, new Point(-1, -1));
	}

	private int getMaxHeight(NodeWithTwoChilds current, int height) {
		if (current.isLeaf) {
			return height;
		}
		return Math.max(getMaxHeight(current.getLeft(), height + 1), getMaxHeight(current.getRight(), height + 1));
	}


	private void paintNode(NodeWithTwoChilds current, GraphicsContext gc, int currentHeight, int posX,	Point posForParentStartOfBranch) {
		System.out.println(scales.toString());
		double widthPerNode = canvas.getWidth() / Math.pow(2, currentHeight);
		double xCenter = ((posX+0.5)*widthPerNode);
		double yCenter = (nodeSize+sizeBetweenNodesInY)*(currentHeight+0.5)+upperBorder;
		
		double xPos = xCenter-(nodeSize*scales.get(currentHeight)/2);
		double yPos = yCenter - nodeSize*scales.get(currentHeight)*0.5;
	
		
		if (posForParentStartOfBranch.x>=0) {
			gc.setFill(Color.DARKBLUE);
			gc.strokeLine(xCenter, yPos, posForParentStartOfBranch.x, posForParentStartOfBranch.y);
		}
		if (current.getColor()==TREE_COLOR.BLACK) {
			gc.setFill(Color.BLACK);
		}else if (current.getColor()==TREE_COLOR.RED) {
			gc.setFill(Color.RED);
		}else {
			gc.setFill(peach);
		}
	
		gc.fillOval(xPos, yPos, nodeSize*scales.get(currentHeight), nodeSize*scales.get(currentHeight));
		gc.setFill(Color.WHITE);
		gc.setFont(new Font("Times New Roman", fontSize*scales.get(currentHeight)));
		gc.fillText(current.getKey().toString(), xCenter-(current.getKey().toString().length()*5)*scales.get(currentHeight), yCenter+(0.3*fontSize*scales.get(currentHeight)));
		Point currentBottomPoint=new Point(xCenter, yPos+nodeSize*scales.get(currentHeight));
		if (!current.getLeft().isLeaf) {
			paintNode(current.getLeft(), gc, currentHeight+1, posX*2, currentBottomPoint);
		}
		if (!current.getRight().isLeaf) {
			paintNode(current.getRight(), gc, currentHeight+1, posX*2+1, currentBottomPoint);
		}
		
	}

	@Override
	public void update(TreeFunctionable tree) {
		setTree(tree);
		if (tree==null || tree.getRoot()==null | tree.getRoot().isLeaf) {
			GraphicsContext gc = this.canvas.getGraphicsContext2D();
			gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
			return;
		}
		drawTreeVersion1();
	}
}

class Point{
	double x;
	double y;
	
	Point(double x, double y){
		this.x=x;
		this.y=y;
	}
}