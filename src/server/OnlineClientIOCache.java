package server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class OnlineClientIOCache {
    // 针对同一个Socket中获取的流在全局范围中最好只包装一次，以免出错
    private ObjectInputStream ois; // 对象输入流
    private ObjectOutputStream oos; // 对象输出流

    /**
     * 构造方法，接收ObjectInputStream和ObjectOutputStream参数进行初始化
     * @param ois ObjectInputStream对象输入流
     * @param oos ObjectOutputStream对象输出流
     */
    public OnlineClientIOCache(ObjectInputStream ois, ObjectOutputStream oos){
        this.ois = ois;
        this.oos = oos;
    }

    /**
     * 获取ObjectOutputStream对象输出流
     * @return ObjectOutputStream对象输出流
     */
    public ObjectOutputStream getOos(){
        return oos;
    }

    /**
     * 获取ObjectInputStream对象输入流
     * @return ObjectInputStream对象输入流
     */
    public ObjectInputStream getOis() {
        return ois;
    }
}

