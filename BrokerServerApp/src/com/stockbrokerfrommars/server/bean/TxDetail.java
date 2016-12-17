package com.stockbrokerfrommars.server.bean;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class TxDetail {
	
	public static final String BUY_STOCK = "buy";
	public static final String SELL_STOCK = "sell";
	
	private int txid;
	private String type;
	private String stockId;
	private BigDecimal price;
	private BigDecimal amount;
	private Timestamp dateTime;
	private String txSeq;
	private boolean resolved;

	public int getTxid() {
		return txid;
	}

	public void setTxid(int txid) {
		this.txid = txid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStockId() {
		return stockId;
	}

	public void setStockId(String stockId) {
		this.stockId = stockId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Timestamp getDateTime() {
		return dateTime;
	}

	public void setDateTime(Timestamp dateTime) {
		this.dateTime = dateTime;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public boolean isResolved() {
		return resolved;
	}

	public void setResolved(boolean resolved) {
		this.resolved = resolved;
	}

	public String getTxSeq() {
		return txSeq;
	}

	public void setTxSeq(String txSeq) {
		this.txSeq = txSeq;
	}

	@Override
	public String toString() {
		return "TxDetail [txid=" + txid + ", type=" + type + ", stockId=" + stockId + ", price=" + price + ", amount="
				+ amount + ", dateTime=" + dateTime + ", txSeq=" + txSeq + ", resolved=" + resolved + "]";
	}

}
