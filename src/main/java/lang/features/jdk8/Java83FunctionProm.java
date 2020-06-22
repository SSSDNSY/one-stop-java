package lang.features.jdk8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author pengzh
 * @date 2020-03-03
 */
public class Java83FunctionProm {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,3,5,3,6,9,6,7,0);

        System.out.println("输出所有数据：");
        eval(list,n->true);

        System.out.println("输出偶数的数据：");
        eval(list,n-> n%2==0);

        System.out.println("输出所有>3的数：");
        eval(list,n-> n>3);

    }
    public static  void eval(List<Integer> list, Predicate<Integer> predicate){
        for (Integer n:list){
            if(predicate.test(n)){
                System.out.println(n+ "  ");
            }
        }
    }
}
