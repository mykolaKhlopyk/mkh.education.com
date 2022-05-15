package com.education.mkh.trees.models;

import java.util.List;

public interface TreeFunctionable<T> {
	
	public boolean insert(T key);
	public boolean delete(T key);
	public boolean search(T key);
	public NodeWithTwoChilds<T> getLeaf();
	public NodeWithTwoChilds<T> getRoot();
	public void setRoot(NodeWithTwoChilds<T> newRoot);
	public List toList();
}
