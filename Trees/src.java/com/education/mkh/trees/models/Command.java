package com.education.mkh.trees.models;

public interface Command<T> {
	public void execute(T key);
}
