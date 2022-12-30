package com.in28minutes.learnspringframework;

import com.in28minutes.learnspringframework.game.*;

public class AppGamingBasic {

    public static void main(String[] args) {
        //GamingConsole game = new MarioGame();
        //GamingConsole game = new SuperContraGame();
        GamingConsole game = new PacManGame();

        GameRunner gameRunner = new GameRunner(game);
        gameRunner.run();
    }

}
