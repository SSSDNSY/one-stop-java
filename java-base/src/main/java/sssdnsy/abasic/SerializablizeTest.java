package sssdnsy.abasic;

import java.io.*;

/**
 *
 * 序列化、反序列化
 *
 * @author pengzh
 * @since 2020-08-07
 */
public class SerializablizeTest {
    public static void main(String[] args) {
//        Serialize();
        UnSerialize();
    }

    static void Serialize(){
        Person person = new Person(11,192,"Mr. White");
        File personObjFile = new File("person.obj");

        try ( ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(personObjFile))){
            oos.writeObject(person);
            System.out.println("序列化完成！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void UnSerialize(){
        try (  ObjectInputStream iis = new ObjectInputStream(new FileInputStream("person.obj"))){
            final Person object = (Person)iis.readObject();
            System.out.println("反序列化完成！"+object);
        } catch (IOException|ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}


class Person implements Serializable{

    private int age;
    private transient int tall;
    private String name;
    public Person(){

    }
    public Person(int age, int tall, String name) {
        this.age = age;
        this.tall = tall;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getTall() {
        return tall;
    }

    public void setTall(int tall) {
        this.tall = tall;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", tall=" + tall +
                ", name='" + name + '\'' +
                '}';
    }
}
