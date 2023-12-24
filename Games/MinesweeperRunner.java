package Games;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
public class MinesweeperRunner extends JFrame{
	public static	JButton[][] button = new JButton[16][16];
//	public static	JLabel[][] count = new JLabel[16][30];
	static Minesweeper GUI = new Minesweeper();
	public static void main(String[] args) {
		gui();
	}
	public static void gui() {
		GUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GUI.setLayout(null);
		GUI.setSize(850,850);
		GUI.setVisible(true);
		GUI.setTitle("MINESWEEPER");
		GUI.setLocationRelativeTo(null);
		for(int x = 0; x<16; x++) {
			for(int y = 0; y<16; y++) {
				button[x][y] = new JButton("");
				button[x][y].setBounds(x*50,y*50,50,50);
				GUI.add(button[x][y]);
				button[x][y].addActionListener(new Action(x,y));
				
			}
		}
		
	}
	static class Action implements ActionListener{
		public int v1;
		public int v2;
		public Action(int x, int y) {
			 v1 = x;
			 v2 = y;
			 
		}

		@Override
		public void actionPerformed(ActionEvent e) {
				bpress(v1+1,v2+1);
				
		}
		
	}
	public static void bpress(int v1, int v2) {
//		System.out.println(v1+","+v2);
		int bc =GUI.bombCase(v1-1,v2-1);
		String cd = bc+" ";
		if(bc==10){
			over();
//			JLabel over = new JLabel("GAME OVER");
//			over.setBounds(400,400,200,200);
//			button[v1-1][v2-1].setEnabled(false);
//			GUI.add(over);
		}
		else if(bc==20) {
			start(v1-1,v2-1);
		}
		else {
			button[v1-1][v2-1].setBackground(Color.green);
			button[v1-1][v2-1].setText(cd);
			button[v1-1][v2-1].setEnabled(false);
		}
	}
	public static void start(int g, int h) {
		for (int x = (g-1);x<(g+2);x++) {
			for (int y = (h-1);y<(h+2);y++) {
				if(!(x<0)&&!(y<0)&&!(x>15)&&!(y>15)) {
					int bc = GUI.bombCase(x,y);
					String cd = bc+" ";
					if(bc==10) {
						cd = "B";
						button[x][y].setBackground(Color.red);
					}
				
					else {
						if(!button[x][y].isEnabled())
							button[x][y].setBackground(Color.green);
						else
							button[x][y].setBackground(Color.green);
					}
					
					button[x][y].setText(cd);
					button[x][y].setEnabled(false);
				}
			}
			
		}
	}
	public static void over() {
		System.out.println("GAME OVER");
		for(int x = 0; x<16; x++) {
			for(int y = 0; y<16; y++) {
				int bc =GUI.bombCase(x,y);
				String cd = bc+" ";
				if(bc==10) {
					cd = "B";
					button[x][y].setBackground(Color.red);
				}
				
				else {
					if(!button[x][y].isEnabled())
						button[x][y].setBackground(Color.green);
					else
						button[x][y].setBackground(Color.cyan);
				}
				
				button[x][y].setText(cd);
				button[x][y].setEnabled(false);
				
			}
		}
	}
	

}