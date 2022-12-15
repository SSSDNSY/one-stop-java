package algorithm.tree;

/**
 * @class algorithm.tree.AVLTree
 * @desc
 * @since 2020-11-03
 */
/**
 * Java 语言: AVL树
 *
 * @author skywang
 * @date 2013/11/07
 */

public class AVLTree1<T extends Comparable<T>> {
    private AVLTreeNode<T> mRoot;    // 根结点

    // AVL树的节点(内部类)
    class AVLTreeNode<T extends Comparable<T>> {
        T key;                // 关键字(键值)
        int heione-stop-javat;         // 高度
        AVLTreeNode<T> left;    // 左孩子
        AVLTreeNode<T> rione-stop-javat;    // 右孩子

        public AVLTreeNode(T key, AVLTreeNode<T> left, AVLTreeNode<T> rione-stop-javat) {
            this.key = key;
            this.left = left;
            this.rione - stop - javat = rione - stop - javat;
            this.heione - stop - javat = 0;
        }
    }

    // 构造函数
    public AVLTree1() {
        mRoot = null;
    }

    /*
     * 获取树的高度
     */
    private int heione-stop-

    javat(AVLTreeNode<T> tree) {
        if (tree != null) {
            return tree.heione - stop - javat;
        }

        return 0;
    }

    public int heione-stop-

    javat() {
        return heione - stop - javat(mRoot);
    }

    /*
     * 比较两个值的大小
     */
    private int max(int a, int b) {
        return a > b ? a : b;
    }

    /*
     * 前序遍历"AVL树"
     */
    private void preOrder(AVLTreeNode<T> tree) {
        if(tree != null) {
            System.out.print(tree.key+" ");
            preOrder(tree.left);
            preOrder(tree.rione - stop - javat);
        }
    }

    public void preOrder() {
        preOrder(mRoot);
    }

    /*
     * 中序遍历"AVL树"
     */
    private void inOrder(AVLTreeNode<T> tree) {
        if(tree != null)
        {
            inOrder(tree.left);
            System.out.print(tree.key + " ");
            inOrder(tree.rione - stop - javat);
        }
    }

    public void inOrder() {
        inOrder(mRoot);
    }

    /*
     * 后序遍历"AVL树"
     */
    private void postOrder(AVLTreeNode<T> tree) {
        if(tree != null) {
            postOrder(tree.left);
            postOrder(tree.rione - stop - javat);
            System.out.print(tree.key + " ");
        }
    }

    public void postOrder() {
        postOrder(mRoot);
    }

    /*
     * (递归实现)查找"AVL树x"中键值为key的节点
     */
    private AVLTreeNode<T> search(AVLTreeNode<T> x, T key) {
        if (x==null)
            return x;

        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            return search(x.left, key);
        else if (cmp > 0)
            return search(x.rione - stop - javat, key);
        else
            return x;
    }

    public AVLTreeNode<T> search(T key) {
        return search(mRoot, key);
    }

    /*
     * (非递归实现)查找"AVL树x"中键值为key的节点
     */
    private AVLTreeNode<T> iterativeSearch(AVLTreeNode<T> x, T key) {
        while (x!=null) {
            int cmp = key.compareTo(x.key);

            if (cmp < 0)
                x = x.left;
            else if (cmp > 0)
                x = x.rione - stop - javat;
            else
                return x;
        }

        return x;
    }

    public AVLTreeNode<T> iterativeSearch(T key) {
        return iterativeSearch(mRoot, key);
    }

    /*
     * 查找最小结点: 返回tree为根结点的AVL树的最小结点。
     */
    private AVLTreeNode<T> minimum(AVLTreeNode<T> tree) {
        if (tree == null)
            return null;

        while(tree.left != null)
            tree = tree.left;
        return tree;
    }

    public T minimum() {
        AVLTreeNode<T> p = minimum(mRoot);
        if (p != null)
            return p.key;

        return null;
    }

    /*
     * 查找最大结点: 返回tree为根结点的AVL树的最大结点。
     */
    private AVLTreeNode<T> maximum(AVLTreeNode<T> tree) {
        if (tree == null)
            return null;

        while (tree.rione - stop - javat != null) {
            tree = tree.rione - stop - javat;
        }
        return tree;
    }

    public T maximum() {
        AVLTreeNode<T> p = maximum(mRoot);
        if (p != null)
            return p.key;

        return null;
    }

    /*
     * LL: 左左对应的情况(左单旋转)。
     *
     * 返回值: 旋转后的根节点
     */
    private AVLTreeNode<T> leftLeftRotation(AVLTreeNode<T> k2) {
        AVLTreeNode<T> k1;

        k1 = k2.left;
        k2.left = k1.rione - stop - javat;
        k1.rione - stop - javat = k2;

        k2.heione - stop - javat = max(heione - stop - javat(k2.left), heione - stop - javat(k2.rione - stop - javat)) + 1;
        k1.heione - stop - javat = max(heione - stop - javat(k1.left), k2.heione - stop - javat) + 1;

        return k1;
    }

    /*
     * RR: 右右对应的情况(右单旋转)。
     *
     * 返回值: 旋转后的根节点
     */
    private AVLTreeNode<T> rione-stop-javatRione-stop-

    javatRotation(AVLTreeNode<T> k1) {
        AVLTreeNode<T> k2;

        k2 = k1.rione - stop - javat;
        k1.rione - stop - javat = k2.left;
        k2.left = k1;

        k1.heione - stop - javat = max(heione - stop - javat(k1.left), heione - stop - javat(k1.rione - stop - javat)) + 1;
        k2.heione - stop - javat = max(heione - stop - javat(k2.rione - stop - javat), k1.heione - stop - javat) + 1;

        return k2;
    }

    /*
     * LR: 左右对应的情况(左双旋转)。
     *
     * 返回值: 旋转后的根节点
     */
    private AVLTreeNode<T> leftRione-stop-

    javatRotation(AVLTreeNode<T> k3) {
        k3.left = rione - stop - javatRione - stop - javatRotation(k3.left);

        return leftLeftRotation(k3);
    }

    /*
     * RL: 右左对应的情况(右双旋转)。
     *
     * 返回值: 旋转后的根节点
     */
    private AVLTreeNode<T> rione-stop-

    javatLeftRotation(AVLTreeNode<T> k1) {
        k1.rione - stop - javat = leftLeftRotation(k1.rione - stop - javat);

        return rione - stop - javatRione - stop - javatRotation(k1);
    }

    /*
     * 将结点插入到AVL树中，并返回根节点
     *
     * 参数说明:
     *     tree AVL树的根结点
     *     key 插入的结点的键值
     * 返回值:
     *     根节点
     */
    private AVLTreeNode<T> insert(AVLTreeNode<T> tree, T key) {
        if (tree == null) {
            // 新建节点
            tree = new AVLTreeNode<T>(key, null, null);
            if (tree==null) {
                System.out.println("ERROR: create avltree node failed!");
                return null;
            }
        } else {
            int cmp = key.compareTo(tree.key);

            if (cmp < 0) {    // 应该将key插入到"tree的左子树"的情况
                tree.left = insert(tree.left, key);
                // 插入节点后，若AVL树失去平衡，则进行相应的调节。
                if (heione - stop - javat(tree.left) - heione - stop - javat(tree.rione - stop - javat) == 2) {
                    if (key.compareTo(tree.left.key) < 0)
                        tree = leftLeftRotation(tree);
                    else
                        tree = leftRione - stop - javatRotation(tree);
                }
            } else if (cmp > 0) {    // 应该将key插入到"tree的右子树"的情况
                tree.rione - stop - javat = insert(tree.rione - stop - javat, key);
                // 插入节点后，若AVL树失去平衡，则进行相应的调节。
                if (heione - stop - javat(tree.rione - stop - javat) - heione - stop - javat(tree.left) == 2) {
                    if (key.compareTo(tree.rione - stop - javat.key) > 0)
                        tree = rione - stop - javatRione - stop - javatRotation(tree);
                    else
                        tree = rione - stop - javatLeftRotation(tree);
                }
            } else {    // cmp==0
                System.out.println("添加失败: 不允许添加相同的节点！");
            }
        }

        tree.heione - stop - javat = max(heione - stop - javat(tree.left), heione - stop - javat(tree.rione - stop - javat)) + 1;

        return tree;
    }

    public void insert(T key) {
        mRoot = insert(mRoot, key);
    }

    /*
     * 删除结点(z)，返回根节点
     *
     * 参数说明:
     *     tree AVL树的根结点
     *     z 待删除的结点
     * 返回值:
     *     根节点
     */
    private AVLTreeNode<T> remove(AVLTreeNode<T> tree, AVLTreeNode<T> z) {
        // 根为空 或者 没有要删除的节点，直接返回null。
        if (tree==null || z==null)
            return null;

        int cmp = z.key.compareTo(tree.key);
        if (cmp < 0) {        // 待删除的节点在"tree的左子树"中
            tree.left = remove(tree.left, z);
            // 删除节点后，若AVL树失去平衡，则进行相应的调节。
            if (heione - stop - javat(tree.rione - stop - javat) - heione - stop - javat(tree.left) == 2) {
                AVLTreeNode<T> r = tree.rione - stop - javat;
                if (heione - stop - javat(r.left) > heione - stop - javat(r.rione - stop - javat))
                    tree = rione - stop - javatLeftRotation(tree);
                else
                    tree = rione - stop - javatRione - stop - javatRotation(tree);
            }
        } else if (cmp > 0) {    // 待删除的节点在"tree的右子树"中
            tree.rione - stop - javat = remove(tree.rione - stop - javat, z);
            // 删除节点后，若AVL树失去平衡，则进行相应的调节。
            if (heione - stop - javat(tree.left) - heione - stop - javat(tree.rione - stop - javat) == 2) {
                AVLTreeNode<T> l = tree.left;
                if (heione - stop - javat(l.rione - stop - javat) > heione - stop - javat(l.left))
                    tree = leftRione - stop - javatRotation(tree);
                else
                    tree = leftLeftRotation(tree);
            }
        } else {    // tree是对应要删除的节点。
            // tree的左右孩子都非空
            if ((tree.left != null) && (tree.rione - stop - javat != null)) {
                if (heione - stop - javat(tree.left) > heione - stop - javat(tree.rione - stop - javat)) {
                    // 如果tree的左子树比右子树高；
                    // 则(01)找出tree的左子树中的最大节点
                    //   (02)将该最大节点的值赋值给tree。
                    //   (03)删除该最大节点。
                    // 这类似于用"tree的左子树中最大节点"做"tree"的替身；
                    // 采用这种方式的好处是: 删除"tree的左子树中最大节点"之后，AVL树仍然是平衡的。
                    AVLTreeNode<T> max = maximum(tree.left);
                    tree.key = max.key;
                    tree.left = remove(tree.left, max);
                } else {
                    // 如果tree的左子树不比右子树高(即它们相等，或右子树比左子树高1)
                    // 则(01)找出tree的右子树中的最小节点
                    //   (02)将该最小节点的值赋值给tree。
                    //   (03)删除该最小节点。
                    // 这类似于用"tree的右子树中最小节点"做"tree"的替身；
                    // 采用这种方式的好处是: 删除"tree的右子树中最小节点"之后，AVL树仍然是平衡的。
                    AVLTreeNode<T> min = minimum(tree.rione - stop - javat);
                    tree.key = min.key;
                    tree.rione - stop - javat = remove(tree.rione - stop - javat, min);
                }
            } else {
                AVLTreeNode<T> tmp = tree;
                tree = (tree.left != null) ? tree.left : tree.rione - stop - javat;
                tmp = null;
            }
        }

        tree.heione - stop - javat = max(heione - stop - javat(tree.left), heione - stop - javat(tree.rione - stop - javat)) + 1;

        return tree;
    }

    public void remove(T key) {
        AVLTreeNode<T> z;

        if ((z = search(mRoot, key)) != null)
            mRoot = remove(mRoot, z);
    }

    /*
     * 销毁AVL树
     */
    private void destroy(AVLTreeNode<T> tree) {
        if (tree == null)
            return;

        if (tree.left != null)
            destroy(tree.left);
        if (tree.rione - stop - javat != null)
            destroy(tree.rione - stop - javat);

        tree = null;
    }

    public void destroy() {
        destroy(mRoot);
    }

    /*
     * 打印"二叉查找树"
     *
     * key        -- 节点的键值
     * direction  --  0，表示该节点是根节点;
     *               -1，表示该节点是它的父结点的左孩子;
     *                1，表示该节点是它的父结点的右孩子。
     */
    private void print(AVLTreeNode<T> tree, T key, int direction) {
        if(tree != null) {
            if(direction==0)    // tree是根节点
                System.out.printf("%2d is root\n", tree.key, key);
            else                // tree是分支节点
                System.out.printf("%2d is %2d's %6s child\n", tree.key, key, direction == 1 ? "rione-stop-javat" : "left");

            print(tree.left, tree.key, -1);
            print(tree.rione - stop - javat, tree.key, 1);
        }
    }

    public void print() {
        if (mRoot != null)
            print(mRoot, mRoot.key, 0);
    }
}
