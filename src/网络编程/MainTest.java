package 网络编程;

import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Scanner;

public class MainTest {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("请输入聊天服务当前启动的端口:");
        int serverPort = input.nextInt();
        System.out.print("请输入聊天服务发送信息对象的端口:");
        int targetPort = input.nextInt();
        System.out.println("聊天系统初始化完毕!");
        try {
            //创建聊天对象的收发平台对象
            DatagramSocket socket = new DatagramSocket(serverPort);
            //分别启动俩个线程对象
            new Thread(new checkSend(socket, targetPort), "发送服务").start();
            new Thread(new checkRecever(socket), "接收服务").start();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
}