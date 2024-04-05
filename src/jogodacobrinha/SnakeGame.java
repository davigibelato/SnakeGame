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
 * Esta classe representa o jogo da cobrinha.
 */
public class SnakeGame extends JPanel implements ActionListener, KeyListener{
    /**
     * Classe interna que representa um quadrado do jogo.
     */
    private class Tile{
        int x;
        int y;
        
        Tile(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    // Tamanho da tela do jogo
    int larguraTela;
    int alturaTela;
    int tileSize = 25;
    
    //Cobra
    Tile snakeHead; // Cabeça da cobra
    ArrayList<Tile> snakeBody; // Corpo da cobra

    //Comida
    Tile food; // Comida
    Random random;

    //Lógica do jogo
    Timer gameLoop; // Loop do jogo
    int velocityX; // Velocidade em X
    int velocityY; // Velocidade em Y
    boolean gameOver = false; // Verifica se o jogo terminou

    SnakeGame(int larguraTela, int alturaTela){
        // Configuração do tamanho da tela
        this.larguraTela = larguraTela;
        this.alturaTela = alturaTela;
        setPreferredSize(new Dimension(this.larguraTela, this.alturaTela));
        // Configuração da cor de fundo da tela
        setBackground(Color.BLACK);
        addKeyListener(this);
        setFocusable(true);

        // Inicialização da cobra
        snakeHead = new Tile(5, 5);
        snakeBody = new ArrayList<Tile>();
        
        // Inicialização da comida
        food = new Tile(10, 10);
        random = new Random();
        placeFood();

        velocityX = 0;
        velocityY = 1;

        // Inicialização do loop do jogo
        gameLoop = new Timer(100, this);
        gameLoop.start();
    }
    
    public void paintComponent(Graphics g){
        // Desenho dos elementos do jogo
        super.paintComponent(g);
        draw(g);
    }
    
    public void draw(Graphics g){
        // Comida
        g.setColor(Color.red);
        g.fill3DRect(food.x * tileSize, food.y * tileSize, tileSize, tileSize, true);

        // Cabeça da cobra
        g.setColor(Color.green);
        g.fill3DRect(snakeHead.x * tileSize, snakeHead.y * tileSize, tileSize, tileSize, true);

        // Corpo da cobra
        for(int i = 0; i<snakeBody.size(); i++){
            Tile snakePart = snakeBody.get(i);
            g.fill3DRect(snakePart.x * tileSize, snakePart.y * tileSize, tileSize, tileSize, true);
        }

        // Pontuação
        g.setFont(new Font("Arial", Font.PLAIN,16));
        if (gameOver) {
            g.setColor(Color.red);
            g.drawString("Game Over: "+ String.valueOf(snakeBody.size()),tileSize - 16, tileSize);
        }
        else{
            g.drawString("Score: "+ String.valueOf(snakeBody.size()), tileSize - 16, tileSize);
        }
    }

    public void placeFood(){
        // Posiciona a comida em uma posição aleatória na tela
        food.x = random.nextInt(larguraTela/tileSize); //600/25 = 24
        food.y = random.nextInt(alturaTela/tileSize);  
    }

    public boolean collision(Tile tile1, Tile tile2){
        // Verifica se há colisão entre dois quadrados
        return tile1.x == tile2.x && tile1.y == tile2.y;
    }

    public void move(){
        // Movimento dos elementos do jogo

        // Comer a comida
        if(collision(snakeHead, food)){
            snakeBody.add(new Tile(food.x, food.y));
            placeFood();
        }

        // Movimento do corpo da cobra
        for(int i = snakeBody.size()-1; i >= 0; i--){
            Tile snakePart = snakeBody.get(i);
            if(i == 0){
                snakePart.x = snakeHead.x;
                snakePart.y = snakeHead.y;
            }
            else{
                Tile prevSnakePart = snakeBody.get(i-1);
                snakePart.x = prevSnakePart.x;
                snakePart.y = prevSnakePart.y;
            }
        }

        // Movimento da cabeça da cobra
        snakeHead.x += velocityX;
        snakeHead.y += velocityY;

        // Condições de fim de jogo
        for(int i = 0; i < snakeBody.size(); i++){
            Tile snakePart = snakeBody.get(i);
            if (collision(snakeHead, snakePart)) {
                gameOver = true;
            }
        }

        if (snakeHead.x*tileSize < 0 || snakeHead.x*tileSize > larguraTela ||   snakeHead.y*tileSize < 0 || snakeHead.y*tileSize > alturaTela) {
            gameOver =true;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
        if (gameOver) {
            gameLoop.stop();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Lógica para alterar a direção da cobra com base nas teclas pressionadas
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

    // Não é necessário
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
