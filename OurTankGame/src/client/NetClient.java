package client;

import server.TankServer;
import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;

/**
 * Network method collection
 */
public class NetClient {
	private TankClient tc;
	private int UDP_PORT;// UDP port number of the client
	private String serverIP;// Server IP address
	private int serverUDPPort;// The UDP port where the server forwards the client but the UDP
	private DatagramSocket ds = null;// Client's UDP socket

	public void setUDP_PORT(int UDP_PORT) {
		this.UDP_PORT = UDP_PORT;
	}

	public NetClient(TankClient tc) {
		this.tc = tc;
		try {
			this.UDP_PORT = getRandomUDPPort();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * The client randomly obtains the UDP port number 
	 */
	private int getRandomUDPPort() {
		return (int) (Math.random() * 2000);
	}
	/**
	 * Interface replication
	 * @param msg Message type.
	 */
	public void send(Message msg) {
		msg.sendToServer(ds, serverIP, serverUDPPort);
	}
	/**
	 * TCP connection with server
	 * 
	 * @param ip server IP
	 */
	public void connectToServer(String ip) {
		serverIP = ip;
		Socket s = null;
		try {
			ds = new DatagramSocket(UDP_PORT);// Create UDP socket
			try {
				s = new Socket(ip, TankServer.tcpPort);// Create TCP socket
			} catch (Exception e) {
				e.printStackTrace();
			}
			DataOutputStream dos = new DataOutputStream(s.getOutputStream());
			dos.writeInt(UDP_PORT);// Send your own UDP port number to the server
			DataInputStream dis = new DataInputStream(s.getInputStream());
			int id = dis.readInt();// Get your own id number
			this.serverUDPPort = dis.readInt();// Obtain the UDP port number used by the server to forward client message
			tc.clientID(id);// Set the id number of the tank
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (s != null)
					s.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		new Thread(new OurUDPThread()).start();// Open the client UDP thread to send or receive game data to the server

	}


	/**
	 * UDP thread is used for game data exchange
	 */
	public class OurUDPThread implements Runnable {

		byte[] buf = new byte[1024];

		@Override
		public void run() {
			while (null != ds) {
				DatagramPacket dp = new DatagramPacket(buf, buf.length);
				try {
					ds.receive(dp);
					handleMessage(dp);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		/**
		 * Determine the type of data received from the server.
		 */
		private void handleMessage(DatagramPacket dp) {
			ByteArrayInputStream bais = new ByteArrayInputStream(buf, 0, dp.getLength());
			DataInputStream dis = new DataInputStream(bais);
			int msgType = 0;
			try {
				msgType = dis.readInt();// Get message type
			} catch (IOException e) {
				e.printStackTrace();
			}
			Message msg = null;
			switch (msgType) {// Call the analysis method of the corresponding message according to the type of the message

			case Message.MOVEING_MSG:
				msg = new MovingMsg(tc);
				msg.dealFromServer(dis);
				break;
			case Message.TANK_NAME_MSG:
				msg = new tankNameMsg(tc);
				msg.dealFromServer(dis);
				break;
			case Message.DEAD_MSG:
				msg = new tankDeadMsg(tc);
				msg.dealFromServer(dis);
				break;

			}
		}
	}

}
