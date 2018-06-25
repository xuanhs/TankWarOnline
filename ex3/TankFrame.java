package ex3;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


public class TankFrame extends Frame{
	MenuBar mb=new MenuBar();//菜单栏
	Menu m=new Menu("游戏");//菜单
	MenuItem mi1=new MenuItem("新的游戏");//菜单项目
	MenuItem mi2=new MenuItem("暂停游戏");
	MenuItem mi3=new MenuItem("继续游戏");
	Random rr=new Random();
	Image off=null;//图片类的引用
	Tank1 mt=new Tank1(this,100, 100, 14, 10, 1, true, true, Tank1.Dir.D);
	ArrayList<Tank1> tanks=new ArrayList<Tank1>();
	ArrayList<Fire> fi=new ArrayList<Fire>();//就是子弹类
	ArrayList<Boom>boom=new ArrayList<Boom>();
	ArrayList<Wall1>walls=new ArrayList<Wall1>();
	ArrayList<Grass1>grass=new ArrayList<Grass1>();
	ArrayList<Able> ables=new ArrayList<Able>(); 
	boolean bom=true;
	
	Music mu=new Music();
	public int guan;//取值1,2,3，总共三关。第一关没墙没血块，第二关有墙有血块，第三关有墙有血块有草
	
	
public TankFrame(){//大管家
		
		//常规操作
		this.setBounds(300, 100, 800, 600);
		this.setBackground(Color.black);
		this.setVisible(true);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		//大管家里面启动线程
		TankThread th=new TankThread(this, true);	
		th.start();
		
		//加入菜单
		m.add(mi1);
		m.add(mi2);
		m.add(mi3);
		mb.add(m);
		this.setMenuBar(mb);
		
		//新的游戏
		mi1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mt.tf.dispose();//间接使这个窗口消失，然后下面重new
				new TankFrame(1);
			}
		});
		//暂停游戏
		mi2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				th.s=false;
				//s是TankThread的一个布尔值，在TankThread的run方法中为true时重画，
				//false时则不会调用repaint
			}
		});
		//继续游戏
		mi3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				th.s=true;//见mi2
			}
		});
		
		
		//坦克的常规操作
		this.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				switch(e.getKeyCode()){
				case KeyEvent.VK_W:
					mt.u=true;
					mu.ranmove();
					break;
				case KeyEvent.VK_S:
					mt.d=true;
					mu.ranmove();
					break;
				case KeyEvent.VK_D:
					mt.r=true;
					mu.ranmove();
					break;
				case KeyEvent.VK_A:
					mt.l=true;
					mu.ranmove();
					break;
				case KeyEvent.VK_0://自灭
					mt.live=false;
					break;
				case KeyEvent.VK_B:
					mt.ffire();
					mu.ranfire();
					break;
				}
			}
			public void keyReleased(KeyEvent e) {
				switch(e.getKeyCode()){
				case KeyEvent.VK_W:
					mt.u=false;
					break;
				case KeyEvent.VK_S:
					mt.d=false;
					break;
				case KeyEvent.VK_D:
					mt.r=false;
					break;
				case KeyEvent.VK_A:
					mt.l=false;
					break;
				case KeyEvent.VK_SPACE:
					if(mt.live) {
						mu.ranfire();
						mt.fire();
					}
					break;
				}
			}
		});
	}
	

	public TankFrame(int guan){//大管家
		this();
		this.guan=guan;
		//最开始时由LoginFrame调用，传入的值为1
		//当在EndFrame点击下一关之后，传入的值为guan+1即为2
		//当第二关通过时，再点击下一关guan+1即3
		
		switch(guan){
		case 1:
			//设置敌方坦克初始位置
			int x1=30,y1=500;
			for(int i=0;i<10;i++){
				if(i%2!=0) tanks.add(new Tank1(this,x1+i*50, y1, 8, 12, 1, true, false, Tank1.Dir.U));
				else tanks.add(new Tank1(this,x1+i*50, y1-200, 8, 12, 1, true, false, Tank1.Dir.U));
			}
			break;
			
			
		case 2:
			guan=2;
			//设置敌方坦克初始位置
			int x2=200,x22=500,y2=500,y22=550;
			for(int i=0;i<10;i++){
				if(i<5){
					tanks.add(new Tank1(this,x2+i*50, y2, 8, 12, 1, true, false, Tank1.Dir.U));
				}
				else{
					tanks.add(new Tank1(this,x22, y22-i*50, 8, 12, 1, true, false, Tank1.Dir.L));
				}
			}
			
			//设置墙的初始位置，一面墙由多个方块组成
			x2=350;
			y2=120;
			//一面墙是由多个方块组成的，达到一定等级子弹能穿墙时，每一发子弹就能击穿墙的一个方块
			for(int i=0;i<8;i++)//竖墙
				walls.add(new Wall1(x2, y2+i*30, 4, true));//
			x2=60;
			y2=350;
			for(int i=0;i<16;i++)//横墙
				walls.add(new Wall1(x2+i*15, y2,  2, true));
			break;
			
			
		case 3:
			guan=3;
			//设置敌方坦克初始位置
			int x3=30,y3=500,x32=500,y32=100,x33=600,y33=500,xx4=700,yy4=120;
			for(int i=0;i<10;i++){
				if(i<3){
					tanks.add(new Tank1(this,x3+i*50, y3, 8, 12, 1, true, false, Tank1.Dir.U));
				}
				else if(i>=3&&i<6){
					tanks.add(new Tank1(this,x32-i*50, y32, 8, 12, 1, true, false, Tank1.Dir.D));
				}
				else if(i>=6&&i<8){
					tanks.add(new Tank1(this,x33, y33-i*10, 8, 12, 1, true, false, Tank1.Dir.L));
				}
				else {
					tanks.add(new Tank1(this,xx4-i*10, yy4, 8, 12, 1, true, false, Tank1.Dir.L));
				}
			}
			
			//设置墙的初始位置，一面墙由多个方块组成
			x3=350;
			y3=120;
			//一面墙是由多个方块组成的，达到一定等级子弹能穿墙时，每一发子弹就能击穿墙的一个方块
			for(int i=0;i<8;i++)//竖墙
				walls.add(new Wall1(x3, y3+i*30, 4, true));//
			x3=60;
			y3=350;
			for(int i=0;i<16;i++)//横墙
				walls.add(new Wall1(x3+i*15, y3, 2, true));
			
			//设置草的初始位置
			x3=600;
			y3=100;
			for(int i=0;i<4;i++)//竖草
				grass.add(new Grass1(x3, y3+i*60));//
			x3=250;
			y3=480;
			for(int i=0;i<6;i++)//横草
				grass.add(new Grass1(x3+i*60, y3));
			break;
		}
	}
	
	
	
	public void update(Graphics g){//解决闪烁问题的双缓冲
		if(off==null){
			off=this.createImage(800, 600);
		}
		Color c=g.getColor();
		Graphics offg=off.getGraphics();
		offg.setColor(Color.black);
		offg.fillRect(0, 0, 800, 600);
		g.setColor(c);
		paint(offg);
		g.drawImage(off, 0, 0, null);
	}
	
	
	
	public void paint(Graphics g){
		
		//左上角计分
		g.setColor(Color.white);//设置字体颜色
		g.drawString("tank: "+tanks.size(), 20, 90);
		if(guan>1) {
			g.drawString("able: "+ables.size(), 20, 110);
			g.drawString("level: "+mt.level, 20, 70);
		}
		
		
		
		if(mt.live&&tanks.size()==0&&bom){
			bom=false;
			try {
				new EndFrame(mt.tf,1);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//画己方坦克
		if(mt.live){
			mt.draw(g);
		}
		else{
			if(bom){
				mu.ranboom();
				bom=false;
				try {
					new EndFrame(mt.tf,0);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		

		//画敌方坦克，活就画，死就new一个able
		for(int i=0;i<tanks.size();i++){
			Tank1 t=tanks.get(i);
			if(t.live){
				t.draw(g);
			}
			else
			{
				mu.ranboom();
				tanks.remove(i);
				int k=rr.nextInt(3)+1;//敌方死后随机产生一种血块
				ables.add(new Able(t.x, t.y, k, this, true));
			}
		}
		
		//画子弹，活就画还有打坦克，死就remove
		for(int i=0;i<fi.size();i++){
			Fire f=fi.get(i);
			if(f.live){
				f.draw(g);
				f.bett();
			}
			else{
				fi.remove(i);
				mu.ranhit();
			}
		}
		
		//画爆炸，活就画，死就remove
		for(int i=0;i<boom.size();i++){
			Boom bom=boom.get(i);
			if(bom.live){
				bom.draw(g);
			}
			else boom.remove(i);
		}
		
		switch(guan){
		case 1:
			break;
		case 2:
			//画墙，活就画，死就remove
			for(int i=0;i<walls.size();i++){
				Wall1 wa=walls.get(i);
				if(wa.live){
					wa.draw(g);
				}
				else walls.remove(i);
			}
			
			//画able，活就画，死就remove
			for(int i=0;i<ables.size();i++){
				Able ab=ables.get(i);
				if(ab.live){		
					ab.draw(g);
					ab.bett();
				}
				else ables.remove(i);
			}
			break;
		case 3:
			//画墙，活就画，死就remove
			for(int i=0;i<walls.size();i++){
				Wall1 wa=walls.get(i);
				if(wa.live){
					wa.draw(g);
				}
				else walls.remove(i);
			}
			
			//画able，活就画，死就remove
			for(int i=0;i<ables.size();i++){
				Able ab=ables.get(i);
				if(ab.live){		
					ab.draw(g);
					ab.bett();
				}
				else ables.remove(i);
			}
			
			//画草
			for(int i=0;i<grass.size();i++){
				Grass1 gr=grass.get(i);
				gr.draw(g);
			}
			break;
		}
		
	}
	
}
