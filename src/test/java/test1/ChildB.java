package test1;

import org.junit.Assert;
import org.junit.Test;

import java.io.Serializable;

/**
 * @author pengzh
 * @date 2020-03-17
 */
public class ChildB extends AbstractA{

    @Test
    public void test(){
        System.out.println(new ChildB() instanceof Serializable);
        System.out.println(new ChildB() instanceof AbstractA);
        System.out.println(new java.sql.Date(1l) instanceof  java.util.Date);
        Assert.assertTrue("继承传递接口实现",new ChildB() instanceof Serializable);
    }
}
