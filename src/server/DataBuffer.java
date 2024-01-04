package server;

import common.model.entity.Message;
import common.model.entity.User;
import common.util.RecordUtil;
import server.model.entity.OnlineUserTableModel;
import server.model.entity.RegistedUserTableModel;

import java.awt.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListMap;

public class DataBuffer {
    // 服务器端套接字
    public static ServerSocket serverSocket;
    // 在线用户的IO Map
    public static Map<Long, OnlineClientIOCache> onlineUserIOCacheMap;
    // 在线用户Map
    public static Map<Long, User> onlineUsersMap;
    // 服务器配置参数属性集
    public static Properties configProp;
    // 已注册用户表的Model
    public static RegistedUserTableModel registedUserTableModel;
    // 当前在线用户表的Model
    public static OnlineUserTableModel onlineUserTableModel;
    // 当前服务器所在系统的屏幕尺寸
    public static Dimension screenSize;
    // 消息Map，用于存储用户发送的消息
    public static Map<Long, List<Message>> messageMap;
    public static Set<Long> recordIds;

    static {
        // 初始化
        onlineUserIOCacheMap = new ConcurrentSkipListMap<Long, OnlineClientIOCache>();
        onlineUsersMap = new ConcurrentSkipListMap<Long, User>();
        configProp = new Properties();
        registedUserTableModel = new RegistedUserTableModel();
        onlineUserTableModel = new OnlineUserTableModel();
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        messageMap = new ConcurrentSkipListMap<Long, List<Message>>();
        try {
            recordIds = RecordUtil.getRecordIds();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // 加载服务器配置文件
        try {
            configProp.load(Thread.currentThread()
                    .getContextClassLoader()
                    .getResourceAsStream("serverconfig.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

