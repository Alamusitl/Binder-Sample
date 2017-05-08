package com.owl.binder.socket;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class TCPServerService extends Service {

    private boolean mIsServiceDestroyed = false;
    private String[] mDefineMessages = new String[]{"你好啊，哈哈", "请问你叫什么名字？",
            "今天北京天气不错啊，shy", "你知道吗？我可是可以和多个人同事聊天的哦",
            "给你讲个笑话吧：据说爱笑的人运气不会太差，不知道真假。"};

    public TCPServerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        new Thread(new TcpServer()).start();
    }

    @Override
    public void onDestroy() {
        mIsServiceDestroyed = true;
        super.onDestroy();
    }

    private void responseClient(Socket client) throws IOException {
        // 用于接受客户端消息
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        // 用于向客户端发送消息
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())), true);
        System.out.println("欢迎来到聊天室");
        while (!mIsServiceDestroyed) {
            String str = in.readLine();
            System.out.println("msg from client:" + str);
            if (str == null) {
                // 与客户端断开连接
                break;
            }
            int i = new Random().nextInt(mDefineMessages.length);
            String msg = mDefineMessages[i];
            System.out.println(msg);
            System.out.println("send : " + msg);
            System.out.println("client quit.");
            out.close();
            in.close();
            client.close();
        }
    }

    private class TcpServer implements Runnable {
        @Override
        public void run() {
            ServerSocket serverSocket;
            try {
                // 监听本地8688端口
                serverSocket = new ServerSocket(8688);
            } catch (IOException e) {
                System.out.println("establish tcp server failed, port:8688");
                e.printStackTrace();
                return;
            }
            while (!mIsServiceDestroyed) {
                try {
                    // 接受客户端请求
                    final Socket client = serverSocket.accept();
                    System.out.println("accept");
                    new Thread() {
                        @Override
                        public void run() {
                            try {
                                responseClient(client);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
