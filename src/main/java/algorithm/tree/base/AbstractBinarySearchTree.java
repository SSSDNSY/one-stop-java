package algorithm.tree.base;

/**
 * @author fun.pengzh
 * @class algorithm.tree.base.AbstractBinarySearchTree
 * @desc 二叉搜索树的抽象类，包括基本方法的实现。
 * @since 2021-02-14
 */
public abstract class AbstractBinarySearchTree {

    /**
     * root node  where whole tree start.
     */
    public Node root;

    /**
     * size
     */
    public int size;

    /**
     * abstract method for subclass.
     */
    protected abstract Node createNode(int value, Node parent, Node left, Node right);

    /**
     * find a node ,null will return if not found.
     *
     * @return search node
     */
    public Node search(int element) {
        Node node = root;
        while (node != null && node.value != null && node.value != element) {
            if (element < node.value) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return node;
    }


    /**
     * insert new element to tree.
     *
     * @param element
     */
    public Node insert(int element) {
        //create root if null
        if (root == null) {
            root = createNode(element, null, null, null);
            size++;
            return root;
        }
        //finde position to insert new Node
        Node insertParentNode = null;
        Node searchTempNode = root;
        while (searchTempNode != null && searchTempNode.value != null) {
            insertParentNode = searchTempNode;
            if (element < searchTempNode.value) {
                searchTempNode = searchTempNode.left;
            } else {
                searchTempNode = searchTempNode.right;
            }
        }
        //create new Node
        Node newNode = createNode(element, insertParentNode, null, null);
        if (insertParentNode.value > newNode.value) {
            insertParentNode.left = newNode;
        } else {
            insertParentNode.right = newNode;
        }
        size++;
        return newNode;
    }

    /**
     * revmoe element if node with such value exists;
     *
     * @param element
     * @return new node that is in place of deleted node.
     */
    public Node delete(int element) {
        Node deleteNode = search(element);
        if (deleteNode != null) {
            return delete(deleteNode);
        } else {
            return null;
        }
    }

    /**
     * delete logic wheen node is already found.
     *
     * @param deleteNode
     * @return new node that is in place of delete node. Or null if
     * element for delete was not found.
     */
    protected Node delete(Node deleteNode) {
        if (deleteNode != null) {
            Node nodeToReturn = null;
            if (deleteNode != null) {
                if (deleteNode.left == null) {
                    nodeToReturn = transplant(deleteNode, deleteNode.right);
                } else if (deleteNode.right == null) {
                    nodeToReturn = transplant(deleteNode, deleteNode.left);
                } else {
                    Node sucessorNode = getMinimum(deleteNode.right);
                    if (sucessorNode.parent != deleteNode) {
                        transplant(sucessorNode, sucessorNode.right);
                        sucessorNode.right = deleteNode.right;
                        sucessorNode.right.parent = sucessorNode;
                    }
                    transplant(deleteNode, sucessorNode);
                    sucessorNode.left = deleteNode.left;
                    sucessorNode.left.parent = sucessorNode;
                    nodeToReturn = sucessorNode;
                }
                size--;
            }
            return nodeToReturn;
        }
        return null;
    }

    /**
     * put one node from (newNode) to the place of another(nodeToReplace).
     *
     * @param nodeToReplace node which is replace by new node and remove from tree.
     * @param newNode       new node
     * @return new replace Node.
     */
    private Node transplant(Node nodeToReplace, Node newNode) {
        if (nodeToReplace.parent == null) {
            this.root = newNode;
        } else if (nodeToReplace == nodeToReplace.parent.left) {
            nodeToReplace.parent.left = newNode;
        } else {
            nodeToReplace.parent.right = newNode;
        }
        if (newNode != null) {
            newNode.parent = nodeToReplace.parent;
        }
        return newNode;
    }

    /**
     * @param element
     * @return true if tree contains element.
     */
    public boolean contains(int element) {
        return search(element) != null;
    }

    /**
     * get minimum value
     *
     * @return
     */
    public int getMinimum() {
        return getMinimum(root).value;
    }

    protected Node getMinimum(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    /**
     * get maximum value
     *
     * @return
     */
    public int getMaximum() {
        return getMaximum(root).value;
    }

    protected Node getMaximum(Node node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    /**
     * get next element element who is bigger than provided element.
     *
     * @param element
     * @return
     */
    public int getSuccessor(int element) {
        return getSucessor(search(element)).value;
    }

    protected Node getSucessor(Node node) {
        if (node.right != null) {
            return getMinimum(node.right);
        } else {
            Node currentNode = node;
            Node parentNode = node.parent;
            while (parentNode != null && currentNode == parentNode.right) {
                currentNode = parentNode;
                parentNode = parentNode.parent;
            }
            return parentNode;
        }
    }

    /**
     * tree traversal with printing element values. In order method.
     */
    public void printTreeInOrder() {
        printTreeInOrder(root);
    }

    private void printTreeInOrder(Node node) {
        if (node != null) {
            printTreeInOrder(node.left);
            if (node.value != null) {
                System.out.println(node.value + " ");
            }
            printTreeInOrder(node.right);
        }
        System.out.println();
    }

    /**
     * Tree traversal with printing element values. Pre order method.
     */
    public void printTreePreOrder() {
        printTreePreOrder(root);
    }

    private void printTreePreOrder(Node node) {
        if (node != null) {
            if (node.value != null) {
                System.out.println(node.value + " ");
            }
            printTreePreOrder(node.left);
            printTreePreOrder(node.right);
        }
        System.out.println();
    }

    /**
     * Tree traversal with printing element values. Post order method.
     */
    public void printTreePostOrder() {
        printTreePostOrder(root);
    }

    private void printTreePostOrder(Node node) {
        if (node != null) {
            printTreePostOrder(node.left);
            printTreePostOrder(node.right);
            if (node.value != null) {
                System.out.print(node.value + " ");
            }
        }
        System.out.println();
    }

    //-------------------------------- TREE PRINTING ------------------------------------
    public void printTree() {
        printSubTree(root);
    }

    public void printSubTree(Node node) {
        if (node.right != null) {
            printTree(node.right, true, "");
        }
        printNodeValue(node);
        if (node.left != null) {
            printTree(node.left, false, "");
        }
    }

    private void printNodeValue(Node node) {
        if (node.value == null) {
            System.out.print("<null>");
        } else {
            System.out.print(node.value.toString());
        }
        System.out.println();
    }

    private void printTree(Node node, boolean isRight, String indent) {
        if (node.right != null) {
            printTree(node.right, true, indent + (isRight ? "        " : " |      "));
        }
        System.out.print(indent);
        if (isRight) {
            System.out.print(" /");
        } else {
            System.out.print(" \\");
        }
        System.out.print("---- ");
        printNodeValue(node);
        if (node.left != null) {
            printTree(node.left, false, indent + (isRight ? " |      " : "        "));
        }
    }

    /**
     * @return Number of elements in the tree.
     */
    public int getSize() {
        return size;
    }

    /**
     * tree Node
     */
    public static class Node {

        public Integer value;
        public Node parent;
        public Node left;
        public Node right;

        public Node(Integer value, Node parent, Node left, Node right) {
            super();
            this.value = value;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (null == o) {
                return false;
            }
            if (getClass() != o.getClass()) {
                return false;
            }
            Node other = (Node) o;
            if (value == null) {
                if (other.value != null) {
                    return false;
                }
            } else if (!value.equals(other.value)) {
                return false;
            }
            return true;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            return prime * result + ((value == null) ? 0 : value.hashCode());
        }
    }
}
