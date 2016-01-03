package bookworld.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import bookworld.orm.Book;
import bookworld.dao.ReadBookDao;

@SuppressWarnings("serial")
public class RetrieveBookAction extends ActionSupport{
	private Book book;
	private String searchKey;
	private String searchDesc;
	private int page;
	private ReadBookDao readBook;
	private List<Book> bookResult;
	private List<Book> listBooks = new ArrayList<Book>();
	final int pageSize=10;
	
	public List<Book> getListBooks() {
		return listBooks;
	}

	public void setListBooks(List<Book> listBooks) {
		this.listBooks = listBooks;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getSearchDesc() {
		return searchDesc;
	}

	public void setSearchDesc(String searchDesc) {
		this.searchDesc = searchDesc;
	}

	
	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public ReadBookDao getReadBook() {
		return readBook;
	}

	public void setReadBook(ReadBookDao readBook) {
		this.readBook = readBook;
	}
 
	public List<Book> getBookResult() {
		return bookResult;
	}

	public void setBookResult(List<Book> bookResult) {
		this.bookResult = bookResult;
	}

	public String execute(){
		listBooks.clear();
		System.out.println(searchKey+" "+searchDesc);
		if(searchDesc==null){
			return ERROR;
		}else{
			if(searchKey.equals("BookName")){
				bookResult = readBook.readBookByName(searchDesc);
			}else if(searchKey.equals("ISBN")){
				bookResult.add(readBook.readBookByIsbn(searchDesc));
			}else if(searchKey.equals("Writer")){
				bookResult = readBook.readBookByWriter(searchDesc);
			}else if(searchKey.equals("Publisher")){
				bookResult = readBook.readBookByPublisher(searchDesc);
			}else if(searchKey.equals("PublishYear")){
				bookResult = readBook.readBookByYear(searchDesc);
			}else if(searchKey.equals("Category")){
				bookResult = readBook.getBookByCat(searchDesc);
			}else
				return ERROR;
		}
		int minimum = (page+1)*pageSize > bookResult.size() ? bookResult.size() : (page+1)*pageSize;
		System.out.println(page);
		for(int i=page*pageSize;i<minimum;i++){
			listBooks.add(bookResult.get(i));
		}
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		if(session.get("listSize")!=null)
			session.remove("listSize");
		session.put("listSize", bookResult.size());
		session.replace("page", page);
		return SUCCESS;
	}
	
	public String prepare(){
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		session.replace("page", page);
		return SUCCESS;
	}
}
