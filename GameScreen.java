package group_project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GameScreen extends JFrame {

	private final static int WIDTH = 1024;
	private final static int HEIGHT = 768;
	private JPanel gamePanel;
	private JPanel compassPanel;
	private JLabel startScreenBackgroundLabel;
	private JLabel backgroundLabel;
	public JButton north;
	public JButton south;
	public JButton east;
	public JButton west;
	private boolean startMenuExists = true;
	private boolean startMenu = false;
	private Image startScreen;
	private Image background;
	//DEBUG
	public static void main(String[] args){
		new GameScreen();
	}
	
	public GameScreen(){
		setMinimumSize(new Dimension(WIDTH, HEIGHT));
		setMaximumSize(new Dimension(WIDTH, HEIGHT));
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		init();
		drawStartScreen();
		
		setTitle("Flizbaz");
		setLayout(new BorderLayout());
		add(gamePanel);
		pack();
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public void init(){
		gamePanel = new JPanel();
		gamePanel.setLayout(new BorderLayout());
		
		startScreen = Toolkit.getDefaultToolkit().getImage("title_anim_combined.gif");
		startScreenBackgroundLabel = new JLabel(new ImageIcon(startScreen));
		startScreenBackgroundLabel.addMouseListener(new mouseListener());
		
		background = Toolkit.getDefaultToolkit().getImage("background.png");
		backgroundLabel = new JLabel(new ImageIcon(background));
		backgroundLabel.addMouseListener(new mouseListener());
	}
	
	public void drawCompass(){
		JPanel panelHolder = new JPanel();
		compassPanel = new JPanel();
		compassPanel.setLayout(new BorderLayout());
		north = new JButton("N");
		south = new JButton("S");
		east = new JButton("E");
		west = new JButton("W");
		
		north.addActionListener(new compassListener());
		south.addActionListener(new compassListener());
		east.addActionListener(new compassListener());
		west.addActionListener(new compassListener());
		
		panelHolder.add(compassPanel);
		panelHolder.setBackground(Color.BLACK);
		
		compassPanel.add(north, BorderLayout.NORTH);
		compassPanel.add(south, BorderLayout.SOUTH);
		compassPanel.add(east, BorderLayout.EAST);
		compassPanel.add(west, BorderLayout.WEST);
		compassPanel.setBackground(Color.BLACK);
		gamePanel.add(panelHolder, BorderLayout.SOUTH);
	}
	
	public void drawStartScreen() {
		gamePanel.add(startScreenBackgroundLabel);
	}
	
	public void drawGameScreen() {
		gamePanel.remove(startScreenBackgroundLabel);
		gamePanel.setBackground(Color.BLACK);
		drawCompass();
		revalidate();
	}
	
	public class compassListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
		
	}
	public class mouseListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent click) {
			
			System.out.println("X is " + click.getX() + " Y is: " + click.getY());
			if(startMenuExists){
				startMenu = click.getX() > 295 && click.getX() < 758 && click.getY() > 162 && click.getY() < 236;
			}
			if(startMenu){
				startMenuExists = false;
				startMenu = false;
				drawGameScreen();	
			}
		}
		
		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	}
}
