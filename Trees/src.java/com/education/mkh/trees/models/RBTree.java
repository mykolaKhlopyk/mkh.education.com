package com.education.mkh.trees.models;

import java.util.List;

public class RBTree<T extends Comparable<T>> implements TreeFunctionable<T>{
	protected RBTreeNode<T> root;
	protected RBTreeNode<T> leaf;
	
	public RBTree(){
		
		this.leaf=new RBTreeNode<T>();
		this.root=leaf;
		leaf.setColorBlack();
	}
	
	public RBTreeNode<T> getRoot() {
		return this.root;
	}

	public NodeWithTwoChilds<T> getLeaf() {
		return this.leaf;
	}
	
	@Override
	public boolean insert(T key) {
		if (this.root == leaf) {
			this.root = new  RBTreeNode<T>(key, this.leaf, this.leaf);
			this.root.setColorBlack();
			return true;
		}
		if (this.search(key)) {
			return false;
		}
		this.root = (RBTreeNode<T>)this.root.persistentClone(this);
		RBTreeNode<T> current = this.root;
		while (current != this.leaf) {
			if (current.getKey().compareTo(key) < 0) {
				if (current.getRight() != this.leaf) {
					current = (RBTreeNode<T>)current.getRight();
					current = (RBTreeNode<T>)current.persistentClone(this);			
					continue;
				}
				current.setRight(new RBTreeNode<T>(key, this.leaf, current));
				fixTree(current.getRight());
				break;
			} else {
				if (current.getLeft() != this.leaf) {
					current = (RBTreeNode<T>)current.getLeft();
					current = (RBTreeNode<T>)current.persistentClone(this);
					continue;
				}
				current.setLeft(new RBTreeNode<T>(key, this.leaf, current));
				fixTree(current.getLeft());
				break;
			}
		}
		clearCopy(this.root);
		//System.out.println(this.leaf.getColor());
	
	
		return true;
	}

	@Override
	public boolean delete(T key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean search(T key) {
		NodeWithTwoChilds<T> current = this.root;
		while (current != this.leaf) {
			if (current.getKey().compareTo(key) == 0) {
				return true;
			} else if (current.getKey().compareTo(key) > 0) {
				current = current.getLeft();
			} else {
				current = current.getRight();
			}
		}
		return false;
	}

	@Override
	public List toList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setRoot(NodeWithTwoChilds<T> newRoot) {
		this.root=((RBTreeNode<T>)newRoot);
				
	}

	
	private void clearCopy(NodeWithTwoChilds<T> current) {
		
		if (current!= this.leaf && current.isCloned) {
			current.isCloned=false;
			clearCopy(current.getLeft());
			clearCopy(current.getRight());
			
		}
	}

	private void fixTree(NodeWithTwoChilds<T> current) throws NullPointerException {
		NodeWithTwoChilds<T> y;
	
		while (current.getParent().getColor() == TREE_COLOR.RED) {
			
			if (current.getParent() == current.getParent().getParent().getLeft()) {
				y = current.getParent().getParent().getRight();
				if (y.getColor() == TREE_COLOR.RED) {
					if (current.getParent().getColor()==TREE_COLOR.RED) {
						((RBTreeNode<T>)current.getParent().persistentClone(this)).setColorBlack();
					}
					
					if (y.getColor()==TREE_COLOR.RED) {
						((RBTreeNode<T>)y.persistentClone(this)).setColorBlack();
					}
					if (current.getParent().getParent().getColor()==TREE_COLOR.BLACK) {
						((RBTreeNode<T>)current.getParent().getParent().persistentClone(this)).setColorRed();
					}
					current = current.getParent().getParent();

				} else if (current == current.getParent().getRight()) {
					current = current.getParent().persistentClone(this);
					current.left_rotate(this);
				} else {
					if (current.getParent().getColor()==TREE_COLOR.RED) {
						((RBTreeNode<T>)current.getParent().persistentClone(this)).setColorBlack();
					}
					if (current.getParent().getParent().getColor()==TREE_COLOR.BLACK) {
						((RBTreeNode<T>)current.getParent().getParent().persistentClone(this)).setColorRed();
					}
					current.getParent().getParent().persistentClone(this).right_rotate(this);
					//System.out.println(666);
				}
			} else {
				y = current.getParent().getParent().getLeft();
				
				if (y.getColor() == TREE_COLOR.RED) {
					if (current.getParent().getColor()==TREE_COLOR.RED) {
						((RBTreeNode<T>)current.getParent().persistentClone(this)).setColorBlack();
					}
					
					if (y.getColor()==TREE_COLOR.RED) {
						((RBTreeNode<T>)y.persistentClone(this)).setColorBlack();
					}
					if (current.getParent().getParent().getColor()==TREE_COLOR.BLACK) {
						((RBTreeNode<T>)current.getParent().getParent().persistentClone(this)).setColorRed();
					}
					current = current.getParent().getParent();

				} else if (current == current.getParent().persistentClone(this).getLeft()) {
					current = current.getParent().persistentClone(this);
					current.right_rotate(this);
				} else {
					if (current.getParent().getColor()==TREE_COLOR.RED) {
						((RBTreeNode<T>)current.getParent().persistentClone(this)).setColorBlack();
					}
					if (current.getParent().getParent().getColor()==TREE_COLOR.BLACK) {
						((RBTreeNode<T>)current.getParent().getParent().persistentClone(this)).setColorRed();
					}
					current.getParent().getParent().left_rotate(this);
				}
			}
			
		}
		//System.out.println("repering"+ check_sons_parent(this.root)+" "+ current.getKey());

		while (this.root.getParent() != this.leaf) {
			this.root = ((RBTreeNode<T>)this.root.getParent());
		}
		((RBTreeNode<T>)this.root.persistentClone(this)).setColorBlack();

	}

	public void print() {
		if (this.root == null) {
			return;
		}
		printNodeWithRecursion(this.root, 0);
	}

	protected void printNodeWithRecursion(NodeWithTwoChilds<T> current, int n) {

		if (current == this.leaf) {
			for (int i = 0; i < n; i++) {
				System.out.print("\t");
			}
			System.out.println("_");
			return;
		}
		printNodeWithRecursion(current.getRight(), n + 1);

		for (int i = 0; i < n; i++) {
			System.out.print("\t");
		}
		System.out.println(current.getKey().toString()+" " +current.getColor());
		printNodeWithRecursion(current.getLeft(), n + 1);
	}

	public boolean checkSonsForColor(NodeWithTwoChilds<T> current) {

		if (current == this.leaf) {
			return true;
		}
		if (current.getColor() == TREE_COLOR.RED) {
			if (current.getLeft().getColor() == TREE_COLOR.RED || current.getRight().getColor() == TREE_COLOR.RED) {
				return false;
			}
		}
		return checkSonsForColor(current.getLeft()) && checkSonsForColor(current.getRight());

	}

	public int checkNumberOfBlackNodes(NodeWithTwoChilds<T> current) {

		if (current == this.leaf) {
			return 1;
		}
		int left = checkNumberOfBlackNodes(current.getLeft());
		int right = checkNumberOfBlackNodes(current.getRight());
		if (left == -1 || right == -1) {
			return -1;
		}
		if (current.getColor() == TREE_COLOR.RED) {
			if (left == right) {
				return left;
			}
			return -1;
		} else {
			if (left == right) {
				return left + 1;
			}
			return -1;
		}

	}
}
