package com.msb.minio;

import io.minio.MinioClient;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.util.List;
import java.util.logging.Logger;

public class MinioDemo {

    private static String endPoint = "http://192.168.10.170:9000";
    private static String accessKey = "kunlunrepo";
    private static String secretKey = "11111111";
    private static String bucket="test";

    public static void main(String[] args) throws Exception {
        MinioClient minioClient = new MinioClient(endPoint, accessKey, secretKey, bucket);
        // bucket的检查
        boolean bucketExists = minioClient.bucketExists(bucket);
        if (bucketExists) {
            System.out.println("不存在存储目录，请新建");
        } else {
            minioClient.makeBucket(bucket);
        }

        // 文件的上传操作
        minioClient.putObject(bucket, "/group1/pom.xml","D:\\work\\code-repo\\msb\\mca\\dubbo3-simple\\pom.xml", null);
        System.out.println("文件上传成功");
        // 文件的下载操作
        InputStream object = minioClient.getObject(bucket, "/group1/pom.xml");
        List<String> lines = IOUtils.readLines(object, "UTF-8");
        lines.stream().forEach(line -> System.out.println(line));
    }
}
