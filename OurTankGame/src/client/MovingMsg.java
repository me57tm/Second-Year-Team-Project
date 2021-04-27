package client;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import physics.Tank;

/**
 * 坦克移动消息协议
 */
public class MovingMsg implements Message {
	private int msgType = Message.TANK_MOVE_MSG;
	private int id;
	private double rotation,three,fifty,bulletMsg;
    //目前是假设TankClient一直没有关闭，但是这里大概率出bug！！！！！！！
	private TankClient tc;

	public MovingMsg(int id,double rotation,double fifty,double bulletMsg) {
		this.id = id;
		this.fifty = fifty;
		this.rotation = rotation;
		this.bulletMsg = bulletMsg;
	}

	public MovingMsg(TankClient tc) {
		this.tc = tc;
	}

	@Override
	public void send(DatagramSocket ds, String IP, int UDP_Port) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream(100);// 指定大小, 免得字节数组扩容占用时间
		DataOutputStream dos = new DataOutputStream(baos);
		try {
			dos.writeInt(msgType);
			dos.writeInt(id);
			dos.writeDouble(rotation);			
			dos.writeDouble(fifty);
			dos.writeDouble(bulletMsg);
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] buf = baos.toByteArray();
		try {
			DatagramPacket dp = new DatagramPacket(buf, buf.length, new InetSocketAddress(IP, UDP_Port));
			ds.send(dp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void parse(DataInputStream dis) {
		try {
			int id = dis.readInt();
			if (id == this.tc.getClientID()) {
				return;
			}
			double rotation = dis.readDouble();
			double fifty = dis.readDouble();
			double bulletMessage = dis.readDouble();
			for (Tank t : tc.getTanks()) {
				if (t.getId() == id) {
					t.setRotation(rotation);
					t.velocity.setAngle(rotation);
					t.velocity.setLength(fifty * 1);
					t.setBulletMsg(bulletMessage);
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
