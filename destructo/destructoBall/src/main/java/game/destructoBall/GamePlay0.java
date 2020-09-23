package game.destructoBall;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePlay0 extends JPanel implements KeyListener, ActionListener, MouseMotionListener  {
	private boolean play = false;
	private int score = 0;
	private int totalBricks = 21;
	private Timer time;
	private int playerOne = 310;
	private int ballposX = 120;
	private int ballposY = 350;
	private int ballXdir = -2;
	private int ballYdir = -4;
	private MapGen0 map = new MapGen0(3,7);
	int level = 1;
	
	
	public GamePlay0() {
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		int delay = 8;
		time = new Timer(delay,this);
		time.start();
	}

		

	public void paint(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(1, 1, 692, 592); // background
		
		map.draw((Graphics2D)g);
		
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, 3, 592);
		g.fillRect(0, 0, 692, 3);
		g.fillRect(692, 0, 3, 592); // border
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arail",Font.BOLD,25));
		g.drawString(""+score,590,30);
		g.drawString(""+level,590,70);
		g.setColor(Color.green);
		g.fillRect(playerOne, 550, 100, 8); // paddle
		
		g.setColor(Color.yellow);
		g.fillOval(ballposX, ballposY, 20, 20); // ball
	

			repaint();


			g.setColor(Color.RED);
			g.setFont(new Font("Arial",Font.BOLD,30));


			g.setFont(new Font("Arial",Font.BOLD,20));



		
		
			if(ballposY > 570) {
			play = false;
			ballYdir = 0;
			ballXdir = 0;
			g.setColor(Color.RED);
			g.setFont(new Font("Arail",Font.BOLD,30));
			g.drawString("Game Over",230,300);
			
			g.setFont(new Font("Arail",Font.BOLD,20));
			g.drawString("Press Enter to Restart",230,350);
		}
			
			if(totalBricks <= 0) {

				play = false;
				ballYdir = 0;
				ballXdir = 0;
				g.setColor(Color.RED);
				g.setFont(new Font("Arial", Font.BOLD, 30));
				g.drawString("You Win", 230, 300);

				g.setFont(new Font("Arial", Font.BOLD, 20));

				g.drawString("Press Enter to Restart", 230, 350);
			}
		
		g.dispose();
	}
	

	public void actionPerformed(ActionEvent arg0) {
		time.start();
		if(play) {
			if(new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerOne, 550, 100, 8))) {
				ballYdir = -ballYdir;
			}
			A:for(int i = 0; i < map.map.length; i++) {
				 	for(int j = 0; j < map.map[0].length; j++) {
					if(map.map[i][j] > 0) {
						int brickX = j * map.brickWidth +80;
						int brickY = i * map.brickHeight +50;
						int brickHeight = map.brickHeight;
						int brickWidth = map.brickWidth;
						
						Rectangle reck = new Rectangle(brickX, brickY, brickWidth, brickHeight);
						Rectangle ballReck = new Rectangle(ballposX, ballposY, 20, 20);

						if(ballReck.intersects(reck)) {
							map.setBrickValue(0, i, j);
							totalBricks--;
							score++;
							if(ballposX + 19 <= reck.x || ballposX + 1 >= reck.x + reck.width) {
								ballXdir = -ballXdir;
						}
						else {
								ballYdir = -ballYdir;
						}
						break A;
						}
					
					}
			}
		}
			ballposX += ballXdir;
			ballposY += ballYdir;
			
			if(ballposX < 0) {
				ballXdir = -ballXdir;
			}
			if(ballposY < 0) {
				ballYdir = -ballYdir;
			}
			if(ballposX > 670) {
				ballXdir = -ballXdir;
			}
		}
		repaint();
		
	}

	public void keyPressed(KeyEvent arg0) {
		if(arg0.getKeyCode()== KeyEvent.VK_RIGHT) {
			if(playerOne >= 591) {
				playerOne = 591;
			}
			else {
				moveRight();
			}
		}
		if(arg0.getKeyCode()== KeyEvent.VK_LEFT) {
			if(playerOne <= 4) {
				playerOne = 4;
			}
			else {
				moveLeft();
			}
		}
		if(arg0.getKeyCode() == KeyEvent.VK_ENTER){
			totalBricks=0;
			if(!play) {
				play = true;
				ballposX = 120;
			    ballposY = 350;
			    ballXdir = -2;
				ballYdir = -4;
				playerOne = 310;
				score = 0;
				totalBricks = 21;
				map = new MapGen0(3,7);

				repaint();

			}
		}
		
	}
	


	public void moveLeft() {
		play = true;
		playerOne -= 30;
		
	}
	public void moveRight() {
		play = true;
		playerOne += 30;
		
	}
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void mouseMoved(MouseEvent arg0) {
		
		
	}

}
