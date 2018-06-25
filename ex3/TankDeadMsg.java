package ex3;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;


public class TankDeadMsg implements Msg{
	int msgType = Msg.TANK_DEAD_MSG;
	TankClient tf;
	int id;
	Tank tank;
	
	public TankDeadMsg(TankClient tf) {
		this.tf = tf;
	}
	public TankDeadMsg(int id) {
		this.id = id;
	}
	
	public void send(DatagramSocket ds, String IP, int udpPort) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		try {
			dos.writeInt(msgType);
			dos.writeInt(id);
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] buf = baos.toByteArray();
		DatagramPacket dp = new DatagramPacket(buf,buf.length,new InetSocketAddress(IP,udpPort));
		try {
			ds.send(dp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void parse(DataInputStream dis) {
		try {
			int id = dis.readInt();
			if(tf.myTank.id==id) {
				return;
			}
		//	System.out.println("ID:"+id+"-x:"+x+"-y:"+y+"-dir:"+dir+"-good:"+good);
			for(int i=0;i<tf.tanks.size();i++) {
				Tank t = tf.tanks.get(i);
				if(t.id==id) {
					t.setLive(false);
					break;
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
