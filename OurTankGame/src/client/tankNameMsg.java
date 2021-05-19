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
 * Tank name synchronization protocol when starting the game
 */
public class tankNameMsg implements Message {
	private int msgType = Message.TANK_NAME_MSG;
	private int id;
	private String name;
	private TankClient tc;

	public tankNameMsg(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public tankNameMsg(TankClient tc) {
		this.tc = tc;
	}

	@Override
	public void sendToServer (DatagramSocket ds, String IP, int UDP_Port) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream(100);
		DataOutputStream dos = new DataOutputStream(baos);
		try {
			dos.writeInt(msgType);
			dos.writeInt(id);
			dos.writeUTF(name);

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
	public void dealFromServer(DataInputStream dis) {

		try {
			int id = dis.readInt();
			String name = dis.readUTF();
			for (Tank t : tc.getTanks()) {
				if (t.getId() == id) {
					t.setName(name);
					break;
				}
			
			}
			if (this.id < id) {
				tankNameMsg msg = new tankNameMsg(id, name);
				tc.getNc().send(msg);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
