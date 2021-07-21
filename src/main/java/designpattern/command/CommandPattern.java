package designpattern.command;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pengzh
 * @class CommandPattern
 * @since 2021-07-21
 * <p>
 * <p>
 * <p>
 * 命令模式
 * 命令模式（Command Pattern）是一种数据驱动的设计模式，它属于行为型模式。请求以命令的形式包裹在对象中，并传给调用对象。
 * 调用对象寻找可以处理该命令的合适的对象，并把该命令传给相应的对象，该对象执行命令。
 * <p>
 * <p>
 * 介绍
 * 意图：将一个请求封装成一个对象，从而使您可以用不同的请求对客户进行参数化。
 * <p>
 * 主要解决：在软件系统中，行为请求者与行为实现者通常是一种紧耦合的关系，但某些场合，
 * 比如需要对行为进行记录、撤销或重做、事务等处理时，这种无法抵御变化的紧耦合的设计就不太合适。
 * <p>
 * 何时使用：在某些场合，比如要对行为进行"记录、撤销/重做、事务"等处理，这种无法抵御变化的紧耦合是不合适的。
 * 在这种情况下，如何将"行为请求者"与"行为实现者"解耦？将一组行为抽象为对象，可以实现二者之间的松耦合。
 * <p>
 * 如何解决：通过调用者调用接受者执行命令，顺序：调用者→命令→接受者。
 * 关键代码：定义三个角色：1、received 真正的命令执行对象 2、Command 3、invoker 使用命令对象的入口
 * <p>
 * 应用实例：struts 1 中的 action 核心控制器 ActionServlet 只有一个，相当于 Invoker，
 * 而模型层的类会随着不同的应用有不同的模型类，相当于具体的 Command。
 * <p>
 * 优点： 1、降低了系统耦合度。 2、新的命令可以很容易添加到系统中去。
 * 缺点：使用命令模式可能会导致某些系统有过多的具体命令类。
 * <p>
 * 使用场景：认为是命令的地方都可以使用命令模式，比如： 1、GUI 中每一个按钮都是一条命令。 2、模拟 CMD。
 * <p>
 * 注意事项：系统需要支持命令的撤销(Undo)操作和恢复(Redo)操作，也可以考虑使用命令模式，见命令模式的扩展。
 * <p>
 * 命令模式结构示意图:https://www.runoob.com/wp-content/uploads/2014/08/commanduml.jpg
 */
public class CommandPattern {

    @Test
    public void command1() {
        Stock stock = new Stock();
        BuyStockCommand buyStockCommand = new BuyStockCommand(stock);
        SellStockCommand sellStockCommand = new SellStockCommand(stock);

        Broker broker = new Broker();
        broker.addCommand(buyStockCommand)
                .add(sellStockCommand);
        broker.execute();
    }
}

//命令接口类
interface Command {
    void execute();
}

//命令实现类
class BuyStockCommand implements Command {
    private Stock stock;

    public BuyStockCommand(Stock stock) {
        this.stock = stock;
    }

    @Override
    public void execute() {
        stock.buy();
    }
}

class SellStockCommand implements Command {

    private Stock stock;

    public SellStockCommand(Stock stock) {
        this.stock = stock;
    }

    @Override
    public void execute() {
        stock.sell();
    }
}

//被封装的命令
class Stock {

    private String name = "ABC";
    private int quantity = 10;

    public void buy() {
        System.out.println("Stock [ Name: " + name + ", Quantity: " + quantity + " ] bought");
    }

    public void sell() {
        System.out.println("Stock [ Name: " + name + ", Quantity: " + quantity + " ] sold");
    }
}

//命令调用者（根据命令具体实现类确认调用那个明了）
class Broker {
    List<Command> commandList = new ArrayList<>();

    public List<Command> addCommand(Command command) {
        commandList.add(command);
        return commandList;
    }

    public void execute() {
        for (Command command : commandList) {
            command.execute();
        }
    }
}



