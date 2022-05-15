package com.education.mkh.trees.models;

import java.util.Iterator;
import java.util.List;

public class BinaryTree <T extends Comparable<T>> implements TreeFunctionable<T>, Iterable<T>{
	BinaryTreeNode<T> root;
	BinaryTreeNode<T> leaf;
	
	public BinaryTree(){
		this.leaf=new BinaryTreeNode<T>(null, null);
		this.root=leaf;		
	}

	@Override
	public boolean insert(T key) {
	
		return true;
	}

	@Override
	public boolean delete(T key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean search(T key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public NodeWithTwoChilds<T> getLeaf() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setRoot(NodeWithTwoChilds<T> newRoot) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List toList() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NodeWithTwoChilds<T> getRoot() {
		return this.root;
	}
}
