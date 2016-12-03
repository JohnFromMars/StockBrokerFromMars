package com.stockbrokerfrommars.server.bean;

import java.math.BigDecimal;

public class OutStock {

	private String stockId;
	private BigDecimal bestBuyingPrice;
	private BigDecimal bestSellingPrice;
	// 應加欄位，建議買幾股

	public String getStockId() {
		return stockId;
	}

	public void setStockId(String stockId) {
		this.stockId = stockId;
	}

	public BigDecimal getBestBuyingPrice() {
		return bestBuyingPrice;
	}

	public void setBestBuyingPrice(BigDecimal bestBuyingPrice) {
		this.bestBuyingPrice = bestBuyingPrice;
	}

	public BigDecimal getBestSellingPrice() {
		return bestSellingPrice;
	}

	public void setBestSellingPrice(BigDecimal bestSellingPrice) {
		this.bestSellingPrice = bestSellingPrice;
	}

	@Override
	public String toString() {
		return "OutStock [stockId=" + stockId + ", bestBuyingPrice=" + bestBuyingPrice + ", bestSellingPrice="
				+ bestSellingPrice + "]";
	}

}
