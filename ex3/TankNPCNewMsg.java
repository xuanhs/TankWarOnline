package ex3;


import java.io.ByteArrayOutputStream;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

/**
 * 在不以盈利为目的的情况下，可以传播
 * @修改人 wzy
 * */
//NPC坦克产生的消息，需要告诉所有人，NPC坦克来了，屏幕赶紧更新
//int msgType = Msg.TANK_NEW_NPC_MSG;
//只有msgType不一样，其他内容跟坦克产生是一样的，考虑是不是可以复用
public class TankNPCNewMsg implements Msg {
	int msgType = Msg.TANK_NEW_NPC_MSG;

	Tank tank;

	TankClient tf;
	
	public TankNPCNewMsg(Tank tank) {
		this.tank = tank;
	}
	public TankNPCNewMsg(TankClient tf) {
		this.tf = tf;
	}

	@Override
	public void send(DatagramSocket ds, String IP, int udpPort) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		try {
			dos.writeInt(msgType);
			dos.writeInt(tank.id);
			dos.writeInt(tank.x);
			dos.writeInt(tank.y);
			dos.writeInt(tank.dir.ordinal());
			dos.writeBoolean(tank.good);
			dos.writeBoolean(tank.live);
			dos.writeBoolean(tank.isNPC);
	//		dos.writeInt(tank.life);
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] buf = baos.toByteArray();
		try {
			DatagramPacket dp = new DatagramPacket(buf, buf.length,
					new InetSocketAddress(IP, udpPort));
			//System.out.println("发送数据--msgType:"+msgType+",id:"+tank.id+",tank.x:"+tank.x+",tank.y"+tank.y);
			ds.send(dp);
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void parse(DataInputStream dis) {
		try {
			int id = dis.readInt();
			//System.out.println("read tank id:"+id);
			//如果是自己，则不需要重新添加
			if (tf.myTank.id == id) {
				return;
			}
			int x = dis.readInt();
			int y = dis.readInt();
			Dir dir = Dir.values()[dis.readInt()];
			boolean good = dis.readBoolean();
			boolean live = dis.readBoolean();
			boolean isNPC = dis.readBoolean();
		//	int life = dis.readInt();
			byte[] c2 = new byte[1024];
			int len = dis.read(c2);
			String gettankname ="";
			if(len>0){
				gettankname = new String(c2,0,len);
			}

			//System.out.println("id:" + id + "-x:" + x + "-y:" + y + "-dir:" + dir + "-good:" + good);
			boolean exist = false;
			for (int i = 0; i < tf.tanks.size(); i++) {
				Tank t = tf.tanks.get(i);
				if (t.id == id && live) {
					exist = true;
					break;
				}
			}

			if (!exist) {
				
				
				TankNewMsg tnMsg = new TankNewMsg(tf.myTank);
				tf.nc.send(tnMsg);

				Tank t = new Tank(x,y,tf,good,true,dir,isNPC);
				t.id = id;
				if(live){
					//System.out.println(t.isNPC);
					tf.tanks.add(t);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

