package common.util;

import common.model.entity.Message;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class RecordUtil {

    // 方法用于将 List<Message> 对象序列化到文件
    public static void serializeMessages(List<Message> messages, Long id) {
        String fileName = "./ChatRecord/" + id + ".ser";
        try (ObjectOutputStream outputStream = new ObjectOutputStream(Files.newOutputStream(Paths.get(fileName)))) {
            outputStream.writeObject(messages);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 方法用于从文件中反序列化 List<Message> 对象
    public static List<Message> deserializeMessages(String fileName) {
        try (ObjectInputStream inputStream = new ObjectInputStream(Files.newInputStream(Paths.get(fileName)))) {
            Object obj = inputStream.readObject();
            if (obj instanceof List) {
                return (List<Message>) obj;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>(); // 返回一个空的List，或者可以根据实际需求处理
    }

}
