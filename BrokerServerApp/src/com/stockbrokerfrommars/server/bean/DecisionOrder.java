package com.stockbrokerfrommars.server.bean;

import java.util.ArrayList;

import com.batchfrommars.component.SymbolParser;

public class DecisionOrder extends SymbolParser {
	public static final String WATCH_STOCK = "watchingStock";
	public static final String CHECK_TX_STATUS = "checkTxStatus";

	private String type;
	private WatchingStock watchingStock;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public WatchingStock getWatchingStock() {
		return watchingStock;
	}

	public void setWatchingStock(WatchingStock watchingStock) {
		this.watchingStock = watchingStock;
	}

	@Override
	public String toString() {
		return type + "," + watchingStock;
	}

	@Override
	protected ArrayList<String> getFields() {
		ArrayList<String> fieldTable = new ArrayList<>();
		fieldTable.add("type");

		return fieldTable;
	}

	@Override
	protected String getSymbol() {
		return ",";
	}

}
