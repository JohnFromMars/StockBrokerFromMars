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

import com.stockbrokerfrommars.server.bean.InStock;
import com.stockbrokerfrommars.server.bean.WatchingStock;

@Component
public class InStockDao {
	private NamedParameterJdbcTemplate dataSource;

	/**
	 * update in stock amount when selling transaction success
	 * 
	 * @param stockId
	 * @param amount
	 */
	public void updateInStockAmount(String txSeq, BigDecimal amount) {

		String sql = "update instock set  amount = :amount where txSeq = :txSeq ";
		MapSqlParameterSource source = new MapSqlParameterSource();
		source.addValue("amount", amount);
		source.addValue("txSeq", txSeq);

		if (dataSource.update(sql, source) == 0) {
			throw new EmptyResultDataAccessException(
					"Nothing update in stock table, can't find stockId=" + source.getValue("txSeq"), 3);
		}
	}

	/**
	 * insert in stock when buying transaction success
	 * 
	 * @param inStock
	 */
	public void insertInStock(InStock inStock) {
		String sql = "insert into instock(stockId,buyingPrice,bestSellingPrice,amount,txseq) "
				+ "values(:stockId,:buyingPrice,:bestSellingPrice,:amount,:txseq)";

		MapSqlParameterSource source = new MapSqlParameterSource();
		source.addValue("buyingPrice", inStock.getBuyingPrice());
		source.addValue("bestSellingPrice", inStock.getBestSellingPrice());
		source.addValue("amount", inStock.getAmoount());
		source.addValue("stockId", inStock.getStockId());
		source.addValue("txseq", inStock.getTxSeq());
		dataSource.update(sql, source);
	}

	/**
	 * update in stock
	 * 
	 * @param inStock
	 */
	public void updateInStock(InStock inStock) {
		String sql = "update instock set buyingPrice = :buyingPrice , bestSellingPrice = :bestSellingPrice "
				+ ", amount = :amount, txseq = :txseq where stockId = :stockId ";
		MapSqlParameterSource source = new MapSqlParameterSource();
		source.addValue("buyingPrice", inStock.getBuyingPrice());
		source.addValue("bestSellingPrice", inStock.getBestSellingPrice());
		source.addValue("amount", inStock.getAmoount());
		source.addValue("stockId", inStock.getStockId());
		source.addValue("txseq", inStock.getTxSeq());
		
		if (dataSource.update(sql, source) == 0) {
			throw new EmptyResultDataAccessException(
					"Nothing update in stock table, can't find txSeq=" + source.getValue("stockId"), 3);
		}
	}
	

	/**
	 * get the stock list that customer has already bought and and waiting for
	 * sell
	 * 
	 * @return
	 */
	public List<WatchingStock> getWatchingInStock() {
		String sql = "SELECT instock.stockId,instock.buyingPrice,stockquote.price,instock.amount"
				+ ",instock.bestSellingPrice FROM instock,stockquote where instock.stockId=stockquote.stockId;";
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

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = new NamedParameterJdbcTemplate(dataSource);
	}
}
