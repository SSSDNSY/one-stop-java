package statemachine.machine;

import com.alibaba.cola.statemachine.StateMachine;
import com.alibaba.cola.statemachine.StateMachineFactory;

/**
 * 通用基础设定及方法
 *
 * @param <S> 状态枚举/类
 * @param <E> 事件枚举/类
 * @param <C> 上下文数据对象
 */
public interface BaseMachine<S, E, C> {

    /**
     * 获取状态机id
     */
    String getMachineId();

    /**
     * 激活某个事件
     *
     * @param status  状态枚举
     * @param event   事件
     * @param context 上下文参数
     */
    default S fire(S status, E event, C context) {
        String machineId = getMachineId();
        StateMachine<S, E, C> objectObjectObjectStateMachine = StateMachineFactory.get(machineId);
        return objectObjectObjectStateMachine.fireEvent(status, event, context);
    }
}
