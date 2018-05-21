package xyz.dgz48.redman.web.flow;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * ウェブフローの終了を示す.
 *
 * @author win2cot
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface FlowEnd {

}
