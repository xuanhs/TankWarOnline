package ex3;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.Map;

public class Boom {
	
	
	private static Toolkit tool = Toolkit.getDefaultToolkit();
	private static  Image[] images;
	private static Map<String,Image> imgs=new HashMap<String,Image>();
	static{    //静态代码区,一个类中先执行static的
		images = new Image[] {
			tool.getImage(Boom.class.getClassLoader().getResource("images/0.gif")),
			tool.getImage(Boom.class.getClassLoader().getResource("images/1.gif")),
			tool.getImage(Boom.class.getClassLoader().getResource("images/2.gif")),
			tool.getImage(Boom.class.getClassLoader().getResource("images/3.gif")),
			tool.getImage(Boom.class.getClassLoader().getResource("images/4.gif")),
			tool.getImage(Boom.class.getClassLoader().getResource("images/5.gif")),
			tool.getImage(Boom.class.getClassLoader().getResource("images/6.gif")),
			tool.getImage(Boom.class.getClassLoader().getResource("images/7.gif")),
			tool.getImage(Boom.class.getClassLoader().getResource("images/8.gif")),
			tool.getImage(Boom.class.getClassLoader().getResource("images/9.gif")),
			tool.getImage(Boom.class.getClassLoader().getResource("images/10.gif")),
		};
		imgs.put("0", images[0]);
		imgs.put("1", images[1]);
		imgs.put("2", images[2]);
		imgs.put("3", images[3]);
		imgs.put("4", images[4]);
		imgs.put("5", images[5]);
		imgs.put("6", images[6]);
		imgs.put("7", images[7]);
		imgs.put("8", images[8]);
		imgs.put("9", images[9]);
		imgs.put("10", images[10]);
	}
	
	int x,y;
	TankFrame tc;
	boolean live;
	public Boom(int x,int y,boolean live,TankFrame tc){
		this.x=x;
		this.y=y;
		this.tc=tc;
		this.live=live;
	}
	
	public void draw(Graphics g){
		for(int i=0;i<=11;i++){
			switch(i){
			case 0:
				g.drawImage(imgs.get("0"), x, y, null);
				break;
			case 1:
				g.drawImage(imgs.get("1"), x, y, null);
				break;
			case 2:
				g.drawImage(imgs.get("2"), x, y, null);
				break;
			case 3:
				g.drawImage(imgs.get("3"), x, y, null);
				break;
			case 4:
				g.drawImage(imgs.get("4"), x, y, null);
				break;
			case 5:
				g.drawImage(imgs.get("5"), x, y, null);
				break;
			case 6:
				g.drawImage(imgs.get("6"), x, y, null);
				break;
			case 7:
				g.drawImage(imgs.get("7"), x, y, null);
				break;
			case 8:
				g.drawImage(imgs.get("8"), x, y, null);
				break;
			case 9:
				g.drawImage(imgs.get("9"), x, y, null);
				break;
			case 10:
				g.drawImage(imgs.get("10"), x, y, null);
				break;
			}
		}
		this.live=false;
	}
}

