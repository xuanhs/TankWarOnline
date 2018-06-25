package ex3;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;

public class Blood {
	public Blood(int x, int y, TankClient tf, boolean live,int level) {
		this.x = x;
		this.y = y;
		this.tf = tf;
		this.live = live;
		this.level=level;
		this.id = ID--;
	}
	int x,y;
	int id;
	public static int ID = -1;
	public int level;
	public int w=15;
	public int h=15;
	TankClient tf;
	public boolean live =true;
	public boolean isLive() {
		return live;
	}
	public void setLive(boolean live) {
		this.live = live;
	}
	public void draw(Graphics g) {
		if(!live) {
			tf.bloods.remove(this);
			return;
		}
		Color c =g.getColor();
		if(level==1) {
			g.setColor(Color.cyan);
			g.fillRect(x-3, y-3, w+6, h+6);
			g.setColor(Color.magenta);
			g.fillRect(x, y, w, h);
		}
		else if(level==2) {
			g.setColor(Color.cyan);
			g.fillRect(x-3, y-3, w+6, h+6);
			g.setColor(Color.black);
			g.fillRect(x, y, w, h);
		}
		else if(level==3) {
			g.setColor(Color.cyan);
			g.fillRect(x-3, y-3, w+6, h+6);
			g.setColor(Color.yellow);
			g.fillRect(x, y, w, h);
		}
		g.setColor(c);
	}
	public boolean eatBlood(Tank t) {
		if(this.getRect().intersects(t.getRect())&&this.live&&t.isLive()) {
			if(level==1) {
				if(t.good) {
					if(t.getLife()<100) {
						t.setLife(100);
						this.live=false;
					}
				}
			}
			else if(level==2) {
				if(tf.myTank.xs<12 &&tf.myTank.ys<12) {
					tf.myTank.xs+=1;
					tf.myTank.ys+=1;
					this.live=false;
				}
			}
			else if(level ==3) {
				if(t.good)
				tf.myTank.level++;
				else
				for(int i=0;i<tf.tanks.size();i++) {
					Tank t1 = tf.tanks.get(i);
					t1.level++;
				}
				this.live=false;
			}
			return true;
		}
		return false;
	}
	public boolean eatBlood(List<Tank> tanks) {
		for(int i=0;i<tf.tanks.size();i++) {
			Tank t2 = tf.tanks.get(i);
			if(eatBlood(t2)) {
				return true;
			}
		}
		return false;
	}
	public Rectangle getRect() {
		return new Rectangle(x,y,w,h);
	}

}
