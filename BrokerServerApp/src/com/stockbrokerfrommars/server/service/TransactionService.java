package com.stockbrokerfrommars.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stockbrokerfrommars.server.bean.TxDetail;
import com.stockbrokerfrommars.server.dao.InStockDao;
import com.stockbrokerfrommars.server.dao.TxDetailDao;

@Service
public class TransactionService {
	private TxDetailDao txDetailDao;
	private InStockDao inStockDao;

	@Transactional
	public void cancelOrder(String txSeq) {
		txDetailDao.deleteTxDetail(txSeq);
		inStockDao.updateInStockSellingTxSeq(txSeq, null);
	}

	@Transactional
	public void sellingOrder(String originTxSeq, TxDetail txDetail) {
		if (txDetail.getType().equals(TxDetail.SELL_STOCK)) {
			txDetailDao.insertTxDetail(txDetail);
			inStockDao.updateInStockSellingTxSeq(originTxSeq, txDetail.getTxSeq());
			
		} else if (txDetail.getType().equals(TxDetail.BUY_STOCK)) {
			txDetailDao.insertTxDetail(txDetail);
		}
	}

	public void confirmOrder(String txSeq) {

	}

	@Autowired
	public void setTxDetailDao(TxDetailDao txDetailDao) {
		this.txDetailDao = txDetailDao;
	}

	@Autowired
	public void setInStockDao(InStockDao inStockDao) {
		this.inStockDao = inStockDao;
	}

}
