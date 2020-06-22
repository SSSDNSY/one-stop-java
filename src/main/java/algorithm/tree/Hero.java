package algorithm.tree;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

/**
 * @description:测试英雄类
 * @author: pengzh
 * @createDate: 2019/6/5$ 15:51$
 */
public class Hero {
    private int hp;
    private int id;

    public Hero() {
    }

    public Hero(int id, int hp) {
        this.id = id;
        this.hp = hp;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return this.id+" "+this.hp;
    }
}
