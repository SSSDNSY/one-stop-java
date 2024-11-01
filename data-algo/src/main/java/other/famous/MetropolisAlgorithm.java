package other.famous;

import org.apache.commons.lang3.RandomUtils;

/**
 * @author pengzh
 * @desc
 * @class MetropolisAlgorithm
 * @since 2022-01-05
 * <p>
 * <p>
 * <p>
 * 一、1946 蒙特卡洛方法
 * [1946: John von Neumann, Stan Ulam, and Nick Metropolis, all at the Los Alamos Scientific Laboratory, cook up the Metropolis algorithm, also known as the Monte Carlo method.]
 * <p>
 * 1946年，美国拉斯阿莫斯国家实验室的三位科学家John von Neumann,Stan Ulam 和 Nick Metropolis
 * 共同发明，被称为蒙特卡洛方法。
 * <p>
 * 它的具体定义是：
 * 在广场上画一个边长一米的正方形，在正方形内部随意用粉笔画一个不规则的形状，
 * 现在要计算这个不规则图形的面积，怎么计算列?
 * 蒙特卡洛(Monte Carlo)方法告诉我们，均匀的向该正方形内撒N（N 是一个很大的自然数）个黄豆，
 * 随后数数有多少个黄豆在这个不规则几何形状内部，比如说有M个，
 * 那么，这个奇怪形状的面积便近似于M/N，N越大，算出来的值便越精确。
 * 在这里我们要假定豆子都在一个平面上，相互之间没有重叠。(撒黄豆只是一个比喻。)
 * <p>
 * 蒙特卡洛方法可用于近似计算圆周率：
 * 让计算机每次随机生成两个0到1之间的数，看这两个实数是否在单位圆内。
 * 生成一系列随机点，统计单位圆内的点数与总点数，内接圆面积和正方形面积之比为PI:4，PI为圆周率。
 * <p>
 * (多谢网友七里河蠢才指出:S内接圆：S正=PI：4。具体，请看文下第99条评论。十六日修正)，
 * <p>
 * 当随机点取得越多（但即使取10的9次方个随机点时，其结果也仅在前4位与圆周率吻合）时，
 * 其结果越接近于圆周率。
 */
public class MetropolisAlgorithm {


    public static void main(String[] args) {
        menteCarlo(10);
        menteCarlo(100);
        menteCarlo(1000);
        menteCarlo(10000);
        menteCarlo(100000);
        menteCarlo(1000000);
        menteCarlo(10000000);
        menteCarlo(100000000);
        menteCarlo(1000000000);
        menteCarlo(Integer.MAX_VALUE);
    }

    public static void menteCarlo(int n) {
        /**
         * X=[0,2]
         * Y=[0,2]
         */
        int Ps = 0, Po = 0;
        for (int i = 0; i < n; i++) {
            double x = RandomUtils.nextDouble(0, 2);
            double y = RandomUtils.nextDouble(0, 2);
            double Sx = (x - 1) * (x - 1) + (y - 1) * (y - 1);
            if (Sx <= 1) {
                Po++;
            }
            Ps++;
        }
        System.out.println("n=" + n + ",Pi= " + (4 * 1.0 * Po / Ps));
    }
}
