package com.stockbrokerfrommars.server.dao;

import java.math.BigDecimal;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import yahoofinance.quotes.stock.StockQuote;

@Component
public class StockQuoteDao {
	private NamedParameterJdbcTemplate jdbcTemplate;

	public void updateStockQoute(StockQuote stockQuote) {
		String sql = "update stockquote set askSize = :askSize , ask = :ask , bid = :bid, bidSize = :bidSize, price = :price "
				+ ", lastTradeDate = :lastTradeDate, lastTradeTime = :lastTradeTime , lastTradeTimeT = :lastTradeTimeT "
				+ ", open = :open , previousClose = :previousClose , dayLow = :dayLow , dayHigh = :dayHigh"
				+ ", yearLow = :yearLow  , yearHigh = :yearHigh ,priceAvg50 = :priceAvg50 , priceAvg200 = :priceAvg200 "
				+ ", lastTradeSize = :lastTradeSize , volume = :volume ,avgVolume = :avgVolume where stockId = :stockId ";

		MapSqlParameterSource source = new MapSqlParameterSource();
		source.addValue("askSize", stockQuote.getAskSize());
		source.addValue("stockId", stockQuote.getSymbol().substring(0, 4));
		source.addValue("ask", stockQuote.getAsk());
		source.addValue("bid", stockQuote.getBid());
		source.addValue("bidSize", stockQuote.getBidSize());
		source.addValue("price", stockQuote.getPrice());
		source.addValue("askSize", stockQuote.getAskSize());
		source.addValue("lastTradeSize", stockQuote.getLastTradeSize());
		source.addValue("lastTradeDate", stockQuote.getLastTradeDateStr());
		source.addValue("lastTradeTime", stockQuote.getLastTradeTimeStr());
		source.addValue("lastTradeTimeT", stockQuote.getLastTradeTime());
		source.addValue("open", stockQuote.getOpen());
		source.addValue("previousClose", stockQuote.getPreviousClose());
		source.addValue("dayLow", stockQuote.getDayLow());
		source.addValue("dayHigh", stockQuote.getDayHigh());
		source.addValue("yearLow", stockQuote.getYearLow());
		source.addValue("yearHigh", stockQuote.getYearHigh());
		source.addValue("priceAvg50", stockQuote.getPriceAvg50());
		source.addValue("priceAvg200", stockQuote.getPriceAvg200());
		source.addValue("volume", stockQuote.getVolume());
		source.addValue("avgVolume", stockQuote.getAvgVolume());

		if (jdbcTemplate.update(sql, source) == 0) {
			throw new EmptyResultDataAccessException("Nothing update in stockquote table, can't find stockId=" + source.getValue("stockId"), 3);
		}
	}

	public void insertStockQuote(StockQuote stockQuote) throws DataAccessException {
		String sql = "insert into stockquote(askSize,ask,bid,bidSize,price,lastTradeDate, lastTradeTime,lastTradeTimeT"
				+ ",open, previousClose,dayLow,dayHigh,yearLow,yearHigh,priceAvg50,priceAvg200,lastTradeSize,volume,avgVolume,stockId)"
				+ " values( :askSize, :ask, :bid, :bidSize, :price, :lastTradeDate, :lastTradeTime, :lastTradeTimeT, :open"
				+ ", :previousClose, :dayLow, :dayHigh, :yearLow, :yearHigh, :priceAvg50, :priceAvg200, :lastTradeSize, :volume, :avgVolume, :stockId)";

		MapSqlParameterSource source = new MapSqlParameterSource();
		source.addValue("askSize", stockQuote.getAskSize());
		source.addValue("stockId", stockQuote.getSymbol().substring(0, 4));
		source.addValue("ask", stockQuote.getAsk());
		source.addValue("bid", stockQuote.getBid());
		source.addValue("bidSize", stockQuote.getBidSize());
		source.addValue("price", stockQuote.getPrice());
		source.addValue("askSize", stockQuote.getAskSize());
		source.addValue("lastTradeSize", stockQuote.getLastTradeSize());
		source.addValue("lastTradeDate", stockQuote.getLastTradeDateStr());
		source.addValue("lastTradeTime", stockQuote.getLastTradeTimeStr());
		source.addValue("lastTradeTimeT", stockQuote.getLastTradeTime());
		source.addValue("open", stockQuote.getOpen());
		source.addValue("previousClose", stockQuote.getPreviousClose());
		source.addValue("dayLow", stockQuote.getDayLow());
		source.addValue("dayHigh", stockQuote.getDayHigh());
		source.addValue("yearLow", stockQuote.getYearLow());
		source.addValue("yearHigh", stockQuote.getYearHigh());
		source.addValue("priceAvg50", stockQuote.getPriceAvg50());
		source.addValue("priceAvg200", stockQuote.getPriceAvg200());
		source.addValue("volume", stockQuote.getVolume());
		source.addValue("avgVolume", stockQuote.getAvgVolume());

		jdbcTemplate.update(sql, source);
	}

	public void updatePrice(String stockId, BigDecimal price) {
		String sql = "update stockquote set price = :price where stockId = :stockId ";
		MapSqlParameterSource source = new MapSqlParameterSource();
		source.addValue("price", price);
		source.addValue("stockId", stockId);
		jdbcTemplate.update(sql, source);
	}

	@Autowired
	public void setJdbcTemplate(DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

}
