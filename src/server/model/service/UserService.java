package server.model.service;

import common.model.entity.User;
import common.util.IOUtil;
import server.DataBuffer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class UserService {
    private static int idCount = 0; //id

    /**
     * 新增用户
     */
    public void addUser(User user) {
        List<User> users = loadAllUser();
        idCount = users.size();
        user.setId(++idCount);
        users.add(user);
        saveAllUser(users);
    }

    /**
     * 用户登录
     */
    public User login(long id, String password) {
        User result = null;
        List<User> users = loadAllUser();
        for (User user : users) {
            if (id == user.getId() && password.equals(user.getPassword())) {
                result = user;
                break;
            }
        }
        return result;
    }

    /**
     * 根据ID加载用户
     */
    public User loadUser(long id) {
        User result = null;
        List<User> users = loadAllUser();
        for (User user : users) {
            if (id == user.getId()) {
                result = user;
                break;
            }
        }
        return result;
    }


    /**
     * 加载所有用户
     */
    @SuppressWarnings("unchecked")
    public List<User> loadAllUser() {
        List<User> list = null;
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(Files.newInputStream(Paths.get(DataBuffer.configProp.getProperty("dbpath"))));
            list = (List<User>) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtil.close(ois);
        }
        return list == null ? new ArrayList<>() : list;
    }

    private void saveAllUser(List<User> users) {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(Files.newOutputStream(Paths.get(DataBuffer.configProp.getProperty("dbpath"))));
            //写回用户信息
            oos.writeObject(users);
            oos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtil.close(oos);
        }
    }


    /**
     * 初始化几个测试用户
     */
    public void initUser() {
        List<User> users = new CopyOnWriteArrayList<>();
        this.saveAllUser(users);
    }

    public static void main(String[] args) {
        new UserService().initUser();
        List<User> users = new UserService().loadAllUser();
        for (User user : users) {
            System.out.println(user);
        }
    }
}
