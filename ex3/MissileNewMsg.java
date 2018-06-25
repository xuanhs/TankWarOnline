package ex3;

import java.io.*;
import java.net.*;

public class MissileNewMsg implements Msg{
	
	int msgType = Msg.MISSILE_NEW_MSG;
	TankClient tf;
	Missile m;
	
	public MissileNewMsg(Missile m) {
		this.m = m;
	}
	
	public MissileNewMsg(TankClient tf) {
		this.tf = tf;
	}
	
	public void send(DatagramSocket ds, String IP, int udpPort) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		try {
			dos.writeInt(msgType);
			dos.writeInt(m.tankID);
			dos.writeInt(m.id);
			dos.writeInt(m.x);
			dos.writeInt(m.y);
			dos.writeInt(m.dir.ordinal());
			dos.writeBoolean(m.good);
			dos.writeBoolean(m.live);
			dos.writeInt(m.level);
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
		int tankID;
		try {
			tankID = dis.readInt();
			if(tf.myTank.id == tankID) {
				return;
			}
			int id = dis.readInt();
			int x = dis.readInt();
			int y = dis.readInt();
			Dir dir = Dir.values()[dis.readInt()];
			boolean good = dis.readBoolean();
			boolean live = dis.readBoolean();
			int level = dis.readInt();
			Missile m = new Missile(tankID,x,y,tf,true,good,dir,level);
			m.id = id;
			tf.missiles.add(m);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
