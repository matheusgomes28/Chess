package aca14md;

public class Move {
	
	// Declare position variables
	private int xFrom, yFrom,
	            xTo, yTo;
	
	// Declare piece instance of move
	private Piece piece;
	
	// Store piece taken if any
	Piece pTaken = null;
	
	// boolean var to check position occupation
	private boolean occupied;
	
	public Move(Piece p, int xFrom, int yFrom,
			    int xTo, int yTo, boolean o){
		
		// Set private fields' values
		this.xFrom = xFrom;
		this.yFrom = yFrom;
		this.xTo = xTo;
		this.yTo = yTo;
		piece = p;
		occupied = o;
	}
	
	public int getXFrom(){
		return xFrom;
	}
	
	public int getYFrom(){
		return yFrom;
	}

	public int getXTo(){
		return xTo;
	}
	
	public int getYTo(){
		return yTo;
	}
	
	public void setPieceTaken(Piece pTaken){
		this.pTaken = pTaken;
	}
	
	public Piece getPieceTaken(){
		return pTaken;
	}
	
	/**
	 * This method checks whether the position
	 * the piece will move to is occupied.
	 * @return true if position is occupied by
	 * enemy piece.
	 */
	public boolean isOccupied(){
		return occupied;
	}
	
	
	public boolean equals(Object o){
		
		if(o == null) return false;
		else if(o.getClass() != this.getClass()) return false;
		else{
			// Downcast obj to move
			Move obj = (Move) o;
			
			// Check for field equality
			if(this.xFrom == obj.xFrom &&
			   this.xTo == obj.xTo &&
			   this.yFrom == obj.yFrom &&
			   this.yTo == obj.yTo &&
			   this.occupied == obj.occupied)
				return true; 
			
			else return false;
		}
	}
	
	
	/**
	 * Gets the piece object related to this move.
	 * @return the piece obj.
	 */
	public Piece getPiece(){
		return piece;
	}
	
	/**
	 * This method carries out some necessary
	 * steps when making a complete move.
	 */
	public void carryOutMove(){
		
		// Set piece's position on obj and board
		piece.setPosition(xTo, yTo);
		piece.getBoard().setPosition(xFrom, yFrom, null);
		piece.getBoard().setPosition(xTo, yTo, piece);
		
	}
	
	public void carryOutMove(Piece pTaken){
		
		// Set piece that was taken
		this.pTaken = pTaken;
		
		// Carry out normal move now
		carryOutMove();
	}
	
	public void undoMove(){
		// Get objects for readability purposes
		Piece pMoved = getPiece();
		Piece pTaken = getPieceTaken();
		Board board = pMoved.getBoard();
	
		// Set the piece to the previous position
		pMoved.setPosition(getXFrom(), getYFrom());
		board.setPosition(getXFrom(), getYFrom(), pMoved);
		
		// Returns the piece taken if any
		if(isOccupied()){
			board.setPosition(getXTo(), getYTo(), pTaken);
		}
		else
			board.setPosition(getXTo(), getYTo(), null);
	}
	
	public String toString(){
		return "("+xFrom+","+yFrom+") to ("+xTo+","+yTo+"). Occupied:"+occupied;
	}
	
}
