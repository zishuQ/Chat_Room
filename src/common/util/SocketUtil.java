package common.util;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketUtil {
    /**
     * 关闭Socket
     * @param socket 需要关闭的Socket对象
     */
    public static void close(Socket socket) {
        if (socket != null && !socket.isClosed()) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭ServerSocket
     * @param ss 需要关闭的ServerSocket对象
     */
    public static void close(ServerSocket ss) {
        if (ss != null && !ss.isClosed()) {
            try {
                ss.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
