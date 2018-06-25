package ex3;

import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
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

public class LoginFrame extends Frame{
	Label label1=new Label();
	Label label2=new Label();
	TextField name=new TextField();//�ı���
	TextField pass=new TextField();
	Button sure=new Button("��¼");
	Button back=new Button("ע��");
	
	public LoginFrame(){
		this.setTitle("login");
		this.setLayout(null);
		this.setBounds(400, 300, 300, 200);
		this.setVisible(true);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		label1.setBounds(70, 70, 30, 20);
		label2.setBounds(70, 100, 30, 20);
		label1.setText("�û�");
		label2.setText("����");
		this.add(label1);
		this.add(label2);
		name.setBounds(110, 70, 80, 20);
		pass.setBounds(110, 100, 80, 20);
		this.add(name);
		this.add(pass);
		sure.setBounds(100, 130, 30, 20);
		back.setBounds(150, 130, 30, 20);
		this.add(sure);
		this.add(back);	 
	}
	
	public static void main(String[] args) throws IOException {
		LoginFrame lo=new LoginFrame();
		ArrayList<String> str=new ArrayList<String>();//�˻�������
		File f=new File("D:\\copy2\\test\\test1.txt");//E:\\δ���\\����Java
		if(!f.exists()){
			f.createNewFile();
		}
		BufferedReader br=new BufferedReader(new FileReader(f));//��������
		BufferedWriter bw=new BufferedWriter(new FileWriter(f,true));//д������
		lo.sure.addActionListener(new ActionListener() {//�������¼��ťʱ
		//����˻������붼���ڣ����¼������ʧ��new�� TankFrame
			public void actionPerformed(ActionEvent e) {
				String a;
				try {
					while((a=br.readLine())!=null){
						str.add(a);//���ϵİ��ı���������ݼ��뵽str��
					}
					for(int i=0;i<str.size();i+=2){//ż����Ϊ�˻���������Ϊ����
						if(str.get(i).equalsIgnoreCase(lo.name.getText())){
							//��equal�������ƣ�ֻ�ǲ����ִ�Сд��i�����˻�
							if(str.get(i+1).equalsIgnoreCase(lo.pass.getText())){
							//������Ϊ���롣i+1��������
								lo.dispose();
								//��½�ɹ�������ɹ���������ھ���ʧ��
								//new TankFrame(1);
								new xuan();
								break;
							}
						}
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		lo.back.addActionListener(new ActionListener() {//�����ע�ᰴťʱ
			public void actionPerformed(ActionEvent e) {
				if(lo.name.getText()!=null&&lo.pass.getText()!=null){
				//���û�����Ĳ��ǿ�ֵʱ�����˺ź�����д���ĵ�����ʾע��ɹ�
					try {
						//���˺ź�����д���ĵ�
						bw.write(lo.name.getText()+'\n');
						bw.write(lo.pass.getText()+'\n');
						Label l=new Label();
						l.setText("ע��ɹ������¼!");
						l.setBounds(90, 160, 110, 20);
						lo.add(l);
						bw.close();//�ص��ļ�
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
	}

}

