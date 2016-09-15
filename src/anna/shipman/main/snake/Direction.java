package anna.shipman.main.snake;

public enum Direction {
  LEFT("Right"),
  RIGHT("Left"),
  UP("Down"),
  DOWN("Up");
  
  private String opposite;

  Direction(final String opposite){
    this.opposite = opposite;
  }
  
  public String getOpposite(){ //Can't quite figure out how to use this...
    return opposite;
  }
}
