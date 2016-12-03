package com.stockbrokerfrommars.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stockbrokerfrommars.server.bean.OutStock;
import com.stockbrokerfrommars.server.dao.OutStockDao;

@Service
public class OutStockService {
	private OutStockDao outStockDao;

	public void insertOutStock(OutStock outStock) {
		outStockDao.insertOutStock(outStock);
	}

	public void updateOutStock(OutStock outStock) {
		outStockDao.updateOutStock(outStock);
	}

	@Autowired
	public void setOutStockDao(OutStockDao outStockDao) {
		this.outStockDao = outStockDao;
	}

}
