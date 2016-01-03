package bookworld.action;

import bookworld.orm.Book;
import bookworld.dao.ReadBookDao;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import java.util.HashMap;
import java.util.Map;


@SuppressWarnings("serial")
public class AddToCarAction extends ActionSupport{
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

	@SuppressWarnings("unchecked")
	public String execute(){
		/*get books from session*/
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		Map<Integer,Integer> list;
		if((list = (Map<Integer, Integer>)session.get("itemList"))==null){
			list = new HashMap<Integer,Integer>();
			list.put(bookid,1);
		}else{
			if(list.containsKey(bookid)){
				int amount = list.get(bookid);
				list.replace(bookid,amount+1);
			}else{
				list.put(bookid, 1);
			}
		}
		/*store new session*/
		session.remove("itemList");
		System.out.println(list.size());
		System.out.println(bookid);
		session.put("itemList",list);
		
		return SUCCESS;
	}
}