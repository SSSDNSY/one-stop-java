package statemachine;

import org.springframework.stereotype.Component;
import statemachine.dto.ComContext;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class DbExample {

    static Map<String, ComContext> database = new ConcurrentHashMap<>();

    static {
        ComContext context1 = new ComContext();
        context1.setDataId("1");
        context1.setCurrentState(0);
        context1.setCurrentNodeUserId(1L);
        ComContext.AuditNode auditNode1 = new ComContext.AuditNode();

        auditNode1.setUserId(1L);
        context1.setAuditNodes(new ArrayList<>());
        context1.getAuditNodes().add(auditNode1);

        ComContext.AuditNode auditNode2 = new ComContext.AuditNode();
        auditNode2.setUserId(2L);
        context1.getAuditNodes().add(auditNode2);

        database.put("1", context1);
    }


    public ComContext get(String id) {
        return database.get(id);
    }

    public void update(String id, ComContext context) {
        database.put(id, context);
    }
}
