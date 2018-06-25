package ex3;

import java.io.DataInputStream;
import java.net.DatagramSocket;

public interface Msg {
	public static final int TANK_NEW_MSG = 1; /*消息类型*/
	public static final int TANK_MOVE_MSG = 2;
	public static final int MISSILE_NEW_MSG = 3;
	public static final int TANK_DEAD_MSG = 4;
	public static final int MISSILE_DEAD_MSG = 5;
	public static final int TANK_NPC_DEAD_MSG = 6;
	public static final int TANK_NEW_NPC_MSG = 7;
	public static final int WALL_NEW_MSG = 8;
	public static final int Blood_New_Msg = 9;
	public void send(DatagramSocket ds, String IP,int udpPort);
	public void parse(DataInputStream dis);
}
