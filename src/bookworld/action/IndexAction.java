package bookworld.action;

import com.opensymphony.xwork2.ActionSupport;

import java.util.ArrayList;
import java.util.List;

import bookworld.orm.Book;
import bookworld.dao.ReadBookDao;

@SuppressWarnings("serial")
public class IndexAction extends ActionSupport{
	private Book book;
	private String username;
	private ReadBookDao readNewBooks;
	private List<Book> newBooks = new ArrayList<Book>();

	public List<Book> getNewBooks() {
		return newBooks;
	}

	public void setNewBooks(List<Book> newBooks) {
		this.newBooks = newBooks;
	}

	public Book getBook() {
		return book;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setBook(Book book) {
		this.book = book;
	}
	
	public ReadBookDao getReadNewBooks() {
		return readNewBooks;
	}
	//spring×¢ÈëreadNewBooks
	public void setReadNewBooks(ReadBookDao readNewBooks) {
		this.readNewBooks = readNewBooks;
	}

	public String execute(){
		newBooks = readNewBooks.getNewestBooks();
		return SUCCESS;
	}
}
