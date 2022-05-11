package com.education.mkh.trees.models;

public class CommandSearch <T>  implements Command<T>{
	TreeFunctionable<T> tree;
	public CommandSearch(TreeFunctionable<T> tree) {
		this.tree=tree;
	}
	@Override
	public void execute(T key) {
		tree.search(key);
	}
}
