package findmycareer;


import java.awt.Component;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
* @author Robert Strachan
 * @StudentNO: 9105393616
 * @version 0.1
 * @Date 11/06/2017
 * @Bugs Table combo box render not working right
 */
public class ComboBoxTableCellRenderer extends JComboBox implements TableCellRenderer  {
        @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setSelectedItem(value);
        return this;
    }


}
