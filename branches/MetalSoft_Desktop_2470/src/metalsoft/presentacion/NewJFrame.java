/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * NewJFrame.java
 *
 * Created on 19/10/2010, 13:21:26
 */
package metalsoft.presentacion;

import java.awt.BorderLayout;
import java.awt.geom.Arc2D;
import java.util.GregorianCalendar;
import javax.swing.JFrame;
import org.jdesktop.swingx.plaf.ShapeUIResource;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.LegendItem;
import org.jfree.chart.LegendItemCollection;
import org.jfree.chart.LegendItemSource;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.Title;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.time.Month;

/**
 *
 * @author Nino
 */
public class NewJFrame extends javax.swing.JFrame {

    /** Creates new form NewJFrame */
    public NewJFrame() {
        

        initComponents();
        TaskSeriesCollection dataset = new TaskSeriesCollection();
        TaskSeries unavailable = new TaskSeries("Unavailable");
        Task room1 = new Task("19/10/2010",
                new GregorianCalendar(2009, Month.DECEMBER, 1, 8, 00).getTime(),
                new GregorianCalendar(2009, Month.DECEMBER, 1, 17, 00).getTime());
        unavailable.add(room1);

        room1.addSubtask(new Task("Etapa 1",
                new GregorianCalendar(2009, Month.DECEMBER, 1, 9, 00).getTime(),
                new GregorianCalendar(2009, Month.DECEMBER, 1, 16, 00).getTime()));

        Task room2 = new Task("20/10/2010",
                new GregorianCalendar(2009, Month.DECEMBER, 1, 8, 00).getTime(),
                new GregorianCalendar(2010, Month.JANUARY, 1, 17, 00).getTime());
        unavailable.add(room2);

        room2.addSubtask(new Task("Meeting 2",
                new GregorianCalendar(2009, Month.DECEMBER, 1, 10, 00).getTime(),
                new GregorianCalendar(2009, Month.DECEMBER, 1, 11, 00).getTime()));

        room2.addSubtask(new Task("Meeting 3",
                new GregorianCalendar(2009, Month.DECEMBER, 1, 14, 00).getTime(),
                new GregorianCalendar(2009, Month.DECEMBER, 1, 15, 00).getTime()));
        room2.addSubtask(new Task("Meeting 4",
                new GregorianCalendar(2009, Month.DECEMBER, 1, 16, 00).getTime(),
                new GregorianCalendar(2009, Month.DECEMBER, 1, 18, 00).getTime()));

        dataset.add(unavailable);

// title, domain axis, range axis, dataset, legend, tooltip, urls
        JFreeChart chart = ChartFactory.createGanttChart("Produccion", "Room", "Time", dataset, true, true, false);
        ChartPanel chartPanel = new ChartPanel(chart);
        chart.setBorderVisible(true);
        this.getContentPane().add(chartPanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(50, 50, 800, 200);
        JFrame frame = new JFrame("MeetNow!");
        frame.getContentPane().add(chartPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setBounds(50, 50, 800, 200);
        frame.setVisible(true);

//         create a dataset...
//        DefaultPieDataset data = new DefaultPieDataset();
//        data.setValue("Category 1", 43.2);
//        data.setValue("Category 2", 27.9);
//        data.setValue("Category 3", 79.5);
//// create a chart...
//        JFreeChart chart = ChartFactory.createPieChart(
//                "Sample Pie Chart",
//                data,
//                true, // legend?
//                true, // tooltips?
//                false // URLs?
//                );
//        ChartFrame frame=new ChartFrame("chart", chart);
//        frame.pack();
//        frame.setVisible(true);

        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 998, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 524, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new NewJFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
