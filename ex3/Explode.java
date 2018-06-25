package ex3;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
public class Explode {
	
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
	public boolean live =true;
	TankClient tf;
	public Explode(int x, int y, TankClient tf) {
		super();
		this.x = x;
		this.y = y;
		this.tf = tf;
	}
	public boolean isLive() {
		return live;
	}
	public void setLive(boolean live) {
		this.live = live;
	}
//	int[] dameter = {4,8,16,2,39,52,28,8};
//	int step=0;
	public void draw(Graphics g) {
		if(!live) {
			tf.explodes.remove(this);
			return; 
		}
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
			this.live=false;
		}
		
//		step++;
	}//		if(step==dameter.length) {
//			this.live=false;
//			step=0;
//			return;
//		}
//		Color c= g.getColor();
//		g.setColor(Color.yellow);
//		g.fillOval(x, y, dameter[step], dameter[step]);
//		g.setColor(c);
//		

}
