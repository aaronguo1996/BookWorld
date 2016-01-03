package bookworld.action;

import java.util.List;

import bookworld.dao.ReadBookDao;

import com.opensymphony.xwork2.ActionSupport;

public class SearchAction extends ActionSupport{
	private String searchKey;
	private List searchBooks;
	private ReadBookDao readBook;
	
	public String getSearchKey() {
		return searchKey;
	}



	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}



	public List getSearchBooks() {
		return searchBooks;
	}



	public void setSearchBooks(List searchBooks) {
		this.searchBooks = searchBooks;
	}



	public ReadBookDao getReadBook() {
		return readBook;
	}



	public void setReadBook(ReadBookDao readBook) {
		this.readBook = readBook;
	}



	public String execute(){
		searchBooks = readBook.readBook(searchKey);
		return SUCCESS;
	}
}
