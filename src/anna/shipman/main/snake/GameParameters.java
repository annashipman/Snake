package anna.shipman.main.snake;

import java.awt.Point;

public class GameParameters {

  public static final int BOARD_X_AXIS = 200;
  public static final int BOARD_Y_AXIS = 200;
  public static final int SNAKE_SQUARE = 10;
  public static final int FOOD_SQUARE = 6;
  public static final int OFFSET = (SNAKE_SQUARE - FOOD_SQUARE) / 2;

  public static final Point SNAKE_INITIAL_HEAD = new Point(50, 50);
  public static final int SNAKE_INITIAL_LENGTH = 5;
  public static final String SNAKE_INITIAL_DIRECTION = "Right";
  public static final Point FOOD_INITIAL_POINT = new Point(152, 152);

  public static final int LEVEL = 9;
  public static final int GAME_SPEED = 550 - (LEVEL * 50);

  public static int SCORE = 0;
}
