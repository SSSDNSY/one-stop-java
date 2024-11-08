package features.jdk8;

import lombok.Data;
import org.assertj.core.util.Lists;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Desc

 * @Since 2023-06-30
 */
public class Java810FunctionalInterface {

    @FunctionalInterface
    public interface IMyInterface {
        void study();
    }


    public static void main(String[] args) {

        /**
         * 自定义函数接口
         *
         * @param args
         */
        IMyInterface iMyInterface = () -> {
            System.out.println("I like study");
        };
        iMyInterface.study();

        IMyInterface iMyInterface1 = new IMyInterface() {
            @Override
            public void study() {
                System.out.println("I like study1");
            }
        };
        iMyInterface1.study();


        /**
         * 内置四大函数接口
         */

        // 1. 消费型接口: Consumer< T> void accept(T t)有参数，无返回值的抽象方法；

        Consumer<Person> personConsumer = (p) -> System.out.println("Hello, " + p.getName());
        Consumer<Person> personConsumer1 = (p) -> {
            System.out.println("Hello, " + p.getName());
        };
        personConsumer.accept(new Person(1, "Skywalker"));
        personConsumer1.accept(new Person(1, "Skywalker1"));

        // 2. 供给型接口: Supplier < T> T get() 无参有返回值的抽象方法；
        Supplier<Person> personSupplier = Person::new;
        Person person = personSupplier.get();
        System.out.println(person);
        // collect()方法实现 CollectorImpl Collectors.toSet


        // 3. 断定型接口: Predicate<T> boolean test(T t):有参，但是返回值类型是固定的boolean
        Predicate<String> predicate = (s) -> s.length() > 0;

        boolean b1 = predicate.test("foo");
        boolean b2 = predicate.negate().test("foo");
        System.out.println(b1);
        System.out.println(b2);


        Predicate<Boolean> nonNull = Objects::nonNull;
        Predicate<Boolean> isNull = Objects::isNull;

        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> isNotEmpty = isEmpty.negate();

        // 4. 函数型接口: Function<T,R> R apply(T t)有参有返回值的抽象方法；
        // 比如: steam().map() 中参数就是Function<? super T, ? extends R>；
        // reduce()中参数BinaryOperator<T> (ps: BinaryOperator<T> extends BiFunction<T,T,T>)
        Function<String, Integer> toInteger = Integer::valueOf;
        Function<String, String> backToString = toInteger.andThen(String::valueOf);

        System.out.println(backToString.apply("123"));

        /**
         * 一些例子
         */

        // Comparator多属性排序: 先按名字不分大小写排，再按GID倒序排，最后按年龄正序排public static void main(String[] args) {
        // List<Person> personList = getTestList();
        // personList.sort(Comparator.comparing(Person::getName, String.CASE_INSENSITIVE_ORDER)
        //         .thenComparing(Person::getGid, (a, b) -> b.compareTo(a))
        //         .thenComparingInt(Person::getAge));
        // personList.stream().forEach(System.out::println);


        // 输出结果
        // Person [name=dai, gid=303, age=6]
        // Person [name=dai, gid=303, age=8]
        // Person [name=dai, gid=303, age=10]
        // Person [name=dai, gid=303, age=11]
        // Person [name=dai, gid=302, age=9]
        // Person [name=dai, gid=301, age=10]
        // Person [name=Li, gid=301, age=8]
        // Person [name=zhang, gid=302, age=9]
        // Person [name=zhang, gid=301, age=9]

        // 处理字符串两个新的方法可在字符串类上使用:join和chars。第一个方法使用指定的分隔符，将任何数量的字符串连接为一个字符串。
        System.out.println(
                String.join(":", "foobar", "foo", "bar")
        );


        // 第二个方法chars从字符串所有字符创建数据流，所以你可以在这些字符上使用流式操作。
        System.out.println(
                "foobar:foo:bar"
                        .chars()
                        .distinct()
                        .mapToObj(c -> String.valueOf((char) c))
                        .sorted()
                        .collect(Collectors.joining())
        );

        // 不仅仅是字符串，正则表达式模式串也能受益于数据流。我们可以分割任何模式串，并创建数据流来处理它们，而不是将字符串分割为单个字符的数据流，像下面这样:
        System.out.println(Pattern.compile(":")
                .splitAsStream("foobar:foo:bar")
                .filter(s -> s.contains("bar"))
                .sorted()
                .collect(Collectors.joining(":"))
        );
        // => bar:foobar

        // 此外，正则模式串可以转换为谓词。这些谓词可以像下面那样用于过滤字符串流:
        Pattern pattern = Pattern.compile(".*@gmail\\.com");
        System.out.println(Stream.of("bob@gmail.com", "alice@hotmail.com")
                .filter(pattern.asPredicate())
                .count());
        // => 1


        // warm up
        for (int i = 0; i < 101; i++)
            System.out.println(
                    "f(" + i + ") = " + fibonacci(i));

        // Local Cache实现 fibonacci数列
        // read -> cal
        long current = System.currentTimeMillis();
        System.out.println(fibonacci(100));
        System.out.println(System.currentTimeMillis() - current);
    }


    public static List<Person> getTestList() {
        return Lists.newArrayList(
                new Person("dai", "301", 10),
                new Person("dai", "303", 10),
                new Person("dai", "303", 8),
                new Person("dai", "303", 6),
                new Person("dai", "303", 11),
                new Person("dai", "302", 9),
                new Person("zhang", "302", 9),
                new Person("zhang", "301", 9),
                new Person("Li", "301", 8));
    }


    private static ConcurrentHashMap<Integer, Long> cache = new ConcurrentHashMap<>();

    static long fibonacci(int i) {
        if (i == 0)
            return i;

        if (i == 1)
            return 1;

        return cache.computeIfAbsent(i, (key) -> {
            System.out.println("Slow calculation of " + key);

            return fibonacci(i - 2) + fibonacci(i - 1);
        });
    }


}

@Data
class Person {
    private String name;
    private Integer age;
    private Integer id;
    private String gid;

    public Person() {
    }

    public Person(String name, String gid, Integer id) {
        this.name = name;
        this.gid = gid;
        this.id = id;
    }

    public Person(Integer id, String name) {
        this.name = name;
        this.id = id;
    }

}
