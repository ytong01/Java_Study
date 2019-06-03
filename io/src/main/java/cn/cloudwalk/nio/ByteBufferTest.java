package cn.cloudwalk.nio;

import java.io.*;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public class ByteBufferTest {

    public static void main(String[] args) throws IOException {
//        URL url = Thread.currentThread().getContextClassLoader().getResource("nio-data.txt");
//        File file = new File(url.getFile());
//        RandomAccessFile aFile = new RandomAccessFile(file, "rw");
//        System.out.println(aFile.length());
//        FileChannel channel = aFile.getChannel();
//        ByteBuffer buffer = ByteBuffer.allocate(48);
//        while (channel.read(buffer) != -1) {
//            buffer.flip();
//            while (buffer.hasRemaining()) {
//                System.out.println((char)buffer.get());
//            }
//            buffer.clear();
//        }

        read();
    }

    public static void read() {
        URL url = Thread.currentThread().getContextClassLoader().getResource("nio-data.txt");
        File file = new File(url.getFile());
        try (RandomAccessFile aFile = new RandomAccessFile(file, "rw")) {
            ByteBuffer buffer = ByteBuffer.allocate(48);
            while (aFile.getChannel().read(buffer) != -1) {

            }
            buffer.flip();
            byte[] data = buffer.array();
            System.out.println(new String(data, "utf-8"));

            //将所有未读的数据拷贝到buffer起始处
//            buffer.compact();
//            buffer.put(", 凛冬将至".getBytes(Charset.forName("UTF-8")));
//            buffer.flip();
//            aFile.write(buffer.array());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
