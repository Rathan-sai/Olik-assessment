# Book Library Management System

Welcome to the Book Library Management System! This application allows you to manage a library of books, authors, and rentals.

## Setup Instructions

To run the application locally, follow these steps:

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/your-username/book-library-management.git
   ```

2. **Navigate to the Project Directory:**
   ```bash
   cd book-library-management
   ```

3. **Build the Project:**
   ```bash
   mvn clean package
   ```

4. **Run the Application:**
   ```bash
   java -jar target/book-library-management.jar
   ```

## How to Run the Application

Once the application is running, you can access the endpoints using a tool like Postman or cURL.

## Sample Requests

Here are some sample requests to test the endpoints:

- **Create a new book:**
  ```http
  POST /Book/CreateBook
  Content-Type: application/json

  {
    "title": "Sample Book",
    "authorId": "2",
    "isbn": "9783161484100",
    "publicationYear": "2022"
  }
  ```

- **Get all books:**
  ```http
  GET /Book/getAllBooks
  ```

- **Get a specific book by ID:**
  ```http
  GET /Book/getBookById/{id}
  ```

- **Update a book:**
  ```http
  PUT /Book/UpdateBook/{id}
  Content-Type: application/json

  {
    "title": "Updated Book Title",
    "authorId": "2",
    "isbn": "9786161484100",
    "publicationYear": "2022"
  }
  ```

- **Delete a book:**
  ```http
  DELETE /Book/removeBook/{id}
  ```

## Additional Documentation

### Design Decisions

- **Database Schema**: We chose to use a relational database with tables for books, authors, and rentals to organize and manage the data efficiently.

- **RESTful Architecture**: The application follows RESTful principles to provide a standardized and predictable interface for clients to interact with.

### Assumptions

- **ISBN Format**: We assume that ISBNs provided for books follow the standard format, including hyphens for separation.

- **Rental Duration**: We assume a default rental duration of 14 days for each book rental.
