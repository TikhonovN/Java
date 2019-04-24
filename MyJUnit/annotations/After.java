/*помечает метод, который (внезапно) вызывается после каждого теста*/

package annotations;

import java.lang.annotation.*;
import static java.lang.annotation.ElementType.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface After {}