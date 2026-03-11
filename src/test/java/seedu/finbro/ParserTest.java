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

    // Test when the input command is null
    @Test
    public void parse_nullInput_exceptionThrown() {
        ExpenseList expenses = new ExpenseList();
        Ui ui = new Ui();

        assertThrows(NullPointerException.class, () ->
                Parser.parse(null, expenses, ui));
    }

    // Test if non-numeric amount is rejected
    @Test
    public void parse_addInvalidAmount_correctErrorMessage() {
        ExpenseList expenses = new ExpenseList();
        Ui ui = new Ui();

        FinbroException exception = assertThrows(FinbroException.class, () ->
                Parser.parse("add abc food 11/03/2026", expenses, ui));

        assertEquals("Amount must be a number.", exception.getMessage());
    }

    // Test if wrong date format is rejected
    @Test
    public void parse_addInvalidDate_correctErrorMessage() {
        ExpenseList expenses = new ExpenseList();
        Ui ui = new Ui();

        FinbroException exception = assertThrows(FinbroException.class, () ->
                Parser.parse("add 10 food 2026-03-11", expenses, ui));

        assertEquals("Invalid date format! Use dd/MM/yyyy", exception.getMessage());
    }

    // Test if missing add arguments are rejected
    @Test
    public void parse_addMissingArguments_correctErrorMessage() {
        ExpenseList expenses = new ExpenseList();
        Ui ui = new Ui();

        FinbroException exception = assertThrows(FinbroException.class, () ->
                Parser.parse("add 10 food", expenses, ui));

        assertEquals("Usage: add <amount> <category> <date>", exception.getMessage());
    }
}
