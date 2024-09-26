package com.example.demo;


import java.util.Scanner;

public class ManagmentApplication {

	public static void main(String[] args) {
		LibraryService libraryService = new LibraryService();
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("\nLibrary Menu:");
			System.out.println("1. Add a book");
			System.out.println("2. Borrow a book");
			System.out.println("3. Return a book");
			System.out.println("4. View available books");
			System.out.println("5. Exit");
			System.out.print("Choose an option: ");

			int choice = scanner.nextInt();
			scanner.nextLine(); // Consume newline

			switch (choice) {
				case 1:
					System.out.print("Enter ISBN: ");
					String isbn = scanner.nextLine();
					System.out.print("Enter title: ");
					String title = scanner.nextLine();
					System.out.print("Enter author: ");
					String author = scanner.nextLine();
					System.out.print("Enter publication year: ");
					int year = scanner.nextInt();
					scanner.nextLine(); // Consume newline

					Book book = new Book(isbn, title, author, year);
					libraryService.addBook(book);
					break;
				case 2:
					System.out.print("Enter ISBN of the book to borrow: ");
					String borrowIsbn = scanner.nextLine();
					libraryService.borrowBook(borrowIsbn);
					break;
				case 3:
					System.out.print("Enter ISBN of the book to return: ");
					String returnIsbn = scanner.nextLine();
					libraryService.returnBook(returnIsbn);
					break;
				case 4:
					libraryService.viewAvailableBooks();
					break;
				case 5:
					System.out.println("Exiting the system. Goodbye!");
					return;
				default:
					System.out.println("Invalid option. Try again.");
			}
		}
	}

}
