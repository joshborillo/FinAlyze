package ui;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    private ExpenseListPanel tablePanel;
    private JButton addButton;

    /*
    Main graphical user interface for the app. This class represents the main window (JFrame), 
    manages user interactions through buttons and menus, and handles persistent data storage (saving/loading). 
    It organizes the layout by nesting sub-panels like the ExpenseListPanel, and handles top-level action events.
     */
    public GUI() {
        super("FinAlyze");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 500);
        
        setJMenuBar(createMenuBar());

        // initialize custom table panel
        tablePanel = new ExpenseListPanel();
        add(tablePanel, BorderLayout.CENTER);

        addButton = new JButton("Add New Expense");
        add(addButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // File
        JMenu fileMenu = new JMenu("File");
        JMenu reportMenu = new JMenu("Analze");

        JMenuItem saveItem = new JMenuItem("Save Data");
        JMenuItem loadItem = new JMenuItem("Load Data");
        JMenuItem categorySummary = new JMenuItem("Category Summary");
        JMenuItem monthlyCategorySummary = new JMenuItem("Monthly Category Summary");

        fileMenu.add(saveItem);
        fileMenu.add(loadItem);
        reportMenu.add(categorySummary);
        reportMenu.add(monthlyCategorySummary);

        menuBar.add(fileMenu);
        menuBar.add(reportMenu);
        
        return menuBar;
    }
}
