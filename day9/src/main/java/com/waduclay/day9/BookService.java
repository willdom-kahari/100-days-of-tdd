package com.waduclay.day9;


/**
 * @author <a href="mailto:developer.wadu@gmail.com">Willdom Kahari</a>
 */
public interface BookService {
    Book findByIsbn(String isbn);

    Book borrowBook(String isbn);
}
