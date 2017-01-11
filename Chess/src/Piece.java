import java.util.ArrayList;

public abstract class Piece {

	private Player player;
	private int pointsWorth;
	private int locationX;
	private int locationY;
	public boolean hasMoved;//has the piece been moved this game
	public boolean isKing;
	
	public Piece(Player piecesPlayer, int lx, int ly){
		this.player = piecesPlayer;
		this.locationX = lx;
		this.locationY = ly;
		
		this.hasMoved = false;
		this.isKing = false;
		
		
		Chess.board.spaces[ly][lx].setPiece(this);
	}
	
	public Player getPlayer(){
		return this.player;
	}
	
	public int getPoints(){
		return this.pointsWorth;
	}
	
	public int getLX(){
		return this.locationX;
	}
	
	public int getLY(){
		return this.locationY;
	}
	
	public void setLX(int setTo){
		this.locationX = setTo;
	}
	public void setLY(int setTo){
		this.locationY = setTo;
	}

	public boolean canMoveTo(int lx, int ly){//returns true if piece can move to inputed location
		Space examined = Chess.board.spaces[ly][lx];
		if(!(examined.getPiece()== null)){//if space is occupied
			if(!(examined.getPiece().getPlayer().equals(this.getPlayer()))){//if not occupied by friendly piece
				return true;
			}
			return false;
		}
		return true;
		
		
	}
	public void move(int lx, int ly) {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<Space> checkPossibleMoves() {
		// TODO Auto-generated method stub
		return null;
	}
	public ArrayList<Space> removeCheckVulnerableMoves(ArrayList<Space> possibleSpaces) {
		return null;
	}

	public void showPossibleMoves() {
		// TODO Auto-generated method stub
		
	}

	public int getPointsWorth(){
		return this.pointsWorth;
		
	}
	
	public void setPointWorth(int points){
		this.pointsWorth = points;
	}

	public boolean isInCheck() {
		// TODO Auto-generated method stub
		return false;
	}

	public ArrayList<Piece> getOpponentsPieces(){
		return this.player.getKing().getOpponentsPieces();
	}
}
