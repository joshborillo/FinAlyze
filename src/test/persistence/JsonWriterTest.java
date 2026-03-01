package persistence;

import model.Category;
import model.Expense;
import model.ExpenseManager;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;


public class JsonWriterTest extends JsonTest {
    
    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/manager/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
                // pass
        }
    }

    @Test
    void testWriterEmptyExpenseManager() {
        try {
            ExpenseManager em = new ExpenseManager("User's Expense List");
            JsonWriter writer = new JsonWriter("./data/manager/testWriterEmptyExpenseManager.json");
            writer.open();
            writer.write(em);
            writer.close();

            JsonReader reader = new JsonReader("./data/manager/testWriterEmptyExpenseManager.json");
            em = reader.read();
            assertEquals("User's Expense List", em.getName());
            assertEquals(0, em.getNumExpenses());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralExpenseManager() {
        try {
            ExpenseManager em = new ExpenseManager("User's Expense List");
            em.addExpense(new Expense("coffee", LocalDate.of(2026,03,01), 3.5, Category.FOOD));
            em.addExpense(new Expense("textbook", LocalDate.of(2026,03,01), 50.0, Category.SCHOOL));

            JsonWriter writer = new JsonWriter("./data/manager/testWriterGeneralExpenseManager.json");
            writer.open();
            writer.write(em);
            writer.close();

            JsonReader reader = new JsonReader("./data/manager/testWriterGeneralExpenseManager.json");
            em = reader.read();
            assertEquals("User's Expense List", em.getName());
            assertEquals(2, em.getNumExpenses());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
