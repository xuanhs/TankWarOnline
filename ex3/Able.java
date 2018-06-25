package ex3;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Able {
	int x,y,w=30,h=30;
	int level;//able的种类
	TankFrame tc;
	boolean live;
	public Able(int x, int y, int level, TankFrame tc, boolean live) {
		super();
		this.x = x;
		this.y = y;
		this.level = level;
		this.tc = tc;
		this.live = live;
	}
	
	public void draw(Graphics g){
		g.setColor(Color.cyan);
		g.fillRect(x, y, w, h);
		switch(level){
		case 1://bool
			g.setColor(Color.red);
			g.fillRect(x+5, y+5, w-10, h-10);
			break;
		case 2://level
			g.setColor(Color.black);
			g.fillRect(x+5, y+5, w-10, h-10);
			break;
		case 3:
			g.setColor(Color.white);
			g.fillRect(x+5, y+5, w-10, h-10);
			break;
		}
		
	}
	
	public void bett(){
		if(this.getrect().intersects(tc.mt.getrect())){
			if(this.level==1&&tc.mt.blood<3){//红色加血
				tc.mt.blood=3;
				this.live=false;
			}
			else if(this.level==2){//黑色加等级
				tc.mt.level++;
				this.live=false;
			}
			else if(this.level==3){//白色加速度
				tc.mt.sp+=1;
				tc.mt.xp+=2;
				this.live=false;
			}
		}
	}
	
	public Rectangle getrect(){
		return new Rectangle(x,y,w,h);
	}
}

