import java.awt.Color;
import java.io.File;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Knight extends Piece{
	private ImageIcon image;
	
	public Knight(Player piecesPlayer, int lx, int ly) {
		super(piecesPlayer, lx, ly);
		
		this.setPointWorth(3);
		
		File imageCheck;
		if(this.getPlayer().name.equals("p1")){//is player 1
			
			image = new ImageIcon("knightW.png");
			//System.out.println(image.getDescription());
			
			imageCheck = new File("knightW.png");
		}
		else{
			image = new ImageIcon("knightB.png");
			//System.out.println(image.getDescription());
			
			imageCheck = new File("knightB.png");
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
		
		if(true){//also need to check if there are pieces in between
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
			
			if(!(Chess.board.selected.equals(Chess.board.spaces[this.getLY()][this.getLX()]))){//was piece moved
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
		Space examinedSpace;
		
		if(this.getLY()>1){//north prong
			if(this.getLX()<7){//right north
				examinedSpace = Chess.board.spaces[this.getLY()-2][this.getLX()+1];
				if(this.canMoveTo(examinedSpace.getLX(), examinedSpace.getLY())){
					possibleSpaces.add(examinedSpace);
				}
			}
			if(this.getLX()>0){//left north
				examinedSpace = Chess.board.spaces[this.getLY()-2][this.getLX()-1];
				if(this.canMoveTo(examinedSpace.getLX(), examinedSpace.getLY())){
					possibleSpaces.add(examinedSpace);
				}
			}
		}
		if(this.getLY()<6){//south prong
			if(this.getLX()<7){//right south
				examinedSpace = Chess.board.spaces[this.getLY()+2][this.getLX()+1];
				if(this.canMoveTo(examinedSpace.getLX(), examinedSpace.getLY())){
					possibleSpaces.add(examinedSpace);
				}
			}
			if(this.getLX()>0){//left south
				examinedSpace = Chess.board.spaces[this.getLY()+2][this.getLX()-1];
				if(this.canMoveTo(examinedSpace.getLX(), examinedSpace.getLY())){
					possibleSpaces.add(examinedSpace);
				}
			}
		}
		if(this.getLX()<6){//east prong
			if(this.getLY()<7){//lower east
				examinedSpace = Chess.board.spaces[this.getLY()+1][this.getLX()+2];
				if(this.canMoveTo(examinedSpace.getLX(), examinedSpace.getLY())){
					possibleSpaces.add(examinedSpace);
				}
			}
			if(this.getLY()>0){//upper east
				examinedSpace = Chess.board.spaces[this.getLY()-1][this.getLX()+2];
				if(this.canMoveTo(examinedSpace.getLX(), examinedSpace.getLY())){
					possibleSpaces.add(examinedSpace);
				}
			}
		}
		if(this.getLX()>1){//west prong
			if(this.getLY()<7){//lower west
				examinedSpace = Chess.board.spaces[this.getLY()+1][this.getLX()-2];
				if(this.canMoveTo(examinedSpace.getLX(), examinedSpace.getLY())){
					possibleSpaces.add(examinedSpace);
				}
			}
			if(this.getLY()>0){//upper west
				examinedSpace = Chess.board.spaces[this.getLY()-1][this.getLX()-2];
				if(this.canMoveTo(examinedSpace.getLX(), examinedSpace.getLY())){
					possibleSpaces.add(examinedSpace);
				}
			}
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
