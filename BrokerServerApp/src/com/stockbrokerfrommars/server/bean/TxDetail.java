package com.stockbrokerfrommars.server.bean;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class TxDetail {
	private int txid;
	private String type;
	private String stockId;
	private BigDecimal price;
	private int amount;
	private Timestamp dateTime;
	private String txseq;

	public TxDetail() {
	}

	public TxDetail(int txid, String type, String stockId, BigDecimal price, int amount, Timestamp dateTime, String txseq) {
		this.txid = txid;
		this.type = type;
		this.stockId = stockId;
		this.price = price;
		this.amount = amount;
		this.dateTime = dateTime;
		this.txseq = txseq;
	}

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

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Timestamp getDateTime() {
		return dateTime;
	}

	public void setDateTime(Timestamp dateTime) {
		this.dateTime = dateTime;
	}

	public String getTxseq() {
		return txseq;
	}

	public void setTxseq(String txseq) {
		this.txseq = txseq;
	}

	@Override
	public String toString() {
		return "TxDetail [txid=" + txid + ", type=" + type + ", stockId=" + stockId + ", price=" + price + ", amount="
				+ amount + ", dateTime=" + dateTime + ", txseq=" + txseq + "]";
	}

}
