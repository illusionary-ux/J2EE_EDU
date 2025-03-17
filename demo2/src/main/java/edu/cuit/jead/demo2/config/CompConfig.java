package edu.cuit.jead.demo2.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"edu.cuit.jead.demo2.kernel",
        "edu.cuit.jead.demo2.callers",
        "edu.cuit.jead.demo2.gateway"})

public class CompConfig {
}
