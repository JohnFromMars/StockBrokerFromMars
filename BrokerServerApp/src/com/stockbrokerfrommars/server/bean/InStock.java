package com.stockbrokerfrommars.server.bean;

import java.math.BigDecimal;

public class InStock {
	private String stockId;
	private BigDecimal buyingPrice;
	private BigDecimal bestSellingPrice;
	private BigDecimal amoount;

	// 應該異佳是否已賣出flag
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

	public BigDecimal getBestSellingPrice() {
		return bestSellingPrice;
	}

	public void setBestSellingPrice(BigDecimal bestSellingPrice) {
		this.bestSellingPrice = bestSellingPrice;
	}

	public BigDecimal getAmoount() {
		return amoount;
	}

	public void setAmoount(BigDecimal amoount) {
		this.amoount = amoount;
	}

	@Override
	public String toString() {
		return "InStock [stockId=" + stockId + ", buyingPrice=" + buyingPrice + ", bestSellingPrice=" + bestSellingPrice
				+ ", amoount=" + amoount + "]";
	}

}
