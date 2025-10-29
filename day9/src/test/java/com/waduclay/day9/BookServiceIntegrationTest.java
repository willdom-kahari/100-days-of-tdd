package com.waduclay.day9;

import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

/**
 * @author <a href="mailto:developer.wadu@gmail.com">Willdom Kahari</a>
 */
@SpringBootTest
public class BookServiceIntegrationTest {
    @Autowired
    private BookService bookService;

    @MockitoBean
    private BookRepository bookRepository;

    @Test
    void shouldReturnBookWhenIsbnIsPassed() {
        // Arrange
        Book book = new Book("12345", "Hiwe", "comedy", true);
        Optional<Book> optionalBook = Optional.of(book);
        when(bookRepository.findByIsbn(book.getIsbn())).thenReturn(optionalBook);

        // Act
        Book byIsbn = bookService.findByIsbn(book.getIsbn());

        // Assert
        assertThat(byIsbn)
                .usingRecursiveComparison()
                .isEqualTo(book);

        // Verify
        verify(bookRepository, times(1)).findByIsbn(any());
    }

    @Test
    void shouldThrowAnExceptionWhenBookIsNotFound(){

        when(bookRepository.findByIsbn(any())).thenReturn(Optional.empty());

        assertThatThrownBy(()-> bookService.findByIsbn("1234"))
                .isInstanceOf(IllegalArgumentException.class);

        verify(bookRepository, times(1)).findByIsbn("1234");

    }

    @Test
    void shouldThrowAnExceptionIfWeTryBorrowingAnUnAvailableBook() {
        // Arrange
        Book book = new Book("12345", "Hiwe", "comedy", false);
        when(bookRepository.findByIsbn("12345")).thenReturn(Optional.of(book));

        // Act & Assert
        assertThatThrownBy(() -> bookService.borrowBook("12345"))
                .isInstanceOfAny(IllegalStateException.class);

        // Verify
        verify(bookRepository, times(1)).findByIsbn(any());
    }

    @Test
    void shouldBorrowAnAvailableBook() {
        // Arrange
        Book book = new Book("12345", "Hiwe", "comedy", true);
        Book savedBook = new Book("12345", "Hiwe", "comedy", false);
        when(bookRepository.findByIsbn("12345")).thenReturn(Optional.of(book));
        when(bookRepository.save(any())).thenReturn(savedBook);

        // Act
        Book borrowedBook = bookService.borrowBook("12345");

        // Assert
        assertThat(borrowedBook)
                .isNotNull()
                .usingRecursiveComparison()
                .ignoringFields("available")
                .isEqualTo(book);

        assertThat(borrowedBook.isAvailable())
                .isFalse();

        // Verify the number of times the repository method is called
        verify(bookRepository, times(1)).findByIsbn("12345");
        verify(bookRepository, times(1)).save(any());

        // Verify the order of execution
        InOrder inOrder = inOrder(bookRepository);
        inOrder.verify(bookRepository).findByIsbn("12345");
        inOrder.verify(bookRepository).save(any());

    }

}
