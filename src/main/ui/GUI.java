package ui;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    private ExpenseListPanel tablePanel;
    private JButton addButton;

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
