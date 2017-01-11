import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Pawn extends Piece{
	private ImageIcon image;
	
	public Pawn(Player piecesPlayer, int lx, int ly) {
		super(piecesPlayer, lx, ly);
		
		this.setPointWorth(1);
		
		File imageCheck;
		if(this.getPlayer().name.equals("p1")){//is player 1
			
			image = new ImageIcon("pawnW.png");
			//System.out.println(image.getDescription());
			
			imageCheck = new File("pawnW.png");
		}
		else{
			image = new ImageIcon("pawnB.png");
			//System.out.println(image.getDescription());
			
			imageCheck = new File("pawnB.png");
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
	    		
	    		if(this.getLY()==0 || this.getLY()==7){//if pawn makes it to the end of the board
	    			
	    			//this.getPlayer().removePiece(this);
	    			Chess.board.setEnabled(false);
	    			
	    			Prompt promotion = new Prompt();
	    			promotion.promptLabel.setText("CHOOSE A PIECE TO PROMOTE TO:");
	    			
	    			
	    			promotion.option1.setText(" QUEEN*");
	    			promotion.option2.setText("KNIGHT");
	    			promotion.option3.setText("BISHOP");
	    			promotion.option4.setText(" ROOK ");
	    			//promotion.option1.setIcon(new ImageIcon("QueenW"));
	    			//promotion.option2.setIcon(new ImageIcon("KnightW"));
	    			//promotion.option3.setIcon(new ImageIcon("BishopW"));
	    			//promotion.option4.setIcon(new ImageIcon("RookW"));
	    			
	    			
	    			promotion.add(promotion.option1);
	    			promotion.add(promotion.option2);
	    			promotion.add(promotion.option3);
	    			promotion.add(promotion.option4);

	    			Pawn pawnToPromote = this;
	    			
	    			promotion.option1.addActionListener(new ActionListener()
	    			{
	    				  public void actionPerformed(ActionEvent e) 
	    				  {
	    					  pawnToPromote.promote("Queen");
	    					  
	    					  Chess.board.setEnabled(true);
	    					  promotion.setDefaultCloseOperation(1);
	    					  promotion.setVisible(false);
	    					  promotion.dispose();
	    					  
	    					  
	    				  }
	    				  
	    			});

	    			promotion.option2.addActionListener(new ActionListener()
	    			{
	    				  public void actionPerformed(ActionEvent e)
	    				  {
	    					  pawnToPromote.promote("Knight");
	    					  
	    					  Chess.board.setEnabled(true);
	    					  promotion.setDefaultCloseOperation(1);
	    					  promotion.setVisible(false);
	    					  promotion.dispose();
	    				  }
	    				  
	    			});
	    			
	    			promotion.option3.addActionListener(new ActionListener()
	    			{
	    				  public void actionPerformed(ActionEvent e)
	    				  {
	    					  pawnToPromote.promote("Bishop");
	    					  
	    					  Chess.board.setEnabled(true);
	    					  promotion.setDefaultCloseOperation(1);
	    					  promotion.setVisible(false);
	    					  promotion.dispose();
	    				  }
	    				  
	    			});
	    			promotion.option4.addActionListener(new ActionListener()
	    			{
	    				  public void actionPerformed(ActionEvent e)
	    				  {
	    					  pawnToPromote.promote("Rook");
	    					  
	    					  Chess.board.setEnabled(true);
	    					  promotion.setDefaultCloseOperation(1);
	    					  promotion.setVisible(false);
	    					  promotion.dispose();
	    				  }
	    				  
	    			});
	    			
	    			promotion.pack();
	    			
	    			
	    		}	
	    	}
		}
		
	}
	
	public void promote(String newPieceType){
		
		
		this.getPlayer().removePiece(this);
		
		this.getPlayer().addPiece(newPieceType, this.getLX(), this.getLY());
		
		if(this.getLY()==0){//if belongs to p1
			Chess.p2.getKing().getOpponentsPieces().remove(this);//removes pawn from opp king's view
			//adds new piece to opp king's view
			//Chess.p2.getKing().getOpponentsPieces().add(this.getPlayer().getPieces().get(this.getPlayer().getPieces().size()-1));
			System.out.print("P1 promoted pawn to "+this.getPlayer().getPieces().get(this.getPlayer().getPieces().size()-1));
		}
		else{//if belongs to p2
			Chess.p1.getKing().getOpponentsPieces().remove(this);
			//Chess.p1.getKing().getOpponentsPieces().add(this.getPlayer().getPieces().get(this.getPlayer().getPieces().size()-1));
			System.out.print("P2 promoted pawn to "+this.getPlayer().getPieces().get(this.getPlayer().getPieces().size()-1));
		}
	}
	
	@Override
	public ArrayList<Space> checkPossibleMoves(){//returns the piece's possible moves 
		
		ArrayList<Space> possibleSpaces = new ArrayList<Space>();
		//possibleSpaces.add(Chess.board.spaces[this.getLY()][this.getLX()]);
		Space examinedRightFork = null;
		Space examinedLeftFork = null;
		if(this.getLY()!=0 && this.getLY()!=7){//is not at end of board
			if(this.getPlayer().name.equals("p1")){//is player1 and is moving north
				if(this.getLX()<7){
					examinedRightFork = Chess.board.spaces[this.getLY()-1][this.getLX()+1];
				}
				if(this.getLX()>0){
					examinedLeftFork = Chess.board.spaces[this.getLY()-1][this.getLX()-1];
				}
				
			}
			else{//is other player and is moving south
				if(this.getLX()<7){
					examinedRightFork = Chess.board.spaces[this.getLY()+1][this.getLX()+1];
				}
				if(this.getLX()>0){
					examinedLeftFork = Chess.board.spaces[this.getLY()+1][this.getLX()-1];
				}
			}
			
			if(!(examinedRightFork==null)){
				//if there is an enemy piece here
				if(this.canMoveTo(examinedRightFork.getLX(), examinedRightFork.getLY()) && examinedRightFork.getPiece()!=null){
					possibleSpaces.add(examinedRightFork);
				}
			}
			if(!(examinedLeftFork==null)){
				//if there is an enemy piece here
				if(this.canMoveTo(examinedLeftFork.getLX(), examinedLeftFork.getLY()) && examinedLeftFork.getPiece()!=null){
					possibleSpaces.add(examinedLeftFork);
				}
			}
		}
		//add forward move to next(rcv) method to prevent it from registering as a threatening move
		return possibleSpaces;
	}
	@Override
	public ArrayList<Space> removeCheckVulnerableMoves(ArrayList<Space> possibleSpaces) {
		Space forwardMove1 = null;//the pawns forward move is a non hostile move, it is added here
		if(this.getPlayer().name.equals("p1")){//is player1 and is moving north
			forwardMove1 = Chess.board.spaces[this.getLY()-1][this.getLX()];
			if(forwardMove1.getPiece()==null){
				possibleSpaces.add(forwardMove1);
			}
			
		}
		else{//is other player and is moving south
			forwardMove1 = Chess.board.spaces[this.getLY()+1][this.getLX()];
			if(forwardMove1.getPiece()==null){
				possibleSpaces.add(forwardMove1);
			}
		}
		Space forwardMove2 = null;//the pawns forward move is a non hostile move, it is added here
		if(!this.hasMoved){//if a pawn has not moved this game, it can move a second space forward
			if(this.getPlayer().name.equals("p1")){//is player1 and is moving north
				forwardMove2 = Chess.board.spaces[this.getLY()-2][this.getLX()];
				if(forwardMove2.getPiece()==null && forwardMove1.getPiece()==null){
					possibleSpaces.add(forwardMove2);
				}
				
			}
			else{//is other player and is moving south
				forwardMove2 = Chess.board.spaces[this.getLY()+2][this.getLX()];
				if(forwardMove2.getPiece()==null && forwardMove1.getPiece()==null){
					possibleSpaces.add(forwardMove2);
				}
			}
		}
		
		ArrayList<Space> possibleSpacesOutOfCheck = (ArrayList<Space>) possibleSpaces.clone(); 
		
		for(Space possibleMove: possibleSpaces){//Check if any possible spaces result in putting active player in check
			Piece origionalFrom = Chess.board.spaces[this.getLY()][this.getLX()].getPiece();
			Piece origionalTo = possibleMove.getPiece();
			
			int origX = this.getLX();
			int origY = this.getLY();
			
			if(!(origionalTo == null)){
				//System.out.println(x);
				this.getPlayer().getKing().getOpponentsPieces().remove(origionalTo);
			}
			//System.out.println(origionalFrom);
			
			Chess.board.spaces[possibleMove.getLY()][possibleMove.getLX()].setPiece(this);
			Chess.board.spaces[this.getLY()][this.getLX()].setPiece(null);
			
			
			
			
			this.setLX(possibleMove.getLX());
			this.setLY(possibleMove.getLY());
			
			//this.getPlayer().getKing().refreshOpponentsPieces();
			
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
