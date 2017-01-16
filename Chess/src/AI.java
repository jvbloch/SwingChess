import java.util.ArrayList;

public class AI extends Player{

	public AI() {
		// TODO Auto-generated constructor stub
		super("COMPUTER PLAYER");
		Chess.board.panel.p2NameLabel.setText(this.name);
	}
	@Override
	public void takeTurn(){
		if(this.isInCheckMate()){
			
		}
		else{
			Node move =this.chooseNode();//chooses best move
			//moves piece
			move.getPieceToMove().move(move.getSpaceToMoveTo().getLX(), move.getSpaceToMoveTo().getLY());
			System.out.println("AI pieve moved");
		}
		
	}
	
	public Node chooseNode(){
		Node[] possibleMoves = this.generateNodes();
		//choose best move somehow
		Node bestMove = possibleMoves[0];//change to have separate method choose move(like comment below)
		//bestMove = this.chooseBestMove(possibleMoves);
		
		System.out.println(possibleMoves.length);
		//System.out.println(bestMove.getPieceToMove());
		//System.out.println(bestMove.getSpaceToMoveTo().getLX()+", " + bestMove.getSpaceToMoveTo().getLY());
		
		return bestMove;
	}
	
	public Node[] generateNodes(){
		ArrayList<Node> possibleMoves = new ArrayList<Node>();
		//return all possible moves
		for(Piece piece: this.getPieces()){//for each piece
			for(Space move: piece.removeCheckVulnerableMoves(piece.checkPossibleMoves())){ //for each possible move
				if(!(piece.isKing && move.getLX()==piece.getLX() && move.getLY()==piece.getLY())){//if move is not king standing still
					possibleMoves.add(new Node(piece, move));//create node
				}
				//might need to add original position
			}
		}
		Node[] possibleMoveList = possibleMoves.toArray(new Node[possibleMoves.size()]);
		return possibleMoveList;
	}

	public Node chooseBestMove(Node[] possibleMoves){
		return null;
		
	}
}
