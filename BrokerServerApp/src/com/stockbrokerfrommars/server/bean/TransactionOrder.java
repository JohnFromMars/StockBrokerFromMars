package com.stockbrokerfrommars.server.bean;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.batchfrommars.component.SymbolParser;

public class TransactionOrder extends SymbolParser{
	
	public static final String BUYING_STOCK = "00";
	public static final String SELLING_STOCK = "01";
	public static final String CHECK_UNRESOLVED = "02";

	private String txType;
	private String stockId;
	private BigDecimal amount;
	private BigDecimal sellingPrice;
	private BigDecimal buyingPrice;
	
	
	public String getTxType() {
		return txType;
	}
	public void setTxType(String txType) {
		this.txType = txType;
	}
	public String getStockId() {
		return stockId;
	}
	public void setStockId(String stockId) {
		this.stockId = stockId;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public BigDecimal getSellingPrice() {
		return sellingPrice;
	}
	public void setSellingPrice(BigDecimal sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	public BigDecimal getBuyingPrice() {
		return buyingPrice;
	}
	public void setBuyingPrice(BigDecimal buyingPrice) {
		this.buyingPrice = buyingPrice;
	}
	
	@Override
	protected ArrayList<String> getFields() {
		ArrayList<String> fieldTable = new ArrayList<>();
		
		fieldTable.add("txType");
		fieldTable.add("stockId");
		fieldTable.add("amount");
		fieldTable.add("sellingPrice");
		fieldTable.add("buyingPrice");
		
		return fieldTable;
	}
	
	@Override
	protected String getSymbol() {
		return ",";
	}
	
}
