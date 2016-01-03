package bookworld.action;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import bookworld.dao.StatisticDao;
import bookworld.orm.Order;
import bookworld.orm.OrderItem;
import bookworld.orm.Book;

@SuppressWarnings("serial")
public class StatBookAction extends ActionSupport{
	private String searchCat;
	private String bookName;
	private StatisticDao statDao;
	private String beginDate;
	private String endDate;
	private int amount;
	private List<Integer>amountsByCat = Arrays.asList(0,0,0,0,0,0);
	
	public List<Integer> getAmountsByCat() {
		return amountsByCat;
	}

	public void setAmountsByCat(List<Integer> amountsByCat) {
		this.amountsByCat = amountsByCat;
	}

	public String getSearchCat() {
		return searchCat;
	}

	public void setSearchCat(String searchCat) {
		this.searchCat = searchCat;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public StatisticDao getStatDao() {
		return statDao;
	}

	public void setStatDao(StatisticDao statDao) {
		this.statDao = statDao;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@SuppressWarnings("unchecked")
	public String execute() throws ParseException{
		for(int i=0;i<amountsByCat.size();i++){
			amountsByCat.set(i, 0);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date begin = new java.sql.Date(sdf.parse(beginDate).getTime());
		Date end = new java.sql.Date(sdf.parse(endDate).getTime());
		amount = 0;
		List<Order> list = (List<Order>)statDao.getOrderByDate(begin, end);
		for(int i=0;i<list.size();i++){
			List<OrderItem> items = (List<OrderItem>)statDao.getItemInOrder(list.get(i).getId());
			for(int j=0;j<items.size();j++){
				Book book = items.get(j).getBook();
				if(searchCat.equals("bookName")){//calculate one book amount and money
					if(book.getBookName().equals(bookName)){
						amount+=items.get(j).getQuantity();
					}
				}else if(searchCat.equals("allBook")){//calculate all books by category
					int index = -1;
					if(book.getCatagory().equals("novel")){
						index = 0;
					}else if(book.getCatagory().equals("literature")){
						index = 1;
					}else if(book.getCatagory().equals("poem")){
						index = 2;
					}else if(book.getCatagory().equals("prose")){
						index = 3;
					}else if(book.getCatagory().equals("study")){
						index = 4;
					}else if(book.getCatagory().equals("dictionary")){
						index = 5;
					}
					int tmp = amountsByCat.get(index);
					tmp+=items.get(j).getQuantity();
					amountsByCat.set(index, tmp);
				}else{//calculate one category amount
					if(book.getCatagory().equals(searchCat)){
						amount+=items.get(j).getQuantity();
					}
				}
			}
		}
		return SUCCESS;
	}
	
	public void validate(){
		if(beginDate == null){
			this.addFieldError("beginError", "Begin date is required");
		}
		if(endDate == null){
			this.addFieldError("endError","End date is required");
		}
	}
}
