package com.stockbrokerfrommars.batch.component.getallstocksid;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.batchfrommars.component.BatchComponentII;
import com.batchfrommars.file.LogUtil;
import com.stockbrokerfrommars.batch.bean.TwseUrlData;

public class StepGetStocksId extends BatchComponentII {
	private ResourceBundle resource = ResourceBundle.getBundle("com.stockbrokerfrommars.batch.config.job");
	private Logger logger = LogUtil.setLogger(getClass().getSimpleName(), resource.getString("sit.getAllStocksId.log"), Logger.getLogger(getName()));
	private int count = 0;

	@Override
	protected LinkedList<String> excuteProcess(LinkedList<String> inputList) {
		LinkedList<String> outputList = new LinkedList<String>();
		String output = null;

		if (inputList.get(INPUT_1) != null) {

			output = "";
			TwseUrlData data = new TwseUrlData();
			data.parse(inputList.get(INPUT_1));
			logger.info(data.getUrlPath());

			try {
				URL url = new URL(data.getUrlPath());

				try {
					Document document = Jsoup.parse(url, 3000);
					Elements contents = document.getElementsByTag("form");
					Elements tableDatas = contents.get(1).getElementsByTag("td");

					for (int i = 0; i < tableDatas.size(); i++) {

						if (!tableDatas.get(i).text().substring(0, 1).equals("0") ) {
							
							if (i == (tableDatas.size() - 1)) {
								output = output + tableDatas.get(i).text().substring(0, 4);
							} else {
								output = output + tableDatas.get(i).text().substring(0, 4) + "\r\n";
							}
							count++;
						}
					}

				} catch (IOException e) {
					System.out.println(data + " " + e.getMessage());
					logger.warning(e.getStackTrace()[0].toString() + " " + data.toString());
				}

			} catch (MalformedURLException e) {
				System.out.println(data + " " + e.getMessage());
				logger.warning(e.getStackTrace()[0].toString() + " " + data.toString());
			}

		}

		outputList.add(output);
		return outputList;
	}

	@Override
	protected void onInit() {
		super.onInit();
		logger.info("----" + getClass().getSimpleName() + START_MSG + "----");
	}

	@Override
	protected void onFinish() {
		super.onFinish();
		logger.info("total stock id  = " + count);
		logger.info("----" + getClass().getSimpleName() + COMPELETE_MSG + "----");
	}

}
