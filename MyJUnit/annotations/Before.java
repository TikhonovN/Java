/*помечает метод, который вызывается перед каждым тестом*/

package annotations;

import java.lang.annotation.*;
import static java.lang.annotation.ElementType.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Before{}