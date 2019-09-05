package com.huifer.happy.file.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;

@RequestMapping("/test")
@RestController
public class TestDemo {

    public static void main(String[] args) throws Exception {
        firstDo();
        File file = new File("C:\\dev\\dw\\kafka_2.12-2.1.0.tar");
        FileInputStream fis = new FileInputStream(file);
//         跳过1024*1024字节
        fis.skip(1024 * 1024);
        // 1024*1024 字节的文件
        File sp = new File("C:\\dev\\dw\\222.tar");
        FileInputStream fissp = new FileInputStream(sp);


        OutputStream output = new FileOutputStream("C:\\dev\\dw\\222-2.tar");


        SequenceInputStream si = new SequenceInputStream(fis, fissp);
        byte[] buf = new byte[1024];
        int length = 0;
        while ((length = si.read(buf)) != -1) {
            output.write(buf, 0, length);
        }

        output.flush();
        fis.close();
        fissp.close();
        si.close();
    }


    private static void firstDo() throws IOException {
        File file = new File("C:\\dev\\dw\\kafka_2.12-2.1.0.tar");
        FileInputStream fis = new FileInputStream(file);

        InputStream input = new BufferedInputStream(fis);
        OutputStream output = new FileOutputStream("C:\\dev\\dw\\222.tar");

        byte[] data = new byte[1024 * 1024];
        long total = 0;

        int count = 0;
        int i = 0;
        while ((count = input.read(data)) != -1) {
            output.write(data, 0, count);
            total += count;
            i++;
            if (i == 1) {
                break;
            }
        }

        output.flush();
        output.close();
        input.close();
    }
}
