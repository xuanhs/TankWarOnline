package ex3;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class Fire {
	
	private static Toolkit tool = Toolkit.getDefaultToolkit();
	private static  Image[] images;
	private static Map<String,Image> imgs=new HashMap<String,Image>();
	static{    //静态代码区,一个类中先执行static的
		images = new Image[] {
				tool.getImage(Fire.class.getClassLoader().getResource("images/missileL.gif")),
				tool.getImage(Fire.class.getClassLoader().getResource("images/missileLU.gif")),
				tool.getImage(Fire.class.getClassLoader().getResource("images/missileU.gif")),
				tool.getImage(Fire.class.getClassLoader().getResource("images/missileRU.gif")),
				tool.getImage(Fire.class.getClassLoader().getResource("images/missileR.gif")),
				tool.getImage(Fire.class.getClassLoader().getResource("images/missileRD.gif")),
				tool.getImage(Fire.class.getClassLoader().getResource("images/missileD.gif")),
				tool.getImage(Fire.class.getClassLoader().getResource("images/missileLD.gif")),
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
	
	
	
	TankFrame tc;
	boolean live;
	boolean good;
	int x,y,w=10,h=10;
	int sp,xp;
	int level;
	Color co;//坦克颜色给子弹
	Tank1.Dir dir;
	
	public Fire(Tank1.Dir dir,TankFrame tc, boolean live, boolean good, int x, int y, int sp,
			int xp, int level) {
		super();
		this.tc = tc;
		this.live = live;
		this.good = good;
		this.x = x;
		this.y = y;
		this.sp = sp;
		this.xp = xp;
		this.level = level;
		this.dir=dir;
	};
	
	public void draw(Graphics g){
		move();
		
		switch(dir){
		case L:
			g.drawImage(imgs.get("L"), x-w/2, y-h/2, null);
			break;
		case LU:
			g.drawImage(imgs.get("LU"), x-w/2, y-h/2, null);
			break;
		case R:
			g.drawImage(imgs.get("R"), x-w/2, y-h/2,null);
			break;
		case RD:
			g.drawImage(imgs.get("RD"), x-w/2, y-h/2, null);
			break;
		case D:
			g.drawImage(imgs.get("D"),  x-w/2, y-h/2, null);
			break;
		case LD:
			g.drawImage(imgs.get("LD"),  x-w/2, y-h/2, null);
			break;
		case U:
			g.drawImage(imgs.get("U"),  x-w/2, y-h/2, null);
			break;
		case RU:
			g.drawImage(imgs.get("RU"),  x-w/2, y-h/2, null);
			break;
		}
	}
	
	public void move(){
		switch(dir){
		case U:
			y-=sp;
			break;
		case D:
			y+=xp;
			break;
		case L:
			x-=xp;
			break;
		case R:
			x+=xp;
			break;
		case LU:
			x-=sp;
			y-=sp;
			break;
		case LD:
			x-=sp;
			y+=sp;
			break;
		case RU:
			x+=sp;
			y-=sp;
			break;
		case RD:
			x+=sp;
			y+=sp;
			break;
		}
	}

	public Rectangle getrect(){
		return new Rectangle(x, y, w, h);
	}
	
	public void bett(){//子弹打坦克，子弹打子弹，子弹打墙
		for(int i=0;i<tc.tanks.size();i++){
			bett(tc.tanks.get(i));
		}
		for(int i=0;i<tc.fi.size();i++){
			bett(tc.fi.get(i));
		}
		for(int i=0;i<tc.walls.size();i++){
			bett(tc.walls.get(i));
		}
		if(tc.mt.live) bett(tc.mt);
	}
	
	public void bett(Tank1 m){
		if(this.getrect().intersects(m.getrect())&&this.good!=m.good){
			this.live=false;
			m.blood--;
			if(m.blood<=0){
				m.live=false;
				tc.boom.add(new Boom(m.x, m.y, true, tc));
			}
		}
	}
	
	public void bett(Fire f){
	//若子弹等级相等，两颗子弹碰撞抵消，否则碰撞后等级低的子弹消失
		if(this.getrect().intersects(f.getrect())&&this.good!=f.good){
			if(this.level<=f.level){
				this.live=false;
			}
			if(this.level>=f.level){
				f.live=false;
			}
		}
	}
	
	public void bett(Wall1 wa){
		if(this.getrect().intersects(wa.getrect())){
			if(this.level>=wa.level){
				wa.live=false;
			}
			this.live=false;
		}
	}
}

