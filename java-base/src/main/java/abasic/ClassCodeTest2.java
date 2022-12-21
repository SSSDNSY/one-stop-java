package abasic;

/**
 * @author fun.pengzh
 * @class sssdnsy.abasic.ClassCodeTest2
 * @desc
 * @since 2021-02-09
 */
public class ClassCodeTest2 {
    public static void main(String[] args) {
        synchronized(args){
            System.out.println(1);
        }
    }
}
