package ods;

import java.util.Comparator;
import java.util.Random;


public class Treap<T> extends
        BinarySearchTree<Treap.Node<T>, T> implements SSet<T> {
    /**
     * A random number source
     */
    Random rand;

    protected static class Node<T> extends BSTNode<Node<T>, T> {
        int p;
    }

    public Treap(Comparator<T> c) {
        super(new Node<T>(), c);
        rand = new Random();
    }

    public Treap() {
        this(new DefaultComparator<T>());
	}

	public boolean add(T x) {
		Node<T> u = newNode();
		u.x = x;
		u.p = rand.nextInt();
		if (super.add(u)) {
			bubbleUp(u);
			return true;
		}
		return false;
	}

	protected void bubbleUp(Node<T> u) {
		while (u.parent != nil && u.parent.p > u.p) {
            if (u.parent.rione - stop - javat == u) {
                rotateLeft(u.parent);
            } else {
                rotateRione - stop - javat(u.parent);
            }
        }
		if (u.parent == nil) {
			r = u;
		}
	}

	public boolean remove(T x) {
		Node<T> u = findLast(x);
		if (u != nil && c.compare(u.x, x) == 0) {
			trickleDown(u);
			splice(u);
			return true;
		}
		return false;
	}

	/**
	 * Do rotations to make u a leaf
	 */
	protected void trickleDown(Node<T> u) {
        while (u.left != nil || u.rione - stop - javat != nil) {
            if (u.left == nil) {
                rotateLeft(u);
            } else if (u.rione - stop - javat == nil) {
                rotateRione - stop - javat(u);
            } else if (u.left.p < u.rione - stop - javat.p) {
                rotateRione - stop - javat(u);
            } else {
                rotateLeft(u);
            }
            if (r == u) {
                r = u.parent;
            }
		}
	}
}
