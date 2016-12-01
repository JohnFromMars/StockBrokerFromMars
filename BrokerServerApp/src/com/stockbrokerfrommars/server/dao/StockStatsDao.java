package com.stockbrokerfrommars.server.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import yahoofinance.quotes.stock.StockStats;

@Component
public class StockStatsDao {

	private NamedParameterJdbcTemplate jdbcTemplate;

	public void updateStockStats(StockStats stockStats) throws DataAccessException {
		String sql = "update stockstats set marketCap = :marketCap , sharesFloat = :sharesFloat , sharesOutstanding = :sharesOutstanding, sharesOwned = :sharesOwned, eps = :eps "
				+ ", pe = :pe, peg = :peg , epsEstimateCurrentYear = :epsEstimateCurrentYear "
				+ ", epsEstimateNextQuarter = :epsEstimateNextQuarter , epsEstimateNextYear = :epsEstimateNextYear , priceBook = :priceBook , priceSales = :priceSales"
				+ ", bookValuePerShare = :bookValuePerShare  , revenue = :revenue ,EBITDA = :EBITDA , oneYearTargetPrice = :oneYearTargetPrice "
				+ ", shortRatio = :shortRatio, ROE = :ROE where stockId = :stockId ";

		MapSqlParameterSource source = new MapSqlParameterSource();
		source.addValue("marketCap", stockStats.getMarketCap());
		source.addValue("sharesFloat", stockStats.getSharesFloat());
		source.addValue("sharesOutstanding", stockStats.getSharesOutstanding());
		source.addValue("sharesOwned", stockStats.getSharesOwned());
		source.addValue("eps", stockStats.getEps());
		source.addValue("pe", stockStats.getPe());
		source.addValue("peg", stockStats.getPeg());
		source.addValue("epsEstimateCurrentYear", stockStats.getEpsEstimateCurrentYear());
		source.addValue("epsEstimateNextQuarter", stockStats.getEpsEstimateNextQuarter());
		source.addValue("epsEstimateNextYear", stockStats.getEpsEstimateNextYear());
		source.addValue("priceBook", stockStats.getPriceBook());
		source.addValue("priceSales", stockStats.getPriceSales());
		source.addValue("bookValuePerShare", stockStats.getBookValuePerShare());
		source.addValue("EBITDA", stockStats.getEBITDA());
		source.addValue("oneYearTargetPrice", stockStats.getOneYearTargetPrice());
		source.addValue("shortRatio", stockStats.getShortRatio());
		source.addValue("revenue", stockStats.getRevenue());
		source.addValue("stockId", stockStats.getSymbol().substring(0,4));
		source.addValue("ROE", stockStats.getROE());

		if (jdbcTemplate.update(sql, source) == 0) {
			throw new EmptyResultDataAccessException("Nothing update in stockstats table, can't find stockId=" + source.getValue("stockId"), 3);
		}

	}

	public void insertStockStats(StockStats stockStats) throws DataAccessException {
		String sql = "insert into stockStats(marketCap,sharesFloat,sharesOutstanding,sharesOwned,eps,pe, peg,epsEstimateCurrentYear"
				+ ",epsEstimateNextQuarter, epsEstimateNextYear,priceBook,priceSales,bookValuePerShare,EBITDA,oneYearTargetPrice,shortRatio,revenue,stockId,ROE)"
				+ " values( :marketCap, :sharesFloat, :sharesOutstanding, :sharesOwned, :eps, :pe, :peg, :epsEstimateCurrentYear, :epsEstimateNextQuarter"
				+ ", :epsEstimateNextYear, :priceBook, :priceSales, :bookValuePerShare, :EBITDA, :oneYearTargetPrice, :shortRatio, :revenue, :stockId, :ROE)";

		MapSqlParameterSource source = new MapSqlParameterSource();
		source.addValue("marketCap", stockStats.getMarketCap());
		source.addValue("sharesFloat", stockStats.getSharesFloat());
		source.addValue("sharesOutstanding", stockStats.getSharesOutstanding());
		source.addValue("sharesOwned", stockStats.getSharesOwned());
		source.addValue("eps", stockStats.getEps());
		source.addValue("pe", stockStats.getPe());
		source.addValue("peg", stockStats.getPeg());
		source.addValue("epsEstimateCurrentYear", stockStats.getEpsEstimateCurrentYear());
		source.addValue("epsEstimateNextQuarter", stockStats.getEpsEstimateNextQuarter());
		source.addValue("epsEstimateNextYear", stockStats.getEpsEstimateNextYear());
		source.addValue("priceBook", stockStats.getPriceBook());
		source.addValue("priceSales", stockStats.getPriceSales());
		source.addValue("bookValuePerShare", stockStats.getBookValuePerShare());
		source.addValue("EBITDA", stockStats.getEBITDA());
		source.addValue("oneYearTargetPrice", stockStats.getOneYearTargetPrice());
		source.addValue("shortRatio", stockStats.getShortRatio());
		source.addValue("revenue", stockStats.getRevenue());
		source.addValue("stockId", stockStats.getSymbol().substring(0,4));
		source.addValue("ROE", stockStats.getROE());

		jdbcTemplate.update(sql, source);
	}

	@Autowired
	public void setJdbcTemplate(DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

}
