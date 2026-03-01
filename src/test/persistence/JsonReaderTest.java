package persistence;

import model.Category;
import model.Expense;
import model.ExpenseManager;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/manager/noSuchFile.json");
        try {
            ExpenseManager em = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyExpenseManager() {
        JsonReader reader = new JsonReader("./data/manager/testReaderEmptyExpenseManager.json");
        try {
            ExpenseManager em = reader.read();
            assertEquals("User's Expense List", em.getName());
            assertEquals(0, em.getNumExpenses());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralExpenseManager() {
        JsonReader reader = new JsonReader("./data/manager/testReaderGeneralExpenseManager.json");
        try {
            ExpenseManager em = reader.read();
            assertEquals("User's Expense List", em.getName());
            List<Expense> expenses = em.getExpenses();
            assertEquals(2, expenses.size());

            checkExpenses("coffee", Category.FOOD, LocalDate.of(2026,02,28), 3.5, expenses.get(0));
            checkExpenses("textbook", Category.SCHOOL, LocalDate.of(2026, 03, 01), 100.0, expenses.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}