package ru.roman.pammcontr.gui.common.grid;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roman on 29.01.2015.
 */
public class ListTableModel<T> extends AbstractTableModel {

    protected List<T[]> data;
    protected String[] columnInfo;

    public ListTableModel() {
        this(new String[0]);
    }

    public ListTableModel(String[] columnInfo) {
        this(columnInfo, new ArrayList<T[]>());
    }

    public ListTableModel(String[] columnInfo, List<T[]> data) {
        this.data = data;
        this.columnInfo = columnInfo;
    }

    public List<T[]> getData() {
        return data;
    }

    public String[] getColumnInfo() {
        return columnInfo;
    }

    public int getRowCount() {
        return data.size();
    }

    public T getValueAt(int row, int col) {
        return data.get(row)[col];
    }

    public int getColumnCount() {
        return columnInfo.length;
    }

    @Override
    public String getColumnName(int col) {
        return columnInfo[col];
    }

    @Override
    public void setValueAt(Object val, int row, int col) {
        data.get(row)[col] = (T)val;
    }

    public void setData(String[] columnInfo, List<T[]> dataTable) {
        this.columnInfo = columnInfo;
        this.data = dataTable;
    }

    public void setColumnInfo(String[] columnInfo) {
        this.columnInfo = columnInfo;
    }

    public void setData(List<T[]> dataTable) {
        this.data = dataTable;
    }
}

