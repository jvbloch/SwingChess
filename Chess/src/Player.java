import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Player {
	private ArrayList<Piece> pieces;
	private int points;
	public String name;
	private Piece king;
	
	public static String newPieceType;
	public final static Object lock = new Object();
	
	public Player(String name) {
		this.points = 0;
		this.name = name;
		
		
		
		//Chess.board.panel.note1.setText(name.toUpperCase());;
		
		this.pieces = new ArrayList<Piece>();
	}
	
	public boolean isInCheckMate(){
		
		
		
		for(Piece piece: this.pieces){//checks if any piece on team has a legal move
			
			//System.out.println(this.pieces);
			if(piece.removeCheckVulnerableMoves(piece.checkPossibleMoves()).size()>0){
				//System.out.println(piece);
				//System.out.println(piece.removeCheckVulnerableMoves(piece.checkPossibleMoves()).size());
				return false;
			}
		}
		
		return true;
	}
	
	public ArrayList<Piece> getPieces(){
		return this.pieces;
	}
	
	public void setPieces(ArrayList<Piece> newPieces){
		this.pieces = newPieces;
	}
	
	public void addPiece(String kindOfPiece, int lx, int ly){
		//add piece for player
		Piece newPiece = null;
		if(kindOfPiece.equals("Rook")){//rook
			newPiece = new Rook(this, lx, ly);
			System.out.println("New rook for "+this.name);
			
		}
		else if(kindOfPiece.equals("Knight")){//kngiht
			newPiece = new Knight(this, lx, ly);
			System.out.println("New knight for "+this.name);
		}
		else if(kindOfPiece.equals("Bishop")){//kngiht
			newPiece = new Bishop(this, lx, ly);
			System.out.println("New bishop for "+this.name);
		}
		else if(kindOfPiece.equals("Queen")){//kngiht
			newPiece = new Queen(this, lx, ly);
			System.out.println("New queen for "+this.name);
		}
		else if(kindOfPiece.equals("King")){//king
			newPiece = new King(this, lx, ly);
			System.out.println("New king for "+this.name);
		}
		else if(kindOfPiece.equals("Pawn")){//king
			newPiece = new Pawn(this, lx, ly);
			System.out.println("New pawn for "+this.name);
		}
		
		
		this.pieces.add(newPiece);
		
	}
	
	public void removePiece(Piece pieceToRemove){
		//remove piece
		//System.out.println(this.pieces);
		this.pieces.remove(pieceToRemove);
	}
	
	public void addPoints(int pointsToAdd){
		this.points+=pointsToAdd;
		if(this.name.equals("p1")){
			Chess.board.panel.p1PointsLabel.setText(this.points+" POINTS");
		}
		else{
			Chess.board.panel.p2PointsLabel.setText(this.points+" POINTS");
		}
	}

	public void setKing(Piece newKing){
		this.king = newKing;
	}
	public Piece getKing(){
		return this.king;
	}
	
	public void pawnPromotion(){
		
			
		
		Prompt promotion = new Prompt();
		promotion.promptLabel.setText("CHOOSE A PIECE TO PROMOTE TO:");
		
		
		promotion.option1.setText("QUEEN (RECOMMENDED)");
		promotion.option2.setText("       KNIGHT      ");
		promotion.option3.setText("       BISHOP      ");
		promotion.option4.setText("       ROOK        ");
		
		promotion.add(promotion.option1);
		promotion.add(promotion.option2);
		promotion.add(promotion.option3);
		promotion.add(promotion.option4);

		
		
		promotion.option1.addActionListener(new ActionListener()
		{
			  public void actionPerformed(ActionEvent e) 
			  {
				  
				  newPieceType = "Queen";
				  notifyAll();

				  promotion.setDefaultCloseOperation(1);
				  promotion.setVisible(false);
				  promotion.dispose();
				  
				  
			  }
			  
		});

		promotion.option2.addActionListener(new ActionListener()
		{
			  public void actionPerformed(ActionEvent e)
			  {
				  newPieceType = "Kight";
				  notifyAll();
				  
				  promotion.setDefaultCloseOperation(1);
				  promotion.setVisible(false);
				  promotion.dispose();
			  }
			  
		});
		
		promotion.option3.addActionListener(new ActionListener()
		{
			  public void actionPerformed(ActionEvent e)
			  {
				  newPieceType = "Bishop";
				  notifyAll();
				  
				  promotion.setDefaultCloseOperation(1);
				  promotion.setVisible(false);
				  promotion.dispose();
			  }
			  
		});
		promotion.option4.addActionListener(new ActionListener()
		{
			  public void actionPerformed(ActionEvent e)
			  {
				  newPieceType = "Rook";
				  notifyAll();
				  
				  promotion.setDefaultCloseOperation(1);
				  promotion.setVisible(false);
				  promotion.dispose();
			  }
			  
		});
		
		promotion.pack();
	
		//return newPieceType;
		
	}
}
