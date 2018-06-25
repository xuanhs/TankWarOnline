package ex3;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.Map;

public class Grass1 {
	
	private static Toolkit tool = Toolkit.getDefaultToolkit();
	private static  Image[] images;
	private static Map<String,Image> imgs=new HashMap<String,Image>();
	static{    //静态代码区,一个类中先执行static的
		images = new Image[] {
				tool.getImage(Grass1.class.getClassLoader().getResource("images/grass.gif"))
			};
			imgs.put("grass", images[0]);
	}
	
	int x,y;
	
	public Grass1(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void draw(Graphics g){
		g.drawImage(imgs.get("grass"), x, y, null);
	}
}
