package group_project;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JLayeredPane;

@SuppressWarnings("serial")
public class GameScreen extends Canvas implements Runnable {

	private final static int WIDTH = 1024;
	private final static int HEIGHT = 768;
	private JPanel mainPanel;
	private JLabel backgroundLabel;
	private BufferedImage blackBackground;
	private JLabel titleLabel;
	private JLayeredPane layeredPane;

	public static void main(String[] args){
		new GameScreen();
	}
	
	public GameScreen()
	{
		setMinimumSize(new Dimension(WIDTH, HEIGHT));
		setMaximumSize(new Dimension(WIDTH, HEIGHT));
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		
		JFrame frame = new JFrame("Flizbaz");
		
		create();
		
		frame.setLayout(new BorderLayout());
		frame.add(mainPanel);
		frame.pack();
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		create();
	}

	
	public void create() {
		try{
			blackBackground = ImageIO.read(new File("../Data Structures Group Project/assets/black_background.png"));
		}
		catch(Exception e){
			e.printStackTrace();
		}
//		backgroundLabel = new JLabel(new ImageIcon(blackBackground));
		mainPanel = new JPanel();
		
		Image image = Toolkit.getDefaultToolkit().getImage("../Data Structures Group Project/assets/title_anim_combined.gif");
		backgroundLabel = new JLabel(new ImageIcon(image));
		mainPanel.add(backgroundLabel);
	}
	
	public void run() {
		
		
	}
}
