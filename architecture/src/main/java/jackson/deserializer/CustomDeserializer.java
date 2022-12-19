package middleware.jackson.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.IntNode;
import middleware.jackson.pojo.Person;

import java.io.IOException;

/**
 * @author pengzh
 * @desc 自定义反序列化
 * @class CustomSerializer
 * @since 2022-02-11
 */
public class CustomDeserializer extends StdDeserializer<Person> {
    public CustomDeserializer() {
        super(Person.class);
    }

    @Override
    public Person deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        Person person = new Person();
        int age = (Integer) ((IntNode) node.get("age")).numberValue();
        String name = node.get("name").asText();
        person.setAge(age + 1);
        person.setName(name);
        return person;
    }
}
