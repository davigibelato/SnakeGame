/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogodacobrinha;
import java.awt.*;
import java.awt.event.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

/**
 *
 * @author Senai
 */
public class SnakeGame extends JPanel implements ActionListener, KeyListener{
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
    
    //Snake
    Tile snakeHead;
    ArrayList<Tile> snakeBody;

    //Food
    Tile food;
    Random random;

    //game logic
    Timer gameLoop;
    int velocityX;
    int velocityY;

    SnakeGame(int larguraTela, int alturaTela){
        //dimensionando o tamanho da tela
        this.larguraTela = larguraTela;
        this.alturaTela = alturaTela;
        setPreferredSize(new Dimension(this.larguraTela, this.alturaTela));
        //definindo a cor de fundo da tela
        setBackground(Color.BLACK);
        addKeyListener(this);
        setFocusable(true);

        snakeHead = new Tile(5, 5);
        snakeBody = new ArrayList<Tile>();
        
        food = new Tile(10, 10);
        random = new Random();
        palceFood();

        velocityX = 0;
        velocityY = 1;

        gameLoop = new Timer(100, this);
        gameLoop.start();
    }
    
    public void paintComponent(Graphics g){
        //pintando o quadradinho
        super.paintComponent(g);
        desenhar(g);
    }
    
    public void desenhar(Graphics g){
        //Grid
        for(int i = 0; i < larguraTela/tileSize; i++){
            //(x1,y1,x2,y2)
            g.drawLine(i*tileSize, 0, i*tileSize, alturaTela);
            g.drawLine(0, i*tileSize, larguraTela, i*tileSize);
        }

        //Food
        g.setColor(Color.red);
        g.fillRect(food.x * tileSize, food.y * tileSize, tileSize, tileSize);

        //Snake Head
        g.setColor(Color.green);
        g.fillRect(snakeHead.x * tileSize, snakeHead.y * tileSize, tileSize, tileSize);
    }

    public void palceFood(){
        food.x = random.nextInt(larguraTela/tileSize); //600/25 = 24
        food.y = random.nextInt(alturaTela/tileSize);  
    }

    public boolean collision(Tile tile1, Tile tile2){
        //Colisão dos quadrados
        return tile1.x == tile2.x && tile1.y == tile2.y;
    }

    public void move(){

        //eat food
        if(collision(snakeHead, food)){
            snakeBody.add(new Tile(food.x, food.y));
            palceFood();
        }


        //Snake Head -- cabeça da cobrinha
        snakeHead.x += velocityX;
        snakeHead.y += velocityY;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if(e.getKeyCode() == KeyEvent.VK_UP && velocityY != 1){
            velocityX= 0;
            velocityY = -1;
        }
        else if(e.getKeyCode() == KeyEvent.VK_DOWN && velocityY != -1){
            velocityX = 0;
            velocityY = 1;
        }
        else if(e.getKeyCode() == KeyEvent.VK_LEFT && velocityX != 1){
            velocityX = -1;
            velocityY = 0;
        }
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT && velocityX != -1){
            velocityX = 1;
            velocityY = 0;
        }
    }

    //não ne necessário
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
