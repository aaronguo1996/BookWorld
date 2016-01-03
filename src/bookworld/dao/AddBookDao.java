package bookworld.dao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import bookworld.orm.Book;

public class AddBookDao extends HibernateDaoSupport{
	private static final Log log = LogFactory.getLog(AddBookDao.class);
	
	protected void initDao(){
		
	}
	
	//����saveBook,��Ҫ����һ������������ӵ���Ķ���
	//�������ɹ����򷵻�true�����򷵻�false
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
