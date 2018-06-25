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
	TextField name=new TextField();//文本框
	TextField pass=new TextField();
	Button sure=new Button("登录");
	Button back=new Button("注册");
	
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
		label1.setText("用户");
		label2.setText("密码");
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
		ArrayList<String> str=new ArrayList<String>();//账户和密码
		File f=new File("D:\\copy2\\test\\test1.txt");//E:\\未完成\\超级Java
		if(!f.exists()){
			f.createNewFile();
		}
		BufferedReader br=new BufferedReader(new FileReader(f));//读出数据
		BufferedWriter bw=new BufferedWriter(new FileWriter(f,true));//写入数据
		lo.sure.addActionListener(new ActionListener() {//当点击登录按钮时
		//如果账户和密码都存在，则登录窗口消失，new出 TankFrame
			public void actionPerformed(ActionEvent e) {
				String a;
				try {
					while((a=br.readLine())!=null){
						str.add(a);//不断的把文本里面的数据加入到str中
					}
					for(int i=0;i<str.size();i+=2){//偶数行为账户，奇数行为密码
						if(str.get(i).equalsIgnoreCase(lo.name.getText())){
							//与equal功能相似，只是不区分大小写。i读出账户
							if(str.get(i+1).equalsIgnoreCase(lo.pass.getText())){
							//奇数行为密码。i+1读出密码
								lo.dispose();
								//登陆成功后这个成功后这个窗口就消失了
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

		lo.back.addActionListener(new ActionListener() {//当点击注册按钮时
			public void actionPerformed(ActionEvent e) {
				if(lo.name.getText()!=null&&lo.pass.getText()!=null){
				//当用户输入的不是空值时，把账号和密码写进文档，显示注册成功
					try {
						//把账号和密码写进文档
						bw.write(lo.name.getText()+'\n');
						bw.write(lo.pass.getText()+'\n');
						Label l=new Label();
						l.setText("注册成功，请登录!");
						l.setBounds(90, 160, 110, 20);
						lo.add(l);
						bw.close();//关掉文件
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
	}

}

