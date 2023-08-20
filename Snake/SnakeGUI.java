package Snake;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.Timer;

public class SnakeGUI {
    
    JFrame frame;

    SnakeGame panel;

    Timer move;

    public SnakeGUI() {
        
        frame = new JFrame("SNAKE GAME");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        panel = new SnakeGame();
        frame.add(panel);
        
        frame.pack();

        frame.addKeyListener(new SnakeMove());

        move = new Timer(100, new SnakeTimer());
        
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        move.start();
    }

    private class SnakeTimer implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            SnakeGame.move();
            SnakeGame.eat();
            SnakeGame.checkCollisions();
            SnakeGame.setPrevDirection();
            //SnakeGame.drawArray();
        }

    }

    private class SnakeMove implements KeyListener{

        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_W){
                    SnakeGame.setDirection('W');
                }
            if(e.getKeyCode() == KeyEvent.VK_S){
                
                    SnakeGame.setDirection('S');
            }
            if(e.getKeyCode() == KeyEvent.VK_A){
        
                    SnakeGame.setDirection('A');
            }
            if(e.getKeyCode() == KeyEvent.VK_D){
                    SnakeGame.setDirection('D');
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            // TODO Auto-generated method stub
           // throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
        }

        @Override
        public void keyTyped(KeyEvent e) {
            // TODO Auto-generated method stub
            //throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
        }

    }

    

}
