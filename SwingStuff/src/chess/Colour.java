package chess;

public enum Colour {
	// Colours needed for chess game
	BLACK, WHITE;
	
	// Instance to store rgb
	private int[] rgb;
	
	// Private constructor
	private Colour() {
		
		// Assign the correct rgb
		switch(this.name()){
		case "BLACK":
			rgb = new int[]{0, 0, 0};
			break;
		case "WHITE":
			rgb = new int[]{255, 255, 255};
			break;
		default: 
			rgb = null;
			break;
		}
	}
	
	// Get RGB of colour
	public int[] getRGB() {
		return rgb;
	}
}
