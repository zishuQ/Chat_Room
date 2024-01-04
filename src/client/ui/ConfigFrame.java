package client.ui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ConfigFrame extends JFrame {
    private JTextField ipTxt;
    private JTextField portTxt;
    private JButton submitBtn;  // 添加提交按钮属性

    private String storedIP = "0";  // 使用默认值
    private int storedPort = 0;           // 使用默认值

    public ConfigFrame() {

        init();
        setVisible(true);
    }

    private void init() {
        // 初始化UI组件和布局...
        this.setTitle("连接服务器");
        this.setSize(430, 350);
        //设置默认窗体在屏幕中央
        int x = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        int y = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((x - this.getWidth()) / 2, (y - this.getHeight()) / 2);
        this.setResizable(false);

        //把Logo放置到JFrame的北边
        Icon icon = new ImageIcon("images/logo.png");
        JLabel label = new JLabel(icon);
        label.setPreferredSize(new Dimension(430, 150));
        this.add(label, BorderLayout.NORTH);

        //登录信息面板
        JPanel mainPanel = new JPanel();
        Border border = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        mainPanel.setBorder(BorderFactory.createTitledBorder(border, "~请输入你的服务器信息~", TitledBorder.CENTER, TitledBorder.TOP));
        this.add(mainPanel, BorderLayout.CENTER);
        mainPanel.setLayout(null);

        JLabel ipLbl = new JLabel("服务器IP:");
        ipLbl.setBounds(100, 60, 80, 22);
        mainPanel.add(ipLbl);
        ipTxt = new JTextField();
        ipTxt.setBounds(155, 60, 140, 22);
        mainPanel.add(ipTxt);

        JLabel portLbl = new JLabel("端口:");
        portLbl.setBounds(100, 90, 40, 22);  // 调整这里的y坐标
        mainPanel.add(portLbl);
        portTxt = new JTextField();
        portTxt.setBounds(155, 90, 140, 22);  // 调整这里的y坐标
        mainPanel.add(portTxt);

        submitBtn = new JButton("提交");
        submitBtn.setBounds(170, 130, 80, 30); // 调整按钮的位置和大小
        mainPanel.add(submitBtn);  // 添加按钮到主面板

        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 获取IP和端口
                String ip = ipTxt.getText();
                String port = portTxt.getText();

                // 更新存储的IP和端口
                storedIP = ip;
                try {
                    storedPort = Integer.parseInt(port);
                } catch (NumberFormatException ex) {
                    System.err.println("Invalid port number: " + port);
                }

                // 执行窗口的关闭操作
                ConfigFrame.this.dispose();  // 关闭当前窗口
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // 执行您想在窗口关闭时执行的操作
                System.out.println("窗口正在关闭...");
                // 可以选择关闭程序或其他操作
                System.exit(0);
            }
        });
    }

    public String getStoredIP() {
        return storedIP;
    }

    public int getStoredPort() {
        return storedPort;
    }

}
