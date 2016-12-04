package com.stockbrokerfrommars.server.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stockbrokerfrommars.server.bean.WatchingStock;
import com.stockbrokerfrommars.server.dao.InStockDao;
import com.stockbrokerfrommars.server.dao.OutStockDao;

@Service
public class WatchingStockService {
	private OutStockDao outStockDao;
	private InStockDao inStockDao;

	/**
	 * get the in stock and out stock as a watching list
	 * 
	 * @return
	 * @throws DataAccessException
	 */
	@Transactional
	public List<WatchingStock> getWatchingStocks() throws DataAccessException {
		List<WatchingStock> watchingStocks = new LinkedList<>();

		watchingStocks = inStockDao.getWatchingInStocks();
		watchingStocks.addAll(outStockDao.getWatchingOutStocks());

		return watchingStocks;
	}

	@Autowired
	public void setOutStockDao(OutStockDao outStockDao) {
		this.outStockDao = outStockDao;
	}

	@Autowired
	public void setInStockDao(InStockDao inStockDao) {
		this.inStockDao = inStockDao;
	}

}
