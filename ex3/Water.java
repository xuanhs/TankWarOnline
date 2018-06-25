package ex3;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
public class Water {
	public Water(int x, int y, int w, int h) {
		super();
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	int x,y;
	public int w;
	public int h;
	private static Toolkit tool = Toolkit.getDefaultToolkit();
	private static Image[] images = null;
	private static Map<String,Image> imgs = new HashMap<String,Image>();
	static {
		images = new Image[] {
			tool.getImage(Water.class.getClassLoader().getResource("images/water4.jpg")),
		};
		imgs.put("0", images[0]);
	}
	public void draw(Graphics g) {
		Color c =g.getColor();
		g.setColor(Color.blue);
		g.fillRect(x, y, w, h);
		g.setColor(c);
		g.drawImage(imgs.get("0"), x, y, null);
	}
	public Rectangle getRect() {
		return new Rectangle(x,y,w,h);
	}
}
