package com.education.mkh.trees.models;

public class CommandMoveRight<T> implements Command<T>{
	private PersistentTreeFunctionable<T> tree;
	public CommandMoveRight(PersistentTreeFunctionable<T> tree) {
		this.tree=tree;
	}
	
	@Override
	public void execute(String key) {
		tree.moveRight();
	}
}