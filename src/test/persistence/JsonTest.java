package persistence;

import model.Category;
import model.Expense;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
public class JsonTest {
    protected void checkExpenses(String name, Category category, LocalDate date, double amount, Expense expense) {
        assertEquals(name, expense.getItem());
        assertEquals(date, expense.getDate());
        assertEquals(amount, expense.getAmount());
        assertEquals(category, expense.getCategory());
        assertEquals(name + " $" + amount, expense.toString());
    }
}
