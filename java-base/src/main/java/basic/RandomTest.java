package basic;

import java.util.Random;

/**
 * @Desc
 * @Author pengzh
 * @Since 2023-12-14
 * <p>
 * <p>
 * * Java中产生随机数的方法有两种：
 * * 　　第一种：Math.random()
 * * 　　第二种：new Random()
 * *
 * *  种子的作用是什么？
 * *
 * * 　　种子就是产生随机数的第一次使用值,机制是通过一个函数,将这个种子的值转化为随机数空间中的某一个点上,并且产生的随机数均匀的散布在空间中。以后产生的随机数都与前一个随机数有关。
 * *
 * *
 * * 总结：
 * * 　　1.同一个种子,生成N个随机数,当你设定种子的时候,这N个随机数是什么已经确定。相同次数生成的随机数字是完全相同的。
 * *
 * * 　　2.如果用相同的种子创建两个 Random 实例,如上面的r3,r4,则对每个实例进行相同的方法调用序列,它们将生成并返回相同的数字序列。
 * *
 * *    3.Java的随机数都是通过算法实现的,Math.random()本质上属于Random()类。
 * *
 * *    4.使用java.util.Random()会相对来说比较灵活一些。
 * *
 * *  使用种子避免伪随机
 * <p>
 * <p>
 * 什么是伪随机数？
 * 　　 1.伪随机数是看似随机实质是固定的周期性序列,也就是有规则的随机。
 * 　　2.只要这个随机数是由确定算法生成的,那就是伪随机,只能通过不断算法优化,使你的随机数更接近随机。
 * 　   　(随机这个属性和算法本身就是矛盾的)
 * 　　3.通过真实随机事件取得的随机数才是真随机数。
 */
public class RandomTest {

    public static void main(String[] args) {
        double a = Math.random();

        double b = Math.random();

        Random r1 = new Random();

        int i = r1.nextInt(10);

        Random r2 = new Random();

        int i1 = r2.nextInt(10);

        /**
         * 有可能两个数相同
         */
        System.out.println(i);
        System.out.println(i1);
    }

}
