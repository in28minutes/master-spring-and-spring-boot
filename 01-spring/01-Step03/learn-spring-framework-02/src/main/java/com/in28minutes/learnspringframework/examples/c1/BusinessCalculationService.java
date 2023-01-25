package com.in28minutes.learnspringframework.examples.c1;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class BusinessCalculationService {

    private DataService dataService;

    public BusinessCalculationService(DataService dataService) {
        super();
        this.dataService = dataService;
    }

    public int findMax() {
        return Arrays.stream(dataService.retrieveData()).max().orElse(0);
    }

    // without Spring Framework
    /*public static void main(String[] args) {
        BusinessCalculationService businessCalculationService =
                new BusinessCalculationService(new MongoDbDataService());
        System.out.println(businessCalculationService.findMax());
    }*/

    // with Spring Framework
    // See RealWorldSpringContextLauncherApplication Class
}
