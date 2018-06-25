package ex3;

import java.io.*;
import java.net.*;
import java.util.*;


public class TankServer {
	TankClient tf;
	private static int ID = 100;
	private  int npc_tank_id_init = 1000;
	public boolean isFirstConnect = true;//�״ε�½����NPC
	public boolean isFirstStart = true;
	public boolean isCreateNPC = true;//�������NPC������������������ֵ����ˢ��NPC
	public static final int TCP_PORT = 8888;
	public static final int UDP_PORT = 6666;
//	List<Tank> tanks_npc = new ArrayList<Tank>();
	List<Tank> tanks_npc = new ArrayList<Tank>();
	List<Wall> walls_npc = new ArrayList<Wall>();
	List<Client> clients = new ArrayList<Client>();
	public TankServer(){
		
	}
	public void start() {/*��̬������һ����һ��this�����ã��Ѿ��а�װ��Ķ���new ����ı���װ����ڲ������*/
		
		new Thread(new AutoCreateNPC()).start();
		new Thread(new UDPThread()).start();
		ServerSocket ss = null;
		try {
			ss = new ServerSocket(TCP_PORT);
		} catch (IOException e) {
			e.printStackTrace();
		}
		while(true) {
			Socket s = null;
			try {
				s = ss.accept();
				DataInputStream dis = new DataInputStream(s.getInputStream());
				String IP = s.getInetAddress().getHostAddress();
				int udpPort = dis.readInt();
				Client c = new Client(IP,udpPort);
				clients.add(c);
				DataOutputStream dos = new DataOutputStream(s.getOutputStream());
				dos.writeInt(ID++);
			//	dos.writeInt(CID);
				//s.close();
				System.out.println("A Client Connect! Addr="+s.getInetAddress()+":"+s.getPort()+"----UDP PORT:"+udpPort);
			}catch(IOException e) {
				e.printStackTrace();
			}finally {
				if(s != null) {
					try {
						s.close();
						s = null;
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}
			}
				
		}
	}
	public static void main(String[] args) { 
	
		new TankServer().start();
	}

	public class Client{  /*��������װ��Client����,����ø����ͻ���Client��IP��udpPort*/
		String IP;
		int udpPort;
 
		public Client(String IP, int udpPort) {/*����UDP��port*/
			this.IP = IP;
			this.udpPort = udpPort;
		}
	}
	public class UDPThread implements Runnable{
		byte[] buf = new byte[6666];

		public void run() {
			DatagramSocket ds = null;
			try {
				ds = new DatagramSocket(UDP_PORT);
			} catch (SocketException e) {
				e.printStackTrace();
			}
			System.out.println("UDP threads started at port:"+UDP_PORT);
			while(ds != null) {
				DatagramPacket dp = new DatagramPacket(buf,buf.length); 
				try {
					ds.receive(dp);
					ByteArrayInputStream bais = new ByteArrayInputStream(buf, 0, dp.getLength());
					DataInputStream dis = new DataInputStream(bais);
					int msgType = 0;
					int id =0;
					try {
						msgType = dis.readInt();
						id = dis.readInt();
					} catch (IOException e) {
						e.printStackTrace();
					}
					for(int i=0;i<clients.size();i++) {
						Client c = clients.get(i);
						dp.setSocketAddress(new InetSocketAddress(c.IP,c.udpPort));
						
						if(msgType==Msg.TANK_NEW_MSG ){
							for (int j = 0; j < tanks_npc.size(); j++) {
								Tank t = tanks_npc.get(j);
								//�ڳ����з���NPC̹�ˣ�����̹�˵�λ�ø���������
								TankNPCNewMsg tnpc = new TankNPCNewMsg(t);
								tnpc.send(ds, c.IP, c.udpPort);
							}
							
						}
						//���NPC̹�˱������ڷ��������Ƴ���NPC
						if(msgType== Msg.TANK_NPC_DEAD_MSG){
							//ѭ�����������̹�˱������Ƴ���̹��
							for (int j = 0; j < tanks_npc.size(); j++) {
								Tank t = tanks_npc.get(j);
								if (t.id == id) {
									tanks_npc.remove(t);
									break;
								}
							}
						}
						ds.send(dp);;
						
					}
					System.out.println("a packet received!");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	class AutoCreateNPC implements Runnable{
		private int count = 0;
		//��ʱ��
		public void run() {
			try {
				while (true) {
					if(isFirstConnect && isCreateNPC){
						createNPC();
						isFirstConnect = false;
						isCreateNPC =false;
					}
					if(tanks_npc.size()==0 && count%60==0){
						createNPC();
						isCreateNPC = true;
						count=0;
					}
					count++;
					Thread.currentThread().sleep(1000);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		public void createNPC(){
			for (int j = 0; j < 5; j++) {
				Tank t = new Tank(50,500,tf,false,true,Dir.STOP,false);
				t.id=npc_tank_id_init++;
				tanks_npc.add(t);
			}
			for (int j = 0; j < 5; j++) {
				Tank t = new Tank(500,500,tf,true,true,Dir.STOP,false);
				t.id=npc_tank_id_init++;
				tanks_npc.add(t);
			}
		}
		
	}
	
}
