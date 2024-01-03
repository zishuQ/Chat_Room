package server;

import common.model.entity.Message;
import common.model.entity.User;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import org.omg.PortableInterceptor.LOCATION_FORWARD;
import server.controller.RequestProcessor;
import server.ui.ServerInfoFrame;

import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Map;

public class MainServer {

    public static void main(String[] args) throws Exception {
        // 启动BeautyEye外观主题
        BeautyEyeLNFHelper.launchBeautyEyeLNF();

        // 获取服务器端口号
        int port = Integer.parseInt(DataBuffer.configProp.getProperty("port"));

        // 初始化服务器套接字
        DataBuffer.serverSocket = new ServerSocket(port);

        // 启动新线程进行客户端连接监听
        new Thread (new Runnable() {
            public void run() {
                while (true) {
                    // 监听客户端的连接
                    Socket socket = null;
                    try {
                        socket = DataBuffer.serverSocket.accept();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    // 打印客户端地址和端口号
                    System.out.println("客户来了：" + socket.getInetAddress().getHostAddress() + ":" + socket.getPort());

                    // 针对每个客户端启动一个线程，在线程中调用请求处理器来处理每个客户端的请求
                    new Thread(new RequestProcessor(socket)).start();
                }
            }
        }).start();

        // 设置外观感觉
        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        try {
            // 获取系统外观主题类名
            String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
            // 设置外观主题
            UIManager.setLookAndFeel(lookAndFeel);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 启动服务器监控窗体
        new ServerInfoFrame();
    }
}

