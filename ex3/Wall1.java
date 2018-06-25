package ex3;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.Map;

public class Wall1 {
	
	private static Toolkit tool = Toolkit.getDefaultToolkit();
	private static  Image[] images;
	private static Map<String,Image> imgs=new HashMap<String,Image>();
	static{    //静态代码区,一个类中先执行static的
		images = new Image[] {
			tool.getImage(Wall1.class.getClassLoader().getResource("images/wall.gif")),
			tool.getImage(Wall1.class.getClassLoader().getResource("images/walls.gif"))
		};
		imgs.put("wall", images[0]);
		imgs.put("walls", images[1]);
	}
	
	int x,y,w,h;
	int level;//子弹达到一定等级墙可以被打穿，子弹等级由坦克等级决定
	boolean live;
	
	public Wall1(int x, int y, int level,boolean live) {
		super();
		this.x = x;
		this.y = y;
		this.level = level;
		this.live=live;
	}
	
	public void draw(Graphics g){
		if(level<4){
			g.drawImage(imgs.get("wall"), x, y, null);
			w=20;
			h=20;
		}
		else{ 
			g.drawImage(imgs.get("walls"), x, y, null);
			w=60;
			h=60;
		}
	}
	
	public Rectangle getrect(){
		return new Rectangle(x,y,w,h);
	}
}

