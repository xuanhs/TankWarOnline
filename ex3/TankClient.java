package ex3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.*;
import java.util.*;
import java.util.List;

import javax.swing.JOptionPane;
public class TankClient extends Frame implements ActionListener{
	Random r1 =  new Random();
	Music mu=new Music();
	MenuBar bar = null;
	Menu menu=null;
	MenuItem mi1 = null;
	MenuItem mi2 = null;
	MenuItem mi3 = null;
	MenuItem mi4 = null;
	MenuItem mi5 = null;
	boolean printable = true;
	Tank myTank = new Tank(370,280,this,true,true,Dir.STOP);
	List<Missile> missiles = new ArrayList<Missile>();
	List<Tank> tanks = new ArrayList<Tank>();
	List<Tank> comptanks = new ArrayList<Tank>();
	List<Explode> explodes = new ArrayList<Explode>();
	List<Wall> walls = new ArrayList<Wall>();
	List<Blood> bloods = new ArrayList<Blood>();
	List<Water> waters = new ArrayList<Water>();
	List<Grass> grasss = new ArrayList<Grass>();
	Home home = new Home(375,50,true,true,this);
	Home home1 = new Home(375,670,false,true,this);
	ContDialog dialog = new ContDialog();
	Random rr = new Random();
	public int w = 800;
	public int h = 730;
	public int G=0;
	NetClient nc = new NetClient(this);
	Image offScreenImage = null;
	public void update(Graphics g) {
		if(offScreenImage == null) {
			offScreenImage = this.createImage(w, h);
		}
		Graphics gOffScreen = offScreenImage.getGraphics();
		Color c = gOffScreen.getColor();
		gOffScreen.setColor(new Color(0xcce8cf));
		gOffScreen.fillRect(0, 0, w, h);
		gOffScreen.setColor(c);
		paint(gOffScreen);
		g.drawImage(offScreenImage, 0, 0, null);
	}
	public TankClient(String title) {
		super(title);
		newMenu();
		for(int i=0;i<1;i++) {
			grasss.add(new Grass(0,500));
			grasss.add(new Grass(60,500));
			
			grasss.add(new Grass(0,200));
			grasss.add(new Grass(0,260));
			grasss.add(new Grass(0,320));
			grasss.add(new Grass(0,380));
			grasss.add(new Grass(0,440));
			grasss.add(new Grass(60,200));
			grasss.add(new Grass(60,260));
			grasss.add(new Grass(60,320));
			grasss.add(new Grass(60,380));
			grasss.add(new Grass(60,440));
			
		}
		for(int i=0;i<12;i++) {
			grasss.add(new Grass(60+60*(i+1),500));
		}
		for(int i=0;i<1;i++) {
			waters.add(new Water(100,280,52,35));
			waters.add(new Water(152,280,52,35));
			waters.add(new Water(204,280,52,35));
			waters.add(new Water(256,280,52,35));
			waters.add(new Water(308,280,52,35));
			
			waters.add(new Water(460,280,52,35));
			waters.add(new Water(512,280,52,35));
			waters.add(new Water(564,280,52,35));
			waters.add(new Water(616,280,52,35));
			waters.add(new Water(668,280,52,35));
		}
		for(int i=0;i<1;i++) {
			walls.add(new Wall(330,35,0,15,15,this,true,1));
			walls.add(new Wall(330,50,0,15,15,this,true,1));
			walls.add(new Wall(330,65,0,15,15,this,true,1));
			walls.add(new Wall(330,80,0,15,15,this,true,1));
			walls.add(new Wall(330,95,0,15,15,this,true,1));
			walls.add(new Wall(330,110,0,15,15,this,true,1));
			
			walls.add(new Wall(345,110,0,15,15,this,true,1));
			walls.add(new Wall(360,110,0,15,15,this,true,1));
			walls.add(new Wall(375,110,0,15,15,this,true,1));
			walls.add(new Wall(390,110,0,15,15,this,true,1));
			walls.add(new Wall(405,110,0,15,15,this,true,1));
			walls.add(new Wall(420,110,0,15,15,this,true,1));
			walls.add(new Wall(435,110,0,15,15,this,true,1));
			
			walls.add(new Wall(450,35,0,15,15,this,true,1));
			walls.add(new Wall(450,50,0,15,15,this,true,1));
			walls.add(new Wall(450,65,0,15,15,this,true,1));
			walls.add(new Wall(450,80,0,15,15,this,true,1));
			walls.add(new Wall(450,95,0,15,15,this,true,1));
			walls.add(new Wall(450,110,0,15,15,this,true,1));
		}
		
		for(int i=0;i<1;i++) {
			walls.add(new Wall(330,710,0,15,15,this,true,1));
			walls.add(new Wall(330,695,0,15,15,this,true,1));
			walls.add(new Wall(330,680,0,15,15,this,true,1));
			walls.add(new Wall(330,665,0,15,15,this,true,1));
			walls.add(new Wall(330,650,0,15,15,this,true,1));
			walls.add(new Wall(330,635,0,15,15,this,true,1));
			
			walls.add(new Wall(345,635,0,15,15,this,true,1));
			walls.add(new Wall(360,635,0,15,15,this,true,1));
			walls.add(new Wall(375,635,0,15,15,this,true,1));
			walls.add(new Wall(390,635,0,15,15,this,true,1));
			walls.add(new Wall(405,635,0,15,15,this,true,1));
			walls.add(new Wall(420,635,0,15,15,this,true,1));
			walls.add(new Wall(435,635,0,15,15,this,true,1));
			
			walls.add(new Wall(450,710,0,15,15,this,true,1));
			walls.add(new Wall(450,695,0,15,15,this,true,1));
			walls.add(new Wall(450,680,0,15,15,this,true,1));
			walls.add(new Wall(450,665,0,15,15,this,true,1));
			walls.add(new Wall(450,650,0,15,15,this,true,1));
			walls.add(new Wall(450,635,0,15,15,this,true,1));
		}
		
		for(int i=0;i<10;i++) {
			walls.add(new Wall(15+15*(i+1),150,0,15,15,this,true,1));
			walls.add(new Wall(15+15*(i+1),165,0,15,15,this,true,1));
		}
		for(int i=0;i<1;i++) {
			walls.add(new Wall(300,330,0,15,15,this,true,1));
			walls.add(new Wall(315,330,0,15,15,this,true,1));
			walls.add(new Wall(330,330,0,15,15,this,true,1));
			walls.add(new Wall(345,330,0,15,15,this,true,1));
			walls.add(new Wall(360,330,0,15,15,this,true,1));
			walls.add(new Wall(375,330,0,15,15,this,true,1));
			walls.add(new Wall(390,330,0,15,15,this,true,1));
			walls.add(new Wall(405,330,0,15,15,this,true,1));
			walls.add(new Wall(420,330,0,15,15,this,true,1));
			walls.add(new Wall(435,330,0,15,15,this,true,1));
			
			walls.add(new Wall(300,345,0,15,15,this,true,1));
			walls.add(new Wall(315,345,0,15,15,this,true,1));
			walls.add(new Wall(330,345,0,15,15,this,true,1));
			walls.add(new Wall(345,345,0,15,15,this,true,1));
			walls.add(new Wall(360,345,0,15,15,this,true,1));
			walls.add(new Wall(375,345,0,15,15,this,true,1));
			walls.add(new Wall(390,345,0,15,15,this,true,1));
			walls.add(new Wall(405,345,0,15,15,this,true,1));
			walls.add(new Wall(420,345,0,15,15,this,true,1));
			walls.add(new Wall(435,345,0,15,15,this,true,1));
			
			walls.add(new Wall(300,360,0,15,15,this,true,1));
			walls.add(new Wall(315,360,0,15,15,this,true,1));
			walls.add(new Wall(330,360,0,15,15,this,true,1));
			walls.add(new Wall(345,360,0,15,15,this,true,1));
			walls.add(new Wall(360,360,0,15,15,this,true,1));
			walls.add(new Wall(375,360,0,15,15,this,true,1));
			walls.add(new Wall(390,360,0,15,15,this,true,1));
			walls.add(new Wall(405,360,0,15,15,this,true,1));
			walls.add(new Wall(420,360,0,15,15,this,true,1));
			walls.add(new Wall(435,360,0,15,15,this,true,1));
			
			walls.add(new Wall(300,375,0,15,15,this,true,1));
			walls.add(new Wall(315,375,0,15,15,this,true,1));
			walls.add(new Wall(330,375,0,15,15,this,true,1));
			walls.add(new Wall(345,375,0,15,15,this,true,1));
			walls.add(new Wall(360,375,0,15,15,this,true,1));
			walls.add(new Wall(375,375,0,15,15,this,true,1));
			walls.add(new Wall(390,375,0,15,15,this,true,1));
			walls.add(new Wall(405,375,0,15,15,this,true,1));
			walls.add(new Wall(420,375,0,15,15,this,true,1));
			walls.add(new Wall(435,375,0,15,15,this,true,1));
			
		}
		for(int i=0;i<1;i++) {
			walls.add(new Wall(600,150,0,15,15,this,true,1));
			walls.add(new Wall(615,150,0,15,15,this,true,1));
			walls.add(new Wall(630,150,0,15,15,this,true,1));
			walls.add(new Wall(645,150,0,15,15,this,true,1));
			walls.add(new Wall(660,150,0,15,15,this,true,1));
			walls.add(new Wall(675,150,0,15,15,this,true,1));
			walls.add(new Wall(690,150,0,15,15,this,true,1));
			walls.add(new Wall(705,150,0,15,15,this,true,1));
			walls.add(new Wall(720,150,0,15,15,this,true,1));
		
			walls.add(new Wall(600,165,0,15,15,this,true,1));
			walls.add(new Wall(615,165,0,15,15,this,true,1));
			walls.add(new Wall(630,165,0,15,15,this,true,1));
			walls.add(new Wall(645,165,0,15,15,this,true,1));
			walls.add(new Wall(660,165,0,15,15,this,true,1));
			walls.add(new Wall(675,165,0,15,15,this,true,1));
			walls.add(new Wall(690,165,0,15,15,this,true,1));
			walls.add(new Wall(705,165,0,15,15,this,true,1));
			walls.add(new Wall(720,165,0,15,15,this,true,1));
		}
		for(int i=0;i<1;i++) {
			
			walls.add(new Wall(300,150,10,30,30,this,true,2));
			walls.add(new Wall(330,150,10,30,30,this,true,2));
			walls.add(new Wall(360,150,10,30,30,this,true,2));
			walls.add(new Wall(390,150,10,30,30,this,true,2));
			walls.add(new Wall(420,150,10,30,30,this,true,2));
			walls.add(new Wall(450,150,10,30,30,this,true,2));
		}
		this.setBounds(100, 0, w, h);
		this.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				System.exit(-1);
			}
			
		});
		this.addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent e) {
				switch(e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					myTank.l=true;
					mu.ranmove();
					break;
				case KeyEvent.VK_UP:
					myTank.u=true;
					mu.ranmove();
					break;
				case KeyEvent.VK_RIGHT:
					myTank.r=true;
					mu.ranmove();
					break;
				case KeyEvent.VK_DOWN:
					myTank.d=true;
					break;
				case KeyEvent.VK_C:
					dialog.setVisible(true);
				}
				myTank.locate();
			}

			public void keyReleased(KeyEvent e) {
				switch(e.getKeyCode()) {
				case KeyEvent.VK_CONTROL:
					myTank.fire();
					mu.ranfire();
					break;
				case KeyEvent.VK_LEFT:
					myTank.l=false;
					break;
				case KeyEvent.VK_UP:
					myTank.u=false;
					break;
				case KeyEvent.VK_RIGHT:
					myTank.r=false;
					break;
				case KeyEvent.VK_DOWN:
					myTank.d=false;
					break;
				}
				myTank.locate();
			}
			
		});
		this.setVisible(true);
		this.setResizable(false);
		this.setBackground(Color.green);
		new Thread(new PaintThread()).start();
	//	nc.connect("127.0.0.1", TankServer.TCP_PORT);
	}
	private void newMenu() {
		bar = new MenuBar();
		menu =new Menu("游戏");
	//	mi5 = new MenuItem("开始");
		mi1 = new MenuItem("重新开始");
		mi2 = new MenuItem("暂停");
		mi3 = new MenuItem("继续");
		mi4 = new MenuItem("退出");
		bar.add(menu);
	//	menu.add(mi5);
		menu.add(mi1);
		menu.add(mi2);
		menu.add(mi3);
		menu.add(mi4);
	
		mi1.addActionListener(this);
		mi1.setActionCommand("newgame");
		mi2.addActionListener(this);
		mi2.setActionCommand("pause");
		mi3.addActionListener(this);
		mi3.setActionCommand("continue");
		mi4.addActionListener(this);
		mi4.setActionCommand("exit");
		this.setMenuBar(bar);
	}
	public void paint(Graphics g) {
		
		g.drawString("missiles count:"+missiles.size(), 10, 50);
		g.drawString("explodes count:"+explodes.size(), 10, 70);
		g.drawString("enemytanks count:"+tanks.size(), 10, 90);
		if(myTank.isLive()){
			myTank.draw(g);}
			else 
			{
				mu.ranadd();
				myTank.x=370;
				myTank.y=280;
				myTank.live=true;
				myTank.setLife(100);
//				TankNewMsg msg = new TankNewMsg(myTank); /*采用UDP的发送*/
//				nc.send(msg);
				//return;
			}
		for(int i=0;i<tanks.size();i++) {
			Tank t  = tanks.get(i);
			int k = rr.nextInt(6)+1;
			
			if(!t.live) {
				//mu.ranboom();
				G+=10;
				if(t.id>=1000&&!t.good) {
				Blood b = new Blood(t.x,t.y,this,true,k);
				bloods.add(b);
				BloodNewMsg msg = new BloodNewMsg(b);
				nc.send(msg);
				if(t.id>1000)
				tanks.remove(i);
				if(t.id<1000){
					t.x=370;
					t.y=280;
					t.setLive(true);
					t.setLife(100);
				}
			}
				
			}
			t.collidedTank(myTank);
			t.collidesWater(waters);
			t.collidedWall(walls);
			t.draw(g);
		}
		if(!home.live||!home1.live) {
			this.dispose();
			Over o = new Over(this);
			o.drawOver();
		}
		for(int i=0;i<waters.size();i++) {
			Water w = waters.get(i);
			w.draw(g);
		}
		for(int i=0;i<grasss.size();i++) {
			Grass gr = grasss.get(i);
			gr.draw(g);
		}
		for(int i=0;i<missiles.size();i++) {
			Missile m =missiles.get(i);
			m.hitTank(tanks);
			if(m.hitTank(myTank)) {
				TankDeadMsg msg = new TankDeadMsg(myTank.id);
				nc.send(msg);
			}
			if(m.hitHome(home))
				home1.live=false;
			if(m.hitHome(home1))
				home.live=false;
			m.hitWall(walls);
			m.draw(g);
		}

		for(int i=0;i<comptanks.size();i++) {
			Tank t1 = comptanks.get(i);
			t1.draw(g);
		}
		
		
		for(int i=0;i<walls.size();i++) {
			Wall w = walls.get(i);
			w.draw(g);
		}
		
		for(int i=0;i<explodes.size();i++) {
			Explode  e =explodes.get(i);
			e.draw(g);
		}
		for(int i=0;i<bloods.size();i++) {
			Blood b = bloods.get(i);
			b.eatBlood(myTank);
			b.eatBlood(tanks);
			b.draw(g);
		}
		myTank.collidesWater(waters);
		home.draw(g);
		home1.draw(g);
		myTank.collidedTank(tanks);
		myTank.collidedWall(walls);
		
	}
	public class PaintThread extends Thread{
		public void run() {
			while(printable) {
				repaint();
				try {
					sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equalsIgnoreCase("newgame")) {
			printable = false;  
			Object[] options = { "确定", "取消" };  
			int response = JOptionPane.showOptionDialog(this, "您确认要重新开始！", "标题",  
			JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);  
			if (response == 0) {
				printable = true;
				this.dispose();
				new TankClient("TankWar");
			}
			printable = true;  
			new Thread(new PaintThread()).start(); // 线程启动 
	    }
	    else if(e.getActionCommand().equalsIgnoreCase("pause")){
	    	printable=false;
	    }
	    else if(e.getActionCommand().equalsIgnoreCase("continue")) {
	    	if(!printable) {
	    		printable=true;
	    		new Thread(new PaintThread()).start();
	    	}
	    }
	    else if(e.getActionCommand().equalsIgnoreCase("exit")){
	    	printable = false;  
	    	Object[] options = { "确定", "取消" };  
	    	int response = JOptionPane.showOptionDialog(this, "您确认要退出吗", "",  
	    	JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null,  
	    	options, options[0]);  
	    	if (response == 0) {  
	    	System.out.println("退出");  
	    	System.exit(0);  
	    	} else {  
	    	printable = true;  
	    	new Thread(new PaintThread()).start(); // 线程启动  
	    }
	    }
	    
	}
class ContDialog extends Dialog{
		
		Button b = new Button("确定");
		TextField tfIP = new TextField("127.0.0.1",12);
		TextField tfPort = new TextField(""+TankServer.TCP_PORT,4);
		TextField tfMyUDPPort = new TextField("2223",4);
		public ContDialog() {
			super(TankClient.this,true);

			b.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					String IP = tfIP.getText().trim();
					int port = Integer.parseInt(tfPort.getText().trim());
					int myUDPPrt = Integer.parseInt(tfMyUDPPort.getText().trim());
					nc.setUdpPort(myUDPPrt);
					nc.connect(IP, port);
					setVisible(false);
				}
				
			});
			this.setLayout(new FlowLayout());
			this.add(new Label("IP:"));
			this.add(tfIP);
			this.add(new Label("Port:"));
			this.add(tfPort);
			this.add(new Label("My UDP Port:"));
			this.add(tfMyUDPPort);
			this.add(b);
			this.setLocation(300, 300);
			this.pack();
			this.addWindowListener(new WindowAdapter() {

				public void windowClosing(WindowEvent e) {
					setVisible(false);
				}
				
			});
		}
	}
	public static void main(String[] args) {
		TankClient tf = new TankClient("TankWar");

	}

}
