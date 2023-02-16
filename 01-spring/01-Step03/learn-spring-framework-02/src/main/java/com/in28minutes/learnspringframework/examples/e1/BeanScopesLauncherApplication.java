package com.in28minutes.learnspringframework.examples.e1;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
class NormalClass {

}

@Scope(value= ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
class PrototypeClass {

}

@Configuration
@ComponentScan
public class BeanScopesLauncherApplication {

    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context =
                      new AnnotationConfigApplicationContext
                              (BeanScopesLauncherApplication.class)) {

            System.out.println(context.getBean(NormalClass.class));
            System.out.println(context.getBean(NormalClass.class));

            System.out.println(context.getBean(PrototypeClass.class));
            System.out.println(context.getBean(PrototypeClass.class));
            System.out.println(context.getBean(PrototypeClass.class));

            /*
            SAMPLE OUTPUT:
            --------------
            com.in28minutes.learnspringframework.examples.e1.NormalClass@1356d4d4
            com.in28minutes.learnspringframework.examples.e1.NormalClass@1356d4d4
            com.in28minutes.learnspringframework.examples.e1.PrototypeClass@c03cf28
            com.in28minutes.learnspringframework.examples.e1.PrototypeClass@1329eff
            com.in28minutes.learnspringframework.examples.e1.PrototypeClass@6497b078
             */

        }
    }
}
