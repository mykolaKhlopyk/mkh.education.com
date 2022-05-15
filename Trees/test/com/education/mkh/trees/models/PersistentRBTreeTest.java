package com.education.mkh.trees.models;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.Test;

class PersistentRBTreeTest {

	@Test
	void testInsert1() {
		PersistentRBTree<Integer> tree=new PersistentRBTree<Integer>();
		
		for (int i = 0; i < 15; i++) {
			tree.taskWithTree(i, TREES_TASKS.INSERT);
		}
		tree.taskWithTree(tree.getRoot().key, TREES_TASKS.DELETE);
		tree.moveLeft();
		assertTrue(tree.getRoot().getColor() == TREE_COLOR.BLACK);
		assertTrue(tree.getLeaf().getColor() == TREE_COLOR.BLACK);
		assertTrue(tree.getLeaf().isLeaf);
		assertTrue(tree.checkSonsForColor(tree.getRoot()));
		assertTrue(tree.checkNumberOfBlackNodes(tree.root)!=-1);
	}
	@Test
	void test2() {
		PersistentRBTree<Integer> tree=new PersistentRBTree<Integer>();
		
		for (int i = 0; i < 15; i++) {
			tree.taskWithTree(i, TREES_TASKS.INSERT);
		}
		tree.taskWithTree(tree.getRoot().key, TREES_TASKS.DELETE);
		assertTrue(tree.getRoot().getColor() == TREE_COLOR.BLACK);
		assertTrue(tree.getLeaf().getColor() == TREE_COLOR.BLACK);
		assertTrue(tree.getLeaf().isLeaf);
		assertTrue(tree.checkSonsForColor(tree.getRoot()));
		assertTrue(tree.checkNumberOfBlackNodes(tree.root)!=-1);
	}
}
