package com.stockbrokerfrommars.server.dao;

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

import com.stockbrokerfrommars.server.bean.TxDetail;

@Component
public class TxDetailDao {
	private NamedParameterJdbcTemplate dataSource;

	public void insertTxDetail(TxDetail txDetail) {
		String sql = "insert into txdetail(type,stockId,price,amount,txSeq,dateTime,resolved) "
				+ "values(:type,:stockId,:price,:amount,:txSeq,:dateTime,:resolved)";

		MapSqlParameterSource source = new MapSqlParameterSource();
		source.addValue("type", txDetail.getType());
		source.addValue("stockId", txDetail.getStockId());
		source.addValue("price", txDetail.getPrice());
		source.addValue("amount", txDetail.getAmount());
		source.addValue("txSeq", txDetail.getTxSeq());
		source.addValue("dateTime", txDetail.getDateTime());
		source.addValue("resolved", txDetail.isResolved());
		dataSource.update(sql, source);
	}

	public void updateTxDetail(TxDetail txDetail) {
		String sql = "update txdetail set type = :type , stockId = :stockId,price=:price "
				+ ", amount = :amount, dateTime=:dateTime,resolved=:resolved where txSeq = :txSeq ";

		MapSqlParameterSource source = new MapSqlParameterSource();
		source.addValue("type", txDetail.getType());
		source.addValue("stockId", txDetail.getStockId());
		source.addValue("price", txDetail.getPrice());
		source.addValue("amount", txDetail.getAmount());
		source.addValue("txSeq", txDetail.getTxSeq());
		source.addValue("dateTime", txDetail.getDateTime());
		source.addValue("resolved", txDetail.isResolved());

		if (dataSource.update(sql, source) == 0) {
			throw new EmptyResultDataAccessException(
					"Nothing update in stock table, can't find txSeq=" + source.getValue("txSeq"), 3);
		}
	}

	public TxDetail getTxDetail(String txSeq) {
		TxDetail txDetail = null;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("txSeq", txSeq);

		txDetail = dataSource.queryForObject("select * from txdetail where txSeq=:txSeq", param,
				new RowMapper<TxDetail>() {

					public TxDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
						TxDetail txDetail = new TxDetail();
						txDetail.setTxid(rs.getInt("txid"));
						txDetail.setType(rs.getString("type"));
						txDetail.setStockId(rs.getString("stockId"));
						txDetail.setPrice(rs.getBigDecimal("price"));
						txDetail.setAmount(rs.getBigDecimal("amount"));
						txDetail.setTxSeq(rs.getString("txSeq"));
						txDetail.setDateTime(rs.getTimestamp("dateTime"));
						txDetail.setResolved(rs.getBoolean("resolved"));
						return txDetail;
					}
				});

		return txDetail;
	}

	public List<TxDetail> getTxDetails(int limit) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("limit", limit);

		return dataSource.query("select * from txdetail order by txid desc limit :limit", param,
				new RowMapper<TxDetail>() {

					public TxDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
						TxDetail txDetail = new TxDetail();
						txDetail.setTxid(rs.getInt("txid"));
						txDetail.setType(rs.getString("type"));
						txDetail.setStockId(rs.getString("stockId"));
						txDetail.setPrice(rs.getBigDecimal("price"));
						txDetail.setAmount(rs.getBigDecimal("amount"));
						txDetail.setTxSeq(rs.getString("txseq"));
						txDetail.setDateTime(rs.getTimestamp("dateTime"));
						txDetail.setResolved(rs.getBoolean("resolved"));
						return txDetail;
					}
				});

	}

	public void deleteTxDetail(String txSeq) {
		String sql = "delete from txdetail where txSeq=:txSeq";
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("txSeq", txSeq);
		dataSource.update(sql, param);
	}

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = new NamedParameterJdbcTemplate(dataSource);
	}

}
