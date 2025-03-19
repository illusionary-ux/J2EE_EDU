package edu.cuit.jead.demo2.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = {"edu.cuit.jead.demo2.kernel",
        "edu.cuit.jead.demo2.callers",
        "edu.cuit.jead.demo2.gateway",
        "edu.cuit.jead.demo2.aop",
})
@EnableAspectJAutoProxy
public class CompConfig {
}
