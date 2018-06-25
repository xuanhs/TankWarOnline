package ex3;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class BloodNewMsg implements Msg{
	
	int msgType = Msg.Blood_New_Msg;
	int id;
	TankClient tf;
	Blood b;
	public BloodNewMsg(TankClient tf) {
		this.tf = tf;
	}
	public BloodNewMsg(Blood b) {
		this.b = b;
	}
	
	public void send(DatagramSocket ds, String IP, int udpPort) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		try {
			dos.writeInt(msgType);
			dos.writeInt(b.id);
			dos.writeInt(b.x);
			dos.writeInt(b.y);
			dos.writeBoolean(b.live);
			dos.writeInt(b.level);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] buf = baos.toByteArray();
		DatagramPacket dp =new DatagramPacket(buf, buf.length, new InetSocketAddress(IP, udpPort));
		try {
			ds.send(dp);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void parse(DataInputStream dis) {
		try {
			int id = dis.readInt();
			int x = dis.readInt();
			int y = dis.readInt();
			boolean live = dis.readBoolean();
			int level = dis.readInt();
			boolean exist = false;
			for(int i=0;i<tf.bloods.size();i++) {
				Blood b = tf.bloods.get(i);
				if(b.id==id) {
					exist = true;
				}
			}
			if(!exist) {
			Blood b = new Blood(x,y,tf,live,level);
			b.id = id;
			tf.bloods.add(b);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
}
