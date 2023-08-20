package Snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JPanel;

public class SnakeGame extends JPanel{
    
    private static final int SIZE = 25;
    private static final int X = 900;
    private static final int Y = 600;
    
    private static final int XGRID = X/SIZE;
    private static final int YGRID = Y/SIZE;

    //private static final int snakeLength = 3;
    private static Random rand = new Random();

    private static int headX = 3;
    private static int headY = 1;

    private static int appleX = 0;
    private static int appleY = 0;

    private static int score = 0;

    private static LinkedList<Point> snake = new LinkedList<>();

    private static char snakeDirection = 'D';
    private static char prevDirection = 'D';

    private static boolean grow = false;

    public SnakeGame() {
        super();
        this.setPreferredSize(new Dimension(X,Y));
        init();
        
    }

    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        
        // Drawing Background
        g2.setColor(Color.BLACK);
        g2.fillRect(0,0,getWidth(), getHeight());

        drawSnake(g2);
        drawApple(g2);
        drawScore(g2);
        repaint();
    }

    public static void init() {
        snake.clear();
        snake.add(new Point(1,1));
        snake.add(new Point(2,1));
        snake.add(new Point(3,1));
        setDirection('D');
        headX = 3;
        headY = 1;
        randomizeApple();
    }

    public static void drawScore(Graphics2D g2){
        g2.setColor(Color.CYAN);
        g2.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
        g2.drawString("Score: "+score, X/2-100, 50);
    }

    public static void eat(){
        if(headX == appleX && headY == appleY){
            grow = true;
            randomizeApple();
            score++;
        }
    }

    public void drawApple(Graphics2D g2){
        g2.setColor(Color.RED);
        g2.fillRect(appleX*SIZE, appleY*SIZE, SIZE-1, SIZE-1);
    }

    public static void randomizeApple(){
        

        appleX = rand.nextInt(XGRID);
        appleY = rand.nextInt(YGRID);

        for(int i =0; i<snake.size(); i++){
            if(appleX == snake.get(i).getX() && appleY == snake.get(i).getY())
                randomizeApple();
        }
    }

    public void drawSnake(Graphics2D g2){

        g2.setColor(Color.GREEN);

        for(int i=0; i<snake.size(); i++){
            g2.fillRect((int)snake.get(i).getX()*SIZE, (int)snake.get(i).getY()*SIZE, SIZE-1, SIZE-1);
        }

    }

    public static void checkCollisions(){
        if(headX < 0 || headY < 0 || headX >= XGRID || headY >= YGRID)
            gameOver();

        
        for(int i =0; i<snake.size()-1; i++){
            if(headX == snake.get(i).getX() && headY == snake.get(i).getY())
                gameOver();
        }
    }

    public static void gameOver(){
        System.out.println("Game over");
        
        //new SnakeGame();
        
    }

    public static void move(){
        if(grow){
            grow = false;
        }
        else
            snake.remove();

        if(snakeDirection == 'W')
            headY--;
        if(snakeDirection == 'S')
            headY++;
        if(snakeDirection == 'D')
            headX++;
        if(snakeDirection == 'A')
            headX--;

        snake.add(new Point(headX, headY));
    }

    public static void setDirection(char c) {
        if(c == 'W' && prevDirection != 'S')
            snakeDirection = c;
        if(c == 'S' && prevDirection != 'W')
            snakeDirection = c;
        if(c == 'A' && prevDirection != 'D')
            snakeDirection = c;
        if(c == 'D' && prevDirection != 'A')
            snakeDirection = c;
        
        
        
    }

    public static void setPrevDirection(){
        prevDirection = snakeDirection;
    }

   
}
