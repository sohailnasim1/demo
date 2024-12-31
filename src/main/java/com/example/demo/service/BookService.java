package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Book;
import com.example.demo.domain.BookAlreadyExistsException;
import com.example.demo.domain.BookNotFoundException;
import com.example.demo.domain.BookRepository;

@Service
public class BookService {
	private final BookRepository bookRepository;

	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	public Iterable<Book> viewBookList() {
		return bookRepository.findAll();
	}

	public Book viewBookDetails(String isbn) {
		return bookRepository.findByIsbn(isbn).orElseThrow(() -> new BookNotFoundException(isbn));
	}

	public Book addBookToCatalog(Book book) {
		if (bookRepository.existsByIsbn(book.isbn())) {
			throw new BookAlreadyExistsException(book.isbn());
		}
		return bookRepository.save(book);
	}

	public void removeBookFromCatalog(String isbn) {
		bookRepository.deleteByIsbn(isbn);
	}

	public Book editBookDetails(String isbn, Book book) {
		return bookRepository.findByIsbn(isbn).map(bo->{
			var updateBook = new Book(bo.isbn(),
					book.title(),
					book.author(),
					book.price()
					);
			return bookRepository.save(updateBook);
		}).orElseGet(()->addBookToCatalog(book));
	}

}
