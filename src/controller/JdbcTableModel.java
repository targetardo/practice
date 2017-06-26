package controller;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.sql.RowSet;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import com.sun.rowset.CachedRowSetImpl;

public class JdbcTableModel implements TableModel {
	
	private ResultSet rs;
	private RowSet rowSet;
	private ResultSetMetaData metadata;
	private int columnCount;
	private int rowCount;

	public JdbcTableModel(ResultSet rs)  {
		try {
			this.rs = rs;
			metadata = rs.getMetaData();
			columnCount = metadata.getColumnCount();
			rowSet = new CachedRowSetImpl();
			((CachedRowSetImpl)rowSet).populate(rs);
			this.rowSet.beforeFirst();
			this.rowCount = 0;
			while (this.rowSet.next()) 	this.rowCount++;
			this.rowSet.beforeFirst();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int getRowCount() {
		return rowCount;
	}
	
	@Override
	public int getColumnCount() {
		return columnCount;
	}

	@Override
	public String getColumnName(int columnIndex) {
		try {
	        return this.metadata.getColumnLabel(columnIndex + 1);
	    } catch (SQLException e) {
	        return e.toString();
	    }
		}
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		 return String.class;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		 try {
		        this.rowSet.absolute(rowIndex + 1);
		        Object o = this.rowSet.getObject(columnIndex + 1);
		        if (o == null)
		            return null;
		        else
		            return o.toString();
		    } catch (SQLException e) {
		        return e.toString();
		    }
	}

	@Override
	public void addTableModelListener(TableModelListener arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeTableModelListener(TableModelListener arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setValueAt(Object arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
