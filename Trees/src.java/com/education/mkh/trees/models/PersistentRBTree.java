package com.education.mkh.trees.models;

import java.util.ArrayList;
import java.util.List;

public class PersistentRBTree<T extends Comparable<T>>extends RBTree<T>{
	private ArrayList<RBTreeNode<T>> roots;
	
	public PersistentRBTree() {
		super();
		this.roots = new ArrayList<>();
		roots.add(leaf);
	}
	
	public boolean taskWithTree(T key, TREES_TASKS task) {
		
		switch (task) {
		case INSERT:
			if (this.search(key)) {
				return false;
			}
			if (this.root!=this.roots.get(this.roots.size()-1)) {
				deleteOldRootsAndCorrectionNodesParent();
			}
			if (!super.insert(key)) {
				return false;
			}
			break;
		case DELETE:
			if (!this.search(key)) {
				return false;
			}
			if (this.root!=this.roots.get(this.roots.size()-1)) {
				deleteOldRootsAndCorrectionNodesParent();
			}
			if (!super.delete(key)) {
				return false;
			}
			break;
		case SEARCH:
			return search(key);
		default:
			break;
		}
		clearCopy(root);
		this.roots.add(root);
		return true;
			
	}
		
	public boolean moveRight()  {
		if (root==null) {
			return false;
		}
		int index = roots.indexOf(root);
		if (index < roots.size()-1) {
			this.root = roots.get(index + 1);
			return true;
		} else {
			return false;
		}
	}

	public boolean moveLeft()  {
		if (root==null) {
			return false;
		}
		int index = roots.indexOf(root);
		if (index > 0) {
			this.root = roots.get(index - 1);
			return true;
		} else {
			return false;
		}
	}
	
	private void clearCopy(BinaryTreeNode<T> current) {
		if (!current.isLeaf && current.isCloned) {
			current.isCloned=false;
			clearCopy(current.getLeft());
			clearCopy(current.getRight());
		}
	}

	private void deleteOldRootsAndCorrectionNodesParent() {
		int index=this.roots.indexOf(root);
	
		this.roots.subList(index+1, roots.size()).clear();;
		recursionCorrectionParent(root);
	}
	
	protected void recursionCorrectionParent(NodeWithTwoChilds<T> current) {
		if (!current.getLeft().isLeaf) {
			current.getLeft().setParent(current);
			recursionCorrectionParent(current.getLeft());
		}
		if (!current.getRight().isLeaf) {
			current.getRight().setParent(current);
			recursionCorrectionParent(current.getRight());
		}
	}

}
