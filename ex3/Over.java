package ex3;
import java.awt.HeadlessException;
import javax.swing.*;


public class Over extends JFrame{
	TankClient tf;
	public Over(TankClient tf) {
		this.tf=tf;
	}
	private JPanel panel=null;
	private JLabel l1 = null;
	private JLabel l2 = null;
	private JTextField tf1 = null;
	private JTextField tf2 = null;
	private JTextArea content =null;
	int grade =0;
	String str =grade+"";

	public void drawOver() {
		setBounds(500, 350, 300, 200);
		setLayout(null);
		l1 = new JLabel("·ÖÊý:");
	//	l2 = new JLabel("ÅÅÃû:");
		tf1 = new JTextField(10);
	//	tf2 = new JTextField(10);
		tf1.setText(tf.G+"");
		add(l1);
	//	add(l2);
		add(tf1);
	//	add(tf2);

		l1.setBounds(30, 30, 50, 20);
	//	l2.setBounds(30, 80, 50, 20);
		tf1.setBounds(80, 30, 50, 20);
	//	tf2.setBounds(80, 80, 50, 20);
		this.setVisible(true);
	}
}
