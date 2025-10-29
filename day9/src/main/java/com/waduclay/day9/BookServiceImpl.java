package com.waduclay.day9;


import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:developer.wadu@gmail.com">Willdom Kahari</a>
 */
@Service
public class BookServiceImpl implements BookService{
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book findByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn)
                .orElseThrow(()-> new IllegalArgumentException("Book with ISBN %s not found".formatted(isbn)));
    }

    @Override
    public Book borrowBook(String isbn) {
        Book book = findByIsbn(isbn);
        if (book.isAvailable()){
            book.setAvailable(false);
            return bookRepository.save(book);
        }
        throw new IllegalStateException("The book is not available for borrowing");
    }
}
