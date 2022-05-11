package com.education.mkh.trees.models;

public class CommandDelete<T>  implements Command<T>{
	TreeFunctionable<T> tree;
	public CommandDelete(TreeFunctionable<T> tree) {
		this.tree=tree;
	}
	@Override
	public void execute(T key) {
		tree.delete(key);
	}
	
}