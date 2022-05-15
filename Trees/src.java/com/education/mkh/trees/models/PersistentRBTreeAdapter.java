package com.education.mkh.trees.models;

import java.util.List;

public class PersistentRBTreeAdapter<T extends Comparable<T>> implements PersistentTreeFunctionable<T>{
	private PersistentRBTree<T> tree;
	public PersistentRBTreeAdapter(PersistentRBTree<T> tree) {
		this.tree=tree;
	}
	@Override
	public boolean insert(T key) {
		return tree.taskWithTree(key, TREES_TASKS.INSERT);
	}

	@Override
	public boolean delete(T key) {
		return tree.taskWithTree(key, TREES_TASKS.DELETE);
	}

	@Override
	public boolean search(T key) {
		return tree.taskWithTree(key, TREES_TASKS.SEARCH);
	}

	@Override
	public NodeWithTwoChilds<T> getLeaf() {
		return tree.getLeaf();
	}

	@Override
	public void setRoot(NodeWithTwoChilds<T> newRoot) {
		tree.setRoot(newRoot);
	}

	@Override
	public List toList() {
		tree.recursionCorrectionParent(tree.root);
		return tree.toList();
	}

	@Override
	public boolean moveRight() {
		return tree.moveRight();
	}

	@Override
	public boolean moveLeft() {
		return tree.moveLeft();
	}

	public void correctionParent() {
		tree.recursionCorrectionParent(tree.root);
	}
	@Override
	public NodeWithTwoChilds<T> getRoot() {
		return tree.root;
	}
}
