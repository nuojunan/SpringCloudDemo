package com.bjnja.fastdfs.demo;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.util.UUID;

import com.bjnja.fastdfs.demo.client.FastDFSClient;



public class FDFSTestCase {


	public static void main(String[] args) {
		created();
	}
	public static void created() {
		try {
			FileInputStream stream = new FileInputStream("e://test.jpg");
			byte[] bytes = new byte[stream.available()];
			stream.read(bytes);
			stream.close();
			long start = System.currentTimeMillis();
//			for (int i = 0; i < 1000; i++) { // 57s
				ByteArrayInputStream is = new ByteArrayInputStream(bytes);
				String uuid = UUID.randomUUID().toString();
				String upload = new FastDFSClient().upload(is, uuid + ".jpg", null);
				System.out.println("上传文件结果： " + upload);
//			}
			System.out.println(System.currentTimeMillis() - start + "ms");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
