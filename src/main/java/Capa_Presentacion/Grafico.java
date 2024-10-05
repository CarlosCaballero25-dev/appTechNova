/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Capa_Presentacion;

import Controlador.Graficos;
import com.toedter.calendar.JDateChooser;
import java.util.Date;

/**
 *
 * @author Eduardo
 */
public class Grafico extends javax.swing.JPanel {

    public Grafico(JDateChooser dcInicio, JDateChooser dcFin) {
        initComponents();
        // Llama al método para graficar utilizando las fechas
        Graficos.Graficar(dcInicio, dcFin);
        // Aquí puedes agregar el componente gráfico que genera el gráfico basado en los datos
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JPanelGrafico = new javax.swing.JPanel();

        setPreferredSize(new java.awt.Dimension(610, 540));

        javax.swing.GroupLayout JPanelGraficoLayout = new javax.swing.GroupLayout(JPanelGrafico);
        JPanelGrafico.setLayout(JPanelGraficoLayout);
        JPanelGraficoLayout.setHorizontalGroup(
            JPanelGraficoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 610, Short.MAX_VALUE)
        );
        JPanelGraficoLayout.setVerticalGroup(
            JPanelGraficoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 540, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JPanelGrafico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JPanelGrafico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JPanelGrafico;
    // End of variables declaration//GEN-END:variables
}