package com.education.mkh.trees.models;

import java.util.Iterator;

public abstract class NodeWithTwoChilds<T> implements PersistentClonable<T>, Iterator{
	protected T key;
	protected NodeWithTwoChilds<T> left;
	protected NodeWithTwoChilds<T> right;
	protected NodeWithTwoChilds<T> parent;
	protected boolean isCloned;
	protected boolean isLeaf=false;

	public NodeWithTwoChilds(T key, NodeWithTwoChilds<T> leaf) {
		this.key = key;
		left = right = parent = leaf;
	}

	public T getKey() {
		return key;
	}

	public NodeWithTwoChilds<T> getLeft() {
		return left;
	}

	public NodeWithTwoChilds<T> getRight() {
		return right;
	}

	public NodeWithTwoChilds<T> getParent() {
		return parent;
	}

	public void setLeft(NodeWithTwoChilds<T> newLeftNode) {
		this.left = newLeftNode;
	}

	public void setRight(NodeWithTwoChilds<T> newRightNode) {
		this.right = newRightNode;
	}

	public void setParent(NodeWithTwoChilds<T> newParentNode) {
		this.parent = newParentNode;
	};

	public abstract TREE_COLOR getColor();

	public void right_rotate(TreeFunctionable<T> tree) {
		NodeWithTwoChilds<T> y = this.left;
		if (tree instanceof PersistentRBTree) {
			y = this.left.persistentClone(tree);
		}
		this.left = y.right;
		if (y.right != tree.getLeaf()) {
			y.right.parent = this;
		}
		y.parent = this.parent;
		if (this.parent == null) {
			tree.setRoot(y);
		} else if (this == this.parent.right) {
			this.parent.right = y;
		} else {
			this.parent.left = y;
		}
		y.right = this;
		this.parent = y;
	}

	public void left_rotate(TreeFunctionable<T> tree) {
		NodeWithTwoChilds<T> y = this.right;
		if (tree instanceof PersistentRBTree) {
			y = this.right.persistentClone(tree);
		}
		this.right = y.left;
		if (y.left != tree.getLeaf()) {
			y.left.parent = this;
		}
		y.parent = this.parent;
		if (this.parent == null) {
			tree.setRoot(y);
		} else if (this == this.parent.left) {
			this.parent.left = y;
		} else {
			this.parent.right = y;
		}
		y.left = this;
		this.parent = y;
	}

	protected NodeWithTwoChilds<T> min_son() {
		NodeWithTwoChilds<T> current = this;
		while (!current.getLeft().isLeaf) {
			current = current.getLeft();
		}
		return current;
	}
	@Override
	public boolean hasNext() {
		if (this==null || this.isLeaf || this.right.isLeaf && (this.parent==null ||this.parent.isLeaf)) {
			return false;
		}
		return true;
	}
	@Override
	public Iterator next() {
		if (!this.right.isLeaf) {
			return this.right.min_son();
		}
		if (this.parent!=null && !this.parent.isLeaf) {
			return this.parent;
		}
		throw new IndexOutOfBoundsException();

	}

}

class BinaryTreeNode<T> extends NodeWithTwoChilds<T> {

	public BinaryTreeNode(T key, NodeWithTwoChilds<T> leaf) {
		super(key, leaf);
	}

	@Override
	public NodeWithTwoChilds<T> persistentClone(TreeFunctionable<T> tree) {
		//System.out.println("will made in future");
		return this;
	}

	@Override
	public TREE_COLOR getColor() {

		return TREE_COLOR.MISSING;
	}

}

class RBTreeNode<T> extends NodeWithTwoChilds<T> {
	protected TREE_COLOR color;
	
	public RBTreeNode() {
		this(null, null);
		this.color=TREE_COLOR.BLACK;
		this.isLeaf=true;
	}
	
	public RBTreeNode(T key, NodeWithTwoChilds<T> leaf, NodeWithTwoChilds<T> parent) {
		super(key, leaf);
		left = right = leaf;
		color = TREE_COLOR.RED;
		this.key = key;
		this.parent = parent;
		this.isLeaf=false;
	}

	public RBTreeNode(T key, NodeWithTwoChilds<T> leaf) {
		this(key, leaf, leaf);
	}

	public TREE_COLOR getColor() {
		return color;
	}

	public void setColorRed() {
		color = TREE_COLOR.RED;
	}

	public void setColorBlack() {
		color = TREE_COLOR.BLACK;
	}

	public NodeWithTwoChilds<T> getGrandparent() {
		try {
			return this.parent.parent;
		} catch (Exception e) {
			return null;
		}

	}

	@Override
	public NodeWithTwoChilds<T> persistentClone(TreeFunctionable<T> tree) {
		if (tree instanceof PersistentRBTree) {

			if (this == tree.getLeaf() || this.isCloned) {
				return this;
			}
			RBTreeNode<T> clone_node = new RBTreeNode<T>(this.key, tree.getLeaf());
			clone_node.isCloned = true;
			clone_node.setParent(this.parent);
			clone_node.setLeft(this.getLeft());
			clone_node.setRight(this.getRight());
			if (this == this.parent.getLeft()) {
				this.parent.setLeft(clone_node);
			} else {
				this.parent.setRight(clone_node);
			}
			if (this.getColor() == TREE_COLOR.BLACK) {
				clone_node.setColorBlack();
			}
			clone_node.correctionParent(tree);
			return clone_node;
		}else {
			return this;
		}
	}

	private void correctionParent(TreeFunctionable<T> tree) {
		if (this.left != null && this.left != tree.getLeaf()) {
			this.left.parent = this;
		}
		if (this.right != null && this.right != tree.getLeaf()) {
			this.right.parent = this;
		}
	}

	
}
