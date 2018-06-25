package ex3;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class xuan extends Frame implements ActionListener{
	
	private static Toolkit tool = Toolkit.getDefaultToolkit();
	private static  Image[] images;
	private static Map<String,Image> imgs=new HashMap<String,Image>();
	static{    //静态代码区,一个类中先执行static的
		images = new Image[] {
				tool.getImage(xuan.class.getClassLoader().getResource("images/bj.jpg"))
			};
			imgs.put("bj", images[0]);
	}
	
	Button sure = null;
	Button back = null; 
	Image image;
	Music mu=new Music();
	public xuan(){
		this.setTitle("login");
		this.setLayout(null);
		sure=new Button("网络对战");
		sure.addActionListener(this);
		back=new Button("单人游戏");
		back.addActionListener(this);
		this.setBounds(200, 100, 800, 600);
		this.setVisible(true);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		sure.setBackground(Color.black);
		sure.setForeground(Color.red);
		back.setBackground(Color.black);
		back.setForeground(Color.red);
		sure.setBounds(350, 280, 100, 30);
		back.setBounds(350, 320, 100, 30);
		this.add(sure);
		this.add(back);	 
	}
	
	public void paint(Graphics g){
		g.drawImage(imgs.get("bj"), 0, 0,800,600, null);
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		repaint();
	}
	
	public static void main(String[] args) throws IOException {
		xuan lo=new xuan();
	}

	public void actionPerformed(ActionEvent e) {
		  if(e.getSource()==sure){
			  this.dispose();
			  mu.rannew();
			  new TankClient("TankWar");
		  }
		  else if(e.getSource()==back) {
			  this.dispose();
			  mu.rannew();
			  new TankFrame(1);
		  }
	}

}