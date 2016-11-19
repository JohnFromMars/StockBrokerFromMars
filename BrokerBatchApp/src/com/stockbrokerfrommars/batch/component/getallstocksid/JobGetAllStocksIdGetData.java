package com.stockbrokerfrommars.batch.component.getallstocksid;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.batchfrommars.component.BatchComponentII;
import com.stockbrokerfrommars.batch.bean.TwseUrlData;

public class JobGetAllStocksIdGetData extends BatchComponentII {

	@Override
	protected LinkedList<String> excuteProcess(LinkedList<String> inputList) {
		LinkedList<String> outputList = new LinkedList<String>();
		String output = null;

		if (inputList.get(INPUT_1) != null) {
			output = "";
			TwseUrlData data = new TwseUrlData();
			data.parse(inputList.get(INPUT_1));

			try {
				URL url = new URL(data.getUrlPath());

				try {
					Document document = Jsoup.parse(url, 3000);
					Elements contents = document.getElementsByTag("form");
					Elements tableDatas = contents.get(1).getElementsByTag("td");

					for (int i = 44; i < tableDatas.size(); i++) {
						if (i == (tableDatas.size() - 1)) {
							output = output + tableDatas.get(i).text().substring(0, 4);
						}else{
							output = output + tableDatas.get(i).text().substring(0, 4) + "\r\n";
						}
						
					}

					System.out.println(output);

				} catch (IOException e) {
					System.out.println(data + " " + e.getMessage());
				}

			} catch (MalformedURLException e) {
				System.out.println(data + " " + e.getMessage());
			}

		}

		outputList.add(output);
		return outputList;
	}

}
