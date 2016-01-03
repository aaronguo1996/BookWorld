package bookworld.action;

import com.opensymphony.xwork2.ActionSupport;

import bookworld.dao.AddBookDao;
import bookworld.dao.ReadBookDao;
import bookworld.dao.ManageBookDao;
import bookworld.orm.Book;

@SuppressWarnings("serial")
public class UpdateBookAction extends ActionSupport{
	private Book book;
	private AddBookDao addBook;
	private ReadBookDao readBook;
	private ManageBookDao manageBook;
	private int bookid;
	
	public int getBookid() {
		return bookid;
	}

	public void setBookid(int bookid) {
		this.bookid = bookid;
	}

	public ReadBookDao getReadBook() {
		return readBook;
	}

	public void setReadBook(ReadBookDao readBook) {
		this.readBook = readBook;
	}

	public ManageBookDao getManageBook() {
		return manageBook;
	}

	public void setManageBook(ManageBookDao manageBook) {
		this.manageBook = manageBook;
	}

	public AddBookDao getAddBook() {
		return addBook;
	}

	public void setAddBook(AddBookDao addBook) {
		this.addBook = addBook;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}
	
	public String execute(){
		if(manageBook.UpdateBook(book))
			return SUCCESS;
		else
			return ERROR;
	}
	
	public String prepare(){
		book = readBook.readOneBook(bookid);
		return SUCCESS;
	}
	
	public void validate(){
		this.clearErrorsAndMessages();
		if(book == null) return;
		if(book.getBookName()== null){
			this.addFieldError("nameError", "book name is REQUIRED");
		}
		if(readBook.readBookByIsbn(book.getIsbn())!=null){
			this.addFieldError("isbnError","book has EXISTED");
		}
		if(book.getIsbn()==null){
			this.addFieldError("isbnError", "ISBN is REQUIRED");
		}
		if(book.getWriter()==null){
			this.addFieldError("writerError","writer is REQUIRED");
		}
		if(book.getPublisher()==null){
			this.addFieldError("pubError","publisher is REQUIRED");
		}
		if(book.getCatagory()==null){
			this.addFieldError("catError","category is REQUIRED");
		}
		if(book.getPicture()==null){
			this.addFieldError("picError", "picture is REQUIRED");
		}
		if(book.getPrice()==0){
			this.addFieldError("priceError","price is REQUIRED");
		}
		if(book.getRemaining()==0){
			this.addFieldError("amountError","amount is REQUIRED");
		}
		if(book.getDate()==null){
			this.addFieldError("yearError","publish date is REQUIRED");
		}
	}
}
