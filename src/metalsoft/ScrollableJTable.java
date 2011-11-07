/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import metalsoft.util.JTableUtils;

public class ScrollableJTable {

    public static void main(String[] args) {
        new ScrollableJTable();
    }

    public ScrollableJTable() {
        JFrame frame = new JFrame("Creating a Scrollable JTable!");
        JPanel panel = new JPanel();
        String data[][] = {{"001", "vinod", "Bihar", "India", "Biology", "65", "First"},
            {"002", "Raju", "ABC", "Kanada", "Geography", "58", "second"},
            {"003", "Aman", "Delhi", "India", "computer", "98", "Dictontion"},
            {"004", "Ranjan", "Bangloor", "India", "chemestry", "90", "Dictontion"},
            {"001", "vinod", "Bihar", "India", "Biology", "65", "First"},
            {"002", "Raju", "ABC", "Kanada", "Geography", "58", "second"},
            {"003", "Aman", "Delhi", "India", "computer", "98", "Dictontion"},
            {"004", "Ranjan", "Bangloor", "India", "chemestry", "90", "Dictontion"},
            {"001", "vinod", "Bihar", "India", "Biology", "65", "First"},
            {"002", "Raju", "ABC", "Kanada", "Geography", "58", "second"},
            {"003", "Aman", "Delhi", "India", "computer", "98", "Dictontion"},
            {"004", "Ranjan", "Bangloor", "India", "chemestry", "90", "Dictontion"},
            {"001", "vinod", "Bihar", "India", "Biology", "65", "First"},
            {"002", "Raju", "ABC", "Kanada", "Geography", "58", "second"},
            {"003", "Aman", "Delhi", "India", "computer", "98", "Dictontion"},
            {"004", "Ranjan", "Bangloor", "India", "chemestry", "90", "Dictontion"},
            {"001", "vinod", "Bihar", "India", "Biology", "65", "First"},
            {"002", "Raju", "ABC", "Kanada", "Geography", "58", "second"},
            {"003", "Aman", "Delhi", "India", "computer", "98", "Dictontion"},
            {"004", "Ranjan", "Bangloor", "India", "chemestry", "90", "Dictontion"},
            {"001", "vinod", "Bihar", "India", "Biology", "65", "First"},
            {"002", "Raju", "ABC", "Kanada", "Geography", "58", "second"},
            {"003", "Aman", "Delhi", "India", "computer", "98", "Dictontion"},
            {"004", "Ranjan", "Bangloor", "India", "chemestry", "90", "Dictontion"},
            {"001", "vinod", "Bihar", "India", "Biology", "65", "First"},
            {"002", "Raju", "ABC", "Kanada", "Geography", "58", "second"},
            {"003", "Aman", "Delhi", "India", "computer", "98", "Dictontion"},
            {"004", "Ranjan", "Bangloor", "India", "chemestry", "90", "Dictontion"},
            {"001", "vinod", "Bihar", "India", "Biology", "65", "First"},
            {"002", "Raju", "ABC", "Kanada", "Geography", "58", "second"},
            {"003", "Aman", "Delhi", "India", "computer", "98", "Dictontion"},
            {"0044523532543253254", "Ranjanvdfsvdvfdfvdsvfdsvfdfvdfsv", "Bangloor", "India", "chemestry", "90", "Dictontion"}};
        String col[] = {"Roll", "Name", "State", "country", "Math", "Marks", "Grade"};
        JTable table = new JTable(data, col);
        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.yellow);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);        // Adiciona dados aqui        int vColIndex = 1;
        int margin = 2;
        JTableUtils.packColumns(table, margin);

        JScrollPane pane = new JScrollPane(table);
        panel.add(pane);
        frame.add(panel);
        frame.setSize(700, 500);
        frame.setUndecorated(true);
        frame.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}