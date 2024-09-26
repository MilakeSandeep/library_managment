package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LibraryService {
    private List<Book> books;

    public LibraryService() {
        books = new ArrayList<>();
    }

    // Add a new book to the library
    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added successfully.");
    }

    // Borrow a book
    public void borrowBook(String isbn) {
        Optional<Book> bookOpt = books.stream().filter(book -> book.getIsbn().equals(isbn)).findFirst();
        if (bookOpt.isPresent()) {
            Book book = bookOpt.get();
            if (book.isAvailable()) {
                book.setAvailable(false);
                System.out.println("You borrowed the book: " + book.getTitle());
            } else {
                System.out.println("Sorry, the book is already borrowed.");
            }
        } else {
            System.out.println("Book not found.");
        }
    }

    // Return a book
    public void returnBook(String isbn) {
        Optional<Book> bookOpt = books.stream().filter(book -> book.getIsbn().equals(isbn)).findFirst();
        if (bookOpt.isPresent()) {
            Book book = bookOpt.get();
            if (!book.isAvailable()) {
                book.setAvailable(true);
                System.out.println("You returned the book: " + book.getTitle());
            } else {
                System.out.println("This book was not borrowed.");
            }
        } else {
            System.out.println("Book not found.");
        }
    }

    // View available books
    public void viewAvailableBooks() {
        System.out.println("Available books:");
        books.stream().filter(Book::isAvailable).forEach(System.out::println);
    }

    // Add this getter method to access the book list in the test
    public List<Book> getBooks() {
        return books;
    }
}
