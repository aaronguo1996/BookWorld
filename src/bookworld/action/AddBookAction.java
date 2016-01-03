package bookworld.action;

import com.opensymphony.xwork2.ActionSupport;

import bookworld.dao.AddBookDao;
import bookworld.dao.ReadBookDao;
import bookworld.orm.Book;

@SuppressWarnings("serial")
public class AddBookAction extends ActionSupport{
	private Book book;
	private AddBookDao addBook;
	private ReadBookDao readBook;
	
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
		if(addBook.saveBook(book))
			return SUCCESS;
		else
			return ERROR;
	}
	
	public void validate(){
		this.clearErrorsAndMessages();
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
