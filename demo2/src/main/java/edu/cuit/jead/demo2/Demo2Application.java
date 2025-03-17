package edu.cuit.jead.demo2;

import edu.cuit.jead.demo2.config.CompConfig;
import edu.cuit.jead.demo2.gateway.CallerGateway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class Demo2Application {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CompConfig.class);
        CallerGateway gateway = context.getBean(CallerGateway.class);
        //从Spring容器中获取CallerGateway类的Bean实例
        //context是AnnotationConfigApplicationContext的实例，表示Spring容器
        gateway.doSome();
        //调用CallerGateway实例的doSome方法，启动程序的业务逻辑
    }

}
//AnnotationConfigApplicationContext加载CompConfig配置类。
//Spring容器扫描com.example.kernel, com.example.callers, com.example.gateway包下的所有类，并将带有@Component注解的类注册为Bean。

