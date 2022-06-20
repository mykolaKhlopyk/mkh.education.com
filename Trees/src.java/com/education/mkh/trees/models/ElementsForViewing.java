package com.education.mkh.trees.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class ElementsForViewing implements Subject{
	LinkedList<Observer> observers;
	TreeFunctionable tree;
	public ElementsForViewing() {
		this.observers=new LinkedList<Observer>();
		tree=null;
	}
	
	@Override
	public void registerObserver(Observer observer) {
		observers.add(observer);
	}

	@Override
	public void removeObserver(Observer observer) {
		observers.remove(observer);
	}

	@Override
	public void notifyObservers() {
		Iterator<Observer> it=observers.iterator();
		while (it.hasNext()) {
			Observer observer = (Observer) it.next();
			//Runnable r=()->{
				observer.update(tree);
			//};
			//new Thread(r).start();
					
		}
	}

	public void setTree(TreeFunctionable tree) {
		this.tree=tree;
	}
}
