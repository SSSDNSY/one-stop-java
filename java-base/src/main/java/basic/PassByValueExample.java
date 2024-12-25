package basic;

public class PassByValueExample {
    public static void main(String[] args) {
        Cat cat = new Cat("A");
        System.out.println(cat.getObjectAddress()); // Dog@4554617c
        System.out.println("func前");
        func(cat);
        System.out.println("func后");
        System.out.println(cat.getObjectAddress()); // Dog@4554617c
        System.out.println(cat.getName());          // A
    }

    private static void func(Cat cat) {
        System.out.println(cat.getObjectAddress()); // Dog@4554617c
        cat = new Cat("B");
        System.out.println(cat.getObjectAddress()); // Dog@74a14482
        System.out.println(cat.getName());          // B
    }
}

class Cat {
    String name;

    Cat(String name) {
        this.name = name;
    }

    String getName() {
        return this.name;
    }

    void setName(String name) {
        this.name = name;
    }

    String getObjectAddress() {
        return super.toString();
    }
}