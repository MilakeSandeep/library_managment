package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DemoApplicationTests {

	private LibraryService libraryService;
	private Book book1;
	private Book book2;

	@BeforeEach
	void setUp() {
		// Initialize LibraryService and Book objects with Hindi book titles in English
		libraryService = new LibraryService();
		book1 = new Book("12345", "Godan", "Premchand", 1936);
		book2 = new Book("67890", "Gaban", "Premchand", 1931);

		// Add books to the library
		libraryService.addBook(book1);
		libraryService.addBook(book2);
	}


	@Test
	void testAddBook() {
		// Test adding a new Hindi book in English transliteration
		Book book3 = new Book("11111", "Kamayani", "Jaishankar Prasad", 1936);
		libraryService.addBook(book3);

		// Ensure the book was added
		assertTrue(libraryService.getBooks().contains(book3));
		assertEquals("Kamayani", book3.getTitle());
	}

	@Test
	void testBorrowBookSuccess() {
		// Test borrowing an available book
		libraryService.borrowBook("12345");
		assertFalse(book1.isAvailable());  // The book should now be marked as borrowed
	}

	@Test
	void testBorrowBookNotAvailable() {
		// Test borrowing a book that has already been borrowed
		libraryService.borrowBook("12345");
		libraryService.borrowBook("12345");  // Try borrowing again
		assertFalse(book1.isAvailable());  // The book should still be unavailable
	}

	@Test
	void testReturnBookSuccess() {
		// Test returning a borrowed book
		libraryService.borrowBook("12345");
		libraryService.returnBook("12345");
		assertTrue(book1.isAvailable());  // The book should now be available again
	}

	@Test
	void testReturnBookNotBorrowed() {
		// Test returning a book that hasn't been borrowed
		libraryService.returnBook("67890");
		assertTrue(book2.isAvailable());  // The book should still be available
	}

	@Test
	void testViewAvailableBooks() {
		// Test viewing available books before and after borrowing
		libraryService.viewAvailableBooks();  // Should show both books

		// Borrow a book and check again
		libraryService.borrowBook("12345");
		assertFalse(book1.isAvailable());  // This book should now be unavailable
	}
}
