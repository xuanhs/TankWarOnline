package ex3;

import java.awt.*;
import java.util.*;

public class Grass {
	public Grass(int x, int y) {
		this.x = x;
		this.y = y;
	}
	int x,y;
	public static int w=10;
	public static int h=10;
	private static Toolkit tool = Toolkit.getDefaultToolkit();
	private static Image[] images = null;
	private static Map<String,Image> imgs = new HashMap<String,Image>();
	static {
		images = new Image[] {
				tool.getImage(Grass.class.getClassLoader().getResource("images/grass.png")),
		};
		imgs.put("0",images[0]);
	}
	public void draw(Graphics g) {
		Color c = g.getColor();
		g.drawImage(imgs.get("0"), x, y, null);
	}

}
