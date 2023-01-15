package com.in28minutes.learnspringframework;

import com.in28minutes.learnspringframework.game.*;

public class App01GamingBasic {

    public static void main(String[] args) {
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
