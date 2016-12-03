package com.stockbrokerfrommars.server.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.stockbrokerfrommars.server.bean.OutStock;
import com.stockbrokerfrommars.server.bean.WatchingStock;

@Component
public class OutStockDao {
	private NamedParameterJdbcTemplate dataSource;

	/**
	 * update out stock
	 * 
	 * @param inStock
	 */
	public void updateOutStock(OutStock outStock) {
		String sql = "update outstock set bestBuyingPrice = :bestBuyingPrice , bestSellingPrice = :bestSellingPrice "
				+ " where stockId = :stockId ";
		
		MapSqlParameterSource source = new MapSqlParameterSource();
		source.addValue("bestBuyingPrice", outStock.getBestBuyingPrice());
		source.addValue("bestSellingPrice", outStock.getBestSellingPrice());
		// source.addValue("amount", outStock.getAmoount());
		source.addValue("stockId", outStock.getStockId());

		if (dataSource.update(sql, source) == 0) {
			throw new EmptyResultDataAccessException(
					"Nothing update in stock table, can't find stockId=" + source.getValue("stockId"), 3);
		}
	}

	/**
	 * insert out stock when selling transaction success
	 * 
	 * @param inStock
	 */
	public void insertOutStock(OutStock outStock) {
		String sql = "insert into outstock(stockId,bestBuyingPrice,bestSellingPrice) "
				+ "values(:stockId,:bestBuyingPrice,:bestSellingPrice)";

		MapSqlParameterSource source = new MapSqlParameterSource();
		source.addValue("bestBuyingPrice", outStock.getBestBuyingPrice());
		source.addValue("bestSellingPrice", outStock.getBestSellingPrice());
		// source.addValue("amount", outStock.getAmoount());
		source.addValue("stockId", outStock.getStockId());

		dataSource.update(sql, source);
	}

	/**
	 * update out stock amount when buying transaction success
	 * 
	 * @param stockId
	 * @param amount
	 */
	public void updateOutStockAmount(String stockId, BigDecimal amount) {

		String sql = "update outstock set  amount = :amount where stockId = :stockId ";
		MapSqlParameterSource source = new MapSqlParameterSource();
		source.addValue("amount", amount);
		source.addValue("stockId", stockId);

		if (dataSource.update(sql, source) == 0) {
			throw new EmptyResultDataAccessException(
					"Nothing update in stock table, can't find stockId=" + source.getValue("stockId"), 3);
		}
	}

	/**
	 * get the stock that system is watching to buy in
	 * 
	 * @return
	 */
	public List<WatchingStock> getWatchingOutStock() {
		String sql = "SELECT outstock.stockId,outstock.bestBuyingPrice,outstock.bestSellingPrice,stockquote.price"
				+ " FROM outstock,stockquote where outstock.stockId=stockquote.stockId";
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
