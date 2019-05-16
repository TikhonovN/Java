package annotations;

import java.lang.annotation.*;
import static java.lang.annotation.ElementType.*;

/**
 * Помечает метод, который вызывается перед каждым тестом
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Before {}