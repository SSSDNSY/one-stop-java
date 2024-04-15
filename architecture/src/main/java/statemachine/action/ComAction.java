package statemachine.action;

import com.alibaba.cola.statemachine.Action;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import statemachine.DbExample;
import statemachine.dto.ComContext;
import statemachine.events.ComEvents;
import statemachine.machine.ComAuditMachine;
import statemachine.status.ComStates;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Objects;


@Slf4j
@Component
public class ComAction {


    @Resource
    DbExample dbExample;

    @Lazy
    @Resource
    ComAuditMachine comAuditMachine;

    /**
     *
     * 提交审核处理
     */
//    @Transactional(rollbackFor = Exception.class)
    public Action<ComStates, ComEvents, ComContext> submitAction(){
        return (from, to, event, ctx)-> {
            log.info("submit action ----->");
            //注意这里需要编程式事务处理，切面有问题哟
//            TransactionStatus begin = dataSourceManager.getTransaction(new DefaultTransactionAttribute());
            //执行审核
            try {
                //业务代码 1/2/3/4

                ComContext context=new ComContext();
                BeanUtils.copyProperties(ctx,context);
                context.setCurrentState(ComStates.PENDING_AUDIT.getValue());
                dbExample.update(context.getDataId(),context);

//                dataSourceManager.commit(begin);
            }catch (Exception e){
//                dataSourceManager.rollback(begin);
            }
        };
    }

    /**
     * 取消操作
     * @return
     */
    public Action<ComStates, ComEvents, ComContext> cancelAction(){
        return (from, to, event, ctx)-> {
            log.info("cancel action ----->");
            //注意这里需要编程式事务处理，切面有问题哟
//            TransactionStatus begin = dataSourceManager.getTransaction(new DefaultTransactionAttribute());
            //执行审核
            try {
                //业务代码 1/2/3/4

                ComContext context=new ComContext();
                BeanUtils.copyProperties(ctx,context);
                context.setCurrentState(ComStates.CANCEL.getValue());
                dbExample.update(context.getDataId(),context);

//                dataSourceManager.commit(begin);
            }catch (Exception e){
//                dataSourceManager.rollback(begin);
            }
        };
    }

    /**
     * 多人审核
     * @return
     */
    public Action<ComStates, ComEvents, ComContext> auditAction(){
        return (from, to, event, ctx)-> {
            log.info("audit action ----->");
            //注意这里需要编程式事务处理，切面有问题哟
//            TransactionStatus begin = dataSourceManager.getTransaction(new DefaultTransactionAttribute());
            //执行审核
            try {
                //业务代码 1/2/3/4
                if (Objects.equals(ctx.getCurrentNodeAuditState(),ComStates.PENDING_AUDIT.getValue())){
                    ctx.setCurrentState(ComStates.MULTI_AUDIT.getValue());
                }

                ComContext context=new ComContext();
                BeanUtils.copyProperties(ctx,context);

                ComContext.AuditNode auditNode = ctx.getAuditNodes().stream().filter(o -> Objects.equals(o.getUserId(), ctx.getCurrentNodeUserId())).findFirst().orElse(null);
                auditNode.setState(ctx.getCurrentNodeAuditState());
                auditNode.setReason(ctx.getCurrentNodeReason());
                auditNode.setOpTime(new Date());


                if (Objects.equals(ctx.getCurrentNodeAuditState(),2)){
                    //驳回，触发驳回事件
                    comAuditMachine.fire(ComStates.getByValue(ctx.getCurrentState()),ComEvents.REJECT_AUDIT,ctx);
                }else{
                    long count = ctx.getAuditNodes().stream().filter(o -> Objects.equals(o.getState(), 0)).count();
                    dbExample.update(context.getDataId(),context);
                    if (count==0){
                        //审核通过事件
                        comAuditMachine.fire(ComStates.MULTI_AUDIT,ComEvents.PASS_AUDIT,ctx);
                    }
                }

//                dataSourceManager.commit(begin);
            }catch (Exception e){
//                dataSourceManager.rollback(begin);
            }
        };
    }

    /**
     * 所有人审核通过
     * @return
     */
    public Action<ComStates, ComEvents, ComContext> passAction(){
        return (from, to, event, ctx)-> {
            log.info("pass action ----->");
            //注意这里需要编程式事务处理，切面有问题哟
//            TransactionStatus begin = dataSourceManager.getTransaction(new DefaultTransactionAttribute());
            //执行审核
            try {
                //业务代码 1/2/3/4

                ComContext context=new ComContext();
                BeanUtils.copyProperties(ctx,context);
                context.setCurrentState(ComStates.SUCCESS.getValue());
                dbExample.update(context.getDataId(),context);
//                dataSourceManager.commit(begin);
            }catch (Exception e){
//                dataSourceManager.rollback(begin);
            }
        };
    }


    public Action<ComStates, ComEvents, ComContext> rejectAction(){
        return (from, to, event, ctx)-> {
            log.info("reject action ----->");
            //注意这里需要编程式事务处理，切面有问题哟
//            TransactionStatus begin = dataSourceManager.getTransaction(new DefaultTransactionAttribute());
            //执行审核
            try {
                //业务代码 1/2/3/4
                ComContext context=new ComContext();
                BeanUtils.copyProperties(ctx,context);
                context.setCurrentState(ComStates.REJECT.getValue());
                dbExample.update(context.getDataId(),context);
//                dataSourceManager.commit(begin);
            }catch (Exception e){
//                dataSourceManager.rollback(begin);
            }
        };
    }
}
