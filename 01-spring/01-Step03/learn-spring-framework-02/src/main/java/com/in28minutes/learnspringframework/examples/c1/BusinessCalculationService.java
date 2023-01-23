package com.in28minutes.learnspringframework.examples.c1;

import java.util.Arrays;

public class BusinessCalculationService {

    public DataService dataService;

    public BusinessCalculationService(DataService dataService) {
        this.dataService = dataService;
    }

    public int findMax() {
        return Arrays.stream(dataService.retrieveData()).max().orElse(0);
    }

    // without Spring Framework
    public static void main(String[] args) {
        BusinessCalculationService businessCalculationService =
                new BusinessCalculationService(new MongoDbDataService());
        System.out.println(businessCalculationService.findMax());
    }
}
