package cjuc.concurrent.disrupter.base;
/**
*@Description: 基本事件单元
*@Author: pengzh
*@date: 2019/9/16
*/
public class LongEvent {
    private long value;

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }
}
