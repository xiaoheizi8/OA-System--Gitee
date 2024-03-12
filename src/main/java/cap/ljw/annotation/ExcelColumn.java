package cap.ljw.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Ljw
 * 2023.7.8
 * 可以把事务理解和手写spring一样
 */
@Retention(RetentionPolicy.RUNTIME)//保留在程序运行期间
@Target(ElementType.FIELD)
public @interface ExcelColumn {
    //列name
    String value()default "";
}
