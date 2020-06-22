package test1;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author pengzh
 * @date 2020-03-13
 */
public class Test1 {
    @Before
    public void before(){
        System.out.println("before...");

    }
    @After
    public void after(){
        System.out.println("after...");
    }
    @Test
    public void doTest(){
        Assert.assertEquals(2,1+1);
        System.out.println("doTest...");
    }
}
