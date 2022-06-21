package 网络编程;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

//实现Runnable接口
public class checkSend implements Runnable {
    private DatagramSocket socket;
    private int targetPort;

    public checkSend(DatagramSocket socket, int targetPort) {
        this.socket = socket;
        this.targetPort = targetPort;
    }

    @Override
    public void run() {
        try {
            //输入要传送的信息
            Scanner input = new Scanner(System.in);
            while (true) {
                String data = input.nextLine();
                //转换输入信息的数据类型
                byte[] bytes = data.getBytes();
                //对数据进行包装
                DatagramPacket packet = new DatagramPacket(bytes, bytes.length, InetAddress.getByName("127.0.0.1"), targetPort);
                //发送信息
                socket.send(packet);
            }
        } catch (UnknownHostException e) {
        } catch (IOException e) {
        }
    }
}