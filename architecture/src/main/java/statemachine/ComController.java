package statemachine;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import statemachine.dto.ComContext;
import statemachine.events.ComEvents;
import statemachine.machine.ComAuditMachine;
import statemachine.status.ComStates;

import javax.annotation.Resource;

@RestController
public class ComController {

    @Resource
    DbExample dbExample;

    @Resource
    ComAuditMachine comAuditMachine;

    @GetMapping("/com/get")
    public ComContext get(String dataId){
        return dbExample.get(dataId);
    }

    @GetMapping("/com/submit")
    public Object submit(String dataId){
        ComContext context = dbExample.get(dataId);
        ComContext ctx=new ComContext();
        BeanUtils.copyProperties(context,ctx);
        return comAuditMachine.fire(ComStates.getByValue(context.getCurrentState()), ComEvents.SUBMIT, ctx);
    }

    @GetMapping("/com/cancel")
    public Object cancel(String dataId){
        ComContext context = dbExample.get(dataId);
        ComContext ctx=new ComContext();
        BeanUtils.copyProperties(context,ctx);
        return comAuditMachine.fire(ComStates.getByValue(context.getCurrentState()), ComEvents.CANCEL, ctx);
    }


    @GetMapping("/com/audit")
    public Object audit(String dataId,Long suid,Integer state,String msg){
        ComContext context = dbExample.get(dataId);
        ComContext ctx=new ComContext();
        BeanUtils.copyProperties(context,ctx);
        ctx.setSessionUserId(suid);
        ctx.setCurrentNodeUserId(suid);//设置一样，否则检查报错操作人员与当前登录用户不匹配
        ctx.setCurrentNodeAuditState(state);
        ctx.setCurrentNodeReason(msg);
        return comAuditMachine.fire(ComStates.getByValue(context.getCurrentState()),ComEvents.AUDIT,ctx);
    }
}
