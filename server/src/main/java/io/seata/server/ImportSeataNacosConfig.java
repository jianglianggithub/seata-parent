package io.seata.server;

import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpUtil;
import com.google.common.primitives.Chars;

import java.io.File;
import java.nio.charset.Charset;

public class ImportSeataNacosConfig {

    public static void main(String[] args) {
//        FileUtil.readLines(new File("C:\\Users\\keyi\\IdeaProjects\\seata-parent\\script\\config-center\\config.txt"), Charset.defaultCharset()).stream().forEach(str -> {
//            String dataId = str.split("=")[0];
//            String content = str.split("=")[1];
//            String url = "http://127.0.0.1:8848/nacos/v1/cs/configs?dataId=%s&group=SEATA_GROUP&content=%s";
//            String s = HttpUtil.post(String.format(url, dataId, content),"");
//            System.out.println(s);
//        });
    }
}
