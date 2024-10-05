/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Capa_Presentacion;

/**
 *
 * @author Eduardo
 */
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class CurrencyCellRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        // Verificar si el valor es un número
        if (value instanceof Number) {
            // Formatear el valor a String con el símbolo "S/"
            String formattedValue = "S/ " + String.format("%.2f", ((Number) value).doubleValue());
            setText(formattedValue);
        } else {
            setText(value != null ? value.toString() : "");
        }

        return cell;
    }
}
