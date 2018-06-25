package ex3;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Wall {
	public Wall(int x, int y, int level, int w, int h, TankClient tf, boolean live,int type) {
		this.x = x;
		this.y = y;
		this.level =level;
		this.w = w;
		this.h = h;
		this.tf = tf;
		this.live = live;
		this.type =type;
	}
	int x,y;
	int id;
	int type;
	public int level;
	public int w;
	public int h;
	TankClient tf;
	public boolean live =true;
	public boolean isLive() {
		return live;
	}
	public void setLive(boolean live) {
		this.live = live;
	}
	
	private static Toolkit tool = Toolkit.getDefaultToolkit();
	private static Image[] images = null;
	private static Map<String,Image> imgs = new HashMap<String,Image>();
	static {
		images = new Image[] {
				tool.getImage(Wall.class.getClassLoader().getResource("images/wall.gif")),
				tool.getImage(Wall.class.getClassLoader().getResource("images/steel.gif")),
		};
		imgs.put("0", images[0]);
		imgs.put("1", images[1]);
	}
	
	public void draw(Graphics g) {
		if(!live) {
			tf.walls.remove(this);
			return;
		}
		Color c =g.getColor();
		g.setColor(Color.cyan);
		g.fillRect(x, y, w, h);
		if(type==1)
			g.drawImage(imgs.get("0"), x, y, null);
		else if(type==2)
			g.drawImage(imgs.get("1"), x, y, null);
		g.setColor(c);
//		WallNewMsg msg = new WallNewMsg(this);
//		tf.nc.send(msg);
	}
	public Rectangle getRect() {
		return new Rectangle(x,y,w,h);
	}
}
