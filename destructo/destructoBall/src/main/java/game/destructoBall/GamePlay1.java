package game.destructoBall;

import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.Timer;
import javax.swing.JPanel;

public class

GamePlay1 extends JPanel implements KeyListener, ActionListener, MouseMotionListener {
	private static boolean play = false;
	private int score = 0;
	private int totalBricks = 21;
	private Timer time;
	private int delay = 8;
	private int playerOne = 310;
	private int ballposX = 120;
	private int ballposY = 350;
	private int ballXdir = -2;
	private int ballYdir = -4;
	private int ball2posX = 320;
	private int ball2posY = 350;
	private int ball2Xdir = -2;
	private int ball2Ydir = -4;
	private MapGen1 map;

	public GamePlay1() {
		map = new MapGen1(3, 7);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		time = new Timer(delay, this);
		time.start();
	}

	public void paint(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(1, 1, 692, 592); // background

		map.draw((Graphics2D) g);

		g.setColor(Color.GRAY);
		g.fillRect(0, 0, 3, 592);
		g.fillRect(0, 0, 692, 3);
		g.fillRect(692, 0, 3, 592); // border

		g.setColor(Color.WHITE);
		g.setFont(new Font("Arail", Font.BOLD, 25));
		g.drawString("" + score, 590, 30);

		g.setColor(Color.green);
		g.fillRect(playerOne, 550, 140, 8); // paddle

		g.setColor(Color.yellow);
		g.fillOval(ballposX, ballposY, 20, 20); // ball

		g.setColor(Color.blue);
		g.fillOval(ball2posX, ball2posY, 20, 20); // ball2

		if (totalBricks <= 0) {

			play = false;
			ballYdir = 0;
			ballXdir = 0;
			ball2Ydir = 0;
			ball2Xdir = 0;
			g.setColor(Color.RED);
			g.setFont(new Font("Arail", Font.BOLD, 30));
			g.drawString("You Win", 230, 300);

			g.setFont(new Font("Arail", Font.BOLD, 20));

			g.drawString("Press Enter to Restart", 230, 350);

		}

		if (ballposY > 570 && ball2posY > 570) {
			play = false;
			ballYdir = 0;
			ballXdir = 0;
			ball2Ydir = 0;
			ball2Xdir = 0;
			g.setColor(Color.RED);
			g.setFont(new Font("Arail", Font.BOLD, 30));
			g.drawString("Game Over", 230, 300);

			g.setFont(new Font("Arail", Font.BOLD, 20));
			g.drawString("Press Enter to Restart", 230, 350);
		}

		g.dispose();
	}

	public void actionPerformed(ActionEvent arg0) {
		time.start();
		play = true;
		if (play) {
			if (new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerOne, 550, 140, 8))) {
				ballYdir = -ballYdir;
			}
			if (new Rectangle(ball2posX, ball2posY, 20, 20).intersects(new Rectangle(playerOne, 550, 140, 8))) {
				ball2Ydir = -ball2Ydir;
			}
			if (new Rectangle(ball2posX, ball2posY, 20, 20).intersects(new Rectangle(ballposX, ballposY, 20, 20))) {
				ball2Ydir = -ball2Ydir;
				ballYdir = -ballYdir;
			}
			A: for (int i = 0; i < map.map1.length; i++) {
				for (int j = 0; j < map.map1[0].length; j++) {
					if (map.map1[i][j] > 0) {
						int brickX = j * map.brickWidth + 80;
						int brickY = i * map.brickHeight + 50;
						int brickHeight = map.brickHeight;
						int brickWidth = map.brickWidth;

						Rectangle reck = new Rectangle(brickX, brickY, brickWidth, brickHeight);
						Rectangle ballReck = new Rectangle(ballposX, ballposY, 20, 20);
						Rectangle brickReck = reck;

						if (ballReck.intersects(brickReck)) {
							map.setBrickValue(0, i, j);
							totalBricks--;
							score++;
							if (ballposX + 19 <= brickReck.x || ballposX + 1 >= brickReck.x + brickReck.width) {
								ballXdir = -ballXdir;
							} else {
								ballYdir = -ballYdir;
							}
							break A;
						}

					}
				}
			}
			B: for (int i = 0; i < map.map1.length; i++) {
				for (int j = 0; j < map.map1[0].length; j++) {
					if (map.map1[i][j] > 0) {
						int brickX = j * map.brickWidth + 80;
						int brickY = i * map.brickHeight + 50;
						int brickHeight = map.brickHeight;
						int brickWidth = map.brickWidth;

						Rectangle reck = new Rectangle(brickX, brickY, brickWidth, brickHeight);
						Rectangle ball2Reck = new Rectangle(ball2posX, ball2posY, 20, 20);
						Rectangle brickReck = reck;

						if (ball2Reck.intersects(brickReck)) {
							map.setBrickValue(0, i, j);
							totalBricks--;
							score++;
							if (ball2posX + 19 <= brickReck.x || ball2posX + 1 >= brickReck.x + brickReck.width) {
								ball2Xdir = -ball2Xdir;
							} else {
								ball2Ydir = -ball2Ydir;
							}
							break B;
						}
						
					}
				}
			}

			ballposX += ballXdir;
			ballposY += ballYdir;
			ball2posX += ball2Xdir;
			ball2posY += ball2Ydir;

			if (ballposX < 0) {
				ballXdir = -ballXdir;
			}
			if (ballposY < 0) {
				ballYdir = -ballYdir;
			}
			if (ballposX > 670) {
				ballXdir = -ballXdir;
			}

			if (ball2posX < 0) {
				ball2Xdir = -ball2Xdir;
			}
			if (ball2posY < 0) {
				ball2Ydir = -ball2Ydir;
			}
			if (ball2posX > 670) {
				ball2Xdir = -ball2Xdir;
			}

		}
		repaint();

	}

	public void keyPressed(KeyEvent arg0) {
		if (arg0.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (playerOne >= 591) {
				playerOne = 591;
			} else {
				moveRight();
			}
		}
		if (arg0.getKeyCode() == KeyEvent.VK_LEFT) {
			if (playerOne <= 4) {
				playerOne = 4;
			} else {
				moveLeft();
			}
		}
		if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
			if (!play) {
				play = true;
				
				ball2posX = 120;
				ball2posY = 350;
				ball2Xdir = -2;
				ball2Ydir = -4;

				ballposX = 120;
				ballposY = 350;
				ballXdir = -2;
				ballYdir = -4;
				playerOne = 310;
				score = 0;
				totalBricks = 21;
				
				map = new MapGen1(3, 7);

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