package bookworld.action;

import bookworld.orm.Book;
import bookworld.dao.ReadBookDao;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


@SuppressWarnings("serial")
public class ShoppingCarAction extends ActionSupport{
	private int bookid;
	private Map<Book,Integer> books;
	private ReadBookDao bookDao;
	
	public Map<Book, Integer> getBooks() {
		return books;
	}

	public void setBooks(Map<Book, Integer> books) {
		this.books = books;
	}

	public ReadBookDao getBookDao() {
		return bookDao;
	}

	public void setBookDao(ReadBookDao bookDao) {
		this.bookDao = bookDao;
	}

	public int getBookid() {
		return bookid;
	}

	public void setBookid(int bookid) {
		this.bookid = bookid;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String execute(){
		/*get books from session*/
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		Map<Integer,Integer> list;
		list = (Map)session.get("itemList");
		if(list == null){
			return SUCCESS;
		}
		/*get books in session to put into shopping car*/
		Iterator it = list.keySet().iterator();
		books = new HashMap<Book,Integer>();
		double orderPrice = 0;
		while(it.hasNext()){
			int id = (Integer)it.next();
			Book book = bookDao.readOneBook(id);
			orderPrice += book.getPrice()*list.get(id);
			books.put(book,list.get(id));
		}
		/*store new session*/
		session.remove("itemList");
		session.put("itemList",list);
		if(session.get("orderPrice")!=null)
			session.remove("orderPrice");
		session.put("orderPrice", orderPrice);
		
		return SUCCESS;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String addOne(){
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		Map<Integer,Integer> list = (Map<Integer,Integer>)session.get("itemList");
		if(list.containsKey(bookid)){
			int amount = list.get(bookid);
			list.replace(bookid,amount+1);
		}
		/*get books in session to put into shopping car*/
		Iterator it = list.keySet().iterator();
		books = new HashMap<Book,Integer>();
		double orderPrice = 0;
		while(it.hasNext()){
			int id = (Integer)it.next();
			Book book = bookDao.readOneBook(id);
			orderPrice += book.getPrice()*list.get(id);
			books.put(book,list.get(id));
		}
		/*store new session*/
		session.remove("itemList");
		session.put("itemList",list);
		if(session.get("orderPrice")!=null)
			session.remove("orderPrice");
		session.put("orderPrice", orderPrice);
		return SUCCESS;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String deleteOne(){
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		Map<Integer,Integer> list = (Map<Integer,Integer>)session.get("itemList");
		if(list.containsKey(bookid)){
			int amount = list.get(bookid);
			if(amount == 1){
				list.remove(bookid);
			}
			list.replace(bookid,amount-1);
		}
		/*get books in session to put into shopping car*/
		Iterator it = list.keySet().iterator();
		books = new HashMap<Book,Integer>();
		double orderPrice = 0;
		while(it.hasNext()){
			int id = (Integer)it.next();
			Book book = bookDao.readOneBook(id);
			orderPrice += book.getPrice()*list.get(id);
			books.put(book,list.get(id));
		}
		/*store new session*/
		session.remove("itemList");
		session.put("itemList",list);
		if(session.get("orderPrice")!=null)
			session.remove("orderPrice");
		session.put("orderPrice", orderPrice);
		return SUCCESS;
	}
	
	public String clear(){
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		session.remove("itemList");
		return SUCCESS;
	}
}
