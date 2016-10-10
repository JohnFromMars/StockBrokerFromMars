package com.stockbrokerfrommars.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stockbrokerfrommars.web.bean.TxDetail;
import com.stockbrokerfrommars.web.dao.TxDetailDao;

@Service
public class TxDetailService {
	private TxDetailDao txDetailDao;

	public List<TxDetail> getTxDetails() {
		return txDetailDao.getTxDetails(10);
	}

	@Autowired
	public void setTxDetailDao(TxDetailDao txDetailDao) {
		this.txDetailDao = txDetailDao;
	}

}
