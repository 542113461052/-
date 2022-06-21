package 网络编程;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class checkRecever implements Runnable {
    private DatagramSocket socket;

    public checkRecever(DatagramSocket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        try{
            byte [] bytes = new byte[1024];
            DatagramPacket packet = new DatagramPacket(bytes,bytes.length);
            while (true){
                //接收传过来的信息
                socket.receive(packet);
                String str = new String(packet.getData(),0,packet.getLength());
                System.out.println(str);
            }
        }catch (Exception e){
        }
    }
}