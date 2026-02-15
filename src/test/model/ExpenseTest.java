package model;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ExpenseTest {
    private Expense expense;

    @BeforeEach
    void runBefore() {
        expense = new Expense("Coffee", LocalDate.of(2025, 2, 14), 4.5, Category.FOOD);
    }

    @Test
    void testGetItem() {
        assertEquals("Coffee", expense.getItem());
    }

    @Test 
    void testGetAmount() {
        assertEquals(4.5, expense.getAmount());
    }

    @Test
    void testGetDate() {
        assertEquals(LocalDate.of(2025, 2, 14), expense.getDate());
    }

    @Test
    void testGetCategory() {
        assertEquals(Category.FOOD, expense.getCategory());
    }

}
