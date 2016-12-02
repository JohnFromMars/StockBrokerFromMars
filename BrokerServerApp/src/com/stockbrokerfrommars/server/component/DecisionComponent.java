package com.stockbrokerfrommars.server.component;

import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import com.batchfrommars.component.BatchComponentII;
import com.batchfrommars.file.LogUtil;
import com.stockbrokerfrommars.server.bean.TransactionOrder;
import com.stockbrokerfrommars.server.bean.WatchingStock;

public class DecisionComponent extends BatchComponentII {

	private static final int DECISION_QUE = 0;
	private Logger logger;
	private ResourceBundle resource;
	private int count = 0;

	@Override
	protected void onInit() {
		super.onInit();
		resource = ResourceBundle.getBundle("com.stockbrokerfrommars.server.config.serverapp");
		logger = LogUtil.setLogger("DecisionComponent", resource.getString("sit.app.log"),
				Logger.getLogger("DecisionComponent"));
		logger.info(" ==== DecisionComponent started ====");
	}

	@Override
	protected LinkedList<String> excuteProcess(LinkedList<String> inputList) {
		LinkedList<String> outputList = new LinkedList<>();
		String txQueData = null;

		if (inputList.get(DECISION_QUE) != null) {
			WatchingStock watchingStock = new WatchingStock();
			watchingStock.parse(inputList.get(DECISION_QUE));
			System.out.println(watchingStock);
			decision(txQueData, watchingStock);

			count++;
		}
		System.out.println(txQueData);
		outputList.add(txQueData);
		return outputList;
	}

	@Override
	protected void onFinish() {
		super.onFinish();
		logger.info("---decesion report---");
		logger.info("decesion total comand reicved : " + count);
		logger.info("---------------------");
		logger.info(" ==== DecisionComponent compeleted ====");
	}

	private void decision(String txQueData, WatchingStock watchingStock) {
		switch (watchingStock.getType()) {

		case WatchingStock.IN_STOCK:
			inStockProcess(txQueData, watchingStock);
			break;

		case WatchingStock.OUT_STOCK:
			outStockProcess(txQueData, watchingStock);
			break;

		default:
			break;
		}
	}

	private void inStockProcess(String txQueData, WatchingStock watchingStock) {
		int compare = watchingStock.getCurrentPrice().compareTo(watchingStock.getBestSellingPrice());

		if (compare >= 0) {

			TransactionOrder transactionOrder = new TransactionOrder();
			transactionOrder.setTxType(TransactionOrder.SELLING_STOCK);
			transactionOrder.setSellingPrice(watchingStock.getCurrentPrice());
			transactionOrder.setAmount(watchingStock.getAmount());
			transactionOrder.setStockId(watchingStock.getStockId());

			txQueData = transactionOrder.toString();
		}

	}

	private void outStockProcess(String txQueData, WatchingStock watchingStock) {
		int compare = watchingStock.getCurrentPrice().compareTo(watchingStock.getBestBuyingPrice());

		if (compare <= 0) {

		}
	}
}
