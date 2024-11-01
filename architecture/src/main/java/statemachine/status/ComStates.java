package statemachine.status;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

@Getter
public enum ComStates {

    PENDING_SUBMIT(0, "待提审"),
    PENDING_AUDIT(1, "待处理"),
    MULTI_AUDIT(2, "审核中（多人）"),
    SUCCESS(3, "已通过"),
    REJECT(4, "已驳回"),
    CANCEL(5, "已取消"),
    ;
    private final Integer value;
    private final String name;

    ComStates(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public static ComStates getByValue(Integer value) {
        return new ArrayList<>(Arrays.asList(ComStates.values())).stream().filter(o -> Objects.equals(o.value, value)).findFirst().orElse(null);
    }
}
