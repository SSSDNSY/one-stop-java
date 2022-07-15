package designpattern.observer;

import org.junit.Test;

import java.util.*;

/**
 * @author pengzh
 * @class ObserverPattern
 * @since 2021-07-14
 * <p>
 * 观察者模式
 * 当对象间存在一对多关系时，则使用观察者模式（Observer Pattern）。比如，当一个对象被修改时，则会自动通知它的依赖对象。观察者模式属于行为型模式。
 * <p>
 * 介绍
 * 意图：定义对象间的一种一对多的依赖关系，当一个对象的状态发生改变时，所有依赖于它的对象都得到通知并被自动更新。
 * 主要解决：一个对象状态改变给其他对象通知的问题，而且要考虑到易用和低耦合，保证高度的协作。
 * 何时使用：一个对象（目标对象）的状态发生改变，所有的依赖对象（观察者对象）都将得到通知，进行广播通知。
 * 如何解决：使用面向对象技术，可以将这种依赖关系弱化。
 * 关键代码：在抽象类里有一个 ArrayList 存放观察者们。
 * 应用实例： 1、拍卖的时候，拍卖师观察最高标价，然后通知给其他竞价者竞价。 2、西游记里面悟空请求菩萨降服红孩儿，菩萨洒了一地水招来一个老乌龟，这个乌龟就是观察者，他观察菩萨洒水这个动作。
 * <p>
 * 优点： 1、观察者和被观察者是抽象耦合的。 2、建立一套触发机制。
 * 缺点： 1、如果一个被观察者对象有很多的直接和间接的观察者的话，将所有的观察者都通知到会花费很多时间。 2、如果在观察者和观察目标之间有循环依赖的话，观察目标会触发它们之间进行循环调用，可能导致系统崩溃。 3、观察者模式没有相应的机制让观察者知道所观察的目标对象是怎么发生变化的，而仅仅只是知道观察目标发生了变化。
 * <p>
 * 使用场景： 1、有多个子类共有的方法，且逻辑相同。 2、重要的、复杂的方法，可以考虑作为模板方法。
 * 注意事项： 1、JAVA 中已经有了对观察者模式的支持类。 2、避免循环引用。 3、如果顺序执行，某一观察者错误会导致系统卡壳，一般采用异步方式。
 * <p>
 * 实现
 * 观察者模式使用三个类 Subject、Observer 和 Client。Subject 对象带有绑定观察者到 Client 对象和从 Client 对象解绑观察者的方法。我们创建 Subject 类、Observer 抽象类和扩展了抽象类 Observer 的实体类。
 * ObserverPatternDemo，我们的演示类使用 Subject 和实体类对象来演示观察者模式。
 * 观察者模式的 UML 图
 * <p>
 * https://atts.w3cschool.cn/attachments/uploads/2014/08/observer_pattern_uml_diagram.jpg
 * <p>
 * <p>
 * more example:
 * <p>
 * https://www.cnblogs.com/rickiyang/p/12001524.html
 */
public class ObserverPattern {


    /**
     * 入门理解版
     *
     * @param args
     */
    @Test
    public void ver1() {

        Subject subject = new Subject();

        final HexaObserver hexaObserver = new HexaObserver(subject);
        final OctalObserver octalObserver = new OctalObserver(subject);
        final BinaryObserver binaryObserver = new BinaryObserver(subject);

        subject.setState(2);
        subject.setState(5);
        subject.setState(10);

    }

    /**
     * jdk自带观察者（事件处理机制）版本
     *
     * @param args
     */
    @Test
    public void ver2() {
        DoorEvent openDoorEvent = new DoorEvent("openDoor", 1);
        OpenDoorListener openDoorListener = new OpenDoorListener();
        CloseDoorListener closeDoorListener = new CloseDoorListener();
        List<DoorListener> listenerList = new ArrayList<>();
        listenerList.add(openDoorListener);
        listenerList.add(closeDoorListener);
        listenerList.forEach(listener -> {
            listener.dealDoorEvent(openDoorEvent);
        });


    }

}

/******************************************************* ver1 ********************************************************/

/**
 * 观察者
 */
abstract class Observer {

    protected Subject subject;

    protected abstract void update();

}


/**
 * 被观察者
 */
class Subject {

    int state;
    List<Observer> observers = new LinkedList<>();

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        notifyAllObservers();
    }

    public List<Observer> getObservers() {
        return observers;
    }

    public void setObservers(List<Observer> observers) {
        this.observers = observers;
    }

    public void attach(Observer observer) {
        observers.add(observer);
    }

    private void notifyAllObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}

class BinaryObserver extends Observer {

    public BinaryObserver(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    protected void update() {
        System.out.println("BinaryObserver 观察到变化" + Integer.toBinaryString(subject.getState()));
    }
}

class OctalObserver extends Observer {

    public OctalObserver(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    protected void update() {
        System.out.println("OctalObserver 观察到变化" + Integer.toOctalString(subject.getState()));
    }
}

class HexaObserver extends Observer {

    public HexaObserver(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }


    @Override
    protected void update() {
        System.out.println("HexaObserver 观察到变化" + Integer.toHexString(subject.getState()));
    }

}

/******************************************************* ver1 ********************************************************/










/******************************************************* ver2 ********************************************************/

class DoorEvent extends EventObject {

    private int status;

    public DoorEvent(Object o) {
        super(o);
    }

    public DoorEvent(Object o, int status) {
        super(o);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

interface DoorListener extends EventListener {
    void dealDoorEvent(DoorEvent doorEvent);
}

class OpenDoorListener implements DoorListener {

    @Override
    public void dealDoorEvent(DoorEvent doorEvent) {
        if (doorEvent.getStatus() == 1) {
            System.out.println("处理开门事件。。。");
        }
    }
}

class CloseDoorListener implements DoorListener {

    @Override
    public void dealDoorEvent(DoorEvent doorEvent) {
        if (doorEvent.getStatus() == 2) {
            System.out.println("处理关门事件...");
        }
    }
}

