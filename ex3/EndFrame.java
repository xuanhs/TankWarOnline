package ex3;

import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class EndFrame extends Frame{
	Label name=new Label();
	Label cj=new Label();
	Button sure=new Button("退出");
	Button next=new Button("下一关");
	Button back=new Button("重新开始");
	TankFrame tf;
	public int ju;
//	int []str=new int[1000];

	

	
	public EndFrame(TankFrame tf,int ju) throws IOException{
		this.tf=tf;
		this.ju=ju;
		
		
		this.setTitle("游戏结束");
		this.setLayout(null);
		this.setBounds(500, 250, 300, 200);
		this.setVisible(true);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		this.setLayout(null);
		
//		File f=new File("E:\\未完成\\超级Java\\Tankwar2.txt");
//		if(!f.exists()) {
//			f.createNewFile();
//		}
//		FileInputStream fin=new FileInputStream(f);//文件输入流
//		FileOutputStream fout=new FileOutputStream(f,true);//文件输出流
//		
//		int maxn;//第一名分数
//		int a;
//		int n=(10-tf.tanks.size())*10;//分数
//		fout.write(n);//分数
//		fout.close();
//		int i=0;
//		int sumn=1;//排名
//		maxn=n;
//		while((a=fin.read())!=-1){
//			str[i++]=a;
//		}
//		for(int j=0;j<str.length;j++){
//			if(j%2==0&&str[j]>maxn){
//				sumn++;
//				maxn=str[j];
//			} 
//		}
//		name.setText("总分： "+n+"    排名： "+sumn);
//		cj.setText("第一名分数： "+maxn);
//		name.setBounds(80, 50, 250, 20);
//		cj.setBounds(80, 70, 200, 20);
//		this.add(name);
//		this.add(cj);
		switch(ju){
		
		case 0:
			name.setText("很遗憾没通关~ ");
			name.setBounds(80, 50, 250, 20);
			this.add(name);
			cj.setText("分数： "+(10*(10-tf.tanks.size())));
			cj.setBounds(80, 70, 200, 20);
			this.add(cj);
			sure.setBounds(70, 130, 30, 20);
			this.add(sure);
			back.setBounds(160, 130, 50, 20);
			this.add(back);
			sure.addActionListener(new ActionListener(){//关掉所有窗口
				public void actionPerformed(ActionEvent e) {
					tf.dispose();
					dispear();
				}
			});
			back.addActionListener(new ActionListener(){//关掉EndFrame，开新游戏
				public void actionPerformed(ActionEvent e) {
					tf.dispose();
					dispear();
					new TankFrame(1);
				}
			});
			break;
		
		case 1:
			name.setText("恭喜通过本关！ ");
			name.setBounds(80, 50, 250, 20);
			this.add(name);
			sure.setBounds(70, 130, 30, 20);
			this.add(sure);
			next.setBounds(110, 130, 40, 20);
			this.add(next);
			back.setBounds(160, 130, 50, 20);
			this.add(back);
			sure.addActionListener(new ActionListener(){//关掉所有窗口
				public void actionPerformed(ActionEvent e) {
					tf.dispose();
					dispear();
				}
			});
			
			next.addActionListener(new ActionListener(){//下一关
				public void actionPerformed(ActionEvent e) {
					tf.dispose();
					dispear();
					new TankFrame(tf.guan+1);
				}
			});
			
			back.addActionListener(new ActionListener(){//关掉EndFrame，开新游戏
				public void actionPerformed(ActionEvent e) {
					dispear();
					new TankFrame(1);
				}
			});
			break;
		}
	}	
	
	public void dispear(){
		this.dispose();
	}
	
}

