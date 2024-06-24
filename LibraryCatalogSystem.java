import java.util.ArrayList;
import java.util.Scanner;

class Book {
    private String title;
    private String author;
    private String isbn;
    private String category;
    private boolean available;

    public Book(String title, String author, String isbn, String category) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.category = category;
        this.available = true;
    }

    // Getters for book details
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getCategory() {
        return category;
    }

    public boolean isAvailable() {
        return available;
    }

    // Methods to check out and return the book
    public void checkOut() {
        available = false;
    }

    public void returnBook() {
        available = true;
    }
}

public class LibraryCatalogSystem {
    private static ArrayList<Book> catalog = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline character

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    searchBooks();
                    break;
                case 3:
                    viewBookDetails();
                    break;
                case 4:
                    checkoutBook();
                    break;
                case 5:
                    returnBook();
                    break;
                case 6:
                    System.out.println("Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addBook() {
        System.out.print("Enter title of the book: ");
        String title = scanner.nextLine();
        System.out.print("Enter the author name: ");
        String author = scanner.nextLine();
        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Enter the category: ");
        String category = scanner.nextLine();

        Book newBook = new Book(title, author, isbn, category);
        catalog.add(newBook);
        System.out.println("Book added successfully.");
    }

    private static void searchBooks() {
        System.out.print("Enter title or author to search: ");
        String query = scanner.nextLine().toLowerCase();
        boolean found = false;

        for (Book book : catalog) {
            if (book.getTitle().toLowerCase().contains(query) ||
                    book.getAuthor().toLowerCase().contains(query)) {
                System.out.println("Found: " + book.getTitle() + " by " + book.getAuthor());
                found = true;
            }
        }

        if (!found) {
            System.out.println("No books found matching the query.");
        }
    }

    private static void viewBookDetails() {
        System.out.print("Enter ISBN to view details: ");
        String isbn = scanner.nextLine();

        for (Book book : catalog) {
            if (book.getIsbn().equals(isbn)) {
                System.out.println("Title: " + book.getTitle());
                System.out.println("Author: " + book.getAuthor());
                System.out.println("Category: " + book.getCategory());
                System.out.println("Available: " + (book.isAvailable() ? "Yes" : "No"));
                return;
            }
        }

        System.out.println("Book not found.");
    }

    private static void checkoutBook() {
        System.out.print("Enter ISBN to checkout: ");
        String isbn = scanner.nextLine();

        for (Book book : catalog) {
            if (book.getIsbn().equals(isbn)) {
                if (book.isAvailable()) {
                    book.checkOut();
                    System.out.println("Book checked out successfully.");
                } else {
                    System.out.println("Book is already checked out.");
                }
                return;
            }
        }

        System.out.println("Book not found.");
    }

    private static void returnBook() {
        System.out.print("Enter ISBN to return: ");
        String isbn = scanner.nextLine();

        for (Book book : catalog) {
            if (book.getIsbn().equals(isbn)) {
                if (!book.isAvailable()) {
                    book.returnBook();
                    System.out.println("Book returned successfully.");
                } else {
                    System.out.println("Book is already available.");
                }
                return;
            }
        }

        System.out.println("Book not found.");
    }

    private static void displayMenu() {
        System.out.println("\nLibrary Catalog System");
        System.out.println("1. Add a new book");
        System.out.println("2. Search books");
        System.out.println("3. View book details");
        System.out.println("4. Checkout a book");
        System.out.println("5. Return a book");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }
}
