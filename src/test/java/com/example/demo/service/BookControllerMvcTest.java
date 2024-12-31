package com.example.demo.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.controller.BookController;
import com.example.demo.domain.BookNotFoundException;

@WebMvcTest(BookController.class)
public class BookControllerMvcTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private BookService bookService;
	
	@Test
	void testBookNotFoundException() throws Exception{
		String isbn = "1234561231";
		given(bookService.viewBookDetails(isbn)).willThrow(BookNotFoundException.class);
		mockMvc.perform(get("/bboks/"+isbn)).andExpect(status().isNotFound());
	}

}
