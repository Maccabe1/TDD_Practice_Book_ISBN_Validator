public class Book {

    private String isbn;
    private String title;
    private String author;

    public Book(String isbn, String title, String author) {
        super();
        this.isbn = isbn;
        this.title = title;
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getIsbn() {
        return isbn;
    }
}
