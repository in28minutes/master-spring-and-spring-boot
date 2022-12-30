package com.in28minutes.learnspringframework;

import com.in28minutes.learnspringframework.game.GameRunner;
import com.in28minutes.learnspringframework.game.MarioGame;
import com.in28minutes.learnspringframework.game.SuperContraGame;

public class AppGamingBasic {

    public static void main(String[] args) {
        MarioGame marioGame = new MarioGame();
        SuperContraGame superContraGame = new SuperContraGame();
        GameRunner gameRunner = new GameRunner(superContraGame);
        gameRunner.run();
    }

}
