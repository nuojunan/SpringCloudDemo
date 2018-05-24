package com.bjnja.fastdfs.demo;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bjnja.fastdfs.demo.client.FastDFSClient;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FDFSTestCase {

	private FastDFSClient fastDFSClient = new FastDFSClient();

	@Test
	public void created() {
		try {
			FileInputStream stream = new FileInputStream("e://test.jpg");
			byte[] bytes = new byte[stream.available()];
			stream.read(bytes);
			stream.close();
			long start = System.currentTimeMillis();
//			for (int i = 0; i < 1000; i++) { // 57s
				ByteArrayInputStream is = new ByteArrayInputStream(bytes);
				String uuid = UUID.randomUUID().toString();
				String upload = fastDFSClient.upload(is, uuid + ".jpg", null);
				System.out.println("上传文件结果： " + upload);
//			}
			System.out.println(System.currentTimeMillis() - start + "ms");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
