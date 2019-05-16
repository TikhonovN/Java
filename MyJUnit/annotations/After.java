package annotations;

import java.lang.annotation.*;
import static java.lang.annotation.ElementType.*;

/**
 * Помечает метод, который вызывается после каждого теста.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface After {}