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

    // 方法用于将 List<Message> 对象序列化到文件
    public static void serializeMessages(List<Message> messages, Long id) {
        String filePath = path + id + suffix;
        try (ObjectOutputStream outputStream = new ObjectOutputStream(Files.newOutputStream(Paths.get(filePath)))) {
            outputStream.writeObject(messages);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 方法用于从文件中反序列化 List<Message> 对象
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

    public static Set<Long> getRecordIds() throws IOException {
        return Files.walk(Paths.get(path))
               .filter(Files::isRegularFile)
               .map(path -> path.getFileName().toString())
               .map(path -> path.substring(0, path.lastIndexOf(".")))
               .map(Long::parseLong)
               .collect(java.util.stream.Collectors.toSet());
    }
}
