package ex3;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;


public class TankNewMsg implements Msg{
	int msgType = Msg.TANK_NEW_MSG;
	Tank tank;
	TankClient tf;
	public TankNewMsg(Tank tank) {
		this.tank = tank;
	}
	public TankNewMsg(TankClient tf) {
		this.tf = tf;
	}
	public void send(DatagramSocket ds,String IP,int udpPort) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();/*字节数组*/
		DataOutputStream dos = new DataOutputStream(baos);
		try {
			dos.writeInt(msgType);
			dos.writeInt(tank.id);
			dos.writeInt(tank.x);
			dos.writeInt(tank.y);
			dos.writeInt(tank.dir.ordinal()); /*方向数组的下标值*/
			dos.writeBoolean(tank.good);
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
	public void parse(DataInputStream dis) { /*分析数据*/
		try {
			int id = dis.readInt();
			if(tf.myTank.id==id) {
				return;
			}
			
			
			int x = dis.readInt();
			int y = dis.readInt();
			Dir dir = Dir.values()[dis.readInt()];
			boolean good = dis.readBoolean();
		//	System.out.println("ID:"+id+"-x:"+x+"-y:"+y+"-dir:"+dir+"-good:"+good);
			boolean exist =false;
//			if(exist==false) {
//				int j=0;
//				for(int i=0;i<2;i++) {
//					Tank t =new Tank(60,500,true,good,tf,dir);
//					t.id = j++;
//					tf.tanks.add(t);
//				}
//			}
			for(int i=0;i<tf.tanks.size();i++) {
				Tank t = tf.tanks.get(i);
				if(t.id==id) {
					exist = true;
					break;
				}
			}
			if(!exist) {
				TankNewMsg tnMsg = new TankNewMsg(tf.myTank);
				tf.nc.send(tnMsg);
				
//				for(int i=0;i<tf.comptanks.size();i++) {
//				Tank t = tf.comptanks.get(i);
//				TankNewMsg enmsg = new TankNewMsg(t);
//				tf.nc.send(enmsg);
//				}
				
				Tank t =new Tank(x,y,tf,good,true,dir);
				t.id = id;
				tf.tanks.add(t);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
