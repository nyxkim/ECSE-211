import java.util.ArrayList;


public class GridSquare {
	private boolean isWall;
	private int x,y;
	
	public GridSquare(int x, int y, boolean wall){
		this.x = x;
		this.y = y;
		
		this.isWall = wall;
		
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public boolean isWall(){
		return isWall;
	}
	
	public int getXCoord(){
		return (x+1)*15;
	}
	
	public int getYCoord(){
		return (y+1)*15;
	}
	
	public ArrayList<GridSquare> getSquares(){
		return new ArrayList<GridSquare>();
		//TODO
	}
	
}