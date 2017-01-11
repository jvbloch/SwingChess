import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class StatBoard extends JPanel{
	
	public JLabel p1NameLabel;
	public JLabel p1PointsLabel;
	public JLabel p2NameLabel;
	public JLabel p2PointsLabel;
	public JLabel statusLabel;

	public StatBoard() {
		// TODO Auto-generated constructor stub
		this.setSize(400, 75);
		this.setBackground(Color.gray);
		setLayout(new GridLayout(3, 2));
		p1NameLabel = new JLabel("PLAYER 1");
		add(p1NameLabel);
		p1PointsLabel = new JLabel("0 POINTS");
		add(p1PointsLabel);
		p2NameLabel = new JLabel("PLAYER 2");
		add(p2NameLabel);
		p2PointsLabel = new JLabel("0 POINTS");
		add(p2PointsLabel);
		statusLabel = new JLabel("PLAYER 1'S TURN");
		add(statusLabel);
		//JLabel note6 = new JLabel("note6");
		//add(note6);
	
		Font font = new Font("Courier", Font.BOLD,16);
		p1NameLabel.setFont(font);
		p1PointsLabel.setFont(font);
		p2NameLabel.setFont(font);
		p2PointsLabel.setFont(font);
		font = new Font("Courier", Font.BOLD,14);
		statusLabel.setFont(font);
		
		//note6.setFont(font);
		
		p1NameLabel.setFont(font);
		p1NameLabel.setForeground(Color.white);
		p1PointsLabel.setForeground(Color.white);
		statusLabel.setForeground(new Color(10,255,10));
	}

}
