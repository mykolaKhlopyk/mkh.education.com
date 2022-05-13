package com.education.mkh.trees.models;

public class TreeFactory<T extends Comparable<T>> {
	public TreeFunctionable<T> createTree(TREES_TYPE type) {
		TreeFunctionable<T> tree=null;
		switch (type) {
		case BINARY_TREE:
			tree=new BinaryTree<T>();
			break;
		case RB_TREE:
			tree=new RBTree<T>();
			break;
		case PERSISTENT_RB_TREE:
			tree=new PersistentRBTreeAdapter<T>(new PersistentRBTree());
			break;
		default:
			break;
		}
		return tree;
	}
}
