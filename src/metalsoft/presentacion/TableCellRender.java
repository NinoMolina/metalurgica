/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.presentacion;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Nino
 */
public class TableCellRender extends DefaultTableCellRenderer{

    private ArrayList<Integer> rowNoSeleccionable;


    public TableCellRender()
    {
        rowNoSeleccionable=new ArrayList<Integer>();
    }

    public ArrayList<Integer> getRowNoSeleccionable() {
        return rowNoSeleccionable;
    }

    public void setRowNoSeleccionable(ArrayList<Integer> rowNoSeleccionable) {
        this.rowNoSeleccionable = rowNoSeleccionable;
    }

    private boolean rowInRowNoSeleccionable(int row)
    {
        //Iterator<Integer> i=rowNoSeleccionable.iterator();
        return rowNoSeleccionable.contains(row);
    }
    @Override
    public Component getTableCellRendererComponent(JTable table,
    Object value,
    boolean isSelected,
    boolean hasFocus,
    int row,
    int column)
    {
        super.getTableCellRendererComponent (table, value, isSelected, hasFocus, row, column);
        if(rowInRowNoSeleccionable(row))
        {
            this.setOpaque(true);
            this.setBackground(Color.red);
        }
//        else
//        {
//            this.setOpaque(true);
//            this.setBackground(Color.green);
//        }
    //          if (isSelected)
    //          {
    //             this.setOpaque(true);
    //             this.setBackground(Color.BLACK);
    //             this.setForeground(Color.YELLOW);
    //          }
        return this;
    }

}
