package server.model.entity;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class OnlineUserTableModel extends AbstractTableModel {
    private static final long serialVersionUID = -444245379288364831L;

    /**
     * 列名标题
     */
    private String[] title = {"账号", "昵称", "性别"};

    /**
     * 数据行
     */
    private List<String[]> rows = new ArrayList<String[]>();

    /**
     * 获取行数
     * @return 数据行数
     */
    public int getRowCount() {
        return rows.size();
    }

    /**
     * 获取列数
     * @return 列数
     */
    public int getColumnCount() {
        return title.length;
    }

    /**
     * 获取列名
     * @param column 列索引
     * @return 列名
     */
    public String getColumnName(int column) {
        return title[column];
    }

    /**
     * 获取单元格值
     * @param row 行索引
     * @param column 列索引
     * @return 单元格值
     */
    public String getValueAt(int row, int column) {
        return (rows.get(row))[column];
    }

    /**
     * 添加一行数据
     * @param value 待添加的数据
     */
    public void add(String[] value) {
        int row = rows.size();
        rows.add(value);
        fireTableRowsInserted(row, row);
    }

    /**
     * 删除一行数据
     * @param id 待删除数据的账号ID
     */
    public void remove(long id) {
        int row = -1;
        for (int i = 0; i <= rows.size(); i++) {
            if (String.valueOf(id).equals(getValueAt(i , 0))) {
                row = i;
                break;
            }
        }
        rows.remove(row);
        fireTableRowsDeleted(2, 3);
    }
}

