package characters;

import map.Block;
import obstacles.Element;

public class Enemy extends Element{
	
	private String direction;
	private int maxMove;
	private int move;
	
	public Enemy(Block position, String direction, int maxMove, int move) {
		super(position);
		this.direction = direction;
		this.maxMove = maxMove;
		this.move = move;
	}

	public String getDirection() {
		return direction;
	}
	
	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	public int getMove() {
		return move;
	}
	
	public void setMove(int move) {
		this.move = move;
	}
	
	public int getMaxMove() {
		return maxMove;
	}
}
