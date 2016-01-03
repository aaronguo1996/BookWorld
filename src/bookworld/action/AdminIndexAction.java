package bookworld.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import bookworld.dao.ReadBookDao;
import bookworld.orm.Book;

@SuppressWarnings("serial")
public class AdminIndexAction extends ActionSupport{
	private ReadBookDao readBooks;
	private List<Book> allBooks;
	private List<Book> allBooksInPage;
	private int page;
	final int pageSize=10;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public List<Book> getAllBooksInPage() {
		return allBooksInPage;
	}

	public void setAllBooksInPage(List<Book> allBooksInPage) {
		this.allBooksInPage = allBooksInPage;
	}

	public List<Book> getAllBooks() {
		return allBooks;
	}

	public void setAllBooks(List<Book> allBooks) {
		this.allBooks = allBooks;
	}

	public ReadBookDao getReadBooks() {
		return readBooks;
	}

	public void setReadBooks(ReadBookDao readBooks) {
		this.readBooks = readBooks;
	}
	
	private List<Book> retrieveBookInAmount(){
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		List<Book>result = new ArrayList<Book>();
		for(int i=pageSize*(page);i<pageSize*(page+1);i++){
			result.add(allBooks.get(i));
		}
		session.put("listSize", allBooks.size());
		session.put("page", page);
		return result;
	}
	
	public String execute(){
		allBooks = readBooks.readAllBook();
		allBooksInPage = retrieveBookInAmount();
		return SUCCESS;
	}
}
