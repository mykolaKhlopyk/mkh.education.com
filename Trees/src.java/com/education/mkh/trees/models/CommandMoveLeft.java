package com.education.mkh.trees.models;

public class CommandMoveLeft<T> implements Command<T>{
	PersistentTreeFunctionable<T> tree;
	public CommandMoveLeft(PersistentTreeFunctionable<T> tree) {
		this.tree=tree;
	}
	
	@Override
	public void execute(T key) {
		tree.moveLeft();
	}
}