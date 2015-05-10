package aca14md;

public class TextDisplay implements Display {

	/**
	 * This method simply shows the pieces in the
	 * console. Nothing too complicated.
	 */
	public void showPiecesOnBoard(Piece[][] data) {
		
		System.out.println("  A B C D E F G H");
		
		// i = x coordinate, j = y.
		for(int j = data.length -1; j >= 0; j--){
			
			System.out.print(j);
			
			for(int i = 0; i < data[j].length; i++){
				if(data[i][j] == null)
					System.out.print(" .");
				else
					System.out.print(" "+data[i][j]);
			}
			System.out.print(" "+(j));
			System.out.println();
		}
		System.out.println("  A B C D E F G H");
		System.out.println();

	}

}
