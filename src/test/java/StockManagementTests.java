import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StockManagementTests {

    @Test
    public void testCanGetACorrectLocatorCode() {

        // create overriding mock version of external 3rd party web service (test stub)
        ExternalISBNDataService testService = new ExternalISBNDataService() {
            @Override
            public Book lookup(String isbn) {
                return new Book(isbn, "Of Mice and Men", "J. Steinbeck");
            }
        };

        StockManager stockManager = new StockManager();
        stockManager.setService(testService);

        // Test business logic
        String isbn = "0140177396";
        String locatorCode = stockManager.getLocatorCode(isbn);
        assertEquals("7396J4", locatorCode);
    }
}
