package com.stockbrokerfrommars.batch.util.url;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class UrlUtil {

	public static void readUrlSourceCode(String strURL,String filePath,String fileName) {
		int chunksize = 4096;
		byte[] chunk = new byte[chunksize];
		int count;
		try {
			URL pageUrl = new URL(strURL);

			// 讀入網頁(位元串流)
			BufferedInputStream bis = new BufferedInputStream(pageUrl.openStream());
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath+fileName, false));
			System.out.println("read1() running ");
			while ((count = bis.read(chunk, 0, chunksize)) != -1) {
				bos.write(chunk, 0, count); // 寫入檔案
			}
			bos.close();
			bis.close();

			System.out.println("Done");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
