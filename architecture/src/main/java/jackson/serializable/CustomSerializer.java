package jackson.serializable;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import jackson.pojo.Person;

import java.io.IOException;

/**
 * @desc
 * @class CustomSerializer
 * @since 2022-02-11
 */
public class CustomSerializer extends StdSerializer<Person> {
    public CustomSerializer() {
        super(Person.class);
    }

    @Override
    public void serialize(Person person, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeStartObject();
        jgen.writeNumberField("age", person.getAge());
        jgen.writeStringField("name", person.getName());
        jgen.writeStringField("msg", "已被自定义序列化");
        jgen.writeEndObject();
    }

}
