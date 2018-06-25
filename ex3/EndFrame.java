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
	Button sure=new Button("�˳�");
	Button next=new Button("��һ��");
	Button back=new Button("���¿�ʼ");
	TankFrame tf;
	public int ju;
//	int []str=new int[1000];

	

	
	public EndFrame(TankFrame tf,int ju) throws IOException{
		this.tf=tf;
		this.ju=ju;
		
		
		this.setTitle("��Ϸ����");
		this.setLayout(null);
		this.setBounds(500, 250, 300, 200);
		this.setVisible(true);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		this.setLayout(null);
		
//		File f=new File("E:\\δ���\\����Java\\Tankwar2.txt");
//		if(!f.exists()) {
//			f.createNewFile();
//		}
//		FileInputStream fin=new FileInputStream(f);//�ļ�������
//		FileOutputStream fout=new FileOutputStream(f,true);//�ļ������
//		
//		int maxn;//��һ������
//		int a;
//		int n=(10-tf.tanks.size())*10;//����
//		fout.write(n);//����
//		fout.close();
//		int i=0;
//		int sumn=1;//����
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
//		name.setText("�ܷ֣� "+n+"    ������ "+sumn);
//		cj.setText("��һ�������� "+maxn);
//		name.setBounds(80, 50, 250, 20);
//		cj.setBounds(80, 70, 200, 20);
//		this.add(name);
//		this.add(cj);
		switch(ju){
		
		case 0:
			name.setText("���ź�ûͨ��~ ");
			name.setBounds(80, 50, 250, 20);
			this.add(name);
			cj.setText("������ "+(10*(10-tf.tanks.size())));
			cj.setBounds(80, 70, 200, 20);
			this.add(cj);
			sure.setBounds(70, 130, 30, 20);
			this.add(sure);
			back.setBounds(160, 130, 50, 20);
			this.add(back);
			sure.addActionListener(new ActionListener(){//�ص����д���
				public void actionPerformed(ActionEvent e) {
					tf.dispose();
					dispear();
				}
			});
			back.addActionListener(new ActionListener(){//�ص�EndFrame��������Ϸ
				public void actionPerformed(ActionEvent e) {
					tf.dispose();
					dispear();
					new TankFrame(1);
				}
			});
			break;
		
		case 1:
			name.setText("��ϲͨ�����أ� ");
			name.setBounds(80, 50, 250, 20);
			this.add(name);
			sure.setBounds(70, 130, 30, 20);
			this.add(sure);
			next.setBounds(110, 130, 40, 20);
			this.add(next);
			back.setBounds(160, 130, 50, 20);
			this.add(back);
			sure.addActionListener(new ActionListener(){//�ص����д���
				public void actionPerformed(ActionEvent e) {
					tf.dispose();
					dispear();
				}
			});
			
			next.addActionListener(new ActionListener(){//��һ��
				public void actionPerformed(ActionEvent e) {
					tf.dispose();
					dispear();
					new TankFrame(tf.guan+1);
				}
			});
			
			back.addActionListener(new ActionListener(){//�ص�EndFrame��������Ϸ
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

