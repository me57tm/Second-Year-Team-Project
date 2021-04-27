package server;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
/**
 * 服务器端
 */

public class TankServer /*extends Frame*/ {

    public static int ID = 100;//id号的初始序列
    public static final int tcpPort= 55555;//TCP端口号
    public static final int udpPort = 55556;//转发客户端数据的UDP端口号
    public static final int endUdpPort = 55557;//接收客户端坦克死亡的端口号
    private List<Client> clients = new ArrayList<>();//客户端集合


    public static void main(String[] args) {
        TankServer ts = new TankServer();
        ts.start();
    }

	@SuppressWarnings("resource")
	public void start(){
        new Thread(new OurUDPThread()).start();
        new Thread(new TankDeadUDPThread()).start();
        ServerSocket ss = null;
        try {
            ss = new ServerSocket(tcpPort);//在TCP欢迎套接字上监听客户端连接
            //System.out.println("TankServer has started...");
        } catch (IOException e) {
            e.printStackTrace();
        }

        while(true){
            Socket s = null;
            try {
                s = ss.accept();//给客户但分配专属TCP套接字
                
                //System.out.println("A client has connected...");
                DataInputStream dis = new DataInputStream(s.getInputStream());
                int UDP_PORT = dis.readInt();//记录客户端UDP端口
                Client client = new Client(s.getInetAddress().getHostAddress(), UDP_PORT, ID);//创建Client对象
                clients.add(client);//添加进客户端容器
                
                 System.out.println("id : " + client.id + " - IP : " + client.IP);
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());
                dos.writeInt(ID++);//向客户端分配id号
                dos.writeInt(TankServer.udpPort);//告诉客户端自己的UDP端口号
                dos.writeInt(TankServer.endUdpPort);
            }catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    if(s != null) s.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class OurUDPThread implements Runnable{

        byte[] buf = new byte[1024];

        @Override
        public void run() {
            DatagramSocket ds = null;
            try{
                ds = new DatagramSocket(udpPort);
                
            }catch (SocketException e) {
                e.printStackTrace();
            }

            while (null != ds){
                DatagramPacket dp = new DatagramPacket(buf, buf.length);
                try {
                    ds.receive(dp);
                    
                    for (Client c : clients){
                        dp.setSocketAddress(new InetSocketAddress(c.IP, c.udpPort));
                        ds.send(dp);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 监听坦克死亡的UDP线程
     */
    private class TankDeadUDPThread implements Runnable{
        byte[] buf = new byte[300];
        @Override
        public void run() {
            DatagramSocket ds = null;
            try{
                ds = new DatagramSocket(endUdpPort);
            } catch (SocketException e) {
                e.printStackTrace();
            }
            while(null != ds){
                DatagramPacket dp = new DatagramPacket(buf, buf.length);
                ByteArrayInputStream bais = null;
                DataInputStream dis = null;
                try{
                    ds.receive(dp);
                    bais = new ByteArrayInputStream(buf, 0, dp.getLength());
                    dis = new DataInputStream(bais);
                    int deadTankUDPPort = dis.readInt();
                    for(int i = 0; i < clients.size(); i++){
                        Client c = clients.get(i);
                        if(c.udpPort == deadTankUDPPort){
                            clients.remove(c);
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if (null != dis){
                        try {
                            dis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if(null != bais){
                        try {
                            bais.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public class Client{
        String IP;
        int udpPort,id;


        public Client(String ipAddr, int UDP_PORT, int id) {
            this.IP = ipAddr;
            this.udpPort = UDP_PORT;
            this.id = id;
        }
    }
 }

