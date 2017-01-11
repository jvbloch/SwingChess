import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Prompt extends JFrame{

	public JLabel promptLabel;
	public JButton option1;
	public JButton option2;
	public JButton option3;
	public JButton option4;
	
	public Prompt() {
		// TODO Auto-generated constructor stub
		
		JPanel panel = new JPanel();
		this.setSize(400, 300);
		this.setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
		
		
		this.setTitle("Prompt");
		//setLayout(null);  
		setVisible(true);  
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Font font = new Font("Courier", Font.BOLD,24);
		
		//Create text prompt
		this.promptLabel = new JLabel("Prompt Text");
		this.promptLabel.setAlignmentX(this.CENTER_ALIGNMENT);
		this.promptLabel.setFont(font);
		add(this.promptLabel);
		
		this.option1 = new JButton("Option1");
		this.option1.setAlignmentX(this.CENTER_ALIGNMENT);
		this.option1.setFont(font);
		//add(this.option1);
		
		this.option2 = new JButton("option2");
		this.option2.setAlignmentX(this.CENTER_ALIGNMENT);
		this.option2.setFont(font);
		//add(this.option2);
		
		this.option3 = new JButton("option3");
		this.option3.setAlignmentX(this.CENTER_ALIGNMENT);
		this.option3.setFont(font);
		//add(this.option3);
		
		this.option4 = new JButton("option4");
		this.option4.setAlignmentX(this.CENTER_ALIGNMENT);
		this.option4.setFont(font);
		//add(this.option4);
		
		this.pack();
	}

}
