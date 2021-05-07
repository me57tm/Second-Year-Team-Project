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
	private int id, compass, up, down;
	private double fire;
	private TankClient tc;

	public MovingMsg(int id, int compass, int up, int down, double fire) {
		this.id = id;
		this.compass = compass;
		this.up = up;
		this.down = down;
		this.fire = fire;
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
			dos.writeInt(compass);
			dos.writeInt(up);
			dos.writeInt(down);
			dos.writeDouble(fire);
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
			int compass = dis.readInt();
			int up = dis.readInt();
			int down = dis.readInt();
			double fire = dis.readDouble();
			for (Tank t : tc.getTanks()) {
				if (t.getId() == id) {
					if (compass == 1) {
						t.setRotation((t.getRotation() - 3));
						t.setCompass(0);
					}
					if (compass == -1) {
						t.setRotation((t.getRotation() + 3));
						t.setCompass(0);
					}
					if (up == 1) {
						t.velocity.setAngle(t.getRotation());
						t.velocity.setLength(60 * t.getSpeedModifier());
						t.setUp(0);
					}
					if (down == 1) {
						t.velocity.setAngle(t.getRotation());
						t.velocity.setLength(-60 * t.getSpeedModifier());
						t.setDown(0);
					}
					if (up == 0 && down ==0 ) {
						t.velocity.setLength(0);
					}
					if(fire == 1) {
						t.setBulletMsg(1);
						t.setFire(0);
					}
					t.update(1 / 60.0, t.getMap());

					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
