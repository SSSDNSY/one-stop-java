package features.annotation;

import java.lang.annotation.*;

/**
 * @date 2020-03-11
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface JDBCconfig {
    String host() default "127.0.0.1";

    int port() default 3306;

    String database();

    String charSet();

    String userName();

    String passWord();
}
