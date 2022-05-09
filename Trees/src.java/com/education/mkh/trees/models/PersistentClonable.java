package com.education.mkh.trees.models;

public interface PersistentClonable<T> {
	public NodeWithTwoChilds<T> persistentClone(TreeFunctionable<T> tree);
}
