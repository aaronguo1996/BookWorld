package bookworld.action;

import bookworld.dao.ManageBookDao;
import bookworld.dao.ReadBookDao;
import bookworld.orm.Book;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class DeleteBookAction extends ActionSupport{
	private int bookid;
	private Book book;
	private ManageBookDao manageBook;
	private ReadBookDao readBook;
	
	public int getBookid() {
		return bookid;
	}

	public void setBookid(int bookid) {
		this.bookid = bookid;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public ManageBookDao getManageBook() {
		return manageBook;
	}

	public void setManageBook(ManageBookDao manageBook) {
		this.manageBook = manageBook;
	}

	public ReadBookDao getReadBook() {
		return readBook;
	}

	public void setReadBook(ReadBookDao readBook) {
		this.readBook = readBook;
	}

	public String execute(){
		book = readBook.readOneBook(bookid);
		if(manageBook.deleteBook(book)){
			return SUCCESS;
		}else{
			return ERROR;
		}
	}
}
