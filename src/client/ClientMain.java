package client;

import client.ui.ConfigFrame;
import client.ui.LoginFrame;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class ClientMain {

    private static String storedIP = "0";     // 声明为成员变量
    private static int storedPort =0;      // 声明为成员变量
    public static void main(String[] args) {
        //设置外观感觉
        try {
            BeautyEyeLNFHelper.launchBeautyEyeLNF();
        } catch (Exception e) {
            System.out.println("加载炫彩皮肤失败！");
        }
        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        try {
            String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
            UIManager.setLookAndFeel(lookAndFeel);

        } catch (Exception e) {
            e.printStackTrace();
        }

        connection(); //连接到服务器
    }

    /**
     * 连接到服务器
     */
    public static void connection() {

        ConfigFrame configFrame = new ConfigFrame();

        configFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent windowEvent) {
//                storedIP = configFrame.getStoredIP();
//                storedPort = configFrame.getStoredPort();
                storedIP = "127.0.0.1";
                storedPort = 6768;

                // 在这里使用 storedIP 和 storedPort 进行操作，例如建立与服务器的连接等...

                System.out.println("Stored IP in ClientMain: " + storedIP);
                System.out.println("Stored Port in ClientMain: " + storedPort);
                try {
                    DataBuffer.clientSocket = new Socket(storedIP, storedPort);
                    DataBuffer.oos = new ObjectOutputStream(DataBuffer.clientSocket.getOutputStream());
                    DataBuffer.ois = new ObjectInputStream(DataBuffer.clientSocket.getInputStream());
                    System.out.println("你连上服务器啦！！！ " );

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(new JFrame(),
                            "连接服务器失败,请检查!", "服务器未连上", JOptionPane.ERROR_MESSAGE);//否则连接失败
                    System.exit(0);
                }
                new LoginFrame();
            }
        });
    }
}
