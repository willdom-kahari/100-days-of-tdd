package com.waduclay.day9;


import java.util.Optional;

/**
 * @author <a href="mailto:developer.wadu@gmail.com">Willdom Kahari</a>
 */
public interface BookRepository {
    Optional<Book> findByIsbn(String isbn);

    Book save(Book book);
}
