package com.stockbrokerfrommars.server.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stockbrokerfrommars.server.bean.WatchingStock;
import com.stockbrokerfrommars.server.dao.WatchingStockDao;

@Service
public class WatchingStockService {
	private WatchingStockDao watchingStockDao;

	/**
	 * get the in stock and out stock as a watching list
	 * @return
	 * @throws DataAccessException
	 */
	@Transactional
	public List<WatchingStock> getWatchingStocks() throws DataAccessException {
		List<WatchingStock> watchingStocks = new LinkedList<>();
		
		watchingStocks=watchingStockDao.getWatchingInStock();
		watchingStocks.addAll(watchingStockDao.getWatchingOutStock());
		
		return watchingStocks;
	}

	@Autowired
	public void setWatchingStockDao(WatchingStockDao watchingStockDao) {
		this.watchingStockDao = watchingStockDao;
	}

}
