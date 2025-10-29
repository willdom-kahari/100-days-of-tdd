package com.waduclay.day8;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author <a href="mailto:developer.wadu@gmail.com">Willdom Kahari</a>
 */
@WebMvcTest(BookController.class)
class BookControllerTest {
    @MockitoBean
    private BookService bookService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnBookMatchingTheIsbnGiven() throws Exception {
        Book book = new Book("12345", "WaduClay", "Fiction");

        when(bookService.findByIsbn(book.getIsbn())).thenReturn(book);

        mockMvc.perform(get("/books/12345"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isbn").value(book.getIsbn()))
                .andExpect(jsonPath("$.title").value(book.getTitle()))
                .andExpect(jsonPath("$.genre").value(book.getGenre()));
    }

}
