package ex3;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Missile {

	public Missile(int tankID ,int x, int y, TankClient tf, boolean live, boolean good, Dir dir,int level) {
		this.tankID = tankID;
		this.x = x;
		this.y = y;
		this.tf = tf;
		this.live = live;
		this.good = good;
		this.dir = dir;
		this.id = ID++;
		this.level = level;
	}
	int x,y;
	int tankID;
	int id;
	public int level;
	public static int ID = 1;
	TankClient tf;
	public boolean live = true;
	public boolean isLive() {
		return live;
	}
	public void setLive(boolean live) {
		this.live = live;
	}
	public boolean good;
	public int xs=10;
	int ys=10; 
	public static int w = 10;
	public static int h = 10;
	Dir dir;
	
	private static Toolkit tool = Toolkit.getDefaultToolkit();
	private static Image[] images = null;
	private static Map<String,Image> imgs = new HashMap<String,Image>();
	static {
		images = new Image[] {
				tool.getImage(Missile.class.getClassLoader().getResource("images/missileL.gif")),
				tool.getImage(Missile.class.getClassLoader().getResource("images/missileLU.gif")),
				tool.getImage(Missile.class.getClassLoader().getResource("images/missileU.gif")),
				tool.getImage(Missile.class.getClassLoader().getResource("images/missileRU.gif")),
				tool.getImage(Missile.class.getClassLoader().getResource("images/missileR.gif")),
				tool.getImage(Missile.class.getClassLoader().getResource("images/missileRD.gif")),
				tool.getImage(Missile.class.getClassLoader().getResource("images/missileD.gif")),
				tool.getImage(Missile.class.getClassLoader().getResource("images/missileLD.gif")),
		};
		imgs.put("L", images[0]);
		imgs.put("LU", images[1]);
		imgs.put("U", images[2]);
		imgs.put("RU", images[3]);
		imgs.put("R", images[4]);
		imgs.put("RD", images[5]);
		imgs.put("D", images[6]);
		imgs.put("LD", images[7]);
	}
	
	public void draw(Graphics g) {
		if(!this.live) {
			tf.missiles.remove(this);
			return; 
		}
		Color c= g.getColor();
		if(!good)
		g.setColor(Color.black);
		else
			g.setColor(Color.red);
		g.fillOval(x, y, w, h);
		switch(dir) {
		case L:
			g.drawImage(imgs.get("L"), x, y, null);
			break;
			case LU:
			g.drawImage(imgs.get("LU"), x, y, null);
			break;
			case R:
			g.drawImage(imgs.get("R"), x, y, null);
			break;
			case RD:
			g.drawImage(imgs.get("RD"), x, y, null);
			break;
			case D:
			g.drawImage(imgs.get("D"), x, y, null);
			break;
			case LD:
			g.drawImage(imgs.get("LD"), x, y, null);
			break;
			case U:
			g.drawImage(imgs.get("U"), x, y, null);
			break;
			case RU:
			g.drawImage(imgs.get("RU"), x, y, null);
			break;
			}
		g.setColor(c);
		move();
	}
	private void move() {
		switch(dir) {
		case L:
			x-=xs;
			break;
		case LU:
			x-=xs;
			y-=ys;
			break;
		case U:
			y-=ys;
			break;
		case RU:
			x+=xs;
			y-=ys;
			break;
		case R:
			x+=xs;
			break;
		case RD:
			x+=xs;
			y+=ys;
			break;
		case D:
			y+=ys;
			break;
		case LD:
			x-=xs;
			y+=ys;
			break;	
		}
	}
	public Rectangle getRect() {
		return new Rectangle(x,y,w,h);
	}
	public boolean hitHome(Home home) {
		if(this.getRect().intersects(home.getRect())&&home.live&&this.live) {
			if(this.good!=home.good) {
				home.setLife(home.getLife()-10);
				if(home.getLife()<=0) {
					home.live=false;
					Explode e =new Explode(x,y,tf);
					tf.explodes.add(e);
					return true;
				}
				this.live=false;
			}
		}
		return false;
	}
	public boolean hitTank(Tank t) {
		if(this.getRect().intersects(t.getRect())&&this.good!=t.good&&t.isLive()) {
			if(t.good) {
				t.setLife(t.getLife()-20);
				if(t.getLife()<=0) {
					t.setLive(false);
				}
			}
			else {
				t.setLife(t.getLife()-20);
				if(t.getLife()<=0) {
					t.setLive(false);
				}
			}
			this.live=false;
			if(t.live==false){
				Explode e =new Explode(x,y,this.tf);
				Music mu=new Music();
				mu.ranboom();
				tf.explodes.add(e);	
				
			}
			return true;
		}
		return false;
	}
	public boolean hitWall(List<Wall> walls) {
		for(int i=0;i<walls.size();i++) {
			Wall w = walls.get(i);
			if(this.getRect().intersects(w.getRect())&&this.live&&w.live&&w.live) {
				if(this.level>w.level) {
					w.live = false;
					this.live = false;
				}
				else if(this.level<=w.level) {
					this.live = false;
				}
				return true;
			}
		}
		return false;
		
	}
	public boolean hitTank(List<Tank> tanks) {
		for(int i=0;i<tanks.size();i++) {
			Tank t= tanks.get(i);
			if(hitTank(t)) {
				return true;
			}
		}
		return false;
	}
}
