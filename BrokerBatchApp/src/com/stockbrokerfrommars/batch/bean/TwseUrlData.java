package com.stockbrokerfrommars.batch.bean;

import java.util.ArrayList;

import com.batchfrommars.component.SymbolParser;

public class TwseUrlData extends SymbolParser{
	private String urlId;
	private String urlPath;

	public String getUrlId() {
		return urlId;
	}

	public void setUrlId(String urlId) {
		this.urlId = urlId;
	}

	public String getUrlPath() {
		return urlPath;
	}

	public void setUrlPath(String urlPath) {
		this.urlPath = urlPath;
	}

	@Override
	public String toString() {
		return "TwseUrlData [urlId=" + urlId + ", urlPath=" + urlPath + "]";
	}

	@Override
	protected ArrayList<String> getFields() {
		ArrayList<String> fieldTable = new ArrayList<String>();
		fieldTable.add("urlId");
		fieldTable.add("urlPath");
		return fieldTable;
	}

	@Override
	protected String getSymbol() {
		return "=";
	}

}
