package Games;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Tetris extends JFrame implements KeyListener{
	int xmod = 0;
	int whil =0;
	int blockT = 0;;
	int yp1 = 700, yp2 = 700, yp3=700,yp4=700;
	int x1;
	int x2;
	int x3;
	int x4;
	int y1;
	int y2;
	int y3;
	int y4;
	
	int xx1;
	int xx2;
	int xx3;
	int xx4;
	int yy1;
	int yy2;
	int yy3;
	int yy4;
	
	int bpos = 0;
	
	boolean offset;
	boolean offsett2;
	boolean[][] filled= new boolean[12][40];
	public Tetris() {
//		JLabel test = new JLabel("H");
//		test.setBounds(420,760,20,20); //--> 420 Middle of x
//		test.setBackground(Color.red);
//		test.setOpaque(true);
//		this.add(test);	
		gui();
	}
	public  void gui() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setSize(850,850);
		this.setTitle("Budget Tetris");
		this.setLocationRelativeTo(null);
		this.addKeyListener(this);
		this.setVisible(true);
		for(int x = 0;x<12;x++) {
			for(int y = 0;y<40;y++) {
				JLabel d = new JLabel("");
				d.setBounds(x*20+320,760-y*20,20,20);
				d.setOpaque(false);
				d.setBorder(BorderFactory.createLineBorder(Color.gray));
				this.add(d);
			}
		}
		newBlock();

	}
	public void newBlock() {
		int[] block =PR((int)Math.floor(Math.random()*(7)+1));
//		System.out.println(block[0]+" "+block[1]+ " " +block[2]+" "+block[3]);
		JLabel[] part = new JLabel[4];
//		JLabel test = new JLabel("H");
//		test.setBounds(30,30,30,30);
//		test.setBackground(Color.red);
//		test.setOpaque(true);
//		this.add(test); 11 x spaces
		xx1 = x1 = xcord(block[0])*20+400;
		xx2 = x2 = xcord(block[1])*20+400;
		xx3 = x3 = xcord(block[2])*20+400;
		xx4 = x4 = xcord(block[3])*20+400;
		yy1 = y1 = ycord(block[0])*20;
		yy2 = y2 = ycord(block[1])*20;
		yy3 = y3 = ycord(block[2])*20;
		yy4 = y4 = ycord(block[3])*20;


		part[0] = new JLabel("1");
		part[0].setBounds(x1,y1,20,20);
		part[1] = new JLabel("2");
		part[1].setBounds(x2 , y2,20,20);;
		part[2] = new JLabel("3");
		part[2].setBounds(x3 , y3,20,20);
		part[3] = new JLabel("4");
		part[3].setBounds(x4 , y4,20,20);
		switch(blockT) {
		case 1:
			part[0].setBackground(Color.red);
			part[1].setBackground(Color.red);
			part[2].setBackground(Color.red);
			part[3].setBackground(Color.red);
			break;
		case 2:
			part[0].setBackground(Color.blue);
			part[1].setBackground(Color.blue);
			part[2].setBackground(Color.blue);
			part[3].setBackground(Color.blue);
			break;
		case 3:
			part[0].setBackground(Color.green);
			part[1].setBackground(Color.green);
			part[2].setBackground(Color.green);
			part[3].setBackground(Color.green);
			break;
		case 4:
			part[0].setBackground(Color.cyan);
			part[1].setBackground(Color.cyan);
			part[2].setBackground(Color.cyan);
			part[3].setBackground(Color.cyan);
			break;
		case 5:
			part[0].setBackground(Color.magenta);
			part[1].setBackground(Color.magenta);
			part[2].setBackground(Color.magenta);
			part[3].setBackground(Color.magenta);
			break;
		case 6:
			part[0].setBackground(Color.yellow);
			part[1].setBackground(Color.yellow);
			part[2].setBackground(Color.yellow);
			part[3].setBackground(Color.yellow);
			break;
		case 7:
			part[0].setBackground(Color.pink);
			part[1].setBackground(Color.pink);
			part[2].setBackground(Color.pink);
			part[3].setBackground(Color.pink);
			break;
		}
		part[0].setOpaque(true);
		part[1].setOpaque(true);
		part[2].setOpaque(true);
		part[3].setOpaque(true);
		this.add(part[0]);
		this.add(part[1]);
		this.add(part[2]);
		this.add(part[3]);
		
		while(whil==0) {
			part[0].setLocation(x1+xmod, y1);
			part[1].setLocation(x2+xmod, y2);
			part[2].setLocation(x3+xmod, y3);
			part[3].setLocation(x4+xmod, y4);
			if(part[0].getX()<320||part[3].getX()<320) {
				x1+=20;
				x2+=20;
				x3+=20;
				x4+=20;
			}
			else if(part[3].getX()>560) {
				x1-=20;
				x2-=20;
				x3-=20;
				x4-=20;
			}
			else if(part[3].getX()>540||part[0].getX()>540) {
				x1-=20;
				x2-=20;
				x3-=20;
				x4-=20;
			}
			
			
		}
		System.out.println(part[0].getY()+" "+part[1].getY()+" "+part[2].getY()+" "+part[3].getY()+" ");

//		yp1+=y1-40;
//		yp2+=y2-40;
//		yp3+=y3-40;
//		yp4+=y4-40;
		
		int vert=checkArray(part[0].getX(),part[1].getX(),part[2].getX(),part[3].getX(),0,0,0,0);
		
//		while(!checkArray(part[0].getX(),part[1].getX(),part[2].getX(),part[3].getX(),700-vert+y1,700-vert+y2,700-vert+y3,700-vert+y4)) {
//			vert+=20;
//		}
		int verMod = 700-vert;
		if(blockT==5&&(bpos==3||bpos==1)){
			verMod = 680-vert;
		}
		
		if(y4<80) {
			part[0].setLocation(part[0].getX(), verMod+y1);
			part[1].setLocation(part[1].getX(), verMod+y2);
			part[2].setLocation(part[2].getX(), verMod+y3);
			part[3].setLocation(part[3].getX(), verMod+y4);
		}
		else {
			part[0].setLocation(part[0].getX(), verMod+y1-20);
			part[1].setLocation(part[1].getX(), verMod+y2-20);
			part[2].setLocation(part[2].getX(), verMod+y3-20);
			part[3].setLocation(part[3].getX(), verMod+y4-20);
		}
		fillArray(part[0].getX(),part[1].getX(),part[2].getX(),part[3].getX(),part[0].getY(),part[1].getY(),part[2].getY(),part[3].getY());
//		System.out.println(part[0].getY()+" "+part[1].getY()+" "+part[2].getY()+" "+part[3].getY()+" ");
//		System.out.println(part[0].getX()+" "+part[1].getX()+" "+part[2].getX()+" "+part[3].getX()+" ");
		xmod=0;
		bpos=0;
		whil = 0;
		newBlock();
		
	}
	public int xcord(int x) {
		int y;
		if(x==1||x==4||x==7||x==10) {
			y=1;
		}
		else if(x==2||x==5||x==8)
			y=2;
		else
			y=3;
		return y;
	}
	public int ycord(int x) {
		int y;
		if(x==1||x==2||x==3) {
			y=1;
		}
		else if(x==4||x==5||x==6)
			y=2;
		else if(x==7||x==8||x==9)
			y=3;
		else
			y=4;
		return y;
	}
	public int[] PR(int x) {
		int[] atr = new int[4];
		if(x==1){//Straight
			atr[0] = 1;
			atr[1] = 4;
			atr[2] = 7;
			atr[3] = 10;
			offset = false;
			offsett2 = false;
			blockT = 1;
		}
		else if(x==2){//t piece
			atr[0] = 7;
			atr[1] = 5;
			atr[2] = 8;
			atr[3] = 9;
			offset = true;
			offsett2 = true;
			blockT = 2;


		}
		else if(x==3){//square
			atr[0] = 4;
			atr[1] = 7;
			atr[2] = 5;
			atr[3] =8;
			offset = true;
			offsett2 = false;
			blockT = 3;


		}
		else if(x==4){//L piece 
			atr[0] = 1;
			atr[1] = 4;
			atr[2] = 7;
			atr[3] = 8;
			offset = true;
			offsett2 = false;
			blockT = 4;

		}
		else if(x==5){//reverse L
			atr[0] = 2;
			atr[1] = 5;
			atr[2] = 8;
			atr[3] =7;
			offset = true;
			offsett2 = false;
			blockT = 5;

		}
		else if(x==6){
			atr[0] = 7;
			atr[1] = 8;
			atr[2] = 5;
			atr[3] = 6;
			offset = true;
			offsett2 = true;
			blockT = 6;

		}
		else{
			atr[0] = 4;
			atr[1] = 5;
			atr[2] = 8;
			atr[3] = 9;
			offset = true;
			offsett2 = true;
			blockT = 7;

		}
		/*
		 * 1 2 3
		 * 4 5 6
		 * 7 8 9
		 * 10 
		 */
//		System.out.print(atr[0]+" "+atr[1]+ " " +atr[2]+" "+atr[3]);
		return atr;
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
//		System.out.println(e.getKeyCode());
		if(e.getKeyCode()==32) {
			whil=10;
		}
		else
			whil=0;
		if(e.getKeyCode()==37){//left arrow
			if(xmod>-100)
				xmod-=20;
			if(blockT==1&&bpos==3)
				xmod-=20;
		}
		else if(e.getKeyCode()==38){//top arrow
			if(bpos==4)
				bpos=0;
			switch(blockT) {
			case 1:
				switch(bpos) {
				case 0:
					x1-=20;
					x3+=20;
					x4+=40;
					y1=y3;
					y2=y3;
					y4=y3;
					break;
				case 1:
					x1=xx1;
					x2=xx2;
					x3=xx3;
					x4=xx4;
					y1=yy1;
					y2=yy2;
					y3=yy3;
					y4=yy4;
					break;
				case 2:
					x1-=20;
					x3+=20;
					x4+=40;
					y1=y3;
					y2=y3;
					y4=y3;
					break;
				case 3:
					x1=xx1;
					x2=xx2;
					x3=xx3;
					x4=xx4;
					y1=yy1;
					y2=yy2;
					y3=yy3;
					y4=yy4;
					break;
				}
				break;
			case 2:
				switch(bpos) {
				case 0:
					x2-=20;
					x3-=20;
					x4-=20;
					y1=y2+40;
					offsett2=false;
					offset=false;
					break;
				case 1:
					x1=xx1;
					x2=xx2;
					x3=xx3;
					x4=xx4;
					y1=yy1;
					y2+=40;
					offsett2=true;
					offset=true;
					break;
				case 2:
					y2=yy2;
					x4-=20;
					y4+=20;
					offsett2=false;
					offset=false;
					break;
				case 3:
					x1=xx1;
					x2=xx2;
					x3=xx3;
					y4=yy4;
					x4=xx4;
					offsett2=true;
					offset=true;
					break;
					
				}
				break;
			case 4:
				switch(bpos) {
				case 0:
					x1=xx1+40;
					x2=xx2+20;
					x4=xx4-20;
					y1=yy1+20;
					y3=yy3-20;
					break;
				case 1:
					y4=yy4-40;
					x3=xx3+20;
					y3=yy3-40;
					x1=xx1+20;
					y1=yy1+40;
					break;
				case 2:
					x1=x1-20;
					y2=y2+20;
					y3=y3+40;
					x3=x3+20;
					x4=x4+40;
					y4=y4+20;
					break;
				case 3:
					x1=xx1;
					x2=xx2;
					x3=xx3;
					x4=xx4;
					y1=yy1;
					y2=yy2;
					y3=yy3;
					y4=yy4;
					break;
					
				}
				break;
			case 5:
				switch(bpos) {
				case 0:
					x1=xx1+20;
					x3=xx3-20;
					y1=yy1+40;
					y2=yy2+20;
					y3=yy3;
					y4=yy4-20;
					break;
				case 1:
					x1=xx1-20;
					x2=xx2-20;
					x3=xx3-20;
					x4=xx4+20;
					y1=yy1;
					y2=yy2;
					y3=yy3;
					y4=yy4-40;
					break;
				case 2:
					x4=xx4+40;
					x1=xx1-20;
					x2=xx2;
					y2=yy2;
					y1=yy1+20;
					x3=xx3+20;
					y3=yy3-20;
					y4=yy4;
					break;
				case 3:
					x1=xx1;
					x2=xx2;
					x3=xx3;
					x4=xx4;
					y1=yy1;
					y2=yy2;
					y3=yy3;
					y4=yy4;
					break;
					
				}
				break;
			case 6:
				switch(bpos) {
				case 0:
					y1=yy1-40;
					x2=xx2-20;
					y2=yy2-20;
					x4=xx4-20;
					y4=yy4+20;
					offsett2=false; 
					break;
				case 1:
					x1=xx1;
					x2=xx2;
					x3=xx3;
					x4=xx4;
					y1=yy1;
					y2=yy2;
					y3=yy3;
					y4=yy4;
					offsett2=true; 
					break;
				case 2:
					y1=yy1-40;
					x2=xx2-20;
					y2=yy2-20;
					x4=xx4-20;
					y4=yy4+20;
					offsett2=false; 
					break;
				case 3:
					x1=xx1;
					x2=xx2;
					x3=xx3;
					x4=xx4;
					y1=yy1;
					y2=yy2;
					y3=yy3;
					y4=yy4;
					offsett2=true; 
					break;
				}
				break;
			case 7:
				switch(bpos) {
				case 0:
					y1=yy1-20;
					x1=xx1+20;
					y3=yy3-20;
					x3=xx3-20;
					x4=xx4-40;
					offsett2=false; 
					break;
				case 1:
					x1=xx1;
					x2=xx2;
					x3=xx3;
					x4=xx4;
					y1=yy1;
					y2=yy2;
					y3=yy3;
					y4=yy4;
					offsett2=true; 
					break;
				case 2:
					y1=yy1-20;
					x1=xx1+20;
					y3=yy3-20;
					x3=xx3-20;
					x4=xx4-40;
					offsett2=false; 
					break;
				case 3:
					x1=xx1;
					x2=xx2;
					x3=xx3;
					x4=xx4;
					y1=yy1;
					y2=yy2;
					y3=yy3;
					y4=yy4;
					offsett2=true; 
					break;
				}
				break;
			}
			bpos++;
			System.out.println(bpos);
		}
		else if(e.getKeyCode()==39){//right arrow
			if(offsett2)
			{
				if(xmod<80)
					xmod+=20;
			}
			else if(offset) {
				if(xmod<100)
					xmod+=20;
			}
			else {
				if(xmod<120)
					xmod+=20;
			}
			
			if(blockT==1&&bpos==1)
				xmod+=20;
		}
		else if(e.getKeyCode()==40) {//bottom arrow
			
		}


	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub 
//		System.out.println(e.getKeyCode());
	}
	public int checkArray(int xa,int xb,int xc,int xd, int ya, int yb, int yc, int yd) {
		int cas = 0;
		int a = 0;
		int g=0;
//		int loopC = 0;
		xa = (xa-320)/20;
		xb = (xb-320)/20;
		xc = (xc-320)/20;
		xd = (xd-320)/20;
		int y = ya;
//	
		
		for(int d = 0; d<40;d++) {
			if(filled[xa][d]) {
				a=20*d+20;
				g=d;
			}
			else if(filled[xb][d]) {
				a=20*d+20;
				g=d;
			}
			else if(filled[xc][d]) {
				a=20*d+20;
				g=d;
			}
			else if(filled[xd][d]) {
				a=20*d+20;
				g=d;
			}
		}
		
		if(blockT==4) {
			if(bpos==1) {
				if(!filled[xa][g+1]&&!filled[xd][g]) {
					a-=20;
				}
			}
			if(bpos==2) {
				if(g>0) {
					if(!filled[xa][g-1]&&!filled[xd][g+1]) {
						a-=40;
					}
				}
				else {
					if(!filled[xd][g+1]) {
						a-=20;
					}
				}
			}
		}
		if(blockT==5) {
			if(bpos==1)
				a-=20;
			if(bpos==2) {
				if(!filled[xa][g]&&!filled[xd][g+1]) {
					a-=40;
				}
			}
			if(bpos==3) {
				if((g+1)>0) {
					if(!filled[xd][g]&&!filled[xc][g]) {
						a-=40;
					}
					else if(!filled[xd][g]&&!filled[xa][g+2]) {
						a-=20;
					}
				}
				else {
					if(!filled[xa][g+1]) {
						a-=20;
					}
				}
			}
		}
		if(blockT==6) {
			if(bpos==1||bpos==3) {
				System.out.println(g);
				if(g>0) {
					if(!filled[xd][g]&&!filled[xb][g+1]) {
						a-=20;
					}
				}
			}
			
		}
		if(blockT==7) {
			if(bpos==1||bpos==3) {
				System.out.println(g);
				if(g>0) {
					if(!filled[xd][g]&&!filled[xb][g+1]) {
						a-=20;
					}
				}
			}
			
		}
		if(blockT==6&&a!=0) {
			if(!filled[xa][a/20-1]&&!filled[xb][a/20-1]&&!filled[xc][a/20]) {
				a-=20;
			}
		}
		if(blockT==7&&a!=0) {
			if(!filled[xa][a/20]&&!filled[xb][a/20-1]&&!filled[xc][a/20-1]) {
				a-=20;
			}
		}
		
		

		return a;
//	
		
	} 
	public void fillArray(int xa,int xb,int xc,int xd, int ya, int yb, int yc, int yd) {
		xa = (xa-320)/20;
		xb = (xb-320)/20;
		xc = (xc-320)/20;
		xd = (xd-320)/20;
		ya = (700-ya)/20+3;
		yb = (700-yb)/20+3;
		yc = (700-yc)/20+3;
		yd = (700-yd)/20+3;
//		System.out.println(ya+" "+yb+" "+yc+" "+yd );
		filled[xa][ya]=true;
		filled[xb][yb]=true;
		filled[xc][yc]=true;
		filled[xd][yd]=true;

		
//		System.out.println(ya+" "+yb+" "+yc+" "+yd);
	}
}