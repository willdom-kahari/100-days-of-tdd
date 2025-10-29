package com.waduclay.day8;


/**
 * @author <a href="mailto:developer.wadu@gmail.com">Willdom Kahari</a>
 */
public class Book {
    private String isbn;
    private String title;
    private String genre;

    public Book(String isbn, String title, String genre) {
        this.isbn = isbn;
        this.title = title;
        this.genre = genre;
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


}
