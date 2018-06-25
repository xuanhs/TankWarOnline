package ex3;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Tank {
	
	boolean isnew=true;
	public Tank(int x, int y, TankClient tf, boolean good, boolean live,  Dir dir) {
		this.x = x;
		this.y = y;
		this.tf = tf;
		this.good = good;
		this.live = live;
		this.dir = dir;
		
	}
	public Tank(int x, int y, TankClient tf, boolean good, boolean live,  Dir dir, boolean isNPC) {
		this(x,y,tf,good,live,dir);
		this.isNPC = isNPC;
	}
	int id;
	int x,y;
	int level = 0;
	TankClient tf;
	NetClient nc;
	public boolean isNPC = true;// «∑ÒNPC
	Random rr =  new Random();
	int step =rr.nextInt(12)+3;
	public static int w = 35;
	public static int h = 35;
	public int xs=8;
	public int ys=8;
	public boolean good;
	public boolean isGood() {
		return good;
	}
	public void setGood(boolean good) {
		this.good = good;
	}
	public boolean live = true;
	public boolean isLive() {
		return live;
	}
	public void setLive(boolean live) {
		this.live = live;
	}
	public int life =100;
	public int getLife() {
		return life;
	}
	public void setLife(int life) {
		this.life = life;
	}
	public Dir dir =Dir.STOP;
	public Dir ptDir = Dir.D;
	int oldx;
	int oldy;
	public boolean l,u,r,d;
	
	private static Toolkit tool = Toolkit.getDefaultToolkit();
	private static Image[] images = null;
	private static Map<String,Image> imgs = new HashMap<String,Image>();
	static {
		images = new Image[] {
			tool.getImage(Tank.class.getClassLoader().getResource("images/TankL.gif")),
			tool.getImage(Tank.class.getClassLoader().getResource("images/tankLU.gif")),
			tool.getImage(Tank.class.getClassLoader().getResource("images/tankU.gif")),
			tool.getImage(Tank.class.getClassLoader().getResource("images/tankRU.gif")),
			tool.getImage(Tank.class.getClassLoader().getResource("images/tankR.gif")),
			tool.getImage(Tank.class.getClassLoader().getResource("images/tankRD.gif")),
			tool.getImage(Tank.class.getClassLoader().getResource("images/tankD.gif")),
			tool.getImage(Tank.class.getClassLoader().getResource("images/tankLD.gif")),
		};
		imgs.put("L",images[0]);
		imgs.put("LU", images[1]);
		imgs.put("U", images[2]);
		imgs.put("RU", images[3]);
		imgs.put("R", images[4]);
		imgs.put("RD", images[5]);
		imgs.put("D", images[6]);
		imgs.put("LD", images[7]);
	}
	
	public void draw(Graphics g) {
		if(!live) {
			if(!good&&id>300) {
				tf.tanks.remove(this);
				return;
			}
			return;
		}
		
		Color c =g.getColor();
		if(good) {
		g.setColor(Color.red);
		g.drawRect(x, y-10, w, 6);
		int w1 = w*life/100;
		g.fillRect(x, y-10, w1, 6);}
		else {
			g.setColor(Color.green);
			g.drawRect(x, y-10, w, 6);
			int w1 = w*life/100;
			g.fillRect(x, y-10, w1, 6);
		}
//		if(good)
//			g.setColor(Color.red);
//		else 
//			g.setColor(Color.blue);
//		g.fillOval(x, y, w, h);
	//	g.drawString("ID:"+id, x, y);
		g.setColor(c);
		move();
		switch(ptDir) {
		case L:
			g.drawImage(imgs.get("L"), x-12, y-10, null);
			break;
			case LU:
			g.drawImage(imgs.get("LU"), x-15, y-15, null);
			break;
			case R:
			g.drawImage(imgs.get("R"), x-6, y-10, null);
			break;
			case RD:
			g.drawImage(imgs.get("RD"), x-15, y-15, null);
			break;
			case D:
			g.drawImage(imgs.get("D"), x-6, y-6, null);
			break;
			case LD:
			g.drawImage(imgs.get("LD"), x-15, y-15, null);
			break;
			case U:
			g.drawImage(imgs.get("U"), x-6, y-6, null);
			break;
			case RU:
			g.drawImage(imgs.get("RU"), x-13, y-13, null);
			break;
			}
		
	}
	private void move() {
		oldx=x;
		oldy=y;
	
		
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
		case STOP:
			break;
		}
		if(dir !=Dir.STOP)
			this.ptDir =this.dir; 
		if(x<5) x=5;
		if(y<50) y=50;
		if(x>763) x=763;
		if(y>695) y=695;
		
		if(id>=1000) {
			Dir [] dirs = Dir.values();
			if(step==0) {
				step=rr.nextInt(12)+3;
				int rn =  rr.nextInt(dirs.length);
				dir = dirs[rn];
			}
			step--;
			if(rr.nextInt(30)>28) this.fire();
			TankMoveMsg msg = new TankMoveMsg(id,x,y,dir,ptDir);
			tf.nc.send(msg);
		}
	}
	public void locate() {
		Dir oldDir = this.dir;
		
		if(l&&!u&&!r&&!d) dir = Dir.L;
		else if(l&&u&&!r&&!d) dir = Dir.LU;
		else if(!l&&u&&!r&&!d) dir = Dir.U;
		else if(!l&&u&&r&&!d) dir = Dir.RU;
		else if(!l&&!u&&r&&!d) dir = Dir.R;
		else if(!l&&!u&&r&&d) dir = Dir.RD;
		else if(!l&&!u&&!r&&d) dir = Dir.D;
		else if(l&&!u&&!r&&d) dir = Dir.LD;
		else if(!l&&!u&&!r&&!d) dir = Dir.STOP;
		
		if(dir != oldDir) {
			TankMoveMsg msg = new TankMoveMsg(id,x,y,dir,ptDir);
			tf.nc.send(msg);
		}
	}
	public Missile fire() {
		if(!live) return null;
		int x = this.x+Tank.w/2-Missile.w/2;
		int y = this.y+Tank.h/2-Missile.h/2;
		Missile m =new Missile(id,x,y,this.tf,true,this.good,ptDir,level);
		tf.missiles.add(m);
		MissileNewMsg msg = new MissileNewMsg(m);
		tf.nc.send(msg);
		return m;
	}
	public boolean collidesWater(java.util.List<Water> waters) {
		for(int i=0;i<waters.size();i++) {
			Water w = waters.get(i);
			if(this.getRect().intersects(w.getRect())) {
				this.stay();
				return true;
			}
		}
		return false;
	}
	public boolean collidedWall(java.util.List<Wall> walls) {
		for(int i=0;i<walls.size();i++) {
			Wall w = walls.get(i);
			if(this.getRect().intersects(w.getRect())&&this.live&&w.live) {
				this.stay();
				return true;
			}
		}
		return false;
	}
	public boolean collidedTank(Tank t) {
		if(this!=t) {
			if(this.getRect().intersects(t.getRect())&&t.isLive()&&this.isLive()){
				this.stay();
				t.stay();
				return true;
			}
		}
		return false;
	}
	public boolean collidedTank(List<Tank> tanks) {
		for(int i=0;i<tanks.size();i++) {
			Tank t = tanks.get(i);
			if(this!=t) {
				if(collidedTank(t)) {
					return true;
				}
			}
		}
		return false;
	}
	private void stay() {
		x=oldx;
		y=oldy;
	}
	public Rectangle getRect() {
		return new Rectangle(x,y,w,h);
	}

}
