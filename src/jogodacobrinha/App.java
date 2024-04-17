package jogodacobrinha;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class App {
    public static void main(String[] args) {
        int larguraTela = 400;
        int alturaTela = larguraTela;

        JFrame frame = new JFrame("Snake");
        frame.setVisible(true);
        frame.setSize(larguraTela, alturaTela);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel buttonPanel = new JPanel();
        JButton closeButton = new JButton("Fechar");

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        buttonPanel.add(closeButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        jogodacobrinha.SnakeGame snakeGame = new jogodacobrinha.SnakeGame(larguraTela, alturaTela);
        frame.add(snakeGame);
        frame.pack();
        snakeGame.requestFocus();
    }
}
