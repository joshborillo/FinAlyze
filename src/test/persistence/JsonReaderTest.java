package persistence;

import model.Category;
import model.Expense;
import model.ExpenseManager;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
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
}