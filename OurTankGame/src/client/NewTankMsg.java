//package client;
//
//import java.io.ByteArrayOutputStream;
//import java.io.DataInputStream;
//import java.io.DataOutputStream;
//import java.io.IOException;
//import java.net.DatagramPacket;
//import java.net.DatagramSocket;
//import java.net.InetSocketAddress;
//import physics.Tank;
//
//public class NewTankMsg implements Message {
//	private int msgType = Message.TANK_NEW_MSG;
//	private Tank tank;
//	private TankClient tc;
//
//	public NewTankMsg(Tank tank) {
//		this.tank = tank;
//	}
//
//	public NewTankMsg(TankClient tc) {
//		this.tc = tc;
//	}
//
//	public void send(DatagramSocket ds, String IP, int UDP_Port) {
//		ByteArrayOutputStream baos = new ByteArrayOutputStream(100);
//		DataOutputStream dos = new DataOutputStream(baos);
//		try {
//			dos.writeInt(msgType);
//			dos.writeInt(tank.getId());
//			dos.writeDouble(tank.getX());
//			dos.writeDouble(tank.getY());
//			// 可能需要添加velocity
//			dos.writeBoolean(tank.isGood());
//			dos.writeUTF(tank.getName());
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		byte[] buf = baos.toByteArray();
//		try {
//			DatagramPacket dp = new DatagramPacket(buf, buf.length, new InetSocketAddress(IP, UDP_Port));
//			ds.send(dp);
//			// System.out.println("开始了");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void parse(DataInputStream dis) {
//		try {
//			int id = dis.readInt();
//			// System.out.println(this.tc.getMyTank().getId());
//			if (id == this.tc.getMyTank().getId()) {
//				tc.giveID(id);
//				return;
//			}
//			double x = dis.readDouble();
//			double y = dis.readDouble();
//			boolean good = dis.readBoolean();
//			// String tankName = dis.readUTF();
//			Tank newTank = new Tank("heihei", x, y, good, "grimfandango-art/tank-red.png");
//			newTank.setId(id);
//			tc.getTanks().add(newTank);
//
//			ExistMsg msg = new ExistMsg(tc.getMyTank());
//			tc.getNc().send(msg);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//}
