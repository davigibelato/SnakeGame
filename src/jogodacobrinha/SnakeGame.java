/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogodacobrinha;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
/**
 *
 * @author Senai
 */
public class SnakeGame extends JPanel{
    private class Tile{
        int x;
        int y;
        
        Tile(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    int larguraTela;
    int alturaTela;
    int tileSize = 25;
    
    Tile snakeHead;
    
    SnakeGame(int larguraTela, int alturaTela){
        //dimensionando o tamanho da tela
        this.larguraTela = larguraTela;
        this.alturaTela = alturaTela;
        setPreferredSize(new Dimension(this.larguraTela, this.alturaTela));
        //definindo a cor de fundo da tela
        setBackground(Color.BLACK);
        
        snakeHead = new Tile(5, 5);
        
    }
    
    public void paintComponent(Graphics g){
        //pintando o quadradinho
        super.paintComponent(g);
        desenhar(g);
    }
    
    public void desenhar(Graphics g){
        //Cobra
        g.setColor(Color.green);
        g.fillRect(snakeHead.x, snakeHead.y, tileSize, tileSize);
    }
}
