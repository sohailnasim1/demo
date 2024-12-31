package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.example.demo.domain.Book;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DemoServiceApplicationTests {
	@Autowired
	private WebTestClient client;

	@Test
	void whenPostRequestBookCreated() {
		var expectedBook = new Book("1231231231", "Title", "Author", 9.90);
		client.post().uri("/books").bodyValue(expectedBook).exchange().expectStatus().isCreated().expectBody(Book.class)
				.value(actualBook -> {
					assertThat(actualBook).isNotNull();
					assertThat(actualBook.isbn()).isEqualTo(expectedBook.isbn());
				});
	}
}