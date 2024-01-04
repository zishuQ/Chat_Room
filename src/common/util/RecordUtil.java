package common.util;

import common.model.entity.Message;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RecordUtil {

    static final String path = "./ChatRecord/";
    static final String suffix = ".ser";

    /**
     * 序列化消息列表，并将结果存储到指定文件中
     * @param messages 待序列化的消息列表
     * @param id 指定文件的标识
     */
    public static void serializeMessages(List<Message> messages, Long id) {
        String filePath = path + id + suffix;
        try (ObjectOutputStream outputStream = new ObjectOutputStream(Files.newOutputStream(Paths.get(filePath)))) {
            outputStream.writeObject(messages);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 序列化消息列表，并将结果存储到本地文件中
     * @param messages 待序列化的消息列表
     * @param id 指定文件的标识
     */
    public static void serializeMessagesInLocal(List<Message> messages, Long id) {
        String filePath = path + id + "_local" + suffix;
        try (ObjectOutputStream outputStream = new ObjectOutputStream(Files.newOutputStream(Paths.get(filePath)))) {
            outputStream.writeObject(messages);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从指定文件中反序列化消息列表
     * @param id 指定文件的标识
     * @return 反序列化后的消息列表
     */
    public static List<Message> deserializeMessages(Long id) {
        String filePath = path + id + suffix;
        try (ObjectInputStream inputStream = new ObjectInputStream(Files.newInputStream(Paths.get(filePath)))) {
            Object obj = inputStream.readObject();
            if (obj instanceof List) {
                return (List<Message>) obj;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>(); // 返回一个空的List，或者可以根据实际需求处理
    }

    /**
     * 获取所有消息的文件标识列表
     * @return 所有消息的文件标识列表
     * @throws IOException 输入/输出异常
     */
    public static Set<Long> getRecordIds() throws IOException {
        return Files.walk(Paths.get(path))
               .filter(Files::isRegularFile)
               .map(path -> path.getFileName().toString())
               .map(path -> path.substring(0, path.lastIndexOf(".")))
               .map(Long::parseLong)
               .collect(java.util.stream.Collectors.toSet());
    }
}

