package com.example.demo.test;

import java.util.List;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.example.demo.domain.Book;
import com.example.demo.domain.BookRepository;

@Component
@Profile("testdata")
public class BookDataLoader {
	private final BookRepository repo;
	public BookDataLoader(BookRepository repo) {
		this.repo = repo;
	}
	
	@EventListener(ApplicationReadyEvent.class)
	public void loadBookTestData() {
		repo.deleteAll();
		Book book1 = Book.of("1234567891", "title 1", "author 1", 9.90);
		Book book2 = Book.of("1234567892", "title 2", "author 2", 10.90);
		repo.saveAll(List.of(book1, book2));
	}
}
