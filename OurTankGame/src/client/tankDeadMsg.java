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
 * Confirmation of tank death information
 */
public class tankDeadMsg implements Message {
	private int msgType = Message.DEAD_MSG;
	private int id,dead;
	private TankClient tc;

	public tankDeadMsg(int id, int dead) {
		this.id = id;
		this.dead = dead;
	}

	public tankDeadMsg(TankClient tc) {
		this.tc = tc;
	}

	@Override
	public void send(DatagramSocket ds, String IP, int UDP_Port) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream(100);
		DataOutputStream dos = new DataOutputStream(baos);
		try {
			dos.writeInt(msgType);
			dos.writeInt(id);
			dos.writeInt(dead);

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
			int dead = dis.readInt();
			for (Tank t : tc.getTanks()) {
				if (t.getId() == id) {
					t.setDead(dead);
					break;
				}
			
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
