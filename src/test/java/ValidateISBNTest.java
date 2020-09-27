import org.junit.Test;

import static org.junit.Assert.*;

public class ValidateISBNTest {

    @Test
    public void checkValidISBN() {
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("0140449116");
        assertTrue("first value", result);
        result = validator.checkISBN("0140177396");
        assertTrue("second value", result);
    }

    @Test
    public void checkAnInvalidISBN() {
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("0140449117");
        assertFalse(result);
    }

    @Test
    public void ISBNEndingWithXAreValid() {
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("014044911X");
        assertTrue(result);
    }

    @Test
    public void nineDigitISBNsAreNotAllowed() {
        ValidateISBN validator = new ValidateISBN();
        assertThrows(NumberFormatException.class,
            () -> { validator.checkISBN("123456789");
        });
    }

    @Test
    public void noneNumericISBNsAreNotAllowed() {
        ValidateISBN validator = new ValidateISBN();
        assertThrows(NumberFormatException.class,
                () -> { validator.checkISBN("helloworld");
        });
    }
}
