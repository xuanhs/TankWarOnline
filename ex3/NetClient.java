package ex3;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class NetClient {
	
	TankClient tf;
	public static int UDP_PORT_STRART =2237;
	private int udpPort;
	String IP; 
	public int getUdpPort() {
		return udpPort;
	}
	public void setUdpPort(int udpPort) {
		this.udpPort = udpPort;
	}
	DatagramSocket ds = null;
	public NetClient(TankClient tf) {
		this.tf = tf;	
		udpPort = UDP_PORT_STRART++;
	}
	public void connect(String IP,int port) {
		
		this.IP = IP;
		try {
			ds = new DatagramSocket(udpPort);
		} catch (SocketException e) {
			e.printStackTrace();
		}
		
		Socket s =null;
		try {
			
			s = new Socket(IP,port);
			DataOutputStream dos = new DataOutputStream(s.getOutputStream());
			dos.writeInt(udpPort);
			DataInputStream dis = new DataInputStream(s.getInputStream());
			int id = dis.readInt();
			tf.myTank.id = id;
//
//			if(tf.myTank.id ==100) {
//				for(int i=0;i<10;i++) {
//					tf.comptanks.add(new Tank(50+50*(i+1),550,tf,false,true,Dir.STOP));
//				}
//			}
		
//			boolean bad = true;
//			for(int i=0;i<tf.comptanks.size();i++) {
//				Tank t = tf.comptanks.get(i);
//				t.id = cid;
//				cid--;
//				bad = false;
//				if(bad == false) {
//					t.good = false;
//				}
//			}
			
			if(id%2==1) {
				tf.myTank.good = false;
			}
			else
				tf.myTank.good = true;
			
			System.out.println("Connected to Server! and Server give me a ID: "+id);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(s!=null) {
				try {
					s.close();
					s = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		TankNewMsg msg = new TankNewMsg(tf.myTank); /*采用UDP的发送*/
		send(msg);
		
//		for(int i=0;i<tf.comptanks.size();i++) {
//			Tank t = tf.comptanks.get(i);
//			TankNewMsg enmsg = new TankNewMsg(t);
//			send(enmsg);
//		}
		
		new Thread(new UDPReceiveThread()).start();
	}
	public void send(Msg msg) {
		msg.send(ds,IP,TankServer.UDP_PORT);  /*消息怎么发消息自己最清楚*/
	}
	private class UDPReceiveThread implements Runnable{ /*接收线程*/
		byte[] buf = new byte[6666];
		public void run() {
			
			while(ds != null) {
				DatagramPacket dp = new DatagramPacket(buf,buf.length); 
				try {
					ds.receive(dp);
					parse(dp);/*解析包里的数据*/
					System.out.println("a packet received from Server!");
				} catch (IOException e) {
					e.printStackTrace();
				} 
			}
		}

		private void parse(DatagramPacket dp) {
			ByteArrayInputStream bais = new ByteArrayInputStream(buf,0,dp.getLength());
			DataInputStream dis = new DataInputStream(bais);
			int msgType = 0;
			try {
				msgType = dis.readInt();
			} catch (IOException e) {
				e.printStackTrace();
			}
			Msg msg = null;
			switch(msgType) {
			case Msg.TANK_NEW_MSG:		
				msg = new TankNewMsg(NetClient.this.tf); /*内部类去访问封装类的对象*/
				msg.parse(dis);
				break;
			case Msg.TANK_MOVE_MSG:
				msg = new TankMoveMsg(NetClient.this.tf);
				msg.parse(dis);
				break;
			case Msg.TANK_NEW_NPC_MSG://电脑坦克加入
				msg = new TankNPCNewMsg(NetClient.this.tf);
				msg.parse(dis);
				break;
			case Msg.TANK_NPC_DEAD_MSG://电脑坦克死亡
				msg = new TankNPCDeadMsg(NetClient.this.tf);
				msg.parse(dis);
				break;
			case Msg.MISSILE_NEW_MSG:
				msg = new MissileNewMsg(NetClient.this.tf);
				msg.parse(dis);
			case Msg.Blood_New_Msg:
				msg = new BloodNewMsg(NetClient.this.tf);
				msg.parse(dis);
			}
			}
			
		}
		
}
