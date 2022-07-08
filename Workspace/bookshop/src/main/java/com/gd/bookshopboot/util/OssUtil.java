package com.gd.bookshopboot.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;

import java.io.InputStream;

//阿里存储工具类
public class OssUtil {

    //区域
    private static String endpoint = "https://oss-cn-qingdao.aliyuncs.com";
    //访问id
    private static String accessKeyId = "LTAI5tMzbsr8r5disq8Q8Wdj";
    //访问秘钥
    private static String accessKeySecret = "XhNiAxJjdNgQKcMLgBewfYqZzWzjuy";
    //桶名称
    private static String bucketName = "lyp3";
    //访问URL
    private static String url = "https://lyp3.oss-cn-qingdao.aliyuncs.com";


    //文件上传
        public static String upload(String fileName, InputStream inputStream) {

            // yourEndpoint填写Bucket所在地域对应的Endpoint。以青岛为例，Endpoint填写为https://oss-cn-qingdao.aliyuncs.com。
            String endpoint = "https://oss-cn-qingdao.aliyuncs.com";
            // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
            String accessKeyId = "LTAI5tMzbsr8r5disq8Q8Wdj";
            String accessKeySecret = "XhNiAxJjdNgQKcMLgBewfYqZzWzjuy";

            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);


            // 依次填写Bucket名称（例如examplebucket）和Object完整路径（例如exampledir/exampleobject.txt）。Object完整路径中不能包含Bucket名称。
            ossClient.putObject("lyp3", fileName, inputStream);

            ossClient.shutdown();


            return url + "/" + fileName;
        }
//文件类型
    public static String getContentType(String FilenameExtension) {
        if (FilenameExtension.equalsIgnoreCase(".bmp")) {
            return "image/bmp";
        }
        if (FilenameExtension.equalsIgnoreCase(".gif")) {
            return "image/gif";
        }
        if (FilenameExtension.equalsIgnoreCase(".jpeg") ||
                FilenameExtension.equalsIgnoreCase(".jpg") ||
                FilenameExtension.equalsIgnoreCase(".png")) {
            return "image/jpg";
        }
        return "image/jpg";
    }


}
