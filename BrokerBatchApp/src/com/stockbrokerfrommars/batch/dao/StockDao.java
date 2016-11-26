package com.stockbrokerfrommars.batch.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.IncorrectResultSetColumnCountException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import yahoofinance.Stock;

@Component
public class StockDao {

	private NamedParameterJdbcTemplate jdbcTemplate;

	public boolean isStockExsist(String stockId) throws DataAccessException {
		MapSqlParameterSource source = new MapSqlParameterSource();
		source.addValue("stockId", stockId);
		String sql = "select * from stock where stockId = :stockId";

		try {
			jdbcTemplate.queryForObject(sql, source, Stock.class);
			return true;
		} catch (EmptyResultDataAccessException e) {
			return false;
		} catch (IncorrectResultSetColumnCountException e) {
			return true;
		}
	}

	public void updateStock(Stock stock) throws DataAccessException {
		String sql = "update stock set currency = :currency , name = :name , stockExchange = :stockExchange where stockId = :stockId ";
		MapSqlParameterSource source = new MapSqlParameterSource();
		source.addValue("currency", stock.getCurrency());
		source.addValue("stockId", stock.getSymbol().substring(0, 4));
		source.addValue("name", stock.getName());
		source.addValue("stockExchange", stock.getStockExchange());

		if (jdbcTemplate.update(sql, source) == 0) {
			throw new EmptyResultDataAccessException("Nothing update in stock table, can't find stockId=" + source.getValue("stockId"), 3);
		}

	}

	public void insertStock(Stock stock) throws DataAccessException {
		String sql = "insert into stock(stockId,name,currency,stockExchange) values(:stockId,:name,:currency,:stockExchange)";
		MapSqlParameterSource source = new MapSqlParameterSource();
		source.addValue("currency", stock.getCurrency());
		source.addValue("stockId", stock.getSymbol().substring(0, 4));
		source.addValue("name", stock.getName());
		source.addValue("stockExchange", stock.getStockExchange());

		jdbcTemplate.update(sql, source);
	}

	@Autowired
	public void setJdbcTemplate(DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

}
