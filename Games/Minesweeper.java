package Games;
import javax.swing.JFrame;
public class Minesweeper  extends JFrame {
	public boolean minefield [][] = new boolean[16][30];
	
	public Minesweeper() {
		setup();
	}
	public int d=0;
	public int f =0;
	public void setup() { // sets up the minesweeper grid, 14% chance of a bomb per square 
		for(int x = 0; x<16; x++) {
			for(int y = 0; y<30; y++) {
				if(((int)(Math.random()*(99))+1)<20){
					minefield[x][y] = true;
					f++;
					}
				else {
					minefield[x][y] = false;
				}
			}
		}
		
	}
	public int bombCase(int x, int y) {
		int z = 0;
		if(d==0) {
			d++;
			return 20;
		}
		if(f==0) {
			System.out.println("Good Game");
			System.exit(0);
		}
		else if(minefield[x][y]) {
			z=10;
//			System.exit(0);
		}
		else {
			if(x==0&&y==0) {
//				System.out.println("TL");
				if(minefield[x+1][y])
					z++;f--;
				if(minefield[x+1][y+1])
					z++;f--;
				if(minefield[x][y+1])
					z++;f--;
			}
			else if(x==15&&y==0) {
//				System.out.println("TR");
				if(minefield[x-1][y])
					z++;f--;
				if(minefield[x-1][y+1])
					z++;f--;
				if(minefield[x][y+1])
					z++;f--;
			}
			else if(x==0&&y==15) {
//				System.out.println("bL");
				if(minefield[x+1][y])
					z++;f--;
				if(minefield[x+1][y-1])
					z++;f--;
				if(minefield[x][y-1])
					z++;f--;
			}
			else if(x==15&&y==15) {
//				System.out.println("bR");
				if(minefield[x-1][y])
					z++;f--;
				if(minefield[x-1][y-1])
					z++;f--;
				if(minefield[x][y-1])
					z++;f--;
			}
			else if(x==0) {
				if(minefield[x][y-1])
					z++;f--;
				if(minefield[x][y+1])
					z++;f--;
				if(minefield[x+1][y-1])
					z++;f--;
				if(minefield[x+1][y])
					z++;f--;
				if(minefield[x+1][y+1])
					z++;f--;
			}
			else if(x==15) {
				if(minefield[x-1][y+1])
					z++;f--;
				if(minefield[x-1][y])
					z++;f--;
				if(minefield[x-1][y-1])
					z++;f--;
				if(minefield[x][y-1])
					z++;f--;
				if(minefield[x][y+1])
					z++;f--;
			}
			else if(y==0) {
				if(minefield[x-1][y+1])
					z++;f--;
				if(minefield[x-1][y])
					z++;f--;
				if(minefield[x][y+1])
					z++;f--;
				if(minefield[x+1][y])
					z++;f--;
				if(minefield[x+1][y+1])
					z++;f--;
			}
			else if(y==15) {
				if(minefield[x-1][y])
					z++;f--;
				if(minefield[x-1][y-1])
					z++;f--;
				if(minefield[x][y-1])
					z++;f--;
				if(minefield[x+1][y-1])
					z++;f--;
				if(minefield[x+1][y])
					z++;f--;
				
			}
		
			else {
				if(minefield[x-1][y+1])
					z++;f--;
				if(minefield[x-1][y])
					z++;f--;
				if(minefield[x-1][y-1])
					z++;f--;
				if(minefield[x][y-1])
					z++;f--;
				if(minefield[x][y+1])
					z++;f--;
				if(minefield[x+1][y-1])
					z++;f--;
				if(minefield[x+1][y])
					z++;f--;
				if(minefield[x+1][y+1])
					z++;f--;

			}
		}
	
		return z;
	}
	
	
	
	
	
}

	