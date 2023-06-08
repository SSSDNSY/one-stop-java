package basic;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Desc
 * @Author pengzh
 * @Since 2023-06-07
 */
public class LombokTest {

    public static void main(String[] args) {
        OverrideSetter overrideSetter = new OverrideSetter();
        System.out.println(overrideSetter);
        overrideSetter.setName("jjjjjjjjjjjjjj");
        System.out.println(overrideSetter);
    }

}

@ToString
@Getter
@Setter
class OverrideSetter {
    private String name;

    public OverrideSetter setName(String name) {
        this.name = "1";
        return this;
    }
}