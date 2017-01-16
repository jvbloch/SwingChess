import java.awt.Color;
import java.io.File;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Rook extends Piece{
	private ImageIcon image;
	
	public Rook(Player piecesPlayer, int lx, int ly) {
		super(piecesPlayer, lx, ly);
		
		this.setPointWorth(5);
		
		File imageCheck;
		if(this.getPlayer().name.equals("p1")){//is player 1
			
			image = new ImageIcon("rookW.png");
			//System.out.println(image.getDescription());
			
			imageCheck = new File("rookW.png");
		}
		else{
			image = new ImageIcon("rookB.png");
			//System.out.println(image.getDescription());
			
			imageCheck = new File("rookB.png");
		}
		
			
			if(imageCheck.exists()) 
			    System.out.println("Image file found!");
			else 
			    System.out.println("Image file not found!");
		
		
		Chess.board.spaces[ly][lx].setIcon( (Icon) image);
		//System.out.println(Chess.board.spaces[ly][lx].getIcon());
		//System.out.println(image.toString());
		
		
	}
	@Override
	public void move(int lx, int ly){
		
		if(lx==this.getLX()||ly==this.getLY()){//also need to check if there are pieces in between
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
			Chess.board.spaces[this.getLY()][this.getLX()].setPiece(this);
			//System.out.println("!!"+Chess.board.selected);
			if(true){//was piece moved !(Chess.board.selected.equals(Chess.board.spaces[this.getLY()][this.getLX()]))
	    		//pass turn
	    		//call a Chess.passturn method
				Chess.passTurn();
				System.out.println("It is P1 turn: " + Chess.isP1Turn);
	    		System.out.println("Pass Turn");
	    	
	    		this.hasMoved = true;//piece has been moved this game
	    	}
			
		}
		
	}
	
	@Override
public ArrayList<Space> checkPossibleMoves(){//returns the piece's possible moves 
		
		ArrayList<Space> possibleSpaces = new ArrayList<Space>();
		//possibleSpaces.add(Chess.board.spaces[this.getLY()][this.getLX()]);
		
		int examinedX = this.getLX();
		int examinedY = this.getLY()-1;
		while(examinedY>-1){//add spaces going North
			if(this.canMoveTo(examinedX, examinedY)){
				possibleSpaces.add(Chess.board.spaces[examinedY][examinedX]);
			}
			else{
				break;
			}
			if(!(Chess.board.spaces[examinedY][examinedX].getPiece()== null)){//if space is occupied
				break;
			}
			
			examinedY--;
		}
		
		examinedX = this.getLX();
		examinedY = this.getLY()+1;
		while(examinedY<8){//add spaces going South
			if(this.canMoveTo(examinedX, examinedY)){
				possibleSpaces.add(Chess.board.spaces[examinedY][examinedX]);
			}
			else{
				break;
			}
			if(!(Chess.board.spaces[examinedY][examinedX].getPiece()== null)){//if space is occupied
				break;
			}
			examinedY++;
		}
		
		examinedX = this.getLX()-1;
		examinedY = this.getLY();
		while(examinedX>-1){//add spaces going West
			if(this.canMoveTo(examinedX, examinedY)){
				possibleSpaces.add(Chess.board.spaces[examinedY][examinedX]);
			}
			else{
				break;
			}
			if(!(Chess.board.spaces[examinedY][examinedX].getPiece()== null)){//if space is occupied
				break;
			}
			examinedX--;
		}
		
		examinedX = this.getLX()+1;
		examinedY = this.getLY();
		while(examinedX<8){//add spaces going East
			if(this.canMoveTo(examinedX, examinedY)){
				possibleSpaces.add(Chess.board.spaces[examinedY][examinedX]);
			}
			else{
				break;
			}
			if(!(Chess.board.spaces[examinedY][examinedX].getPiece()== null)){//if space is occupied
				break;
			}
			examinedX++;
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
			
			
			//System.out.println(origionalFrom);
			
			Chess.board.spaces[possibleMove.getLY()][possibleMove.getLX()].setPiece(this);
			Chess.board.spaces[this.getLY()][this.getLX()].setPiece(null);
			
			if(!(origionalTo == null)){
				this.getPlayer().getKing().getOpponentsPieces().remove(origionalTo);
			}
			
			this.setLX(possibleMove.getLX());
			this.setLY(possibleMove.getLY());
			
			if(this.getPlayer().getKing().isInCheck()){
				//if(!(this.getLX()==origX && this.getLY()==origY)){
					possibleSpacesOutOfCheck.remove(possibleMove);
				//}
			}			
			
			this.setLX(origX);
			this.setLY(origY);
			
			Chess.board.spaces[this.getLY()][this.getLX()].setPiece(origionalFrom);
			possibleMove.setPiece(origionalTo);
			//System.out.println(possibleSpaces==possibleSpacesOutOfCheck);
		
			if(!(origionalTo == null)){
				this.getPlayer().getKing().getOpponentsPieces().add(origionalTo);
			}
			
		}
		return possibleSpacesOutOfCheck;
		
	}
	
	@Override
	public void showPossibleMoves(){
		for(Space space: this.removeCheckVulnerableMoves(this.checkPossibleMoves())){
			space.setBackground(new Color(50,255,50));
		}
	}
	
	
}
