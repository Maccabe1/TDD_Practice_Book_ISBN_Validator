import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class StockManagementTests {

    @Test
    public void testCanGetACorrectLocatorCode() {

        // create overriding mock version of external 3rd party web service (test stub)
        ExternalISBNDataService testWebService = new ExternalISBNDataService() {
            @Override
            public Book lookup(String isbn) {
                return new Book(isbn, "Of Mice and Men", "J. Steinbeck");
            }
        };

        ExternalISBNDataService testDatabaseService = new ExternalISBNDataService() {
            @Override
            public Book lookup(String isbn) {
                return null;
            }
        };

        StockManager stockManager = new StockManager();
        stockManager.setWebService(testWebService);
        stockManager.setDatabaseService(testDatabaseService);

        // Test business logic
        String isbn = "0140177396";
        String locatorCode = stockManager.getLocatorCode(isbn);
        assertEquals("7396J4", locatorCode);
    }

    @Test
    public void databaseIsUsedIfDataIsPresent() {
        // Instantiate class using mock service
        ExternalISBNDataService databaseService = mock(ExternalISBNDataService.class);
        ExternalISBNDataService webService = mock(ExternalISBNDataService.class);
        when(databaseService.lookup("0140177396")).thenReturn(new Book("0140177396", "abc", "abc"));

        StockManager stockManager = new StockManager();
        stockManager.setWebService(webService);
        stockManager.setDatabaseService(databaseService);

        // Test business logic
        String isbn = "0140177396";
        String locatorCode = stockManager.getLocatorCode(isbn);
         verify(databaseService).lookup("0140177396");
         verify(webService, never()).lookup(anyString());
    }

    @Test
    public void webServiceIsUsedIfDataIsMissingInDatabase() {
        // Instantiate class using mock service
        ExternalISBNDataService databaseService = mock(ExternalISBNDataService.class);
        ExternalISBNDataService webService = mock(ExternalISBNDataService.class);

        when(databaseService.lookup("0140177396")).thenReturn(null);
        when(webService.lookup("0140177396")).thenReturn(new Book("0140177396", "abc", "abc"));

        StockManager stockManager = new StockManager();
        stockManager.setWebService(webService);
        stockManager.setDatabaseService(databaseService);

        // Test business logic
        String isbn = "0140177396";
        String locatorCode = stockManager.getLocatorCode(isbn);
        verify(databaseService).lookup("0140177396");
        verify(webService).lookup("0140177396");
    }
}
