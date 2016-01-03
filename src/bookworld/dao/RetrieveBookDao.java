package bookworld.dao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import bookworld.orm.Order;

public class RetrieveBookDao extends HibernateDaoSupport{
	private static final Log log = LogFactory.getLog(RetrieveBookDao.class);
	
	protected void initDao(){
		
	}
	
	//retrieveBook函数需要传递一个参数，该参数是订单的ID。通过订单的ID，调用load函数找到持久化层的订单对象，
	//然后调用delete函数删除对象，如果删除成功则返回true，否则返回false
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
