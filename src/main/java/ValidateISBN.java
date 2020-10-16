public class ValidateISBN {

    private static final int LONG_ISBN = 13;
    private static final int SHORT_ISBN = 10;
    public static final int LONG_ISBN_MULTIPLIER = 10;
    public static final int SHORT_ISBN_MULTIPLIER = 11;

    public boolean checkISBN(String isbn) {
        if (isbn.length() == LONG_ISBN) {
            return checkIfNumberIsAValidLongDigitISBN(isbn);
        } else {

            if (isbn.length() != SHORT_ISBN) throw new NumberFormatException("ISBN must be 10 digits long");
            return checkIfNumberIsAValidShortDigitISBN(isbn);
        }
    }

    private boolean checkIfNumberIsAValidShortDigitISBN(String isbn) {
        int total = 0;

        for (int i = 0; i < SHORT_ISBN; i++) {
            if (!Character.isDigit(isbn.charAt(i))) {
                if (i == 9 && isbn.charAt(i) == 'X') {
                    total += 10;
                } else {
                    throw new NumberFormatException("ISBN must be 10 digits long");
                }
            } else {
                total += Character.getNumericValue(isbn.charAt(i)) * (10 - i);
            }
        }
        return (total % SHORT_ISBN_MULTIPLIER == 0);
    }

    private boolean checkIfNumberIsAValidLongDigitISBN(String isbn) {
        int total = 0;

        for (int i = 0; i < LONG_ISBN; i++) {
            if (i % 2 == 0) {
                total += Character.getNumericValue(isbn.charAt(i));
            } else {
                total += Character.getNumericValue(isbn.charAt(i)) * 3;
            }
        }
        return (total % LONG_ISBN_MULTIPLIER == 0);
    }
}
