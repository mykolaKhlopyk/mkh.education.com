package com.education.mkh.trees.models;

import java.util.Iterator;

public class RBTreeIterator<T extends Comparable<T>> implements Iterator<T> {
	NodeWithTwoChilds<T> current;

	RBTreeIterator(RBTreeNode<T> first) {
		this.current = first;
	}

	@Override
	public boolean hasNext() {
		return !(current == null) && !current.isLeaf;
	}

	@Override
	public T next() {
		T key = current.key;
		if (!current.right.isLeaf) {
			current = current.right.min_son();
			return key;
		}
		if (current == current.parent.left) {
			current = current.parent;
		} else {
			while (!current.isLeaf && current == current.parent.right) {
				current = current.parent;
			}
			current = current.parent;
		}
		return key;

	}
}
