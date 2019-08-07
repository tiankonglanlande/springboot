package com.yuanfenge.image;

import com.yuanfenge.image.service.ImageUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author 猿份哥
 * @description
 * @createTime 2019/8/04 16:35
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ImageTest {

	@Test
	public void imageFileToBase64() throws Exception {
		String filePath="D:\\data\\test\\0.jpg";
		String imageStr = ImageUtils.resizeImage(filePath, 60, 300, 420);
		System.out.println(imageStr);
	}

}