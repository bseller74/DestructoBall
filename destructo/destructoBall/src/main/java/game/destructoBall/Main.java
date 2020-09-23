package game.destructoBall;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		JFrame window = new JFrame();
		GamePlay0 gameplayWindow = new GamePlay0();
		window.setBounds(10,10,700,600);
		window.setTitle("Destructo Ball");
		window.setResizable(false);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.add(gameplayWindow);
		
	}

}
