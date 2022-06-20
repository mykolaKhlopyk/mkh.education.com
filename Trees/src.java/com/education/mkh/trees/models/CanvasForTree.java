
package com.education.mkh.trees.models;

import java.util.ArrayList;
import java.util.Iterator;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class CanvasForTree implements Observer {
	private final int NODE_SIZE = 50;
	private final int SIZE_BETWEEN_NODES_IN_Y = 25;
	private final int UPPER_BORDER = 25;
	private final int FONT_SIZE = 20;
	// private double preScale=1;
	private final Color PEACH = Color.rgb(245, 145, 117);
	private Canvas canvas;
	private ArrayList<Double> scales = new ArrayList<Double>();
	private TreeFunctionable tree;
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
		double scale = 1;
		for (int i = 0; i < 15; i++) {
			scale = ((this.canvas.getWidth() * 1.0) / Math.pow(2, i)) / NODE_SIZE;
			if (scale > 1) {
				scales.add(1.0);
			} else {
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

		int height = getMaxHeight(tree.getRoot(), 0) - 1;
		// System.out.println(this.canvas.getWidth()+"width");
		// System.out.println(height+"height"+tree.getRoot().isLeaf);
		double newSize = (this.canvas.getWidth() * 1.0 / Math.pow(2, height));
		double scale = newSize / NODE_SIZE;
		paintByRecursion(tree.getRoot(), gc, 0, 0, new Point(-1, -1));
	}

	private void drawTreeVersion2() {
		GraphicsContext gc = this.canvas.getGraphicsContext2D();
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

		int height = getMaxHeight(tree.getRoot(), 0) - 1;
		// System.out.println(this.canvas.getWidth()+"width");
		// System.out.println(height+"height"+tree.getRoot().isLeaf);
		double newSize = (this.canvas.getWidth() * 1.0 / Math.pow(2, height));
		double scale = newSize / NODE_SIZE;

		if (!tree.getRoot().isLeaf && !tree.getRoot().getLeft().isLeaf && !tree.getRoot().getRight().isLeaf) {
			Point rootNodePoint = paintNode(tree.getRoot(), gc, 0, 0, new Point(-1, -1));
			Point leftPoint = paintNode(tree.getRoot().getLeft(), gc, 1, 0, rootNodePoint);
			Point rightPoint = paintNode(tree.getRoot().getRight(), gc, 1, 1, rootNodePoint);
			Runnable r0 = () -> {
				if (!tree.getRoot().getLeft().getLeft().isLeaf) {
					paintByRecursion(tree.getRoot().getLeft().getLeft(), gc, 2, 0, leftPoint);
				}
			};
			Runnable r1 = () -> {
				if (!tree.getRoot().getLeft().getRight().isLeaf) {
					paintByRecursion(tree.getRoot().getLeft().getRight(), gc, 2, 1, leftPoint);
				}
			};
			Runnable r2 = () -> {
				if (!tree.getRoot().getRight().getLeft().isLeaf) {
					paintByRecursion(tree.getRoot().getRight().getLeft(), gc, 2, 2, rightPoint);
				}
			};
			Runnable r3 = () -> {
				if (!tree.getRoot().getRight().getRight().isLeaf) {
					paintByRecursion(tree.getRoot().getRight().getRight(), gc, 2, 3, rightPoint);
				}
			};

			new Thread(r0).start();
			new Thread(r1).start();
			new Thread(r2).start();
			new Thread(r3).start();
		} else {
			drawTreeVersion1();
		}
		// paintNode(tree.getRoot(), gc, 0, 0, new Point(-1, -1));

	}

	private int getMaxHeight(NodeWithTwoChilds current, int height) {
		if (current.isLeaf) {
			return height;
		}
		return Math.max(getMaxHeight(current.getLeft(), height + 1), getMaxHeight(current.getRight(), height + 1));
	}

	private void paintByRecursion(NodeWithTwoChilds current, GraphicsContext gc, int currentHeight, int posX,
			Point posForParentStartOfBranch) {
		Point currentBottomPoint = paintNode(current, gc, currentHeight, posX, posForParentStartOfBranch);
		if (!current.getLeft().isLeaf) {
			paintByRecursion(current.getLeft(), gc, currentHeight + 1, posX * 2, currentBottomPoint);
		}
		if (!current.getRight().isLeaf) {
			paintByRecursion(current.getRight(), gc, currentHeight + 1, posX * 2 + 1, currentBottomPoint);
		}
	}

	private Point paintNode(NodeWithTwoChilds current, GraphicsContext gc, int currentHeight, int posX,
			Point posForParentStartOfBranch) {
		// System.out.println(scales.toString());
		if (current.isLeaf) {
			return new Point(-1, -1);
		}
		double widthPerNode = canvas.getWidth() / Math.pow(2, currentHeight);
		double xCenter = ((posX + 0.5) * widthPerNode);
		double yCenter = (NODE_SIZE + SIZE_BETWEEN_NODES_IN_Y) * (currentHeight + 0.5) + UPPER_BORDER;

		double xPos = xCenter - (NODE_SIZE * scales.get(currentHeight) / 2);
		double yPos = yCenter - NODE_SIZE * scales.get(currentHeight) * 0.5;

		synchronized (gc) {

			if (posForParentStartOfBranch.x >= 0) {
				gc.setFill(Color.DARKBLUE);
				gc.strokeLine(xCenter, yPos, posForParentStartOfBranch.x, posForParentStartOfBranch.y);
			}
			if (current.getColor() == TREE_COLOR.BLACK) {
				gc.setFill(Color.BLACK);
			} else if (current.getColor() == TREE_COLOR.RED) {
				gc.setFill(Color.RED);
			} else {
				gc.setFill(PEACH);
			}

			gc.fillOval(xPos, yPos, NODE_SIZE * scales.get(currentHeight), NODE_SIZE * scales.get(currentHeight));
			gc.setFill(Color.WHITE);
			gc.setFont(new Font("Times New Roman", FONT_SIZE * scales.get(currentHeight)));
			gc.fillText(current.getKey().toString(),
					xCenter - (current.getKey().toString().length() * 5) * scales.get(currentHeight),
					yCenter + (0.3 * FONT_SIZE * scales.get(currentHeight)));
		}
		return new Point(xCenter, yPos + NODE_SIZE * scales.get(currentHeight));

	}

	@Override
	public void update(TreeFunctionable tree) {
		setTree(tree);
		if (tree == null || tree.getRoot() == null | tree.getRoot().isLeaf) {
			GraphicsContext gc = this.canvas.getGraphicsContext2D();
			gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
			return;
		}
		drawTreeVersion2();
	}
}

class Point {
	double x;
	double y;

	Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
}

class ThreadForPrintingNodes implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

}