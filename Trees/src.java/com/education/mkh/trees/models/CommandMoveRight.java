package com.education.mkh.trees.models;

public class CommandMoveRight<T> implements Command<T>{
	PersistentTreeFunctionable<T> tree;
	public CommandMoveRight(PersistentTreeFunctionable<T> tree) {
		this.tree=tree;
	}
	
	@Override
	public void execute(T key) {
		tree.moveRight();
	}
}