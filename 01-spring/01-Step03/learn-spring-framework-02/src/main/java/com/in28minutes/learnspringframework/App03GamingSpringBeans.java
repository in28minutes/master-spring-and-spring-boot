package com.in28minutes.learnspringframework;

import com.in28minutes.learnspringframework.game.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class App03GamingSpringBeans {

    @Bean
    public GamingConsole game() {
        GamingConsole game = new PacManGame();
        return game;
    }

    @Bean
    public GameRunner gameRunner(GamingConsole game) {
        GameRunner gameRunner = new GameRunner(game);
        return gameRunner;
    }

    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context =
                      new AnnotationConfigApplicationContext
                              (App03GamingSpringBeans.class)) {

            context.getBean(GamingConsole.class).up();
            context.getBean(GameRunner.class).run();
        }

        //1: Object Creation
        //GamingConsole game = new MarioGame();
        //GamingConsole game = new SuperContraGame();
        GamingConsole game = new PacManGame();

        //2: Object Creation + Wiring of Dependencies
        // Game is a Dependency of GameRunner
        GameRunner gameRunner = new GameRunner(game);
        gameRunner.run();
    }

}
