package game.destructoBall;

import java.awt.BasicStroke;

import java.awt.Color;
import java.awt.Graphics2D;

public class MapGen1 {
	public int[][] map1;
	public int brickWidth;
	public int brickHeight;

	public MapGen1(int row, int column) {
		map1 = new int[row][column];
		for (int i = 0; i < map1.length; i++) {
			for (int j = 0; j < map1[0].length; j++) {
				map1[i][j] = 1;
			}
		}

		brickWidth = 540 / column;
		brickHeight = 150 / row;
	}

	public void draw(Graphics2D g) {
		for (int i = 0; i < map1.length; i++) {
			for (int j = 0; j < map1[0].length; j++) {
				if (map1[i][j] > 0) {
					g.setColor(Color.WHITE);
					g.fillRect(j * brickWidth + 80, i * brickHeight + 80, brickWidth, brickHeight);
					g.setStroke(new BasicStroke(3));
					g.setColor(Color.black);
					g.drawRect(j * brickWidth + 80, i * brickHeight + 80, brickWidth, brickHeight);
				}
			}
		}
	}

	public void setBrickValue(int value, int row, int column) {
		map1[row][column] = value;
	}

}