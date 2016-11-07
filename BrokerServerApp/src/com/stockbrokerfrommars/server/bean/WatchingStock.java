package com.stockbrokerfrommars.server.bean;

import java.math.BigDecimal;

public class WatchingStock {
	private String type;
	private String stockId;
	private BigDecimal buyingPrice;
	private BigDecimal currentPrice;
	private BigDecimal bestSellingPrice;
	private BigDecimal bestBuyingPrice;

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

	@Override
	public String toString() {
		return "WatchingStock [type=" + type + ", stockId=" + stockId + ", buyingPrice=" + buyingPrice + ", currentPrice=" + currentPrice + ", bestSellingPrice=" + bestSellingPrice
				+ ", bestBuyingPrice=" + bestBuyingPrice + "]";
	}

}
