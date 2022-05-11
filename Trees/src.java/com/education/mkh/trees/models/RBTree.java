package com.education.mkh.trees.models;

import java.util.Iterator;
import java.util.List;

public class RBTree<T extends Comparable<T>> implements TreeFunctionable<T>, Iterable<T> {
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
		if (this.root == leaf || this.root==null) {
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
		correctingLeaf();
	
		return true;
	}

	@Override
	public boolean delete(T key) {
		if (!this.search(key)) {
			return false;
		}
		this.root = (RBTreeNode<T>)this.root.persistentClone(this);
		NodeWithTwoChilds<T> current = this.root;
		while (current != this.leaf) {
			if (current.getKey().compareTo(key) < 0) {
				current = current.getRight();
				current = current.persistentClone(this);
			} else if (current.getKey().compareTo(key) > 0) {
				current = current.getLeft();
				current = current.persistentClone(this);
			} else {
				break;
			}
		}
		NodeWithTwoChilds<T> z = current.persistentClone(this);
		NodeWithTwoChilds<T> y = z;
		NodeWithTwoChilds<T> x;
		TREE_COLOR y_color = y.getColor();
		if (z.getLeft() == this.leaf) {	
			x = z.getRight().persistentClone(this);
			transplant(z, z.getRight());
		} else if (z.getRight() == this.leaf) {
			x = z.getLeft().persistentClone(this);
			transplant(z, z.getLeft());
		} else {
			y = min_son(z.getRight().persistentClone(this)).persistentClone(this);
			System.out.println(y.getKey());
			y_color = y.getColor();
			x=y.getRight().persistentClone(this);
			if (y.getParent().persistentClone(this) == z) {
				x=x.persistentClone(this);
				x.setParent(y.persistentClone(this));
			} else {
				transplant(y, y.getRight());
				y=y.persistentClone(this);
				y.setRight(z.getRight().persistentClone(this));
				y.getRight().persistentClone(this).setParent(y);
			
			}
			transplant(z, y);
			y=y.persistentClone(this);
			y.setLeft(z.getLeft().persistentClone(this));
			y.getLeft().persistentClone(this).setParent(y);
			if (z.getColor() == TREE_COLOR.RED) {
				((RBTreeNode<T>)y).setColorRed();
			} else {
				((RBTreeNode<T>)y).setColorBlack();
			}
			
		}
		if (y_color == TREE_COLOR.BLACK) {
			deleteFixUp(x);
		}
		this.leaf.setParent(null);
		while (!this.root.isLeaf && this.root.getParent() != this.leaf) {
			this.root = (RBTreeNode<T>)this.root.getParent();
		}

	
		clearCopy(this.root);
	
		correctingLeaf();
		return true;
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

	protected void clearCopy(NodeWithTwoChilds<T> current) {
		
		if (current!= this.leaf && current.isCloned) {
			current.isCloned=false;
			clearCopy(current.getLeft());
			clearCopy(current.getRight());
			
		}
	}

	protected void fixTree(NodeWithTwoChilds<T> current) throws NullPointerException {
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

	protected void transplant(NodeWithTwoChilds<T> node1, NodeWithTwoChilds<T> node2) {
		if (node1.getParent() == this.leaf) {
			this.root = (RBTreeNode<T>)node2;
		} else if (node1 == node1.getParent().getLeft()) {
			node1.getParent().persistentClone(this).setLeft(node2.persistentClone(this));
		} else {
			node1.getParent().persistentClone(this).setRight(node2.persistentClone(this));
		}
		node2.persistentClone(this).setParent(node1.getParent());
	}

	protected NodeWithTwoChilds<T> min_son(NodeWithTwoChilds<T> rootOfSearch) {
		NodeWithTwoChilds<T> current = rootOfSearch;
		while (current.getLeft() != this.leaf) {
			current = current.getLeft();
		}
		return current;
	}

	protected void correctingLeaf() {
		leaf.left=leaf.right=leaf.parent=null;
	}
	
	protected void deleteFixUp(NodeWithTwoChilds<T> x) {
		NodeWithTwoChilds<T> w;
		while (x != this.root && (x.getColor() == TREE_COLOR.BLACK)) {
			if (x == x.getParent().getLeft()) {
				w = x.getParent().getRight();
				if (w.getColor() == TREE_COLOR.RED) {
					((RBTreeNode<T>)w).setColorBlack();
					((RBTreeNode<T>)x.getParent()).setColorRed();
					x.getParent().left_rotate(this);
					w = x.getParent().getRight();
				}
				if (w.getLeft().getColor() == TREE_COLOR.BLACK && w.getRight().getColor() == TREE_COLOR.BLACK) {
					((RBTreeNode<T>)w).setColorRed();
					x = x.getParent();
				} else if (w.getRight().getColor() == TREE_COLOR.BLACK) {
					((RBTreeNode<T>)w.getLeft()).setColorBlack();
					((RBTreeNode<T>)w).setColorRed();
					w.right_rotate(this);
					w = x.getParent().getRight();
				} else {
					if (x.getParent().getColor() == TREE_COLOR.RED) {
						((RBTreeNode<T>)w).setColorRed();
					} else {
						((RBTreeNode<T>)w).setColorBlack();
					}
					((RBTreeNode<T>)x.getParent()).setColorBlack();
					((RBTreeNode<T>)w.getRight()).setColorBlack();
					x.getParent().left_rotate(this);
					x = this.root;
				}
			} else {
				w = x.getParent().getLeft();
				if (w.getColor() == TREE_COLOR.RED) {
					((RBTreeNode<T>)w).setColorBlack();
					((RBTreeNode<T>)x.getParent()).setColorRed();
					x.getParent().right_rotate(this);
					w = x.getParent().getLeft();
				}
				if (w.getLeft().getColor() == TREE_COLOR.BLACK && w.getRight().getColor() == TREE_COLOR.BLACK) {
					((RBTreeNode<T>)w).setColorRed();
					x = x.getParent();
				} else if (w.getLeft().getColor() == TREE_COLOR.BLACK) {
					((RBTreeNode<T>)w.getRight()).setColorBlack();
					((RBTreeNode<T>)w).setColorRed();
					w.left_rotate(this);
					w = x.getParent().getLeft();
				} else {
					if (x.getParent().getColor() == TREE_COLOR.RED) {
						((RBTreeNode<T>)w).setColorRed();
					} else {
						((RBTreeNode<T>)w).setColorBlack();
					}
					((RBTreeNode<T>)x.getParent()).setColorBlack();
					((RBTreeNode<T>)w.getLeft()).setColorBlack();
					x.getParent().right_rotate(this);
					x = this.root;
				}
			}
		}
		((RBTreeNode<T>)x).setColorBlack();

	}

	@Override
	public Iterator<T> iterator() {
		if (root==null || root.isLeaf) {
			return leaf;
		}
		return new RBTreeIterator((RBTreeNode)root.min_son());
	}

}
