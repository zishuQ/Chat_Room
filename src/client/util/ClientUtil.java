/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: ClientUtil
 * Author:   ITryagain
 * Date:     2019/5/16 20:24
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package client.util;

import client.DataBuffer;
import client.ui.ChatFrame;
import com.vdurmont.emoji.EmojiParser;
import common.model.entity.Message;
import common.model.entity.Request;
import common.model.entity.Response;
import common.model.entity.User;
import common.util.RecordUtil;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ClientUtil {

    /**
     * 发送请求对象,主动接收响应
     */
    public static Response sendTextRequest(Request request) throws IOException {
        Response response = null;
        try {
            // 发送请求
            DataBuffer.oos.writeObject(request);
            DataBuffer.oos.flush();
            System.out.println("客户端发送了请求对象:" + request.getAction());

            if (!"exit".equals(request.getAction())) {
                // 获取响应
                response = (Response) DataBuffer.ois.readObject();
                System.out.println("客户端获取到了响应对象:" + response.getStatus());
            } else {
                System.out.println("客户端断开连接了");
            }
        } catch (IOException e) {
            throw e;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * 发送请求对象,不主动接收响应
     */
    public static void sendTextRequest2(Request request) throws IOException {
        try {
            DataBuffer.oos.writeObject(request); // 发送请求
            DataBuffer.oos.flush();
            System.out.println("客户端发送了请求对象:" + request.getAction());
        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * 把指定文本添加到消息列表文本域中
     */
    public static void appendTxt2MsgListArea(String txt) {
        ChatFrame.msgListArea.append(txt);
        //把光标定位到文本域的最后一行
        ChatFrame.msgListArea.setCaretPosition(ChatFrame.msgListArea.getDocument().getLength());
    }

    public static void appendTxt2MsgListArea(Message message, Long id) {
        message.setMessage(EmojiParser.parseToAliases(message.getMessage()));
        RecordUtil.recordInLocal(message, id);
    }
}
