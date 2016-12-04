package com.stockbrokerfrommars.server.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stockbrokerfrommars.server.bean.InStock;
import com.stockbrokerfrommars.server.dao.InStockDao;

@Service
public class InStockService {
	private InStockDao inStockDao;

	public void  insertInStock(InStock inStock){
		inStockDao.insertInStock(inStock);
	}
	
	public void updateInStock(InStock inStock){
		inStockDao.updateInStock(inStock);
	}
	
	public void updateInStockAmount(String txSeq,BigDecimal amount){
		inStockDao.updateInStockAmount(txSeq, amount);
	}
	
	public void updateInStockSellingTxSeq(String txSeq,String sellingTxSeq){
		inStockDao.updateInStockSellingTxSeq(txSeq,sellingTxSeq);
	}
	
	@Autowired
	public void setInStockDao(InStockDao inStockDao) {
		this.inStockDao = inStockDao;
	}

}
