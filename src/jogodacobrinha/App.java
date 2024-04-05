/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogodacobrinha;

import javax.swing.*;

/**
 *
 * @author Senai
 */
public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int larguraTela = 600;
        int alturaTela = larguraTela;

        JFrame frame = new JFrame("Snake");
        frame.setVisible(true);
        frame.setSize(larguraTela, alturaTela);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        SnakeGame snakeGame = new SnakeGame(larguraTela, alturaTela);
        frame.add(snakeGame);
        frame.pack();
        snakeGame.requestFocus();
    }

}
