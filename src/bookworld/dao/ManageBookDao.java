package bookworld.dao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import bookworld.orm.Book;

public class ManageBookDao extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(ManageBookDao.class);
	
	protected void initDao(){
		
	}
	
	//updateUser��������Ҫ����һ��User������Ϊ������Ȼ����ݸö������update�ķ�����
	//���³־û����User����
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
