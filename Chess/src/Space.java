import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;  

public class Space extends JButton{
	public static int spaceNum = 1;
	private Color spaceColor;
	private Piece piece;
	private int locationX;
	private int locationY;
	
	
	
	public Space(int lx, int ly) {
		// TODO Auto-generated constructor stub
		//this.setText(Integer.toString(spaceNum));
		
		this.piece = null;
		this.locationX = lx;
		this.locationY = ly;
		
		//this.setMargin(new Insets(0, 0, 0, 0));
		//this.setBorder(null);
		
		
		if((spaceNum<=8)||(spaceNum>=17 && spaceNum<=24)||(spaceNum>=17 && spaceNum<=24)||(spaceNum>=33 && spaceNum<=40)||(spaceNum>=49 && spaceNum<=56)){
			if(spaceNum%2==1){
				spaceColor = Color.white;
			}
			else{
				spaceColor = Color.gray;
			}
		}
		else{
			if(spaceNum%2==1){
				spaceColor = Color.gray;
			}
			else{
				spaceColor = Color.white;
			}
		}
		this.setBackground(this.spaceColor);
		spaceNum++;
		
		this.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
			  
			  
		    if(Chess.board.selected==null){//check if piece has NOT been selected for moving
		    	if(getPiece()!=null){//check if space is occupied by a piece
			    	
		    		//check if the piece belongs to the current turn's player
		    		if((Chess.isP1Turn && getPiece().getPlayer().name.equals("p1"))||(!Chess.isP1Turn && !getPiece().getPlayer().name.equals("p1"))){
			    		Chess.board.selected=Chess.board.spaces[locationY][locationX];
			    		//System.out.println(Chess.board.selected);
			    		
			    		getPiece().showPossibleMoves();
			    		
			    		setBackground(Color.red);//arm piece
			    	}
			    	
			    }
			    else{
			    	
			    	
			    	System.out.println("No piece found");
			    }
		    }
		    else{
		    	//if selected piece can move here
		    	
		    	if(Chess.board.selected.getPiece().removeCheckVulnerableMoves(Chess.board.selected.getPiece().checkPossibleMoves()).contains(Chess.board.spaces[locationY][locationX])){
		    		//Space moveFrom = Chess.board.selected;
		    		//Piece movedPiece = moveFrom.getPiece();
		    		//Piece pieceReplacing = piece;
		    		
		    		
		    		
		    		Chess.board.selected.getPiece().move(locationX, locationY);
		    		
		    		/*if(movedPiece.getPlayer().getKing().isInCheck()){//check if active player is moving into check
		    			Chess.passTurn();
		    			//Chess.board.selected = moveFrom;
		    			//piece = pieceReplacing;
		    			piece.move(moveFrom.locationX, moveFrom.locationY);
		    			
		    			System.out.println("Cannot Move into Check");
		    		}*/
		    		
		    		Chess.board.resetSpaceColors();
			    	Chess.board.selected =null;
		    	}
		    	if(Chess.board.selected!=null){
			    	if(Chess.board.selected.getLX()==locationX&&Chess.board.selected.getLY()==locationY){
			    		Chess.board.resetSpaceColors();
				    	Chess.board.selected =null;
			    	}
		    	}
		    	
		    }
		    if(Chess.p1.getKing().isInCheck()){//if p1 is in check
				System.out.println("Player1 is in Check");
				Chess.board.panel.statusLabel.setText("PLAYER 1 IS IN CHECK");
				Chess.board.panel.statusLabel.setForeground(new Color(255, 20, 20));
				
				if(Chess.p1.isInCheckMate()){
					System.out.println("CheckMate: Player 2 Wins!");
					Chess.board.panel.statusLabel.setText("CHECKMATE: PLAYER 2 WINS!");
					//make newGame prompt
					Chess.generateNewGamePrompt("PLAYER 2");
				}
			}
			if(Chess.p2.getKing().isInCheck()){//if p2 is in check
				System.out.println("Player2 is in Check");
				Chess.board.panel.statusLabel.setText("PLAYER 2 IS IN CHECK");
				Chess.board.panel.statusLabel.setForeground(new Color(255, 20, 20));
				
				if(Chess.p2.isInCheckMate()){
					System.out.println("CheckMate: Player 1 Wins!");
					Chess.board.panel.statusLabel.setText("CHECKMATE: PLAYER 1 WINS!");
					//make newGame prompt
					Chess.generateNewGamePrompt("PLAYER 1");
				}
			}
			
			
		  }
		});
		
		
	}
	
	public void setPiece(Piece changeTo){
		this.piece = changeTo;
	}
	
	public Piece getPiece(){
		return this.piece;
	}
	
	public Color getColor(){
		return this.spaceColor;
	}
	
	public int getLX(){
		return this.locationX;
	}
	public int getLY(){
		return this.locationY;
	}
	
	//add actionlistener to move piece
	

}
