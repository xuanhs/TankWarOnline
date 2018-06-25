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
	MenuBar mb=new MenuBar();//�˵���
	Menu m=new Menu("��Ϸ");//�˵�
	MenuItem mi1=new MenuItem("�µ���Ϸ");//�˵���Ŀ
	MenuItem mi2=new MenuItem("��ͣ��Ϸ");
	MenuItem mi3=new MenuItem("������Ϸ");
	Random rr=new Random();
	Image off=null;//ͼƬ�������
	Tank1 mt=new Tank1(this,100, 100, 14, 10, 1, true, true, Tank1.Dir.D);
	ArrayList<Tank1> tanks=new ArrayList<Tank1>();
	ArrayList<Fire> fi=new ArrayList<Fire>();//�����ӵ���
	ArrayList<Boom>boom=new ArrayList<Boom>();
	ArrayList<Wall1>walls=new ArrayList<Wall1>();
	ArrayList<Grass1>grass=new ArrayList<Grass1>();
	ArrayList<Able> ables=new ArrayList<Able>(); 
	boolean bom=true;
	
	Music mu=new Music();
	public int guan;//ȡֵ1,2,3���ܹ����ء���һ��ûǽûѪ�飬�ڶ�����ǽ��Ѫ�飬��������ǽ��Ѫ���в�
	
	
public TankFrame(){//��ܼ�
		
		//�������
		this.setBounds(300, 100, 800, 600);
		this.setBackground(Color.black);
		this.setVisible(true);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		//��ܼ����������߳�
		TankThread th=new TankThread(this, true);	
		th.start();
		
		//����˵�
		m.add(mi1);
		m.add(mi2);
		m.add(mi3);
		mb.add(m);
		this.setMenuBar(mb);
		
		//�µ���Ϸ
		mi1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mt.tf.dispose();//���ʹ���������ʧ��Ȼ��������new
				new TankFrame(1);
			}
		});
		//��ͣ��Ϸ
		mi2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				th.s=false;
				//s��TankThread��һ������ֵ����TankThread��run������Ϊtrueʱ�ػ���
				//falseʱ�򲻻����repaint
			}
		});
		//������Ϸ
		mi3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				th.s=true;//��mi2
			}
		});
		
		
		//̹�˵ĳ������
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
				case KeyEvent.VK_0://����
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
	

	public TankFrame(int guan){//��ܼ�
		this();
		this.guan=guan;
		//�ʼʱ��LoginFrame���ã������ֵΪ1
		//����EndFrame�����һ��֮�󣬴����ֵΪguan+1��Ϊ2
		//���ڶ���ͨ��ʱ���ٵ����һ��guan+1��3
		
		switch(guan){
		case 1:
			//���õз�̹�˳�ʼλ��
			int x1=30,y1=500;
			for(int i=0;i<10;i++){
				if(i%2!=0) tanks.add(new Tank1(this,x1+i*50, y1, 8, 12, 1, true, false, Tank1.Dir.U));
				else tanks.add(new Tank1(this,x1+i*50, y1-200, 8, 12, 1, true, false, Tank1.Dir.U));
			}
			break;
			
			
		case 2:
			guan=2;
			//���õз�̹�˳�ʼλ��
			int x2=200,x22=500,y2=500,y22=550;
			for(int i=0;i<10;i++){
				if(i<5){
					tanks.add(new Tank1(this,x2+i*50, y2, 8, 12, 1, true, false, Tank1.Dir.U));
				}
				else{
					tanks.add(new Tank1(this,x22, y22-i*50, 8, 12, 1, true, false, Tank1.Dir.L));
				}
			}
			
			//����ǽ�ĳ�ʼλ�ã�һ��ǽ�ɶ���������
			x2=350;
			y2=120;
			//һ��ǽ���ɶ��������ɵģ��ﵽһ���ȼ��ӵ��ܴ�ǽʱ��ÿһ���ӵ����ܻ���ǽ��һ������
			for(int i=0;i<8;i++)//��ǽ
				walls.add(new Wall1(x2, y2+i*30, 4, true));//
			x2=60;
			y2=350;
			for(int i=0;i<16;i++)//��ǽ
				walls.add(new Wall1(x2+i*15, y2,  2, true));
			break;
			
			
		case 3:
			guan=3;
			//���õз�̹�˳�ʼλ��
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
			
			//����ǽ�ĳ�ʼλ�ã�һ��ǽ�ɶ���������
			x3=350;
			y3=120;
			//һ��ǽ���ɶ��������ɵģ��ﵽһ���ȼ��ӵ��ܴ�ǽʱ��ÿһ���ӵ����ܻ���ǽ��һ������
			for(int i=0;i<8;i++)//��ǽ
				walls.add(new Wall1(x3, y3+i*30, 4, true));//
			x3=60;
			y3=350;
			for(int i=0;i<16;i++)//��ǽ
				walls.add(new Wall1(x3+i*15, y3, 2, true));
			
			//���òݵĳ�ʼλ��
			x3=600;
			y3=100;
			for(int i=0;i<4;i++)//����
				grass.add(new Grass1(x3, y3+i*60));//
			x3=250;
			y3=480;
			for(int i=0;i<6;i++)//���
				grass.add(new Grass1(x3+i*60, y3));
			break;
		}
	}
	
	
	
	public void update(Graphics g){//�����˸�����˫����
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
		
		//���ϽǼƷ�
		g.setColor(Color.white);//����������ɫ
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
		
		//������̹��
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
		
		

		//���з�̹�ˣ���ͻ�������newһ��able
		for(int i=0;i<tanks.size();i++){
			Tank1 t=tanks.get(i);
			if(t.live){
				t.draw(g);
			}
			else
			{
				mu.ranboom();
				tanks.remove(i);
				int k=rr.nextInt(3)+1;//�з������������һ��Ѫ��
				ables.add(new Able(t.x, t.y, k, this, true));
			}
		}
		
		//���ӵ�����ͻ����д�̹�ˣ�����remove
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
		
		//����ը����ͻ�������remove
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
			//��ǽ����ͻ�������remove
			for(int i=0;i<walls.size();i++){
				Wall1 wa=walls.get(i);
				if(wa.live){
					wa.draw(g);
				}
				else walls.remove(i);
			}
			
			//��able����ͻ�������remove
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
			//��ǽ����ͻ�������remove
			for(int i=0;i<walls.size();i++){
				Wall1 wa=walls.get(i);
				if(wa.live){
					wa.draw(g);
				}
				else walls.remove(i);
			}
			
			//��able����ͻ�������remove
			for(int i=0;i<ables.size();i++){
				Able ab=ables.get(i);
				if(ab.live){		
					ab.draw(g);
					ab.bett();
				}
				else ables.remove(i);
			}
			
			//����
			for(int i=0;i<grass.size();i++){
				Grass1 gr=grass.get(i);
				gr.draw(g);
			}
			break;
		}
		
	}
	
}
