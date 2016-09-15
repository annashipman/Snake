package anna.shipman.main.snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;

public class Board extends JFrame {

  private static JPanel panel = new JPanel();
  
  public Board(final Snake snake, final Food food, final KeyListener directionListener) {
    addKeyListener(directionListener);
    setBackground(Color.BLACK);
    final OverlayLayout overlay = new OverlayLayout(panel);

    panel.setLayout(overlay);
    panel.add(food);
    panel.add(snake);
    panel.setPreferredSize(new Dimension(GameParameters.BOARD_X_AXIS,
        GameParameters.BOARD_Y_AXIS));

    setContentPane(panel);
    setTitle("Snake");

    pack();
    setVisible(true);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
  }

  // @Override
  // public void paint(Graphics g){
  // String msg = "Game Over";
  // Font small = new Font("Helvetica", Font.BOLD, 14);
  // FontMetrics metr = this.getFontMetrics(small);
  //
  // g.setColor(Color.white);
  // g.setFont(small);
  // g.drawString(msg, (WIDTH - metr.stringWidth(msg)) / 2, HEIGHT / 2);
  //
  // }




  public int getRightBoundary() {
    return GameParameters.BOARD_X_AXIS;
  }

  public int getLeftBoundary() {
    int boundary = (0 - GameParameters.SNAKE_SQUARE);
    return boundary;
  }

  public int getTopBoundary() {
    int boundary = (0 - GameParameters.SNAKE_SQUARE);
    return boundary;
  }

  public int getBottomBoundary() {
    return GameParameters.BOARD_Y_AXIS;
  }

}
