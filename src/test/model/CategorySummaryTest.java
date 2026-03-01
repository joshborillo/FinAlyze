package model;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CategorySummaryTest {
    
    private CategorySummary summary;
    private Expense expense1;
    private Expense expense2;

    @BeforeEach
    public void setup() {
        summary = new CategorySummary(Category.FOOD);
        expense1 = new Expense("Coffee", LocalDate.of(2025, 2, 14),4.5, Category.FOOD);
        expense2 = new Expense("Scone", LocalDate.of(2025, 2, 14),5.0, Category.FOOD);
    }

    @Test
    public void testConstructor() {
        assertEquals(Category.FOOD, summary.getCategory());
        assertEquals(0, summary.getNumExpenses());
        assertEquals(0, summary.getTotalSpent());
    }

    @Test
    public void testAddOneExpense() {
        summary.addExpense(expense1.getAmount());
        
        assertEquals(4.5, summary.getTotalSpent());
        assertEquals(1, summary.getNumExpenses());
    }

    @Test
    public void testAddMultipleExpenses() {
        summary.addExpense(expense1.getAmount());
        summary.addExpense(expense2.getAmount());

        assertEquals(9.5, summary.getTotalSpent());
        assertEquals(2, summary.getNumExpenses());
    }

    @Test
    public void testExpenseAmountZero() {
        Expense freeCookie = new Expense("cookie", LocalDate.of(2026,02,14), 0, Category.FOOD);
        summary.addExpense(freeCookie.getAmount());

        assertEquals(0, summary.getTotalSpent());
        assertEquals(0, summary.getNumExpenses());
    }

}
