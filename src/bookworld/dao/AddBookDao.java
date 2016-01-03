package bookworld.dao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import bookworld.orm.Book;

public class AddBookDao extends HibernateDaoSupport{
	private static final Log log = LogFactory.getLog(AddBookDao.class);
	
	protected void initDao(){
		
	}
	
	//函数saveBook,需要传递一个参数，新添加的书的对象。
	//如果保存成功，则返回true；否则返回false
	public boolean saveBook(Book b){
		try{
			this.getHibernateTemplate().save(b);
		}catch(Exception e ){
			log.error(e.getMessage());
			return false;
		}
		return true;
	}
}
