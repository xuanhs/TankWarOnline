package ex3;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * 
 * �ڲ���ӯ��ΪĿ�ĵ�����£����Դ���
 * @�޸��� wzy
 * 
 * NPC̹����������Ϣ
 * ֻ��int msgType = Msg.TANK_NPC_DEAD_MSG;��̹��������Ϣ��һ��
 * �����ܲ��ܸ���
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
	 * ������ص���Ϣ
	 * @param ds ͨ����socket��������
	 * @param IP ���ݵ�Ŀ��IP
	 * @param udpPort ���ݵ�Ŀ��˿�
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
	 * �������յ�����Ϣ����
	 * @param dis ���յ�����Ϣ���ݵ�������
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

