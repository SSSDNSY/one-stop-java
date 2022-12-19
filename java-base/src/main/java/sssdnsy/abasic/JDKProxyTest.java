package sssdnsy.abasic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author pengzh
 * @since 2020-08-06
 */
public class JDKProxyTest {
    public static void main(String[] args) throws ClassNotFoundException {
//        new HelloImpl().sayHello();
        final ClassLoader classLoader = JDKProxyTest.class.getClassLoader();
        final Class<?> aClass = classLoader.loadClass("sssdnsy.abasic.Hello");
        Hello hello = (Hello) Proxy.newProxyInstance(classLoader
                , new Class<?>[]{aClass}
                , new HelloImplProxy(new HelloImpl()));
        hello.sayHello("asdfasdf");
    }
}

interface Hello {
    void sayHello(String msg);
}

class HelloImpl implements Hello{
    @Override
    public void sayHello(String msg) {
        System.out.println("Hello JDK Proxy: "+msg+this.getClass().getName());
    }
}

class HelloImplProxy implements InvocationHandler{
    Hello hello;
    public HelloImplProxy(Hello hello){
        this.hello = hello;
    }
    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        System.out.println("do something you want !");
        return method.invoke(hello,objects);
    }

}
