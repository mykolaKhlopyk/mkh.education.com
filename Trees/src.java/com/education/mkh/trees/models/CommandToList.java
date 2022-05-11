package com.education.mkh.trees.models;

public class CommandToList<T>  implements Command<T>{
	TreeFunctionable<T> tree;
	public CommandToList(TreeFunctionable<T> tree) {
		this.tree=tree;
	}
	
	@Override
	public void execute(T key) {
		tree.toList();
	}
}