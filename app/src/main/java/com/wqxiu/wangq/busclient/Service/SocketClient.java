package com.wqxiu.wangq.busclient.Service;

import android.widget.Button;

import com.wqxiu.wangq.busclient.Entity.Bus;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

public class SocketClient{
    private static Socket socket;
    static String ipaddr = "192.168.43.143";
    public static  void SocketClient() throws IOException {

        try {
            System.out.println("尝试建立Socket连接！！");
            socket =  new Socket(ipaddr, 9050);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public static void PostMsg() throws IOException {

        String p = "客户端发来请求！！";
        if (p.equals("bye")) {
            socket.close();
        }
        // 发送给服务器的数据
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        out.writeUTF(p);




        System.out.println("接收服务器返回数据。。。。");
        // 接收服务器的返回数据

        //System.out.println("server:" + in.readUTF());
        try {
            DataInputStream in = new DataInputStream(socket.getInputStream());
            String tmp = in.readUTF();
            String[] data = tmp.split(" ");
            Date date = new Date();
            System.out.println("Socket接收到的数据：" + tmp +" " + date);
            Bus.tem = data[0];
            Bus.hum = data[1];
            Bus.light = data[2];
            Bus.cardis1 = data[3];
            Bus.cardis2 = data[4];
            Bus.people = data[5];
            Bus.shake = data[6];
            Bus.smoke = data[7];
        } catch (IOException e) {
            e.printStackTrace();
        }


        /*Bus.shake = data[3];
        Bus.smoke = data[4];
        Bus.pir = data[5];
        Bus.cardis1 = data[6];
        Bus.cardis2 = data[7];*/
    }
}
