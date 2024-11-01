package statemachine.events;

import lombok.Getter;

@Getter
public enum ComEvents {
    SUBMIT(1, "提交审核"),
    CANCEL(2, "撤销审核"),
    AUDIT(3, "审核中"),
    PASS_AUDIT(4, "通过审核"),
    REJECT_AUDIT(5, "驳回审核");
    private final Integer value;
    private final String name;

    ComEvents(Integer value, String name) {
        this.value = value;
        this.name = name;
    }
}
