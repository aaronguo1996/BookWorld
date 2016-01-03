package bookworld.dao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import bookworld.orm.Book;

public class ManageBookDao extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(ManageBookDao.class);
	
	protected void initDao(){
		
	}
	
	//updateUser函数，需要传递一个User对象作为参数，然后根据该对象调用update的方法来
	//更新持久化层的User对象
	public boolean UpdateBook(Book u){
		try{
			this.getHibernateTemplate().update(u);
			return true;
		}catch(Exception e){
			log.error(e.getMessage());
			return false;
		}
	}
	
	public boolean deleteBook(Book b){
		try{
			this.getHibernateTemplate().delete(b);
			return true;
		}catch(Exception e){
			log.error(e.getMessage());
			return false;
		}
	}
}
