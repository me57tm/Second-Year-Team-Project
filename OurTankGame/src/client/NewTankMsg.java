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
//import tankUI.Auto_window;
//
///**
// * 旧坦克向新坦克发送消息的协议
// */
//public class NewTankMsg implements Message {
//	private int msgType = Message.TANK_NEW_MSG;
//	private int id;
//	private String name;
//	private TankClient tc;
//
//	public NewTankMsg(int id) {
//		this.id = id;
//
//	}
//
//	public NewTankMsg(TankClient tc) {
//		this.tc = tc;
//	}
//
//	@Override
//	public void send(DatagramSocket ds, String IP, int UDP_Port) {
//		ByteArrayOutputStream baos = new ByteArrayOutputStream(100);
//		DataOutputStream dos = new DataOutputStream(baos);
//		try {
//			dos.writeInt(msgType);
//			dos.writeInt(id);
//
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		byte[] buf = baos.toByteArray();
//		try {
//			DatagramPacket dp = new DatagramPacket(buf, buf.length, new InetSocketAddress(IP, UDP_Port));
//			ds.send(dp);
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
//
//			if (this.id < id) {
////		    new Thread(new WindowThread()).start();
//		    Auto_window a1 = new Auto_window(2200, "Rest players have been in the game", new String("Tips"), false);
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//}
////    class WindowThread implements Runnable{
////
////		@Override
////		public void run() {
////			Auto_window a1 = new Auto_window(2200, "Rest players have been in the game", new String("Tips"), false);
////		}
////    }
//    
