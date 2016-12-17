package com.stockbrokerfrommars.server.component;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.batchfrommars.component.ComponentII;
import com.batchfrommars.file.LogUtil;
import com.stockbrokerfrommars.server.bean.WatchingStock;
import com.stockbrokerfrommars.server.service.StockService;
import com.stockbrokerfrommars.server.service.WatchingStockService;
import com.stockbrokerfrommars.server.util.db.DatabaseUtil;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

/**
 * StockInquiryComponent's job is inquiring stock price, sending update stock
 * price in database request to DatabaseComponent and sending information to
 * DecisionComponent and f it got unresolved transaction, will send a checking
 * request TransactionComponent
 * 
 * 
 * @author Yj
 *
 */
public class StockInquiryComponent extends ComponentII {

	private final static int DECISION_QUE = 0;

	private Date businessStartTime;
	private Date businessEndTime;
	private List<WatchingStock> watchingList;
	private WatchingStockService watchingStockService = DatabaseUtil.getWatchingStockService();
	private StockService stockService = DatabaseUtil.getStockService();
	private ResourceBundle resource = ResourceBundle.getBundle("com.stockbrokerfrommars.server.config.serverapp");
	private Logger logger = LogUtil.setLogger("StockInquiryComponent", resource.getString("sit.app.log"),
			Logger.getLogger("StockInquiryComponent"));

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
				// watching list process
				inquiryWatchingStock(item);
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
	private void inquiryWatchingStock(WatchingStock item) {
		try {
			Stock stock = YahooFinance.get(item.getStockId() + ".tw");

			updateStockPrice(stock);

			// put WatchingStock to DECISION_QUE
			outputFileList.get(DECISION_QUE).writeFile(item.toString());
			count++;

		} catch (IOException e) {
			logger.warning("Inquiry Fail " + item.toString() + "\r\n" + e.getMessage());
		}

	}

	private void updateStockPrice(Stock stock) {
		stockService.updatePrice(stock.getSymbol().substring(0,4), stock.getQuote().getPrice());
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
