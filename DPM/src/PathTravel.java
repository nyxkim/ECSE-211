
public class PathTravel {
	/*     N    
	 *     |
	 * W - - - E
	 *     |
	 *     S
	 */
	private int x,y;
	private String orientation;
	private Navigation navi;
	private Path path;
	public PathTravel(int x,int y, String o, Navigation n,Path p){
		this.x = x;
		this.y = y;
		path = p;
		orientation = o;
		navi = n;
	}
	
	public void moveNorth(){
		y++;
		if(orientation.equals("N")){
			navi.testTile();
		}
		else if(orientation.equals("S")){
			navi.turnCCW();
			navi.turnCCW();
			navi.testTile();
		}
		else if(orientation.equals("E")){
			navi.turnCCW();
			navi.testTile();
		}
		else if(orientation.equals("W")){
			navi.turnCW();
			navi.testTile();
		}
		orientation = "N";
	}
	public void moveSouth(){
		y--;
		if(orientation.equals("S")){
			navi.testTile();
		}
		else if(orientation.equals("N")){
			navi.turnCCW();
			navi.turnCCW();
			navi.testTile();
		}
		else if(orientation.equals("W")){
			navi.turnCCW();
			navi.testTile();
		}
		else if(orientation.equals("E")){
			navi.turnCW();
			navi.testTile();
		}
		orientation = "S";
	}
	public void moveEast(){
		x++;
		if(orientation.equals("E")){
			navi.testTile();
		}
		else if(orientation.equals("W")){
			navi.turnCCW();
			navi.turnCCW();
			navi.testTile();
		}
		else if(orientation.equals("S")){
			navi.turnCCW();
			navi.testTile();
		}
		else if(orientation.equals("N")){
			navi.turnCW();
			navi.testTile();
		}
		orientation = "E";
	}
	public void moveWest(){
		x--;
		if(orientation.equals("W")){
			navi.testTile();
		}
		else if(orientation.equals("E")){
			navi.turnCCW();
			navi.turnCCW();
			navi.testTile();
		}
		else if(orientation.equals("N")){
			navi.turnCCW();
			navi.testTile();
		}
		else if(orientation.equals("S")){
			navi.turnCW();
			navi.testTile();
		}
		orientation = "W";
	}
	
	public String getNext(GridSquare next){
		String mov = "";
		if(next.getY() == y+1 && next.getX() == x){
			mov = "N";
		}
		else if(next.getY() == y-1 && next.getX() == x){
			mov = "S";
		}
		else if(next.getX() == x+1 && next.getY() == y){
			mov = "E";
		}
		else if(next.getX() == x-1 && next.getY() == y){
			mov = "W";
		}
		return mov;
	}
	
	public void setPath(Path pat){
		this.path = pat;
	}
	
	public void travelPath(){
		for(int i = 0; i < path.getSquares().size(); i++){
			String next = getNext(path.getSquares().get(i));
			if(next.equals("N")){
				moveNorth();
			}
			else if(next.equals("S")){
				moveSouth();
			}
			else if(next.equals("E")){
				moveEast();
			}
			else if(next.equals("W")){
				moveWest();
			}
		}
	}

	public void faceWest() {
		if(orientation.equals("W")){
			navi.testTile();
		}
		else if(orientation.equals("E")){
			navi.turnCCW();
			navi.turnCCW();
		}
		else if(orientation.equals("N")){
			navi.turnCCW();
		}
		else if(orientation.equals("S")){
			navi.turnCW();
		}
		orientation = "W";
		
	}
}
