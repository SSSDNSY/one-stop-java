package algorithm.tree;

import java.util.*;

/**
 * @auther: imi
 * @Date: 2019/6/5 15:00
 * @Description:
 */
public class TreeTest {

    public static void main(String[] args) {
        int[] arrs = new int[]{67, 7, 30, 73, 10, 0, 78, 81, 10, 74};
        Node node = new Node();
        for(int i:arrs){
            node.add(i);
        }
        List list  = node.midOrder();
        for(Object i:list){
            System.out.println(i);
        }

        HeroNode hn = new HeroNode();
        for(int i=0;i<15;i++){
          hn.add(new Hero(i,new Random().nextInt(1500)));
        }
        List heroList = hn.midOrder();
        for(Object i:heroList){
            System.out.println(i);
        }
    }
    public static int[] treeSort(int[] arr){
        Node node = new Node();
        for(int i:arr){
            node.add(i);
        }
        List<Integer> list  = node.midOrder();
        for(int i=0;i<list.size();i++){
            arr[i] = list.get(i);
        }
        return arr;
    }

}


class HeroNode {
    private HeroNode left;
    private HeroNode right;
    private Hero value;

    public void add(Hero val){
        if(value == null){
            value = val;
        }else if(val.getHp()>value.getHp()){
            if(null == left){
                left = new HeroNode();
            }
            left.add(val);
        }else {
            if(null == right){
                right = new HeroNode();
            }
            right.add(val);
        }
    }

    public List midOrder(){
        List<Object> list = new ArrayList();
        if(null != left){
            list.addAll(left.midOrder());
        }
        list.add(value);
        if(null != right){
            list.addAll(right.midOrder());
        }
        return list;
    }
}

class Node {

    private Node left;
    private Node right;
    private Object value;

    public void add(Object val){
        if(value == null){
            value = val;
        }else if((Integer) val - (Integer) value <=0){
            if(null == left){
                left = new Node();
            }
            left.add(val);
        }else{
            if(null == right){
                right = new Node();
            }
            right.add(val);
        }
    }

    public List midOrder(){
        List<Object> list = new ArrayList();
        if (null !=left){
            list.addAll(left.midOrder());
        }
        list.add(value);
        if(null != right){
            list.addAll(right.midOrder());
        }
        return list;
    }


}