package basic;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @since 2020-06-29
 */
public class AnnotationRetentionTest {


    @Retention(RetentionPolicy.SOURCE)
    @interface SourceRetention {

    }

    @Retention(RetentionPolicy.CLASS)
    @interface ClassRetention {

    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface RuntimeRetention {

    }
}
