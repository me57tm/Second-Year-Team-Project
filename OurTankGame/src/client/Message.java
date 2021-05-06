package client;

import java.io.DataInputStream;
import java.net.DatagramSocket;

public interface Message {

    public static final int TANK_MOVE_MSG= 1;
    public static final int TANK_NAME_MSG = 2;
    public static final int DEAD_MSG = 3;


    public void send(DatagramSocket ds, String IP, int UDP_Port);
    public void parse(DataInputStream dis);
}
