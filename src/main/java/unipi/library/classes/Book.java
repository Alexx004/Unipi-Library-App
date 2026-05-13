package unipi.library.classes;

public class Book {
	
   private String isbn, title, author, publisher, category;
   private int publicationYear;
   private boolean available;
   
   // Constructor
   public Book(String isbn, String title, String author, String publisher,
               int publicationYear, String category, boolean available) {
       this.isbn = isbn;
       this.title = title;
       this.author = author;
       this.publisher = publisher;
       this.publicationYear = publicationYear;
       this.category = category;
       this.available = available;
   }
   // Getters & Setters
   public String getIsbn() {
       return isbn;
   }
   public void setIsbn(String isbn) {
       this.isbn = isbn;
   }
   public String getTitle() {
       return title;
   }
   public void setTitle(String title) {
       this.title = title;
   }
   public String getAuthor() {
       return author;
   }
   public void setAuthor(String author) {
       this.author = author;
   }
   public String getPublisher() {
       return publisher;
   }
   public void setPublisher(String publisher) {
       this.publisher = publisher;
   }
   public int getPublicationYear() {
	   return publicationYear;
   }
   public void setPublicationYear(int publicationYear) {
	   this.publicationYear = publicationYear;
   }
   public String getCategory() {
	   return category;
   }
   public void setCategory(String category) {
	   this.category = category;
   }
   public boolean isAvailable() {
       return available;
   }
   public void setAvailable(boolean available) {
       this.available = available;
   }
   
   // toString
   @Override
   public String toString() {
       return "Book [isbn=" + isbn + ", title=" + title + ", author=" + author + ", publisher=" + publisher
               + ", category=" + category + ", publicationYear=" + publicationYear + ", available=" + available + "]";
   }
}


