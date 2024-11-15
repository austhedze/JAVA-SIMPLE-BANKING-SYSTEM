// /*This is the Front screen named MainRoot that launches when the application is initializig
//  * it has a progress bar indictor
//  * the actual basic functionality  of this code was adapted from [https://www.javatpoint.com/java-jprogressbar]
//  */

import java.awt.Color;
import java.awt.Font;
import javax.swing.*;

public class ProgressBar {
   JFrame pane = new JFrame("Banking System");
   JLabel welcomelabel = new JLabel("Welcome to Banking System");//creating label and setting label text
   ImageIcon welcomeapp = new ImageIcon("bank3.png");
   JProgressBar bar = new JProgressBar();
	
	//initialize the class proprties
	ProgressBar(){
		bar.setValue(0);
		bar.setBounds(0, 520, 800, 20);
		bar.setStringPainted(true);
		bar.setFont(new Font("Consolas",Font.BOLD,18));
		bar.setBackground(Color.white);
		bar.setForeground(Color.GREEN);
	//	bar.setForeground(new Color(0, 128, 128));
	     
		
		// label design
		welcomelabel.setFont(new Font("consolas", Font.PLAIN, 30));
		welcomelabel.setBounds(210, 130, 500, 400);
		welcomelabel.setIcon(welcomeapp);
		welcomelabel.setHorizontalTextPosition(JLabel.CENTER);
		welcomelabel.setForeground(Color.WHITE);
		welcomelabel.setVerticalTextPosition(JLabel.TOP);
		welcomelabel.setIconTextGap(10);
		
		pane.add(bar);
		pane.add(welcomelabel);
		pane.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pane.setSize(800,650);
		pane.setLayout(null);
		pane.setResizable(false);
		pane.setVisible(true);
		pane.setLocationRelativeTo(null);
		pane.getContentPane().setBackground(new Color(0,80,181));

		
		//call the fill method 
		fill();
	}
	
	public void fill() {
		int counter =0;
		while(counter<=100) {
			bar.setValue(counter);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				JOptionPane.showMessageDialog(null, "Something happened while initializing");
			}
			counter+=1;
		}
		RegisterLogin registerLogin = new RegisterLogin();
		registerLogin.setVisible(true);
		pane.dispose();
	}
	
	public static void main(String[] args) {
		
      new ProgressBar();
	}

}
