package bookworld.dao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import bookworld.orm.Order;

public class RetrieveBookDao extends HibernateDaoSupport{
	private static final Log log = LogFactory.getLog(RetrieveBookDao.class);
	
	protected void initDao(){
		
	}
	
	//retrieveBook������Ҫ����һ���������ò����Ƕ�����ID��ͨ��������ID������load�����ҵ��־û���Ķ�������
	//Ȼ�����delete����ɾ���������ɾ���ɹ��򷵻�true�����򷵻�false
	public boolean RetrieveBook(long id){
		Long i = new Long(id);
		Order order;
		
		try{
			order = (Order)this.getHibernateTemplate().load(Order.class, i);
			this.getHibernateTemplate().delete(order);
		}catch(Exception e){
			log.error(e.getMessage());
			return false;
		}
		return true;
	}
}
