package com.stockbrokerfrommars.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.stockbrokerfrommars.server.bean.WatchingStock;



@Component
public class WatchingStockDao {
	private NamedParameterJdbcTemplate dataSource;

	/**
	 * get the stock list that customer has already bought and and waiting for
	 * sell
	 * 
	 * @return
	 */
	public List<WatchingStock> getWatchingInStock() {
		String sql = "SELECT instock.stockId,instock.buyingPrice,stockquote.price,instock.amount" + ",instock.bestSellingPrice FROM instock,stockquote where instock.stockId=stockquote.stockId;";
		MapSqlParameterSource param = new MapSqlParameterSource();

		List<WatchingStock> watchingStocks = dataSource.query(sql, param, new RowMapper<WatchingStock>() {

			@Override
			public WatchingStock mapRow(ResultSet rs, int rowNum) throws SQLException {
				WatchingStock stock = new WatchingStock();

				stock.setStockId(rs.getString("stockId"));
				stock.setCurrentPrice(rs.getBigDecimal("price"));
				stock.setBuyingPrice(rs.getBigDecimal("buyingPrice"));
				stock.setBestSellingPrice(rs.getBigDecimal("bestSellingPrice"));
				stock.setAmount(rs.getBigDecimal("amount"));
				stock.setType(WatchingStock.IN_STOCK);

				return stock;
			}
		});

		return watchingStocks;
	}

	/**
	 * get the stock that system is watching to buy in
	 * 
	 * @return
	 */
	public List<WatchingStock> getWatchingOutStock() {
		String sql = "SELECT outstock.stockId,outstock.bestBuyingPrice,outstock.bestSellingPrice,stockquote.price" + " FROM outstock,stockquote where outstock.stockId=stockquote.stockId";
		MapSqlParameterSource param = new MapSqlParameterSource();

		List<WatchingStock> watchingStocks = dataSource.query(sql, param, new RowMapper<WatchingStock>() {

			@Override
			public WatchingStock mapRow(ResultSet rs, int rowNum) throws SQLException {
				WatchingStock stock = new WatchingStock();

				stock.setStockId(rs.getString("stockId"));
				stock.setCurrentPrice(rs.getBigDecimal("price"));
				stock.setBestBuyingPrice(rs.getBigDecimal("bestBuyingPrice"));
				stock.setBestSellingPrice(rs.getBigDecimal("bestSellingPrice"));
				stock.setType(WatchingStock.OUT_STOCK);

				return stock;
			}
		});
		return watchingStocks;
	}

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = new NamedParameterJdbcTemplate(dataSource);
	}

}
