package client.model.entity;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import common.model.entity.User;

/**
 * 自定义列表的单元格渲染器
 */
public class MyCellRenderer extends JLabel implements ListCellRenderer {
    private static final long serialVersionUID = 3460394416991636990L;

    /**
     * 获取列表单元格渲染器组件
     * @param list 列表对象
     * @param value 单元格值
     * @param index 单元格索引
     * @param isSelected 是否选中
     * @param cellHasFocus 是否有焦点
     * @return 组件对象
     */
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        User user = (User)value;
        String name = user.getNickname() + "(" + user.getId() + ")";
        setText(name);
        setIcon(user.getHeadIcon());
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
        setEnabled(list.isEnabled());
        setFont(list.getFont());
        setOpaque(true);
        return this;
    }
}
