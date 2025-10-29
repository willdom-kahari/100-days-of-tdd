package com.waduclay.day9;


/**
 * @author <a href="mailto:developer.wadu@gmail.com">Willdom Kahari</a>
 */
public class Book {
    private String isbn;
    private String title;
    private String genre;
    private boolean available;

    public Book(String isbn, String title, String genre, boolean available) {
        this.isbn = isbn;
        this.title = title;
        this.genre = genre;
        this.available = available;
    }

    public String getIsbn() {
        return isbn;
    }



    public String getTitle() {
        return title;
    }



    public String getGenre() {
        return genre;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
