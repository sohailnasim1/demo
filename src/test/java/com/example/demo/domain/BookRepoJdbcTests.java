package com.example.demo.domain;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.test.context.ActiveProfiles;

import com.example.demo.config.DataConfig;

@DataJdbcTest
@Import(DataConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("integration")
public class BookRepoJdbcTests {
	@Autowired
	private BookRepository bookRepo;
	@Autowired
	private JdbcAggregateTemplate jdbcTemplate;
@Test
public void findBookByIsbnWhenExisiting() {
	String isbn = "123456781";
	var book = Book.of(isbn, "Author", "Title", 12.90);
	jdbcTemplate.insert(book);
	Optional<Book> book1 = bookRepo.findByIsbn(isbn);
	assertThat(book1).isPresent();
	assertThat(book1.get().isbn()).isEqualTo(isbn);
}

}
