package com.stockbrokerfrommars.server.component;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.batchfrommars.component.ComponentII;
import com.batchfrommars.file.LogUtil;
import com.stockbrokerfrommars.server.bean.DatabaseOrder;
import com.stockbrokerfrommars.server.bean.DecisionOrder;
import com.stockbrokerfrommars.server.bean.WatchingStock;
import com.stockbrokerfrommars.server.service.WatchingStockService;
import com.stockbrokerfrommars.server.util.db.DatabaseUtil;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

/**
 * StockInquiryComponent's job is inquiring stock price, sending update stock
 * price in database request to DatabaseComponent and sending information to
 * DecisionComponent.
 * 
 * 
 * @author Yj
 *
 */
public class StockInquiryComponent extends ComponentII {

	private final static int DB_QUE = 0;
	private final static int DECISION_QUE = 1;

	private Date businessStartTime;
	private Date businessEndTime;
	private List<WatchingStock> watchingList;
	private WatchingStockService watchingStockService = DatabaseUtil.getWatchingStockService();
	private ResourceBundle resource = ResourceBundle.getBundle("com.stockbrokerfrommars.server.config.serverapp");
	private Logger logger = LogUtil.setLogger("StockInquiryComponent", resource.getString("sit.app.log"), Logger.getLogger("StockInquiryComponent"));

	private int count = 0;

	@Override
	protected void onInit() {
		super.onInit();
		logger.info("--------inquiryStock report--------");
		setBusinessTime();
		setWatchingList();
		logger.info(watchingList.toString());

	}

	@Override
	protected void act() {
		while (isBusinessTime()) {
			for (WatchingStock item : watchingList) {
				inquiryStock(item);
			}
			// reset the watching list to get the latest watchingList
			clearWatchingList();
			setWatchingList();
		}

	}

	@Override
	protected void onFinish() {
		super.onFinish();
		System.out.println(this.getClass().getSimpleName() + COMPELETE_MSG);
		logger.info("inquiryStock total comand send : " + count);
		logger.info("---------------------------------------");

	}

	/**
	 * invest the current stock price and composing the telegram to database
	 * component and decision component
	 * 
	 * @param item
	 */
	private void inquiryStock(WatchingStock item) {
		try {
			Stock stock = YahooFinance.get(item.getStockId() + ".tw");
			DatabaseOrder databaseOrder = new DatabaseOrder();
			DecisionOrder decisionOrder = new DecisionOrder();

			setDatabaseOrder(stock, databaseOrder);
			setDecisionOrder(decisionOrder, item);

			outputFileList.get(DB_QUE).writeFile(databaseOrder.toString());
			outputFileList.get(DECISION_QUE).writeFile(decisionOrder.toString());
			count++;
		} catch (IOException e) {
			e.printStackTrace();
			logger.warning("Inquiry Fail " + item.toString() + "\r\n" + e.getMessage());
		}

	}

	/**
	 * compose the telegram to database component
	 * 
	 * @param stock
	 * @param databaseOrder
	 */
	private void setDatabaseOrder(Stock stock, DatabaseOrder databaseOrder) {

		databaseOrder.setOperateType(DatabaseOrder.UPDATE_CURRENT_PRICE);
		databaseOrder.setStockId(stock.getSymbol().substring(0, 4));

		if (stock.getQuote().getPrice() != null) {
			databaseOrder.setCurrentPrice(stock.getQuote().getPrice());
		} else {
			databaseOrder.setCurrentPrice(new BigDecimal("0"));
		}

		databaseOrder.setBuyingPrice(new BigDecimal("0"));
		databaseOrder.setSellingPrice(new BigDecimal("0"));
		databaseOrder.setTxSeq("");
	}

	/**
	 * set the yelgram to decision component
	 * 
	 * @param decisionOrder
	 * @param watchingStock
	 */
	private void setDecisionOrder(DecisionOrder decisionOrder, WatchingStock watchingStock) {
		decisionOrder.setType(DecisionOrder.WATCH_STOCK);
		decisionOrder.setWatchingStock(watchingStock);

	}

	/**
	 * check if the current time is between businessStartTime and
	 * businessEndTime
	 * 
	 * @return
	 */
	private boolean isBusinessTime() {
		if (getCurrentTime().after(businessStartTime) && getCurrentTime().before(businessEndTime)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * get current time
	 * 
	 * @return
	 */
	private Date getCurrentTime() {
		Date date = new Date();
		return date;
	}

	/**
	 * set businessStartTime and businessEndTime as 9am to 3pm
	 * 
	 */
	private void setBusinessTime() {
		// set businessStartTime and businessEndTime
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(resource.getString("con.app.starthour")));
		calendar.set(Calendar.MINUTE, Integer.parseInt(resource.getString("con.app.startminute")));

		businessStartTime = calendar.getTime();

		calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(resource.getString("con.app.endhour")));
		calendar.set(Calendar.MINUTE, Integer.parseInt(resource.getString("con.app.endminute")));

		businessEndTime = calendar.getTime();

		logger.info("businessStartTime = " + businessStartTime);
		logger.info("businessEndTime   = " + businessEndTime);

	}

	/**
	 * get the watching stocks list from database
	 */
	private void setWatchingList() {
		try {
			this.watchingList = watchingStockService.getWatchingStocks();

		} catch (DataAccessException e) {
			logger.warning(e.getClass().getName() + ":" + e.getMessage());
		}
	}

	private void clearWatchingList() {
		this.watchingList.clear();
	}

	@Autowired
	public void setWatchingStockService(WatchingStockService watchingStockService) {
		this.watchingStockService = watchingStockService;
	}

}
