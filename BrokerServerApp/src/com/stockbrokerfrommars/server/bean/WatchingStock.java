package com.stockbrokerfrommars.server.bean;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.batchfrommars.component.SymbolParser;

public class WatchingStock extends SymbolParser {
	public static final String IN_STOCK = "inStock";
	public static final String OUT_STOCK = "outStock";

	private String type;
	private String stockId;
	private BigDecimal buyingPrice;
	private BigDecimal currentPrice;
	private BigDecimal bestSellingPrice;
	private BigDecimal bestBuyingPrice;
	private BigDecimal amount;

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

	public BigDecimal getBuyingPrice() {
		return buyingPrice;
	}

	public void setBuyingPrice(BigDecimal buyingPrice) {
		this.buyingPrice = buyingPrice;
	}

	public BigDecimal getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(BigDecimal currentPrice) {
		this.currentPrice = currentPrice;
	}

	public BigDecimal getBestSellingPrice() {
		return bestSellingPrice;
	}

	public void setBestSellingPrice(BigDecimal bestSellingPrice) {
		this.bestSellingPrice = bestSellingPrice;
	}

	public BigDecimal getBestBuyingPrice() {
		return bestBuyingPrice;
	}

	public void setBestBuyingPrice(BigDecimal bestBuyingPrice) {
		this.bestBuyingPrice = bestBuyingPrice;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return type + "," + stockId + "," + buyingPrice + "," + currentPrice + "," + bestSellingPrice + "," + bestBuyingPrice + "," + amount;
	}

	@Override
	protected ArrayList<String> getFields() {
		ArrayList<String> fieldTable =new  ArrayList<>();
		
		fieldTable.add("type");
		fieldTable.add("stockId");
		fieldTable.add("buyingPrice");
		fieldTable.add("currentPrice");
		fieldTable.add("bestSellingPrice");
		fieldTable.add("bestBuyingPrice");
		fieldTable.add("amount");
			
		return fieldTable;
	}

	@Override
	protected String getSymbol() {
		return ",";
	}

}
