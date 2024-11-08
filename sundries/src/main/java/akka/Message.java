package akka;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Desc

 * @Since 2023-11-30
 */
@Getter
@Setter
@ToString
public class Message implements Serializable {

    String name;

    public Message(String s) {
        this.name = s;
    }
}
