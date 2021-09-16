package br.com.svvs.jdbc.redis;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class RedisPreparedStatement extends RedisAbstractStatement implements PreparedStatement {
	
	private Map<Integer,String> parameters = new HashMap<Integer,String>();
	
	public RedisPreparedStatement(String sql, RedisConnection conn) {
		super(sql,conn);
	}

	@Override
	public void addBatch() throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void clearParameters() throws SQLException {
		this.parameters.clear();
	}

	@Override
	public boolean execute() throws SQLException {
		
		if(this.isClosed)
			throw new SQLException("This statement is closed.");
		
		// let's try to interpolate all place holders.
		int idx = 1;
		while(this.sql.indexOf("?") > 1) {
			try {
				this.sql = this.sql.replaceFirst("\\Q\u003F\\E", this.parameters.get(Integer.valueOf(idx)));
			} catch(IndexOutOfBoundsException e) {
				throw new SQLException("Can't find defined parameter for position: " + idx);
			}
			idx++;
		}
		
		return super.execute(this.sql);
	}

	@Override
	public ResultSet executeQuery() throws SQLException {
		this.execute();
		return this.resultSet;
	}

	@Override
	public int executeUpdate() throws SQLException {
		this.execute();
		return 0;
	}

	@Override
	public ResultSetMetaData getMetaData() throws SQLException {
		return null;
	}

	@Override
	public ParameterMetaData getParameterMetaData() throws SQLException {
		return null;
	}

	@Override
	public void setArray(int i, Array x) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void setAsciiStream(int arg0, InputStream arg1) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void setAsciiStream(int parameterIndex, InputStream x, int length)
			throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void setAsciiStream(int arg0, InputStream arg1, long arg2)
			throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void setBigDecimal(int parameterIndex, BigDecimal x)
			throws SQLException {
		this.pushIntoParameters(parameterIndex, x.toPlainString());
	}

	@Override
	public void setBinaryStream(int arg0, InputStream arg1) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void setBinaryStream(int parameterIndex, InputStream x, int length)
			throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void setBinaryStream(int arg0, InputStream arg1, long arg2)
			throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void setBlob(int i, Blob x) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void setBlob(int arg0, InputStream arg1) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void setBlob(int arg0, InputStream arg1, long arg2)
			throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void setBoolean(int parameterIndex, boolean x) throws SQLException {
		this.pushIntoParameters(parameterIndex, Boolean.valueOf(x).toString());
	}

	@Override
	public void setByte(int parameterIndex, byte x) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void setBytes(int parameterIndex, byte[] x) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void setCharacterStream(int arg0, Reader arg1) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void setCharacterStream(int parameterIndex, Reader reader, int length)
			throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void setCharacterStream(int arg0, Reader arg1, long arg2)
			throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void setClob(int i, Clob x) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void setClob(int arg0, Reader arg1) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void setClob(int arg0, Reader arg1, long arg2) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void setDate(int parameterIndex, Date x) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void setDate(int parameterIndex, Date x, Calendar cal)
			throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void setDouble(int parameterIndex, double x) throws SQLException {
	}

	@Override
	public void setFloat(int parameterIndex, float x) throws SQLException {
		this.pushIntoParameters(parameterIndex, Float.valueOf(x).toString());
	}

	@Override
	public void setInt(int parameterIndex, int x) throws SQLException {
		this.pushIntoParameters(parameterIndex, Integer.valueOf(x).toString());
	}

	@Override
	public void setLong(int parameterIndex, long x) throws SQLException {
		this.pushIntoParameters(parameterIndex, Long.valueOf(x).toString());
	}

	@Override
	public void setNCharacterStream(int arg0, Reader arg1) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void setNCharacterStream(int arg0, Reader arg1, long arg2)
			throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void setNClob(int arg0, NClob arg1) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void setNClob(int arg0, Reader arg1) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void setObject(int i, Object o, SQLType sqlType, int i1) throws SQLException {
		PreparedStatement.super.setObject(i, o, sqlType, i1);
	}

	@Override
	public void setObject(int i, Object o, SQLType sqlType) throws SQLException {
		PreparedStatement.super.setObject(i, o, sqlType);
	}

	@Override
	public long executeLargeUpdate() throws SQLException {
		return PreparedStatement.super.executeLargeUpdate();
	}

	@Override
	public void setNClob(int arg0, Reader arg1, long arg2) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void setNString(int arg0, String arg1) throws SQLException {
		this.pushIntoParameters(arg0, arg1);
	}

	@Override
	public void setNull(int parameterIndex, int sqlType) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void setNull(int paramIndex, int sqlType, String typeName)
			throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void setObject(int parameterIndex, Object x) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void setObject(int parameterIndex, Object x, int targetSqlType)
			throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void setObject(int parameterIndex, Object x, int targetSqlType,
			int scale) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void setRef(int i, Ref x) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void setRowId(int arg0, RowId arg1) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void setSQLXML(int arg0, SQLXML arg1) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void setShort(int parameterIndex, short x) throws SQLException {
		this.pushIntoParameters(parameterIndex, Short.valueOf(x).toString());
	}

	@Override
	public void setString(int parameterIndex, String x) throws SQLException {
		this.pushIntoParameters(parameterIndex, x);
	}

	@Override
	public void setTime(int parameterIndex, Time x) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void setTime(int parameterIndex, Time x, Calendar cal)
			throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void setTimestamp(int parameterIndex, Timestamp x)
			throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal)
			throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void setURL(int parameterIndex, URL x) throws SQLException {
		this.pushIntoParameters(parameterIndex, x.toString());
	}

	@Override
	public void setUnicodeStream(int parameterIndex, InputStream x, int length)
			throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void addBatch(String sql) throws SQLException {

	}

	@Override
	public void cancel() throws SQLException {

	}

	@Override
	public void clearBatch() throws SQLException {

	}

	@Override
	public void clearWarnings() throws SQLException {

	}

	@Override
	public void close() throws SQLException {
		this.conn = null;
		this.isClosed = true;
	}

	@Override
	public boolean execute(String sql) throws SQLException {
		this.sql = sql;
		return this.execute();
	}

	@Override
	public boolean execute(String sql, int autoGeneratedKeys)
			throws SQLException {
		return this.execute(sql);
	}

	@Override
	public boolean execute(String sql, int[] columnIndexes) throws SQLException {
		return this.execute(sql);
	}

	@Override
	public boolean execute(String sql, String[] columnNames)
			throws SQLException {
		return this.execute(sql);
	}

	@Override
	public int[] executeBatch() throws SQLException {
		return null;
	}

	@Override
	public ResultSet executeQuery(String sql) throws SQLException {
		this.execute(sql);
		return this.resultSet;
	}

	@Override
	public int executeUpdate(String sql) throws SQLException {
		this.execute(sql);
		return 0;
	}

	@Override
	public int executeUpdate(String sql, int autoGeneratedKeys)
			throws SQLException {
		this.execute(sql);
		return 0;
	}

	@Override
	public int executeUpdate(String sql, int[] columnIndexes)
			throws SQLException {
		this.execute(sql);
		return 0;
	}

	@Override
	public int executeUpdate(String sql, String[] columnNames)
			throws SQLException {
		this.execute(sql);
		return 0;
	}

	@Override
	public Connection getConnection() throws SQLException {
		return this.conn;
	}

	@Override
	public int getFetchDirection() throws SQLException {
		return ResultSet.FETCH_FORWARD;
	}

	@Override
	public int getFetchSize() throws SQLException {
		return 0;
	}

	@Override
	public ResultSet getGeneratedKeys() throws SQLException {
		return null;
	}

	@Override
	public int getMaxFieldSize() throws SQLException {
		return 0;
	}

	@Override
	public int getMaxRows() throws SQLException {
		return 0;
	}

	@Override
	public boolean getMoreResults() throws SQLException {
		return false;
	}

	@Override
	public boolean getMoreResults(int current) throws SQLException {
		return false;
	}

	@Override
	public int getQueryTimeout() throws SQLException {
		return 0;
	}

	@Override
	public ResultSet getResultSet() throws SQLException {
		return this.resultSet;
	}

	@Override
	public int getResultSetConcurrency() throws SQLException {
		return ResultSet.CONCUR_READ_ONLY;
	}

	@Override
	public int getResultSetHoldability() throws SQLException {
		return ResultSet.CLOSE_CURSORS_AT_COMMIT;
	}

	@Override
	public int getResultSetType() throws SQLException {
		return ResultSet.TYPE_FORWARD_ONLY;
	}

	@Override
	public int getUpdateCount() throws SQLException {
		return 0;
	}

	@Override
	public SQLWarning getWarnings() throws SQLException {
		return null;
	}

	@Override
	public boolean isClosed() throws SQLException {
		return this.isClosed;
	}

	@Override
	public boolean isPoolable() throws SQLException {
		return false;
	}

	@Override
	public void closeOnCompletion() throws SQLException {

	}

	@Override
	public boolean isCloseOnCompletion() throws SQLException {
		return false;
	}

	@Override
	public long getLargeUpdateCount() throws SQLException {
		return PreparedStatement.super.getLargeUpdateCount();
	}

	@Override
	public void setLargeMaxRows(long l) throws SQLException {
		PreparedStatement.super.setLargeMaxRows(l);
	}

	@Override
	public long getLargeMaxRows() throws SQLException {
		return PreparedStatement.super.getLargeMaxRows();
	}

	@Override
	public long[] executeLargeBatch() throws SQLException {
		return PreparedStatement.super.executeLargeBatch();
	}

	@Override
	public long executeLargeUpdate(String s) throws SQLException {
		return PreparedStatement.super.executeLargeUpdate(s);
	}

	@Override
	public long executeLargeUpdate(String s, int i) throws SQLException {
		return PreparedStatement.super.executeLargeUpdate(s, i);
	}

	@Override
	public long executeLargeUpdate(String s, int[] ints) throws SQLException {
		return PreparedStatement.super.executeLargeUpdate(s, ints);
	}

	@Override
	public long executeLargeUpdate(String s, String[] strings) throws SQLException {
		return PreparedStatement.super.executeLargeUpdate(s, strings);
	}

	@Override
	public void setCursorName(String name) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void setEscapeProcessing(boolean enable) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void setFetchDirection(int direction) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void setFetchSize(int rows) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void setMaxFieldSize(int max) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void setMaxRows(int max) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}
	
	@Override
	public void setPoolable(boolean arg0) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public void setQueryTimeout(int seconds) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		throw new SQLFeatureNotSupportedException();
	}
	
	private void pushIntoParameters(int index, String value) throws SQLException {
		if(index <= 0)
			throw new SQLException("Invalid position for parameter (" + index + ")");
		
		this.parameters.put(Integer.valueOf(index), value);
	}
	
}
