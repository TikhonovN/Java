/**
 * Вешается на методы, которые являются тестами
 */

package annotations;

import java.lang.annotation.*;
import static java.lang.annotation.ElementType.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Test {
    Class expected() default Exception.class;
}