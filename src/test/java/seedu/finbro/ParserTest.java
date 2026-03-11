package seedu.finbro;

import org.junit.jupiter.api.Test;
import seedu.finbro.exception.FinbroException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    //Test if expenses can be successfully added to the list, then the no. of items in expenses list should be 1
    @Test
    public void parse_addValidExpense_expenseAdded() throws FinbroException {
        ExpenseList expenses = new ExpenseList();
        Ui ui = new Ui();

        Parser.parse("add 10 food 11/03/2026", expenses, ui);

        assertEquals(1, expenses.size());
    }

    //This test simulates when user type in an invalid command
    @Test
    public void parse_invalidCommand_correctErrorMessage() {
        ExpenseList expenses = new ExpenseList();
        Ui ui = new Ui();

        FinbroException exception = assertThrows(FinbroException.class, () ->
                Parser.parse("adahwdwada", expenses, ui));

        assertEquals("Invalid command.", exception.getMessage());
    }
}
