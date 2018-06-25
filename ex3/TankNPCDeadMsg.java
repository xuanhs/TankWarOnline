package ex3;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * 
 * 在不以盈利为目的的情况下，可以传播
 * @修改人 wzy
 * 
 * NPC坦克死亡的消息
 * 只有int msgType = Msg.TANK_NPC_DEAD_MSG;跟坦克死亡消息不一样
 * 考虑能不能复用
 * */
public class TankNPCDeadMsg implements Msg {
	int msgType = Msg.TANK_NPC_DEAD_MSG;
	
	public int id;
	
	public TankClient tf;
	
	public TankNPCDeadMsg(int id) {
		this.id =id;
	}

	public TankNPCDeadMsg(TankClient tf) {
		this.tf = tf;
	}
	/**
	 * 发送相关的消息
	 * @param ds 通过该socket发送数据
	 * @param IP 数据的目标IP
	 * @param udpPort 数据的目标端口
	 */
	public void send(DatagramSocket ds, String IP, int udpPort) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		try {
			dos.writeInt(msgType);
			dos.writeInt(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		byte[] buf = baos.toByteArray();
		try {
			DatagramPacket dp = new DatagramPacket(buf, buf.length,
					new InetSocketAddress(IP, udpPort));
			ds.send(dp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 分析接收到的消息数据
	 * @param dis 接收到的消息数据的输入流
	 */
	public void parse(DataInputStream dis) {
		try {
			int id = dis.readInt();
			if (tf.myTank.id == id) {
				return;
			}
			for (int i = 0; i < tf.tanks.size(); i++) {
				Tank t = tf.tanks.get(i);
				if (t.id == id) {
					t.setLive(false);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

