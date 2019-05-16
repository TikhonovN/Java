package annotations;

import java.lang.annotation.*;
import static java.lang.annotation.ElementType.*;

/**
 * Вешается на методы, которые являются тестами
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Test {
    Class expected() default Exception.class;
}