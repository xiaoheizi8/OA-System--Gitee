package cap.ljw;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Ljw
 * 2023.7.4
 * 启动程序
 */
@MapperScan("cap.ljw.mapper")//全局mapper层扫描
@SpringBootApplication
@EnableTransactionManagement//事务管理
//@EnableAspectJAutoProxy
public class BootApplication extends SpringBootServletInitializer {
    @Override  //这个表示使用外部的tomcat容器
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 注意这里要指向原先用main方法执行的启动类
        return builder.sources(BootApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(BootApplication.class,args);

    }
}
