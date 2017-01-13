
public class Node {
	private Piece pieceToMove;
	private Space spaceToMoveTo;
	private boolean isShortCastle;
	private boolean isLongCastle;
	private boolean isPawnPromotion;
	
	private int nodeValue;
	
	//each node holds the information of a potential move
	public Node(Piece piece, Space space) {
		// TODO Auto-generated constructor stub
		this.pieceToMove = piece;
		this.spaceToMoveTo = space;
		this.isShortCastle = false;
		this.isLongCastle = false;
		this.isPawnPromotion = false;
		
		this.nodeValue = 0;//this will be used for the AI to weigh its options
		
		if(space.getLX()==piece.getLX()+2 && piece.isKing){
			this.isShortCastle = true;
		}
		else if(space.getLX()==piece.getLX()-2 && piece.isKing){
			this.isLongCastle = true;
		}
		//if is pawn promotion
		else if(piece.getClass().getName().equals("Pawn") && (space.getLY()==0 || space.getLY()==7)){
			this.isPawnPromotion = true;
		}
		
	}
	public Piece getPieceToMove() {
		return pieceToMove;
	}
	public void setPieceToMove(Piece pieceToMove) {
		this.pieceToMove = pieceToMove;
	}
	public Space getSpaceToMoveTo() {
		return spaceToMoveTo;
	}
	public void setSpaceToMoveTo(Space spaceToMoveTo) {
		this.spaceToMoveTo = spaceToMoveTo;
	}
	public boolean isShortCastle() {
		return isShortCastle;
	}
	
	public boolean isLongCastle() {
		return isLongCastle;
	}
	
	public boolean isPawnPromotion() {
		return isPawnPromotion;
	}
	
	public int getNodeValue() {
		return nodeValue;
	}
	public void setNodeValue(int nodeValue) {
		this.nodeValue = nodeValue;
	}

}
