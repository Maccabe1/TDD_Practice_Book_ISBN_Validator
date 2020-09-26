public class ValidateISBN {

    public boolean checkISBN(String isbn) {
        int total = 0;

        if(isbn.length() != 10) {
            throw new NumberFormatException("ISBN must be 10 digits long");
        }

        for(int i = 0; i < 10; i++) {
            if (!Character.isDigit(isbn.charAt(i)))
                throw new NumberFormatException("ISBN must be 10 digits long");
         total += isbn.charAt(i) * (10 -i);
        }

        if(total % 11 == 0) {
            return true;
        } else {
            return false;
        }
    }
}
