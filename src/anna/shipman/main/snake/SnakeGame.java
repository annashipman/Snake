package anna.shipman.main.snake;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

public class SnakeGame {

  private Snake snake = new Snake();
  private Food food = new Food();
  private KeyListener directionListener = new DirectionInputFromKeyboard();

  private Board board = new Board(snake, food, directionListener);

  private Timer snakeTimer;

  private int score = 0;
  
  private SnakeGame() {
    snakeTimer = new Timer();
    snakeTimer.schedule(new SnakeTimerTask(), 0, GameParameters.GAME_SPEED);
  }

  public static void main(String args[]) {
    new SnakeGame();
  }

  class SnakeTimerTask extends TimerTask {

    @Override
    public void run() {
      final Point nextHead = snake.getNextHeadOfSnake();
      if (nextHeadIsNotLegal(nextHead)) {
        gameOver();
      } else {
        moveSnake(nextHead);
      }
      if (snake.hasDirectionWaiting()){
        run();
      }
    }
  }

  public void gameOver() {
    //TODO here I want to print Game Over and score to board
    //as well as some options - new game, etc.
    //for now they are printed to console and board is closed
    snakeTimer.cancel();
    board.repaint();
    System.out.println("Game Over!");
    System.out.println("Score: " + score);
    board.dispose();
  }
  
  public boolean nextHeadIsNotLegal(Point nextHead) {
    boolean illegal = nextHeadIsPartOfWall(nextHead);
    if (!illegal) {
      illegal = snake.nextPointIsPartOfSnake(nextHead);
    }
    return illegal;
  }
  
  private void moveSnake(final Point nextHead) {
    if (food.nextSquareContainsFood(nextHead)) {
      snake.eat(nextHead);
      System.out.println("Score: " + score);
      createNewFood();
      updateScore();
    } else {
      snake.move(nextHead);
    }
    board.repaint();
  }

  private void updateScore() {
    score = score + GameParameters.LEVEL;
  }

  public void createNewFood() {
    food.createNewFood();
  }

  public boolean nextHeadIsPartOfWall(Point nextPoint) {
    boolean nextPointIsInWall = false;
    if (nextPoint.getX() == board.getLeftBoundary()
        || nextPoint.getY() == board.getTopBoundary()
        || nextPoint.getX() == board.getRightBoundary()
        || nextPoint.getY() == board.getBottomBoundary()) {
      System.out.println("Wall!");
      nextPointIsInWall = true;
    }
    return nextPointIsInWall;
  }
  
  private class DirectionInputFromKeyboard implements KeyListener {

    public void keyPressed(KeyEvent keyEvent) {
      passDirectionToSnake(keyEvent);
    }

    private void passDirectionToSnake(KeyEvent keyEvent) {
      String keyString;

      switch (keyEvent.getKeyCode()) {
        case KeyEvent.VK_UP:
            keyString = "Up";
            break;
        case KeyEvent.VK_DOWN:
            keyString = "Down";
            break;
        case KeyEvent.VK_RIGHT:
            keyString = "Right";
            break;
        case KeyEvent.VK_LEFT:
            keyString = "Left";
            break;
        default:
            keyString = "Ignore";
        }

      if (keyString != "Ignore") {
        snake.setDirectionOfSnake(keyString);
      }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }
  }
  

}
