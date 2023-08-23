package queue;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: imi
 * @Date: 2019/5/30 20:16
 * @Description:广度优先搜索 Breadth-first search  BFS
 */
public class BFS {

    public static void main(String[] args) {
        Node A = new Node('A');
        Node B = new Node('B');
        Node C = new Node('C');
        Node D = new Node('D');
        Node E = new Node('E');
        Node F = new Node('F');
        Node G = new Node('G');
        A.add(B);
        A.add(C);
        A.add(D);
        B.add(E);
        C.add(E);
        C.add(F);
        F.add(G);
        D.add(G);
//        BFS(A,G);
        showClassLoader();
    }

    static int BFS(Node root, Node target) {


        return -1;
    }

    static void showClassLoader() {
        System.out.println(BFS.class.getClassLoader());
        System.out.println(System.class.getClassLoader());
        System.out.println(HashMap.class.getClassLoader());

    }
}

class Node {
    char val;
    private Map next;

    public Node(char val) {
        this.val = val;
        this.next = new HashMap();
    }

    public char getVal() {
        return val;
    }

    public void add(Node next) {
        if (next != null)
            this.next.put(next.getVal(), next);
    }
}
