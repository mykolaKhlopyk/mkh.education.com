package com.education.mkh.trees.models;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.Test;

class RBTreeTest {

	@Test
	void testInsert1() {
		RBTree<Integer> tree=new RBTree<Integer>();
		for (int i = 0; i < 10; i++) {
			tree.insert(i);
		}
		
		assertTrue(tree.getRoot().getColor() == TREE_COLOR.BLACK);
		assertTrue(tree.getLeaf().getColor() == TREE_COLOR.BLACK);
		assertTrue(tree.checkSonsForColor(tree.getRoot()));
		assertTrue(tree.checkNumberOfBlackNodes(tree.root)!=-1);
	}
	@Test
	void testInsert2() {
		RBTree<Integer> tree=new RBTree<Integer>();
		for (int i = 0; i < 10000; i++) {
			tree.insert((int)Math.random()*10000);
		}
		
		assertTrue(tree.getRoot().getColor() == TREE_COLOR.BLACK);
		assertTrue(tree.getLeaf().getColor() == TREE_COLOR.BLACK);
		assertTrue(tree.checkSonsForColor(tree.getRoot()));
		assertTrue(tree.checkNumberOfBlackNodes(tree.root)!=-1);
	}
	@Test
	void testInsert3() {
		RBTree<Integer> tree=new RBTree<Integer>();
		for (int i = 0; i < 10000; i++) {
			tree.insert(10000-i);
		}
		
		assertTrue(tree.getRoot().getColor() == TREE_COLOR.BLACK);
		assertTrue(tree.getLeaf().getColor() == TREE_COLOR.BLACK);
		assertTrue(tree.checkSonsForColor(tree.getRoot()));
		assertTrue(tree.checkNumberOfBlackNodes(tree.root)!=-1);
	}
	@Test
	void testEmprtyTree() {
		RBTree<Integer> tree=new RBTree<Integer>();
				
		assertTrue(tree.getRoot().getColor() == TREE_COLOR.BLACK);
		assertTrue(tree.getLeaf().getColor() == TREE_COLOR.BLACK);
		assertTrue(tree.checkSonsForColor(tree.getRoot()));
		assertTrue(tree.checkNumberOfBlackNodes(tree.root)!=-1);
	}
	@Test
	void testDelete1() {
		RBTree<Integer> tree=new RBTree<Integer>();
		for (int i = 0; i < 10; i++) {
			tree.insert(i);
		}
		tree.delete(4);		
		assertTrue(tree.getRoot().getColor() == TREE_COLOR.BLACK);
		assertTrue(tree.getLeaf().getColor() == TREE_COLOR.BLACK);
		assertTrue(tree.checkSonsForColor(tree.getRoot()));
		assertTrue(tree.checkNumberOfBlackNodes(tree.root)!=-1);
	}
	@Test
	void testDelete2() {
		RBTree<Integer> tree=new RBTree<Integer>();
		for (int i = 0; i < 10; i++) {
			tree.insert(i);
		}
		tree.delete(4);		
		tree.delete(3);
		tree.delete(8);
		tree.delete(2);
		tree.delete(7);	
		assertTrue(tree.getRoot().getColor() == TREE_COLOR.BLACK);
		assertTrue(tree.getLeaf().getColor() == TREE_COLOR.BLACK);
		assertTrue(tree.checkSonsForColor(tree.getRoot()));
		assertTrue(tree.checkNumberOfBlackNodes(tree.root)!=-1);
	}

}
