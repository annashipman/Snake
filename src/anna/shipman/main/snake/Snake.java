package anna.shipman.main.snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JPanel;

public class Snake extends JPanel {

  private final Color color;

  private Point headOfSnake;
  private LinkedList<Point> snakeList;
  
  private String currentDirection = "Right";
  private Queue<String> directionQueue = new LinkedList<String>(); //how to make it max 2?

  public Snake() {
    color = Color.WHITE;
    initialiseSnake();
  }

  private void initialiseSnake() {
    headOfSnake = GameParameters.SNAKE_INITIAL_HEAD;
    currentDirection = GameParameters.SNAKE_INITIAL_DIRECTION;
    snakeList = new LinkedList<Point>();
    int counter = 0;
    while (counter < GameParameters.SNAKE_INITIAL_LENGTH) {
      final Point newPoint = new Point(headOfSnake);
      snakeList.addFirst(newPoint);
      headOfSnake = getNextHeadOfSnake();
      counter++;
    }
  }

  @Override
  public void paint(Graphics graphicsContext) {
    graphicsContext.setColor(color);
    final Iterator<Point> iterator = snakeList.iterator();
    while (iterator.hasNext()) {
      // this is where the concurrent modification exception is
      final Point snakeCoord = iterator.next();
      graphicsContext.drawRect(snakeCoord.x,
          snakeCoord.y, GameParameters.SNAKE_SQUARE,
          GameParameters.SNAKE_SQUARE);
    }
  }

  public void move(Point nextHeadOfSnake) {
    snakeList.addFirst(nextHeadOfSnake);
    snakeList.removeLast();
  }

  public void eat(final Point nextHeadOfSnake) {
    snakeList.addFirst(nextHeadOfSnake);

  }

  public boolean hasDirectionWaiting(){
    boolean directionWaiting = !directionQueue.isEmpty();
    return directionWaiting;
  }
  
  public Point getNextHeadOfSnake() {
    if (hasDirectionWaiting()){
      currentDirection = directionQueue.poll();
    }
    final Point nextHeadOfSnake = new Point(snakeList.getFirst());
    if (currentDirection.equals("Up")) {
      nextHeadOfSnake.y -= GameParameters.SNAKE_SQUARE;
    }
    if (currentDirection.equals("Down")) {
      nextHeadOfSnake.y += GameParameters.SNAKE_SQUARE;
    }
    if (currentDirection.equals("Right")) {
      nextHeadOfSnake.x += GameParameters.SNAKE_SQUARE;
    }
    if (currentDirection.equals("Left")) {
      nextHeadOfSnake.x -= GameParameters.SNAKE_SQUARE;
    }
    return nextHeadOfSnake;
  }

  public boolean nextPointIsPartOfSnake(final Point nextCoord) {
    boolean coordinateIsInSnake = false;
    if (snakeList.contains(nextCoord)) {
      coordinateIsInSnake = true;
    }
    return coordinateIsInSnake;
  }


  public void setDirectionOfSnake(String keyString) {

    boolean addToQueue = true;

    addToQueue = ifNothingInQueueDontAddIfSameOrOppositeOfCurrentDirection(keyString);
    
    if (addToQueue) {
      addToQueue = ifSomethingInQueueDontAddIfItsTheSameOrOppositeOfThat(keyString);
    }
    
    if (addToQueue) {
      addToQueue = ifThereAreAlreadyTwoDirectionsWaitingDoNotAdd();
    }
    
    if (addToQueue) {
      directionQueue.add(keyString);
    }
  }
    
  private boolean ifNothingInQueueDontAddIfSameOrOppositeOfCurrentDirection(final String inputDirection) {
    if (directionQueue.isEmpty() && (inputDirection.equalsIgnoreCase(currentDirection) || getOpposite(inputDirection).equalsIgnoreCase(currentDirection) )){
      return false;
    }
    return true;
  }

  private boolean ifSomethingInQueueDontAddIfItsTheSameOrOppositeOfThat(final String inputDirection) {
    if (directionQueue.size() == 1 && ( inputDirection.equalsIgnoreCase(currentDirection) || getOpposite(inputDirection).equalsIgnoreCase(directionQueue.peek()) ) ){
      return false;
    }
    return true;
  }

  private boolean ifThereAreAlreadyTwoDirectionsWaitingDoNotAdd() {
    if (directionQueue.size() > 1){
      return false;
    }
    return true;
  }


  private static String getOpposite(String keyString) {
    String opposite = "Right";
    if (keyString.equalsIgnoreCase("Right")) {
      opposite = "Left";
    } else if (keyString.equalsIgnoreCase("Up")) {
      opposite = "Down";
    } else if (keyString.equalsIgnoreCase("Down")) {
      opposite = "Up";
    }
    return opposite;
  }

}
