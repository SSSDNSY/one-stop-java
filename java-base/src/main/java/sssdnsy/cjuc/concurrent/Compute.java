package sssdnsy.cjuc.concurrent;

/**
 * @Auther: imi
 * @Date: 2019/4/3 11:02
 * @Description:
 */
public interface Compute<A,V> {

    V compute(A a) throws Exception;
}
