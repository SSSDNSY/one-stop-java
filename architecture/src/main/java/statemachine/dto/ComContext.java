package statemachine.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 上下文对象
 */
@Data
public class ComContext {
    String dataId;
    /**
     * 当前状态
     */
    Integer currentState;
    /**
     * 当前会话操作人
     */
    Long sessionUserId;
    /**
     * 当前节点处理人
     */
    Long currentNodeUserId;
    /**
     * 当前审核状态（对多人）
     */
    Integer currentNodeAuditState;
    /**
     * 当前审核状态为2- 驳回，时候原因数据
     */
    String currentNodeReason;

    /**
     * 多人审批节点
     */
    List<AuditNode> auditNodes;

    @Data
    public static class AuditNode {
        /**
         * 用户
         */
        Long userId;
        /**
         * 审核状态
         * 0 - 未审核
         * 1 - 审核通过
         * 2 - 审核驳回
         */
        Integer state = 0;

        /**
         * 当状态为2时候填写驳回原因
         */
        String reason;
        /**
         * 操作时间
         */
        Date opTime;

        /**
         * 排序值,越打越靠前
         */
        int order = 0;
    }
}
