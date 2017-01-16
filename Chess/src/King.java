import java.awt.Color;
import java.io.File;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class King extends Piece{
	private ImageIcon image;
	private ArrayList<Piece> opponentsPieces;
	private Piece opponentsKing;
	
	public King(Player piecesPlayer, int lx, int ly) {
		super(piecesPlayer, lx, ly);
		
		this.setPointWorth(0);
		this.isKing = true;
		this.getPlayer().setKing(this);
		
		if(this.getPlayer().name.equals("p1")){//shows the king where opponents piece's are
			this.opponentsPieces = Chess.p2.getPieces();
			this.opponentsKing = Chess.p2.getKing();
		}
		else{
			this.opponentsPieces = Chess.p1.getPieces();
			this.opponentsKing = Chess.p1.getKing();
		}
		
		
		
		File imageCheck;
		if(this.getPlayer().name.equals("p1")){//is player 1
			
			image = new ImageIcon("kingW.png");
			//System.out.println(image.getDescription());
			
			imageCheck = new File("kingW.png");
		}
		else{
			image = new ImageIcon("kingB.png");
			//System.out.println(image.getDescription());
			
			imageCheck = new File("kingB.png");
		}
		
			
			/*if(imageCheck.exists()) 
			    System.out.println("Image file found!");
			else 
			    System.out.println("Image file not found!");*/
		
		
		Chess.board.spaces[ly][lx].setIcon( (Icon) image);
		//System.out.println(Chess.board.spaces[ly][lx].getIcon());
		//System.out.println(image.toString());
		
		
	}
	@Override
	public void refreshOpponentsPieces(){
		if(this.getPlayer().name.equals("p1")){//shows the king where opponents piece's are
			this.opponentsPieces = Chess.p2.getPieces();
			this.opponentsKing = Chess.p2.getKing();
		}
		else{
			this.opponentsPieces = Chess.p1.getPieces();
			this.opponentsKing = Chess.p1.getKing();
		}
	}
	@Override
	public void move(int lx, int ly){
		

		
		if(true){
			if(lx==this.getLX()+2){//check if castling short
				this.castleShort();
				Chess.passTurn();
			}
			if(lx==this.getLX()-2){//check if castling long
				this.castleLong();
				Chess.passTurn();
				//System.out.println(Chess.board.spaces[this.getLY()][this.getLX()-4].getPiece());
			}
			
			Chess.board.spaces[this.getLY()][this.getLX()].setIcon(null);
			Chess.board.spaces[this.getLY()][this.getLX()].setPiece(null);
			this.setLX(lx);
			this.setLY(ly);
			
			if(Chess.board.spaces[ly][lx].getPiece()!=null){//check if capturing piece
				Piece capturedPiece= Chess.board.spaces[ly][lx].getPiece();
				capturedPiece.getPlayer().removePiece(capturedPiece);
				
				this.getPlayer().getKing().getOpponentsPieces().remove(Chess.board.spaces[ly][lx].getPiece());
				//Chess.board.spaces[ly][lx].setPiece(null);
				//reward points
				this.getPlayer().addPoints(capturedPiece.getPointsWorth());
				System.out.println("Piece captured. " + capturedPiece.getPointsWorth() + " points rewarded to "+this.getPlayer().name);
    		}
			
			
			//move piece image on board
			
			Chess.board.spaces[ly][lx].setIcon( (Icon) image);
			Chess.board.spaces[this.getLY()][this.getLX()].setPiece(this);//move piece object to space object
			if(Chess.board.selected!=null){//workaround for ai pawnpromotion interaction 
				if(!(Chess.board.selected.equals(Chess.board.spaces[this.getLY()][this.getLX()]))){//was piece moved
		    		//pass turn
		    		//call a Chess.passturn method
					Chess.passTurn();
					System.out.println("It is P1 turn: " + Chess.isP1Turn);
		    		System.out.println("Pass Turn");
		    		
		    		this.hasMoved = true;//piece has been moved this game
		    	}
			}
			else{
				if(true){//was piece moved
		    		//pass turn
		    		//call a Chess.passturn method
					Chess.passTurn();
					System.out.println("It is P1 turn: " + Chess.isP1Turn);
		    		System.out.println("Pass Turn");
		    		
		    		this.hasMoved = true;//piece has been moved this game
		    	}
			}
			
		}
		
	}
	
	@Override
	public ArrayList<Space> checkPossibleMoves(){//returns the piece's possible moves 
		
		ArrayList<Space> possibleSpaces = new ArrayList<Space>();
		possibleSpaces.add(Chess.board.spaces[this.getLY()][this.getLX()]);//may be unnecessary 
		
		if(this.getLY()-1>=0){//if possible, add space going North
			int x = this.getLX();
			int y = this.getLY()-1;
			Space examinedSpace = Chess.board.spaces[y][x];
			
			
			if(!(examinedSpace.getPiece()== null)){//if space is occupied
				if(!(examinedSpace.getPiece().getPlayer().equals(this.getPlayer()))){//if not occupied by friendly piece
					possibleSpaces.add(examinedSpace);
				}
				//break;
			}
			else{
				possibleSpaces.add(examinedSpace);
			}
			//break;
			
		}
		if(this.getLY()+1<8){//add space going South
			int x = this.getLX();
			int y = this.getLY()+1;
			Space examinedSpace = Chess.board.spaces[y][x];
			
			
			if(!(examinedSpace.getPiece()== null)){//if space is occupied
				if(!(examinedSpace.getPiece().getPlayer().equals(this.getPlayer()))){//if not occupied by friendly piece
					possibleSpaces.add(examinedSpace);
				}
				//break;
			}
			else{
				possibleSpaces.add(examinedSpace);
			}
			//break;
		}
		if(this.getLX()+1<8){//add spaces going East
			int x = this.getLX()+1;
			int y = this.getLY();
			Space examinedSpace = Chess.board.spaces[y][x];
			
			
			if(!(examinedSpace.getPiece()== null)){//if space is occupied
				if(!(examinedSpace.getPiece().getPlayer().equals(this.getPlayer()))){//if not occupied by friendly piece
					possibleSpaces.add(examinedSpace);
				}
				//break;
			}
			else{
				possibleSpaces.add(examinedSpace);
			}
			//break;
		}
		if(this.getLX()-1>=0){//add spaces going West
			int x = this.getLX()-1;
			int y = this.getLY();
			Space examinedSpace = Chess.board.spaces[y][x];
			
			
			if(!(examinedSpace.getPiece()== null)){//if space is occupied
				if(!(examinedSpace.getPiece().getPlayer().equals(this.getPlayer()))){//if not occupied by friendly piece
					possibleSpaces.add(examinedSpace);
				}
				//break;
			}
			else{
				possibleSpaces.add(examinedSpace);
			}
			//break;
		}
		if(this.getLY()-1>=0 && this.getLX()+1<8){//if possible, add space going NorthEast
			int x = this.getLX()+1;
			int y = this.getLY()-1;
			Space examinedSpace = Chess.board.spaces[y][x];
			
			
			if(!(examinedSpace.getPiece()== null)){//if space is occupied
				if(!(examinedSpace.getPiece().getPlayer().equals(this.getPlayer()))){//if not occupied by friendly piece
					possibleSpaces.add(examinedSpace);
				}
				//break;
			}
			else{
				possibleSpaces.add(examinedSpace);
			}
			//break;
			
		}
		if(this.getLY()+1<8 && this.getLX()+1<8){//add space going SouthEast
			int x = this.getLX()+1;
			int y = this.getLY()+1;
			Space examinedSpace = Chess.board.spaces[y][x];
			
			
			if(!(examinedSpace.getPiece()== null)){//if space is occupied
				if(!(examinedSpace.getPiece().getPlayer().equals(this.getPlayer()))){//if not occupied by friendly piece
					possibleSpaces.add(examinedSpace);
				}
				//break;
			}
			else{
				possibleSpaces.add(examinedSpace);
			}
			//break;
		}
		if(this.getLY()-1>=0 && this.getLX()-1>=0){//add spaces going NorthWest
			int x = this.getLX()-1;
			int y = this.getLY()-1;
			Space examinedSpace = Chess.board.spaces[y][x];
			
			
			if(!(examinedSpace.getPiece()== null)){//if space is occupied
				if(!(examinedSpace.getPiece().getPlayer().equals(this.getPlayer()))){//if not occupied by friendly piece
					possibleSpaces.add(examinedSpace);
				}
				//break;
			}
			else{
				possibleSpaces.add(examinedSpace);
			}
			//break;
		}
		if(this.getLY()+1<8 && this.getLX()-1>=0){//add spaces going SouthWest
			int x = this.getLX()-1;
			int y = this.getLY()+1;
			Space examinedSpace = Chess.board.spaces[y][x];
			
			
			if(!(examinedSpace.getPiece()== null)){//if space is occupied
				if(!(examinedSpace.getPiece().getPlayer().equals(this.getPlayer()))){//if not occupied by friendly piece
					possibleSpaces.add(examinedSpace);
				}
				//break;
			}
			else{
				possibleSpaces.add(examinedSpace);
			}
			//break;
		}
		
		
		return possibleSpaces;
	}
	@Override
	public ArrayList<Space> removeCheckVulnerableMoves(ArrayList<Space> possibleSpaces) {

		ArrayList<Space> possibleSpacesOutOfCheck = (ArrayList<Space>) possibleSpaces.clone(); 
		for(Space possibleMove: possibleSpaces){//Check if any possible spaces result in putting active player in check
			Piece origionalFrom = Chess.board.spaces[this.getLY()][this.getLX()].getPiece();
			Piece origionalTo = possibleMove.getPiece();
			
			int origX = this.getLX();
			int origY = this.getLY();
			
			if(!(origionalTo == null)){
				this.getPlayer().getKing().getOpponentsPieces().remove(origionalTo);
				//System.out.println(possibleMove.getPiece());
			}
			
			//System.out.println(origionalFrom);
			
			Chess.board.spaces[possibleMove.getLY()][possibleMove.getLX()].setPiece(this);
			Chess.board.spaces[this.getLY()][this.getLX()].setPiece(null);
			
			
			
			this.setLX(possibleMove.getLX());
			this.setLY(possibleMove.getLY());
			
			
			if(this.getPlayer().getKing().isInCheck()){
				//if(!(this.getLX()==origX && this.getLY()==origY)){
					possibleSpacesOutOfCheck.remove(possibleMove);
					
				//}
			}
			else if(this.isKing){//if king moving in range of other king
				if(this.getPlayer().name.equals("p1")){//shows the king where opponents piece's are
					
					this.opponentsKing = Chess.p2.getKing();
				}
				else{
					
					this.opponentsKing = Chess.p1.getKing();
				}
				//System.out.println(this.opponentsKing);
				
				if((possibleMove.getLX()>this.opponentsKing.getLX()-2 && possibleMove.getLX()<this.opponentsKing.getLX()+2)&&(possibleMove.getLY()>this.opponentsKing.getLY()-2 && possibleMove.getLY()<this.opponentsKing.getLY()+2)){
						possibleSpacesOutOfCheck.remove(possibleMove);
				}
			}
			
			
			this.setLX(origX);
			this.setLY(origY);
			
			Chess.board.spaces[this.getLY()][this.getLX()].setPiece(origionalFrom);
			possibleMove.setPiece(origionalTo);
			
			if(!(origionalTo == null)){
				this.getPlayer().getKing().getOpponentsPieces().add(origionalTo);
				//System.out.println(origionalTo);
			}
			
			//add castling here?
			if(this.canCastleShort()){
				possibleSpacesOutOfCheck.add(Chess.board.spaces[this.getLY()][this.getLX()+2]);
			}
			if(this.canCastleLong()){
				possibleSpacesOutOfCheck.add(Chess.board.spaces[this.getLY()][this.getLX()-2]);
			}
		}
		return possibleSpacesOutOfCheck;
		//return possibleSpaces;
	}
	
	@Override
	public void showPossibleMoves(){
		for(Space space: this.removeCheckVulnerableMoves(this.checkPossibleMoves())){
			space.setBackground(new Color(50,255,50));
		}
	}
	@Override
	public boolean isInCheck(){//check if this king is in check
		
		//this.refreshOpponentsPieces();
		
		for(Piece enemy: this.opponentsPieces){
			if(!enemy.isKing){
				if(enemy.checkPossibleMoves().contains(Chess.board.spaces[this.getLY()][this.getLX()])){
					//System.out.println(this.opponentsPieces.size());
					//System.out.println(enemy);
					//System.out.println(this.opponentsPieces);
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public ArrayList<Piece> getOpponentsPieces(){
		return this.opponentsPieces;
	}
	
	public void setOpponentsPieces( ArrayList<Piece> oppPieces){
		this.opponentsPieces = oppPieces;
	}
	
	public boolean canCastleShort(){
		if(!this.hasMoved){//king has not moved
			//king side rook space has a piece
			if(Chess.board.spaces[this.getLY()][this.getLX()+3].getPiece()!=null){
				//king side rook has not moved
				if(!Chess.board.spaces[this.getLY()][this.getLX()+3].getPiece().hasMoved){
					//no piece in between
					if(Chess.board.spaces[this.getLY()][this.getLX()+1].getPiece()==null && Chess.board.spaces[this.getLY()][this.getLX()+2].getPiece()==null){
						//king is not currently in check
						if(!this.isInCheck()){
							//king does not pass through any square in check
							King check1 = new King(this.getPlayer(),this.getLX()+1,this.getLY());
							King check2 = new King(this.getPlayer(),this.getLX()+2,this.getLY());
							if(!check1.isInCheck()){
								Chess.board.spaces[check1.getLY()][check1.getLX()].setPiece(null);
								Chess.board.spaces[check1.getLY()][check1.getLX()].setIcon(null);
								this.getPlayer().setKing(this);
								if(!check2.isInCheck()){
									Chess.board.spaces[check2.getLY()][check2.getLX()].setPiece(null);
									Chess.board.spaces[check2.getLY()][check2.getLX()].setIcon(null);
									this.getPlayer().setKing(this);
									this.removeExtraKings();
									return true;
								}
							}
							Chess.board.spaces[check1.getLY()][check1.getLX()].setPiece(null);
							Chess.board.spaces[check2.getLY()][check2.getLX()].setPiece(null);
							Chess.board.spaces[check1.getLY()][check1.getLX()].setIcon(null);
							Chess.board.spaces[check2.getLY()][check2.getLX()].setIcon(null);
							this.getPlayer().setKing(this);
						}
					}
				}
			}
		}
		
		this.removeExtraKings();
		return false;
	}
	
	public boolean canCastleLong(){
		//System.out.println(this.getPlayer().getPieces());
		if(!this.hasMoved){//king has not moved
			//queen side rook space has a piece
			if(Chess.board.spaces[this.getLY()][this.getLX()-4].getPiece()!=null){
				//king side rook has not moved
				if(!Chess.board.spaces[this.getLY()][this.getLX()-4].getPiece().hasMoved){
					//no piece in between
					if(Chess.board.spaces[this.getLY()][this.getLX()-1].getPiece()==null && Chess.board.spaces[this.getLY()][this.getLX()-2].getPiece()==null && Chess.board.spaces[this.getLY()][this.getLX()-3].getPiece()==null){
						//king is not currently in check
						if(!this.isInCheck()){
							//king does not pass through any square in check
							King check1 = new King(this.getPlayer(),this.getLX()-1,this.getLY());
							King check2 = new King(this.getPlayer(),this.getLX()-2,this.getLY());
							King check3 = new King(this.getPlayer(),this.getLX()-3,this.getLY());
							if(!check1.isInCheck()){
								Chess.board.spaces[check1.getLY()][check1.getLX()].setPiece(null);
								Chess.board.spaces[check1.getLY()][check1.getLX()].setIcon(null);
								if(!check2.isInCheck()){
									Chess.board.spaces[check2.getLY()][check2.getLX()].setPiece(null);
									Chess.board.spaces[check2.getLY()][check2.getLX()].setIcon(null);
									this.getPlayer().setKing(this);
									if(!check3.isInCheck()){
										Chess.board.spaces[check3.getLY()][check3.getLX()].setPiece(null);
										Chess.board.spaces[check3.getLY()][check3.getLX()].setIcon(null);
										this.getPlayer().setKing(this);
										return true;
									}
								}
							}
							Chess.board.spaces[check1.getLY()][check1.getLX()].setPiece(null);
							Chess.board.spaces[check2.getLY()][check2.getLX()].setPiece(null);
							Chess.board.spaces[check3.getLY()][check3.getLX()].setPiece(null);
							Chess.board.spaces[check1.getLY()][check1.getLX()].setIcon(null);
							Chess.board.spaces[check2.getLY()][check2.getLX()].setIcon(null);
							Chess.board.spaces[check3.getLY()][check3.getLX()].setIcon(null);
							this.getPlayer().setKing(this);
						}
					}
				}
			}
		}
		
		
		return false;
	}
	
	public void castleShort(){
		Chess.board.spaces[this.getLY()][this.getLX()+3].getPiece().move(this.getLX()+1, this.getLY());
		//this.getPlayer().setKing(this);
	}
	public void castleLong(){
		Chess.board.spaces[this.getLY()][this.getLX()-4].getPiece().move(this.getLX()-1, this.getLY());
		//this.getPlayer().setKing(this);
	}
	
	public void removeExtraKings(){
		ArrayList<Piece> fixedPieces = (ArrayList<Piece>) Chess.p1.getPieces().clone();
		for(Piece piece: Chess.p1.getPieces()){
			if(piece.isKing && (!Chess.p1.getKing().equals(piece))){
				fixedPieces.remove(piece);
			}
		}
		
		ArrayList<Piece> fixedPieces2 = (ArrayList<Piece>) Chess.p2.getPieces().clone();
		for(Piece piece: Chess.p2.getPieces()){
			if(piece.isKing && (!Chess.p2.getKing().equals(piece))){
				fixedPieces2.remove(piece);
			}
		}
		Chess.p1.setPieces(fixedPieces);
		Chess.p2.setPieces(fixedPieces2);
	}
}
