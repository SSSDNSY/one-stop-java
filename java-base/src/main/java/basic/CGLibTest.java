package basic;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author pengzh
 * @since 2020-08-07
 */
public class CGLibTest {
    public void sayHello(String str) {
        System.out.println("CGLibTest say:" + str);
    }

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(CGLibTest.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {

                System.out.println("before method run...");
                Object result = methodProxy.invokeSuper(o, args);
                System.out.println("after  method run...");
                return result;
            }
        });
        CGLibTest test = (CGLibTest) enhancer.create();
        test.sayHello("aaABDSAAa");
    }
}
