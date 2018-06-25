package ex3;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
public class Home {
	public Home(int x, int y, boolean good,boolean live,TankClient tf) {
		this.x = x;
		this.y = y;
		this.good = good;
		this.live=live;
		this.tf=tf;
	}
	TankClient tf;
	int x,y;
	public int life = 100;
	
	public int getLife() {
		return life;
	}
	public void setLife(int life) {
		this.life = life;
	}
	public static int w = 50;
	public static int h = 50;
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
	
	private static Toolkit tool = Toolkit.getDefaultToolkit();
	private static Image[] images = null;
	private static Map<String,Image> imgs = new HashMap<String,Image>();
	static {
		images = new Image[] {
			tool.getImage(Home.class.getClassLoader().getResource("images/home2.gif")),
		};
		imgs.put("0", images[0]);
	}
	
	public void draw(Graphics g) {
		if(!live) {
			//g.clearRect(x, y, w, h);
			return;
		}
//		Color c= g.getColor();
//		g.setColor(Color.yellow);
//		g.fillOval(x, y, w, h);
//		g.setColor(c);
		g.drawImage(imgs.get("0"), x, y, null);
	}
	public Rectangle getRect() {
		return new Rectangle(x,y,w,h);
	}
}
