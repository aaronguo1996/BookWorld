package bookworld.dao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

import bookworld.orm.Book;

public class ReadBookDao extends HibernateDaoSupport{
	private static final Log log = LogFactory.getLog(LogUserDao.class);
	
	protected void initDao(){
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Book> readAllBook(){
		try{
			String hql = "from Book as b";
			List<Book> list = (List<Book>) this.getHibernateTemplate().find(hql);
			return list;
		}catch(Exception e){
			log.error(e.getMessage());
			return null;
		}
	}
	
	public Book readOneBook(int id){
		try{
			Book b = (Book)this.getHibernateTemplate().load(Book.class,id);
			return b;
		}catch(Exception e){
			log.error(e.getMessage());
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Book> getNewestBooks(){
		try{
			String hql = "from Book as book where book.id >= ((select max(id) from Book)-8)";
			List<Book> list = (List<Book>)this.getHibernateTemplate().find(hql);
			return list;
		}catch(Exception e){
			log.error(e.getMessage());
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Book> getBookByCat(String cat){
		try{
			String hql = "from Book as b where b.catagory=?";
			List<Book> list = (List<Book>) this.getHibernateTemplate().find(hql,cat);
			return list;
		}catch(Exception e){
			log.error(e.getMessage());
			return null;
		}
	}
	
	public Book readBookByIsbn(String isbn){
		try{
			Book b = (Book)this.getHibernateTemplate().load(Book.class,isbn);
			return b;
		}catch(Exception e){
			log.error(e.getMessage());
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Book> readBookByWriter(String writer){
		try{
			String hql = "from Book as b where b.writer=?";
			List<Book> list = (List<Book>) this.getHibernateTemplate().find(hql,writer);
			return list;
		}catch(Exception e){
			log.error(e.getMessage());
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Book> readBookByPublisher(String publisher){
		try{
			String hql = "from Book as b where b.publisher=?";
			List<Book> list = (List<Book>) this.getHibernateTemplate().find(hql,publisher);
			return list;
		}catch(Exception e){
			log.error(e.getMessage());
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Book> readBookByYear(String year){
		try{
			String hql = "from Book as b where b.date=?";
			List<Book> list = (List<Book>) this.getHibernateTemplate().find(hql,year);
			return list;
		}catch(Exception e){
			log.error(e.getMessage());
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Book> readBookByAmount(int amount){
		try{
			String hql = "from Book as b where b.remaining < ?";
			List<Book> list = (List<Book>) this.getHibernateTemplate().find(hql,amount);
			return list;
		}catch(Exception e){
			log.error(e.getMessage());
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Book> readBookByPrice(double lowPrice,double highPrice){
		try{
			String hql = "from Book as b where b.price >= ? and b.price <= ?";
			List<Book> list = (List<Book>) this.getHibernateTemplate().find(hql,lowPrice,highPrice);
			return list;
		}catch(Exception e){
			log.error(e.getMessage());
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Book> readBookByName(String name){
		try{
			String hql = "from Book as b where b.bookName=?";
			List<Book> list = (List<Book>) this.getHibernateTemplate().find(hql,name);
			return list;
		}catch(Exception e){
			log.error(e.getMessage());
			return null;
		}
	}
	
	public List<Book> readBook(String key){
		try{
			key = '%'+key+'%';
			String hql = "from Book as b where b.bookName like ? or b.isbn like ? or b.catagory like ? or b.writer like ? or b.publisher like ?";
			List list = this.getHibernateTemplate().find(hql,key,key,key,key,key);
			return list;
		}catch(Exception e){
			log.error(e.getMessage());
			return null;
		}
	}
}
