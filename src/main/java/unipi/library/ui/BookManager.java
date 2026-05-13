package unipi.library.ui;

import java.util.ArrayList;
import unipi.library.classes.Book;

public class BookManager {
     
	private ArrayList<Book> books = new ArrayList<>(); //To arrayList pou perilambanei ola ta biblia ths bibiliothikis
	
	public BookManager() { //10 arxika biblia pou einai perasmena 
		books.add(new Book("9781234567890", "The Biggest Bird", "Himothy", "Publisher", 1984, "Thriller", true));
		books.add(new Book("9781234567891", "The Birth of the King", "Darduk", "Publisher", 2006, "Historical Novel", true));
		books.add(new Book("9781234567892", "An elephant with shoes", "Chris", "Publisher", 2010, "Tragedy", true));
		books.add(new Book("9781234567893", "The Art of being Nonchalant", "Alex", "Publisher", 2014, "Psychological Fiction", true));
		books.add(new Book("9781234567894", "1 year from now", "Osama", "Publisher", 2000, "Drama", true));
		books.add(new Book("9781234567895", "Vardrid", "Tim Cheese", "Publisher", 2024, "Thriller", true));
		books.add(new Book("9781234567896", "The Tragic Life of John Pork", "Elon Musk", "Publisher", 2022, "Biography", true));
		books.add(new Book("9781234567897", "One Piece", "Oda", "Publisher", 1999, "Peak Fiction", true));
		books.add(new Book("9781234567898", "The Crocodile that bombed people", "Iggy Reels", "Publisher", 2025, "Psychological Thriller", true));
		books.add(new Book("9781234567899", "The Log killer", "Tung Sahur", "Publisher", 2018, "Autobiography", true));
	}
	
	public Book createBook(String isbn, 
                           String title,
                           String author,
                           String publisher,
                           int publicationYear,
                           String category,
                           boolean available) { //methodos gia na ginontai oi metatropes twn text fields kai checkbox se allous typous metablhtwn
                                                //pou xreiazontai gia to book kai h dhmiourgia tou bibliou		
        return new Book(isbn, title, author, publisher, publicationYear, category, available);
    }

	
	public void addBook(Book book) { //methodos gia thn prosthiki bibliou sto database ths bibliothikis
		books.add(book);
	}
	
	public ArrayList<Book> getBooks() { //methodos pou epistrefei ola ta apothikeumena biblia ths bibliothikis
	    return books;
	}

	public Book getBookByIsbn(String isbn) { //methodos gia thn euresh enos bibliou mesw tou isbn tou to opoio einai monadiko gia kathe biblio
		if (books.size() == 0) { //an h lista einai kenh epistrefei null
			return null;
		}
		for (Book book : books) {
			if(book.getIsbn().equals(isbn)) {
				return book;
			}
		}
		return null;
	}

	public void removeBook(Book book) { //methodos gia thn diagrafh bibliou apo to database ths bibliothikis
		books.remove(book);
	}
}
