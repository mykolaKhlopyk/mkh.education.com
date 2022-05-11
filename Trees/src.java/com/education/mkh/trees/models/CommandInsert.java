package com.education.mkh.trees.models;

public class CommandInsert<T>  implements Command<T>{
	TreeFunctionable<T> tree;
	public CommandInsert(TreeFunctionable<T> tree) {
		this.tree=tree;
	}
	@Override
	public void execute(T key) {
		tree.insert(key);
	}
	
}
