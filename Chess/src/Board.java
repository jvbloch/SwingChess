import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.VolatileImage;

import javax.swing.*; 
public class Board extends JFrame{

	public Space[][] spaces;
	
	public static Space selected = null;
	
	public StatBoard panel;
	 
	public Board() {
		//this.setPreferredSize(new Dimension(3000, 3000));
		//this.getContentPane().setSize(3000, 3000);
		
		
		this.setTitle("SWING CHESS");
		ImageIcon img = new ImageIcon("kingW.png");
		
		Space.spaceNum = 1;
		
		this.setIconImage(img.getImage());
		
		this.spaces = new Space[8][8];
		int rowNum=0;
		
		for(Space[] row:this.spaces){
			int colNum=0;
			for(Space square: row){
				square = new Space(colNum, rowNum);
				square.setSize(50, 50);
				
				
				square.setLocation((colNum*50), (rowNum*50));
				this.add(square);
				spaces[rowNum][colNum] = square;
				
				colNum++;
				
			}
			rowNum++;
			
		}
		panel = new StatBoard();
		panel.setLocation(0, 405);
		this.add(panel);
		
		//Space b=new Space();
		//b.setSize(50, 50);
		          
		//add(b);//adding button on frame  
		setSize(405,505);
		setLayout(null);  
		setVisible(true);  
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//spaces[7][0].setPiece(new Rook());
		
		setResizable(false);
		//pack();
	}
	
	public void resetSpaceColors(){
		for(Space[] row: spaces){
			for(Space square: row){
				square.setBackground(square.getColor());
			}
		}
	}

}
