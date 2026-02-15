package model;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MonthlySummaryTest {
    private MonthlySummary summary;

    @BeforeEach
    public void setup() {
        summary = new MonthlySummary(2, 2025);
    }

    // starting with zero dollars
    @Test
    public void testConstructor() {
        assertEquals(2, summary.getMonth());
        assertEquals(2025, summary.getYear());
        assertEquals(0, summary.getTotalSpent());
    }

    @Test
    public void testCategoryTotalsEmpty() {
        assertTrue(summary.getCategoryTotals().isEmpty());
        assertEquals(0, summary.getTotalSpent());
    }

    @Test
    public void testAddExpenseCategory() {
        summary.addExpense(Category.FOOD, 9.5);

        assertEquals(9.5, summary.getTotalSpent());
        assertEquals(9.5, summary.getCategoryTotals().get(Category.FOOD));
    }

    @Test
    public void testAddMultipleCategories() {
        summary.addExpense(Category.FOOD, 9.5);
        summary.addExpense(Category.TRAVEL, 10);

        assertEquals(19.5, summary.getTotalSpent());
        assertEquals(9.5, summary.getCategoryTotals().get(Category.FOOD));
        assertEquals(10, summary.getCategoryTotals().get(Category.TRAVEL));
    }

}
