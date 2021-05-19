package client;

import java.io.DataInputStream;
import java.net.DatagramSocket;
/**
 * Network protocol interface,achieve polymorphism
 */
public interface Message {

    public static final int MOVEING_MSG= 100;
    public static final int TANK_NAME_MSG = 101;
    public static final int DEAD_MSG = 102;
    
    
    public void sendToServer(DatagramSocket ds, String IP, int UDP_Port);
    public void dealFromServer(DataInputStream dis);
}
