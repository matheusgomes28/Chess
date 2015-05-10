package aca14md;
import java.util.ArrayList;

public class Knight extends Piece {
	
	public Knight(int ix, int iy, int c, Board b){
		super(PieceCode.KNIGHT, ix, iy, c, b);
	}
	
	/**
	 * This method will return an array
	 * with all the available moves.
	 * 
	 * @return null if no moves or an array
	 * list of moves otherwise.
	 */
	public ArrayList<Move> availableMoves() {
		
		// Create array to hold orbits to rotate
		int[][] orbits = {{2, 1}, {2, -1}};
		
		// Create arrayList of moves
		ArrayList<Move> v = new ArrayList<>();
		
		// Find available moves by rotating points
		for(int[] orbit : orbits){
			
			// Coordinates to rotate by pi/4
			int x = orbit[0],
				y = orbit[1];
			
			// Loop to rotate them
			for(int i = 0; i < 4; i++){
				
				int temp = x;
				x = -y;
				y = temp;
				
				// New position relative to own position
				int newX = getX() + x;
				int newY = getY() + y;
				
				// Check if points are not out of range
				if(!getBoard().outOfRange(newX, newY)){
					
					// Check for occupied positions & return right move
					if(!getBoard().occupied(newX, newY))
						v.add(new Move(this, getX(), getY(), newX, newY, false));
					else
						if(getBoard().getPiece(newX, newY).getColour() != getColour())
						v.add(new Move(this, getX(), getY(), newX, newY, true));
				}
			}
		}
		
		// Check for empty array;
		if(v.isEmpty()) return null;
		else return v;
	}

}
