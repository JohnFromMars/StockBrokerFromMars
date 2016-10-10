package com.stockbrokerfrommars.web.dao;

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

import com.stockbrokerfrommars.web.bean.TxDetail;



@Component
public class TxDetailDao {
	public static final String DESC = "desc";
	public static final String ASC = "asc";
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public TxDetail getTxDetail(String txseq) {
		TxDetail txDetail = null;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("txseq", txseq);

		try {
			txDetail = jdbcTemplate.queryForObject("select * from txdetail where txseq=:txseq", param,
					new RowMapper<TxDetail>() {

						public TxDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
							TxDetail txDetail = new TxDetail();
							txDetail.setTxid(rs.getInt("txid"));
							txDetail.setType(rs.getString("type"));
							txDetail.setStockId(rs.getString("stockId"));
							txDetail.setPrice(rs.getBigDecimal("price"));
							txDetail.setAmount(rs.getInt("amount"));
							txDetail.setTxseq(rs.getString("txseq"));
							txDetail.setDateTime(rs.getTimestamp("dateTime"));
							return txDetail;
						}
					});
		} catch (EmptyResultDataAccessException e) {
			System.out.println(" no transaction detail of seq " + txseq);

		}

		return txDetail;
	}

	public List<TxDetail> getTxDetails(int limit) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("limit", limit);

		
		return jdbcTemplate.query("select * from txdetail order by txid desc limit :limit", param,
				new RowMapper<TxDetail>() {

			public TxDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
				TxDetail txDetail = new TxDetail();
				txDetail.setTxid(rs.getInt("txid"));
				txDetail.setType(rs.getString("type"));
				txDetail.setStockId(rs.getString("stockId"));
				txDetail.setPrice(rs.getBigDecimal("price"));
				txDetail.setAmount(rs.getInt("amount"));
				txDetail.setTxseq(rs.getString("txseq"));
				txDetail.setDateTime(rs.getTimestamp("dateTime"));
				return txDetail;
			}
		});

	}

}
