import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Chess {

	public static Board board;
	public static Player p1;
	public static Player p2;
	protected static boolean isP1Turn;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Chess.generateMainMenu();
	}
	
	public static void newLocalPVPGame(){
		board = new Board();
		isP1Turn = true;
		//System.out.println(Chess.board.spaces[0][0]);
		
		Player p1 = new Player("p1");
		Player p2 = new Player("p2");
		Chess.p1 = p1;
		Chess.p2 = p2;
		
		p1.addPiece("Rook", 0, 7);
		p1.addPiece("Rook", 7, 7);
		p1.addPiece("Knight", 1, 7);
		p1.addPiece("Knight", 6, 7);
		p1.addPiece("Bishop", 2, 7);
		p1.addPiece("Bishop", 5, 7);
		p1.addPiece("Queen", 3, 7);
		p1.addPiece("Pawn", 0, 6);
		p1.addPiece("Pawn", 1, 6);
		p1.addPiece("Pawn", 2, 6);
		p1.addPiece("Pawn", 3, 6);
		p1.addPiece("Pawn", 4, 6);
		p1.addPiece("Pawn", 5, 6);
		p1.addPiece("Pawn", 6, 6);
		p1.addPiece("Pawn", 7, 6);
		p1.addPiece("King", 4, 7);
		
		
		p2.addPiece("Rook", 0, 0);
		p2.addPiece("Rook", 7, 0);
		p2.addPiece("Knight", 1, 0);
		p2.addPiece("Knight", 6, 0);
		p2.addPiece("Bishop", 2, 0);
		p2.addPiece("Bishop", 5, 0);
		p2.addPiece("Queen", 3, 0);
		p2.addPiece("Pawn", 0, 1);
		p2.addPiece("Pawn", 1, 1);
		p2.addPiece("Pawn", 2, 1);
		p2.addPiece("Pawn", 3, 1);
		p2.addPiece("Pawn", 4, 1);
		p2.addPiece("Pawn", 5, 1);
		p2.addPiece("Pawn", 6, 1);
		p2.addPiece("Pawn", 7, 1);
		
		p2.addPiece("King", 4, 0);
		
		
		
		//Prompt prompt = new Prompt();
		//prompt.promptLabel.setText("HAVE FUN");
		//prompt.setDefaultCloseOperation(1);
	}
	
	public static void passTurn(){//passes the turn between p1 and second player/comp player
		board.panel.statusLabel.setForeground(Color.green);
		if(isP1Turn){
			isP1Turn =false;
			Chess.board.panel.statusLabel.setText("PLAYER 2'S TURN");
			
		}
		else{
			isP1Turn =true;
			Chess.board.panel.statusLabel.setText("PLAYER 1'S TURN");
		}
	}
	
	public static void generateNewGamePrompt(String winner){
		
		Prompt newGamePrompt = new Prompt();
		newGamePrompt.setTitle("Check Mate");
		newGamePrompt.setIconImage(new ImageIcon("kingW.png").getImage());
		newGamePrompt.promptLabel.setText(winner + " WINS!");
		
		newGamePrompt.option1.setText("START NEW GAME");
		newGamePrompt.option2.setText("  MAIN  MENU  ");
		newGamePrompt.option3.setText("     EXIT     ");
		
		newGamePrompt.add(newGamePrompt.option1);
		newGamePrompt.add(newGamePrompt.option2);
		newGamePrompt.add(newGamePrompt.option3);
		
		//start new game
		newGamePrompt.option1.addActionListener(new ActionListener()
		{
			  public void actionPerformed(ActionEvent e)
			  {
				  //new game
				  Chess.board.setDefaultCloseOperation(1);
				  Chess.board.setVisible(false);
				  Chess.board.dispose();
				  
				  newLocalPVPGame();
				  
				  
				  
				  newGamePrompt.setDefaultCloseOperation(1);
				  newGamePrompt.setVisible(false);
				  newGamePrompt.dispose();
			  }
			  
		});
		//main menu
		newGamePrompt.option2.addActionListener(new ActionListener()
		{
			  public void actionPerformed(ActionEvent e)
			  {
				  //main menu
				  Chess.board.setDefaultCloseOperation(1);
				  Chess.board.setVisible(false);
				  Chess.board.dispose();
				  
				  generateMainMenu();

				  newGamePrompt.setDefaultCloseOperation(1);
				  newGamePrompt.setVisible(false);
				  newGamePrompt.dispose();
			  }
			  
		});
		//exit
		newGamePrompt.option3.addActionListener(new ActionListener()
		{
			  public void actionPerformed(ActionEvent e)
			  {
				  System.exit(0);
			  }
			  
		});
		
		newGamePrompt.pack();
	}
	
	public static void generateMainMenu(){
		Prompt mainMenu = new Prompt();
		mainMenu.setTitle("Swing Chess");
		mainMenu.setIconImage(new ImageIcon("kingW.png").getImage());
		mainMenu.promptLabel.setText("SWING CHESS");
		
		JLabel graphic =new JLabel(new ImageIcon("kingW.png"));
		
		graphic.setAlignmentX(mainMenu.CENTER_ALIGNMENT);
		mainMenu.add(graphic);
		
		mainMenu.option1.setText("LOCAL MULTIPLAYER ");
		mainMenu.option2.setText("REMOTE MULTIPLAYER");
		mainMenu.option3.setText("  SINGLE PLAYER   ");
		mainMenu.option4.setText("       EXIT       ");
		
		mainMenu.add(mainMenu.option1);
		mainMenu.add(mainMenu.option2);
		mainMenu.add(mainMenu.option3);
		mainMenu.add(mainMenu.option4);

		mainMenu.option2.setEnabled(false);
		mainMenu.option3.setEnabled(false);
		
		mainMenu.option1.addActionListener(new ActionListener()
		{
			  public void actionPerformed(ActionEvent e)
			  {
				  //new game
				  newLocalPVPGame();

				  mainMenu.setDefaultCloseOperation(1);
				  mainMenu.setVisible(false);
				  mainMenu.dispose();
			  }
			  
		});

		mainMenu.option2.addActionListener(new ActionListener()
		{
			  public void actionPerformed(ActionEvent e)
			  {
				  //newLocalPVPGame();

				  mainMenu.setDefaultCloseOperation(1);
				  mainMenu.setVisible(false);
				  mainMenu.dispose();
			  }
			  
		});
		
		mainMenu.option3.addActionListener(new ActionListener()
		{
			  public void actionPerformed(ActionEvent e)
			  {
				  //newLocalPVPGame();

				  mainMenu.setDefaultCloseOperation(1);
				  mainMenu.setVisible(false);
				  mainMenu.dispose();
			  }
			  
		});
		mainMenu.option4.addActionListener(new ActionListener()
		{
			  public void actionPerformed(ActionEvent e)
			  {
				  System.exit(0);
			  }
			  
		});
		mainMenu.setResizable(false);
		mainMenu.pack();
		
	}

	
}
