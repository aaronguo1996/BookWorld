package bookworld.action;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;

import bookworld.orm.Book;
import bookworld.dao.ReadBookDao;

@SuppressWarnings("serial")
public class DisplayAction extends ActionSupport{
	private ReadBookDao getOneBook;
	private Book book;
	private int id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ReadBookDao getGetOneBook() {
		return getOneBook;
	}

	public void setGetOneBook(ReadBookDao getOneBook) {
		this.getOneBook = getOneBook;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public String execute(){
		book = getOneBook.readOneBook(id);
		return SUCCESS;
	}
}
