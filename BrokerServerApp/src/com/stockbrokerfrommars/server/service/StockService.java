package com.stockbrokerfrommars.server.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stockbrokerfrommars.server.dao.StockDao;
import com.stockbrokerfrommars.server.dao.StockDevidendDao;
import com.stockbrokerfrommars.server.dao.StockQuoteDao;
import com.stockbrokerfrommars.server.dao.StockStatsDao;

import yahoofinance.Stock;

@Service
public class StockService {
	private StockDao stockDao;
	private StockQuoteDao stockQuoteDao;
	private StockDevidendDao stockDevidendDao;
	private StockStatsDao stockStatsDao;

	public boolean isStockExsist(String stockId) throws DataAccessException {
		return stockDao.isStockExsist(stockId);
	}

	@Transactional
	public void updateStock(Stock stock) throws DataAccessException {

		if (isStockExsist(stock.getSymbol().substring(0, 4))) {
			stockDao.updateStock(stock);
			stockQuoteDao.updateStockQoute(stock.getQuote());
			stockDevidendDao.updateStockDevidend(stock.getDividend());
			stockStatsDao.updateStockStats(stock.getStats());
		} else {
			stockDao.insertStock(stock);
			stockQuoteDao.insertStockQuote(stock.getQuote());
			stockDevidendDao.insertStockDevidend(stock.getDividend());
			stockStatsDao.insertStockStats(stock.getStats());
		}
	}

	public void updatePrice(String stockId, BigDecimal price) {
		if (isStockExsist(stockId)) {
			stockQuoteDao.updatePrice(stockId, price);
		} else {
			throw new DataRetrievalFailureException("Can't find stock Id = " + stockId);
		}
	}

	@Autowired
	public void setStockDao(StockDao stockDao) {
		this.stockDao = stockDao;
	}

	@Autowired
	public void setStockQuoteDao(StockQuoteDao stockQuoteDao) {
		this.stockQuoteDao = stockQuoteDao;
	}

	@Autowired
	public void setStockDevidendDao(StockDevidendDao stockDevidendDao) {
		this.stockDevidendDao = stockDevidendDao;
	}

	@Autowired
	public void setStockStatsDao(StockStatsDao stockStatsDao) {
		this.stockStatsDao = stockStatsDao;
	}

}
