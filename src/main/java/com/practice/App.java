package com.practice;

import com.practice.config.AppConfig;
import com.practice.services.ContractService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App
{
    public static void main( String[] args ) {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        ContractService contractService = context.getBean(ContractService.class);

    }
}
