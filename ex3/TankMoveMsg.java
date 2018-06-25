package ex3;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class TankMoveMsg implements Msg{
	int msgType = Msg.TANK_MOVE_MSG;
	int id;
	Dir dir;
	Dir ptDir;
	int x,y;
	TankClient tf;
	TankServer ts;
	public TankMoveMsg(int id, int x, int y, Dir dir, Dir ptDir) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.ptDir = ptDir;
	}
	public TankMoveMsg(TankClient tf) {
		this.tf = tf;
	}
	public void send(DatagramSocket ds, String IP, int udpPort) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();/*字节数组*/
		DataOutputStream dos = new DataOutputStream(baos);
		try {
			dos.writeInt(msgType);
			dos.writeInt(id);
			dos.writeInt(x);
			dos.writeInt(y);
			dos.writeInt(dir.ordinal()); /*方向数组的下标值*/
			dos.writeInt(ptDir.ordinal());
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] buf = baos.toByteArray();
	
		try {
			DatagramPacket dp = new DatagramPacket(buf,buf.length,new InetSocketAddress(IP,udpPort));
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
			int x = dis.readInt();
			int y = dis.readInt();
			Dir dir = Dir.values()[dis.readInt()];
			Dir ptDir = Dir.values()[dis.readInt()];
		//	System.out.println("ID:"+id+"-x:"+x+"-y:"+y+"-dir:"+dir+"-good:"+good);
			boolean exist = false;
			for(int i=0;i<tf.tanks.size();i++) {
				Tank t = tf.tanks.get(i);
				if(t.id==id) {
					t.dir = dir;
					t.ptDir = ptDir;
					t.x = x;
					t.y = y;
					exist = true;
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
