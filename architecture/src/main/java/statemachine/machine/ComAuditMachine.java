package statemachine.machine;

import com.alibaba.cola.statemachine.builder.StateMachineBuilder;
import com.alibaba.cola.statemachine.builder.StateMachineBuilderFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import statemachine.action.ComAction;
import statemachine.condition.ComCondition;
import statemachine.dto.ComContext;
import statemachine.events.ComEvents;
import statemachine.status.ComStates;

import javax.annotation.Resource;

@Component
public class ComAuditMachine implements BaseMachine<ComStates, ComEvents, ComContext>, InitializingBean {

    @Resource
    ComAction comAction;

    @Resource
    ComCondition comCondition;

    @Override
    public void afterPropertiesSet() throws Exception {
        StateMachineBuilder<ComStates, ComEvents, ComContext> builder = StateMachineBuilderFactory.create();

        //提交审核
        builder.externalTransition()
                .from(ComStates.PENDING_SUBMIT)
                .to(ComStates.PENDING_AUDIT)
                .on(ComEvents.SUBMIT)
                .when(comCondition.defaultPassCondition())
                .perform(comAction.submitAction());

        //取消审核
        builder.externalTransition()
                .from(ComStates.PENDING_AUDIT)
                .to(ComStates.CANCEL)
                .on(ComEvents.CANCEL)
                .when(comCondition.defaultPassCondition())
                .perform(comAction.cancelAction());

        //初审
        builder.externalTransition()
                        .from(ComStates.PENDING_AUDIT)
                        .to(ComStates.MULTI_AUDIT)
                        .on(ComEvents.AUDIT)
                        .when(comCondition.defaultPassCondition())
                        .perform(comAction.auditAction());

        //审核/多人会审
        builder.internalTransition()
                        .within(ComStates.MULTI_AUDIT)
                        .on(ComEvents.AUDIT)
                        .when(comCondition.auditCondition())
                        .perform(comAction.auditAction());

        //审核通过
        builder.externalTransition()
                        .from(ComStates.MULTI_AUDIT)
                        .to(ComStates.SUCCESS)
                        .on(ComEvents.PASS_AUDIT)
                        .when(comCondition.defaultPassCondition())
                        .perform(comAction.passAction());


        //审核驳回
        builder.externalTransitions()
                .fromAmong(ComStates.MULTI_AUDIT,ComStates.PENDING_AUDIT)
                .to(ComStates.REJECT)
                .on(ComEvents.REJECT_AUDIT)
                .when(comCondition.defaultPassCondition())
                .perform(comAction.rejectAction());

        builder.build(getMachineId());


    }

    @Override
    public String getMachineId() {
        return "com-machine";
    }
}
