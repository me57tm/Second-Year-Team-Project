package client;

import java.io.DataInputStream;
import java.net.DatagramSocket;

public interface Message {
    public static final int TANK_NEW_MSG = 1;
    public static final int TANK_MOVE_MSG= 2;
    public static final int TANK_NAME_MSG = 3;
    public static final int DEAD_MSG = 4;
    public static final int MISSILE_DEAD_MSG = 5;
    public static final int TANK_ALREADY_EXIST_MSG = 6;
    public static final int TANK_REDUCE_BLOOD_MSG = 7;

    public void send(DatagramSocket ds, String IP, int UDP_Port);
    public void parse(DataInputStream dis);
}
