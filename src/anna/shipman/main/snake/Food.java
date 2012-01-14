package anna.shipman.main.snake;

import java.util.Random;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

public class Food extends JPanel {

	private Point food = GameParameters.FOOD_INITIAL_POINT;
	private Point containingSquare = new Point();
	
	private final Color colour;
	private final Random generator = new Random();

	public Food() {
		colour = Color.RED;
		setFoodsContainingSquare();
	}

	public void paint(Graphics graphicsContext) {
		graphicsContext.setColor(colour);
		graphicsContext.fillRect(food.x, food.y, GameParameters.FOOD_SQUARE,
				GameParameters.FOOD_SQUARE);
	}

	public void createNewFood() {
		int x = getRandomX();
		int y = getRandomY();
		
		x = positionCorrectlyOnBoard(x);
		y = positionCorrectlyOnBoard(y);
		
		if (x == food.x && y == food.y){
			createNewFood();
		} else {
			food.x = x;
			food.y = y;
			setFoodsContainingSquare();
		}
	}

	private int getRandomX() {
		int x = generator.nextInt(GameParameters.BOARD_X_AXIS
				/ GameParameters.SNAKE_SQUARE);
		return x;
	}

	private int getRandomY() {
		int y = generator.nextInt(GameParameters.BOARD_Y_AXIS
				/ GameParameters.SNAKE_SQUARE);
		return y;
	}

	private int positionCorrectlyOnBoard(int coordinate) {
		coordinate = coordinate * GameParameters.SNAKE_SQUARE;
		coordinate = coordinate + GameParameters.OFFSET;
		return coordinate;
	}

	public boolean nextSquareContainsFood(Point nextPoint) {
		if (nextPoint.x == containingSquare.x
				&& nextPoint.y == containingSquare.y) {
			return true;
		}
		return false;
	}

	private void setFoodsContainingSquare() {
		containingSquare.x = food.x - GameParameters.OFFSET;
		containingSquare.y = food.y - GameParameters.OFFSET;
	}

}