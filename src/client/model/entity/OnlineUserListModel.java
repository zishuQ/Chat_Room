package client.model.entity;

import java.util.List;

import javax.swing.AbstractListModel;

import common.model.entity.User;

/**
 * 在线用户列表模型
 */
public class OnlineUserListModel extends AbstractListModel {
    private static final long serialVersionUID = -3903760573171074301L;
    private List<User> onlineUsers;

    /**
     * 构造方法
     * @param onlineUsers 在线用户列表
     */
    public OnlineUserListModel(List<User> onlineUsers) {
        this.onlineUsers = onlineUsers;
    }

    /**
     * 添加元素到在线用户列表
     * @param object 要添加的对象
     */
    public void addElement(Object object) {
        if (onlineUsers.contains(object)) {
            return;
        }
        int index = onlineUsers.size();
        onlineUsers.add((User)object);
        fireIntervalAdded(this, index, index);
    }

    /**
     * 从在线用户列表中移除元素
     * @param object 要移除的对象
     * @return 如果成功移除，返回true；否则返回false
     */
    public boolean removeElement(Object object) {
        int index = onlineUsers.indexOf(object);
        if (index >= 0) {
            fireIntervalRemoved(this, index, index);
        }
        return onlineUsers.remove(object);
    }

    /**
     * 获取在线用户列表的大小
     * @return 在线用户列表的大小
     */
    public int getSize() {
        return onlineUsers.size();
    }

    /**
     * 根据索引获取在线用户列表的元素
     * @param i 索引
     * @return 在线用户列表的元素
     */
    public Object getElementAt(int i) {
        return onlineUsers.get(i);
    }

    /**
     * 获取在线用户列表
     * @return 在线用户列表
     */
    public List<User> getOnlineUsers() {
        return onlineUsers;
    }
}

