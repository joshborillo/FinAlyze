package ui;

import model.Expense;
import model.Category;
import java.util.List;
import java.util.ArrayList;

import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AddExpenseDialog extends JDialog {
    private DefaultTableModel stagingModel;
    private JTable stagingTable;
    private List<Expense> newExpenses;

    public AddExpenseDialog(JFrame parent) {
        super(parent, "Add Multiple Expenses", true);
        setLayout(new BorderLayout());
        setSize(500, 400);
        newExpenses = new ArrayList<>();

        // Setup
        JPanel inputPanel = new JPanel(new GridLayout(0, 2));

        JTextField itemField = new JTextField();
        JTextField dateField = new JTextField(LocalDate.now().toString());
        JTextField amountField = new JTextField();
        JComboBox<Category> categoryBox = new JComboBox<>(Category.values());
        JButton addToListButton = new JButton("Add to List");

        inputPanel.add(new JLabel("Item:"));
        inputPanel.add(itemField);
        inputPanel.add(new JLabel("Date (YYYY-MM-DD):"));
        inputPanel.add(dateField);
        inputPanel.add(new JLabel("Amount ($):"));
        inputPanel.add(amountField);
        inputPanel.add(new JLabel("Category:"));
        inputPanel.add(categoryBox);

        // Staging Table
        stagingModel = new DefaultTableModel(new String[] {"Item", "Amount", "Date", "Category"}, 0);
        stagingTable = new JTable(stagingModel);

        // "Add to List" button
        JButton confirmButton = new JButton("Confirm All and Close");

        addToListButton.addActionListener(e -> {
            try {
                String item = itemField.getText();
                LocalDate date = LocalDate.parse(dateField.getText());
                double amount = Double.parseDouble(amountField.getText());
                Category category = (Category) categoryBox.getSelectedItem();

                stagingModel.addRow(new Object[] {item, date, amount, category});
                newExpenses.add(new Expense(item, date, amount, category));

                itemField.setText("");
                dateField.setText(LocalDate.now().toString());
                amountField.setText("");
                categoryBox.setSelectedIndex(0);

            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(this, "Date must be YYYY-MM-DD");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Amount must be a number");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input! Please check date format and amount.");
            }
        });

        confirmButton.addActionListener(e -> dispose());

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(stagingTable), BorderLayout.CENTER);
        add(confirmButton, BorderLayout.SOUTH);
    }

    public List<Expense> getExpenses() {
        return newExpenses;
    }
}
