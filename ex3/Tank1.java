package ex3;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Tank1 {
	
	private static Toolkit tool = Toolkit.getDefaultToolkit();
	private static  Image[] images;
	private static Map<String,Image> imgs=new HashMap<String,Image>();
	static{    //静态代码区,一个类中先执行static的
		images = new Image[] {
			tool.getImage(Tank1.class.getClassLoader().getResource("images/tankL.gif")),
			tool.getImage(Tank1.class.getClassLoader().getResource("images/tankLU.gif")),
			tool.getImage(Tank1.class.getClassLoader().getResource("images/tankU.gif")),
			tool.getImage(Tank1.class.getClassLoader().getResource("images/tankRU.gif")),
			tool.getImage(Tank1.class.getClassLoader().getResource("images/tankR.gif")),
			tool.getImage(Tank1.class.getClassLoader().getResource("images/tankRD.gif")),
			tool.getImage(Tank1.class.getClassLoader().getResource("images/tankD.gif")),
			tool.getImage(Tank1.class.getClassLoader().getResource("images/tankLD.gif")),
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
	
	TankFrame tf;
	int x,y,w=50,h=50;
	int sp,xp;//sp，斜方向的速度，xp横竖方向速度
	int level;//坦克等级，达到一定等级所发射的子弹可以打穿墙
	boolean live;
	boolean good;
	boolean u,d,l,r;
	enum Dir{U,D,L,R,LU,LD,RU,RD,STOP};
	Dir dir;
	Dir ptdir;
	int blood=3;
	Random rr=new Random();
	int step=0;
	
	public Tank1(TankFrame tf,int x, int y, int sp, int xp, int level, boolean live,
			boolean good, Dir ptdir) {
		super();
		this.x = x;
		this.y = y;
		this.sp = sp;
		this.xp = xp;
		this.level = level;
		this.live = live;
		this.good = good;
		this.ptdir = ptdir;
		this.tf=tf;
	}
	
	public void draw(Graphics g){
		move();
		Color c=g.getColor();
		
		//血条
		g.setColor(Color.gray);
		g.fillRect(x+5, y-10, w-10, 5);
		if(good){
			g.setColor(Color.red);
		}
		else g.setColor(Color.yellow);
		g.fillRect(x+5, y-10, (w-10)*blood/3, 5);
		g.setColor(c);
		//g.fillOval(x, y, 50, 50);
		//坦克
		switch(ptdir) {
		case L:
			g.drawImage(imgs.get("L"), x-5, y, null);
			break;
		case LU:
			g.drawImage(imgs.get("LU"), x-15, y-15, null);
			break;
		case R:
			g.drawImage(imgs.get("R"), x+5, y, null);
			break;
		case RD:
			g.drawImage(imgs.get("RD"), x-10, y-10, null);
			break;
		case D:
			g.drawImage(imgs.get("D"), x, y, null);
			break;
		case LD:
			g.drawImage(imgs.get("LD"), x-10, y-10, null);
			break;
		case U:
			g.drawImage(imgs.get("U"), x-6, y-6, null);
			break;
		case RU:
			g.drawImage(imgs.get("RU"), x-8, y-13, null);
			break;
		}
	}
	
	public void dir(){
		if(u&&!d&&!l&&!r){
			this.dir=Dir.U;
		}
		else if(u&&!d&&l&&!r){
			this.dir=Dir.LU;
		}
		else if(u&&!d&&!l&&r){
			this.dir=Dir.RU;
		}
		else if(!u&&!d&&l&&!r){
			this.dir=Dir.L;
		}
		else if(!u&&!d&&!l&&r){
			this.dir=Dir.R;
		}
		else if(!u&&d&&l&&!r){
			this.dir=Dir.LD;
		}
		else if(!u&&d&&!l&&r){
			this.dir=Dir.RD;
		}
		else if(!u&&d&&!l&&!r){
			this.dir=Dir.D;
		}
		else
			this.dir=Dir.STOP;
	}
	
	public void move(){
		int xx=x,yy=y;
		if(good)
		dir();
		else if(!good){
			Dir[] dirs=Dir.values();
			if(step==0){
				step=rr.nextInt(12)+12;
				int k=rr.nextInt(8);
				dir=dirs[k];
			}
			step--;
			if(rr.nextInt(30)>27)
				fire();
		}
		
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
		if(x<15||x>775) x=xx;
		if(y<50||y>575) y=yy;
		for(int i=0;i<tf.walls.size();i++){
			Wall1 wa=tf.walls.get(i);
			if(wa.getrect().intersects(this.getrect())){
				x=xx;
				y=yy;
			}
		}
		if(dir!=Dir.STOP) ptdir=dir;
	}
	
	public void fire(){
		tf.fi.add(new Fire(ptdir,tf, true, this.good, x+w/2, y+h/2, sp*2, xp*2, level));
	}
	
	public Rectangle getrect(){
		return new Rectangle(x, y, w, h);
	}

	public void ffire() {
		Dir[] dirs=Dir.values();
		for(int i=0;i<8;i++){
			tf.fi.add(new Fire(dirs[i],tf, true, this.good, x+w/2, y+h/2, sp*2, xp*2, level));
		}
	}
}

