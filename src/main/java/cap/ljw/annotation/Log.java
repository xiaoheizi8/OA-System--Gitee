package cap.ljw.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Ljw
 * 2023.8.27补充日志
 * 以注解事务方式实现,在方法尚添加日志记录
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)//反射读取注解信息 注解运行的时候保留
public @interface Log {
    String value() default "";
}

