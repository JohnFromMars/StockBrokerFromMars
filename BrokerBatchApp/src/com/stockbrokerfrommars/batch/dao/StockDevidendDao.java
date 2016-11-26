package com.stockbrokerfrommars.batch.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import yahoofinance.quotes.stock.StockDividend;

@Component
public class StockDevidendDao {
	private NamedParameterJdbcTemplate jdbcTemplate;

	public void updateStockDevidend(StockDividend stockDividend) throws DataAccessException {
		String sql = "update stockdevidend set payDate = :payDate , exDate = :exDate , annualYield = :annualYield" + " , annualYieldPercent =:annualYieldPercent where stockId = :stockId ";
		MapSqlParameterSource source = new MapSqlParameterSource();
		source.addValue("payDate", stockDividend.getPayDate());
		source.addValue("exDate", stockDividend.getExDate());
		source.addValue("annualYield", stockDividend.getAnnualYield());
		source.addValue("annualYieldPercent", stockDividend.getAnnualYieldPercent());
		source.addValue("stockId", stockDividend.getSymbol().substring(0, 4));
		if (jdbcTemplate.update(sql, source) == 0) {
			throw new EmptyResultDataAccessException("Nothing update in stockdevidend table, can't find stockId=" + source.getValue("stockId"), 3);
		}

	}

	public void insertStockDevidend(StockDividend stockDividend) throws DataAccessException {
		String sql = "insert into stockdevidend(stockId,payDate,exDate,annualYield,annualYieldPercent) " + "values(:stockId,:payDate,:exDate,:annualYield,:annualYieldPercent)";
		MapSqlParameterSource source = new MapSqlParameterSource();
		source.addValue("payDate", stockDividend.getPayDate());
		source.addValue("stockId", stockDividend.getSymbol().substring(0, 4));
		source.addValue("exDate", stockDividend.getExDate());
		source.addValue("annualYield", stockDividend.getAnnualYield());
		source.addValue("annualYieldPercent", stockDividend.getAnnualYieldPercent());

		jdbcTemplate.update(sql, source);
	}

	@Autowired
	public void setJdbcTemplate(DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

}
