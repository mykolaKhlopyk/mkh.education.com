package com.education.mkh.trees.models;

public class CommandMoveLeft<T> implements Command<T>{
	private PersistentTreeFunctionable<T> tree;
	public CommandMoveLeft(PersistentTreeFunctionable<T> tree) {
		this.tree=tree;
	}
	
	@Override
	public void execute(String key) {
		tree.moveLeft();
	}
}