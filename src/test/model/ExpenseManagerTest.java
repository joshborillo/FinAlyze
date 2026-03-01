package model;

import java.nio.file.FileVisitOption;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ExpenseManagerTest {

    private ExpenseManager manager;
    private Expense coffee;
    private Expense scone;
    private Expense uber;

    @BeforeEach
    public void setup() {
        manager = new ExpenseManager("User's Expense List");
        coffee = new Expense("Coffee", LocalDate.of(2025,2,14), 4.50, Category.FOOD);
        scone = new Expense("scone", LocalDate.of(2025,2,14), 5.00, Category.FOOD);
        uber = new Expense("Uber", LocalDate.of(2025, 3, 20), 20, Category.TRAVEL);
    }

    @Test
    public void testConstructor() {
        // empty summary
        MonthlySummary summary = manager.getMonthlySummary(3, 2025);

        assertEquals(3, summary.getMonth());
        assertEquals(2025, summary.getYear());
        assertEquals(0, summary.getTotalSpent());
        assertTrue(summary.getCategoryTotals().isEmpty());
    }

    @Test
    public void testAddExpense() {
        manager.addExpense(coffee);

        MonthlySummary summary = manager.getMonthlySummary(2, 2025);

        assertEquals(4.50, summary.getTotalSpent());
        assertEquals(4.50, summary.getCategoryTotals().get(Category.FOOD));
    }

    @Test
    public void testAddMultipleExpensesSameMonth() {
        manager.addExpense(coffee);
        manager.addExpense(scone);

        MonthlySummary summary = manager.getMonthlySummary(2, 2025);

        assertEquals(9.5, summary.getTotalSpent());
        assertEquals(9.5, summary.getCategoryTotals().get(Category.FOOD));
    }

    @Test
    public void testAddExpensesDiffMonth() {
        manager.addExpense(coffee);
        manager.addExpense(scone);
        manager.addExpense(uber);

        MonthlySummary february = manager.getMonthlySummary(2, 2025);
        MonthlySummary march = manager.getMonthlySummary(3, 2025);

        assertEquals(9.50, february.getTotalSpent());
        assertEquals(20, march.getTotalSpent());
    }

    @Test
    public void testRemoveExpense() {    
        manager.addExpense(coffee);
        manager.addExpense(scone);

        MonthlySummary february = manager.getMonthlySummary(2, 2025);
        assertEquals(9.50, february.getTotalSpent());

        manager.removeExpense(scone);
        assertEquals(4.50, manager.getMonthlySummary(2, 2025).getTotalSpent());
    }

    @Test
    public void testRemoveAddExpense() {
        Expense muffin = new Expense("Muffin", LocalDate.of(2025, 2, 14), 3.50, Category.FOOD);

        manager.addExpense(coffee);
        manager.addExpense(scone);

        MonthlySummary february = manager.getMonthlySummary(2, 2025);
        assertEquals(9.50, february.getTotalSpent());

        manager.removeExpense(scone);
        manager.addExpense(muffin);

        assertEquals(8.00, manager.getMonthlySummary(2, 2025).getTotalSpent());
    }

    @Test
    public void testRemoveExpenseFromCategory() {
        manager.addExpense(coffee);
        manager.addExpense(scone);
        
        MonthlySummary february = manager.getMonthlySummary(2, 2025);
        assertEquals(9.50, february.getTotalSpent());

        manager.removeExpenseFromCategory(scone, Category.FOOD);
        assertEquals(4.50, manager.getCategorySummary(Category.FOOD).getTotalSpent());
    }

    @Test
    public void testGetCategorySummary() {
        Expense sandwich = new Expense("Sandwich", LocalDate.of(2025, 4, 2), 10, Category.FOOD);

        manager.addExpense(coffee);
        manager.addExpense(sandwich);

        CategorySummary cs = manager.getCategorySummary(Category.FOOD);

        assertEquals(14.50, cs.getTotalSpent());
        assertEquals(2, cs.getNumExpenses());
    }

    @Test
    public void testGetCategorySummaryEmpty() {
        CategorySummary cs = manager.getCategorySummary(Category.SCHOOL);

        assertEquals(0, cs.getTotalSpent());
        assertEquals(0, cs.getNumExpenses());
    }

}
