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
///**
// * 旧坦克向新坦克发送消息的协议
// */
//public class ExistMsg implements Message {
//	private int msgType = Message.TANK_ALREADY_EXIST_MSG;
//	private Tank tank;
//	private TankClient tc;
//
//	public ExistMsg(Tank tank) {
//		this.tank = tank;
//	}
//
//	public ExistMsg(TankClient tc) {
//		this.tc = tc;
//	}
//
//	@Override
//	public void send(DatagramSocket ds, String IP, int UDP_Port) {
//		ByteArrayOutputStream baos = new ByteArrayOutputStream(88);
//		DataOutputStream dos = new DataOutputStream(baos);
//		try {
//			dos.writeInt(msgType);
//			dos.writeInt(tank.getId());
//			dos.writeDouble(tank.getX());
//			dos.writeDouble(tank.getY());
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
//			// System.out.println("有坦克了send");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	@Override
//	public void parse(DataInputStream dis) {
//
//		try {
//			int id = dis.readInt();
//			if (id == tc.getMyTank().getId()) {
//				tc.giveID(id);
//				return;
//			}
//			boolean exist = false;
//			for (Tank t : tc.getTanks()) {
//				if (id == t.getId()) {
//					exist = true;
//					break;
//				}
//			}
//			if (!exist) {
//				double x = dis.readDouble();
//				double y = dis.readDouble();
//				boolean good = dis.readBoolean();
//
//				Tank existTank = new Tank("heihei", x, y, good, "grimfandango-art/tank-red.png");
//				existTank.setId(id);
//				tc.getTanks().add(existTank);
//			}
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//}
