package com.stockbrokerfrommars.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stockbrokerfrommars.server.bean.TxDetail;
import com.stockbrokerfrommars.server.dao.TxDetailDao;

@Service
public class TxDetailService {
	private TxDetailDao txDetailDao;

	public void insertTxDetail(TxDetail txDetail) {
		txDetailDao.insertTxDetail(txDetail);
	}

	public void updateTxDetail(TxDetail txDetail) {
		txDetailDao.updateTxDetail(txDetail);
	}

	public TxDetail getTxDetail(String txSeq){
		return txDetailDao.getTxDetail(txSeq);
	}
	
	public List<TxDetail> getTxDetails() {
		return txDetailDao.getTxDetails(10);
	}
	
	public void deleteTxDetail(String txSeq){
		txDetailDao.deleteTxDetail(txSeq);
	}
	
	@Autowired
	public void setTxDetailDao(TxDetailDao txDetailDao) {
		this.txDetailDao = txDetailDao;
	}

}
