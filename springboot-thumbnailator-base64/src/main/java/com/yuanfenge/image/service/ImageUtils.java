package com.yuanfenge.image.service;

import net.coobird.thumbnailator.Thumbnails;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * @author 猿份哥
 * @description
 * @createTime 2019/8/04 18:37
 */
public class ImageUtils {

    private static String ImageFormat="jpg";


    /**
     * 按照比例和规格压缩图片得到base64图片字符串
     * @param maxSize 单位kb
     * @param w
     * @param h
     * @return
     */
    public static String resizeImage(String filePath,int maxSize,int w,int h){
        try {
            BufferedImage src = fileToBufferedImage(filePath);
            BufferedImage output = Thumbnails.of(src).size(w, h).asBufferedImage();
            String base64 = imageToBase64(output);
            while (base64.length() - base64.length() / 8 * 2 > maxSize*1000) {
                output = Thumbnails.of(output).scale(0.9f).asBufferedImage();
                base64 = imageToBase64(output);
            }
            return imageToBase64(output);
        } catch (Exception e) {
           e.printStackTrace();
        }
        return null;
    }


    /**
     * 图片文件转BufferedImage
     * @param filePath
     * @return
     * @throws Exception
     */
    public static BufferedImage fileToBufferedImage(String filePath) throws Exception{
        FileInputStream is=new FileInputStream(filePath);
        BufferedImage img = ImageIO.read(is);
        return  img;
    }

    /**
     * 将图片base64字符串转换为BufferedImage
     * @param base64string
     * @return
     */
    public static BufferedImage base64String2BufferedImage(String base64string) {
        BufferedImage image = null;
        try {
            InputStream stream = base64StringToInputStream(base64string);
            image = ImageIO.read(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    /**
     * 将base64字符转换为输入流
     * @param base64string
     * @return
     */
    private static InputStream base64StringToInputStream(String base64string) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(base64string.getBytes());
        InputStream inputStream=byteArrayInputStream;
        return inputStream;
    }

    /**
     * 将BufferedImage转换为base64字符串
     * @param bufferedImage
     * @return
     */
    public static String imageToBase64(BufferedImage bufferedImage) {
        Base64 encoder = new Base64();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, ImageFormat, baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(encoder.encode((baos.toByteArray())));
    }

}
