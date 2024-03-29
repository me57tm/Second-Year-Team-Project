package server;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Service-Terminal.
 * Create a network link to the server for PVP mode
 */

public class TankServer {

	public static int ID = 100;// TankClient ID and tank ID
	public static final int tcpPort = 28965;// TCP port number
	public static final int udpPort = 28966;// UDP port number for forwarding client data
	private List<Client> clients = new ArrayList<>();// Client collection


	@SuppressWarnings("resource")
	public void start() {
		new Thread(new OurUDPThread()).start();
		ServerSocket ss = null;
		try {
			ss = new ServerSocket(tcpPort);// Listen for client connections on TCP sockets
		} catch (IOException e) {
			e.printStackTrace();
		}

		while (true) {
			Socket s = null;
			try {
				s = ss.accept();// Assign dedicated TCP sockets to customers

				// System.out.println("A client has connected...");
				DataInputStream dis = new DataInputStream(s.getInputStream());
				int UDP_PORT = dis.readInt();// Record the client UDP port
				Client client = new Client(s.getInetAddress().getHostAddress(), UDP_PORT, ID);
				if(clients.size() < 2) {
				clients.add(client);// Add to client container
				}
//				System.out.println("id : " + client.id + " - IP : " + client.IP);
				DataOutputStream dos = new DataOutputStream(s.getOutputStream());
				dos.writeInt(ID++);// Assign an id number to the client
				dos.writeInt(TankServer.udpPort);// Tell the client server's own UDP port number
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
		}
	}
	/**
	 * UDP thread is used for real-time game data exchange
	 */
	private class OurUDPThread implements Runnable {

		byte[] buf = new byte[1024];

		@Override
		public void run() {
			DatagramSocket ds = null;
			try {
				ds = new DatagramSocket(udpPort);

			} catch (SocketException e) {
				e.printStackTrace();
			}

			while (null != ds) {
				DatagramPacket dp = new DatagramPacket(buf, buf.length);
				try {
					ds.receive(dp);
					for (int i = 0; i < clients.size();i++) {
						dp.setSocketAddress(new InetSocketAddress(clients.get(i).IP, clients.get(i).udpPort));
						ds.send(dp);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 * Create user objects to facilitate parameter passing
	 */

	public class Client {
		String IP;
		int udpPort, id;

		public Client(String ipAddr, int UDP_PORT, int id) {
			this.IP = ipAddr;
			this.udpPort = UDP_PORT;
			this.id = id;
		}
	}
}
