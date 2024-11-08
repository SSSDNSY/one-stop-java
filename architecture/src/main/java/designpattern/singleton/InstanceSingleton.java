package designpattern.singleton;

/**
 * @desc Effective Java作者推荐的枚举单例(线程安全)
 * @class InstanceSingleton
 * @since 2022-06-01
 * https://bugstack.cn/md/develop/design-pattern/2020-05-31-%E9%87%8D%E5%AD%A6%20Java%20%E8%AE%BE%E8%AE%A1%E6%A8%A1%E5%BC%8F%E3%80%8A%E5%AE%9E%E6%88%98%E5%8D%95%E4%BE%8B%E6%A8%A1%E5%BC%8F%E3%80%8B.html#_7-effective-java%E4%BD%9C%E8%80%85%E6%8E%A8%E8%8D%90%E7%9A%84%E6%9E%9A%E4%B8%BE%E5%8D%95%E4%BE%8B-%E7%BA%BF%E7%A8%8B%E5%AE%89%E5%85%A8
 */
public enum InstanceSingleton {

    INSTANCE;


    public void deal() {
        System.out.println("sdfaf");
    }

    public static void main(String[] args) {
        InstanceSingleton.INSTANCE.deal();
    }

}
