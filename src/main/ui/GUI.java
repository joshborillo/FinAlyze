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
        setSize(600, 400);
        setLayout(new BorderLayout());

        // initialize custom table panel
        tablePanel = new ExpenseListPanel();
        add(tablePanel, BorderLayout.CENTER);

        addButton = new JButton("Add New Expense");
        add(addButton, BorderLayout.SOUTH);

        setVisible(true);
    }
}
