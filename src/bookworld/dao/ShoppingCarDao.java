package bookworld.dao;
import java.util.List;
import bookworld.orm.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;;

public class ShoppingCarDao extends HibernateDaoSupport{
	private static final Log log = LogFactory.getLog(ShoppingCarDao.class);
	
	private String hql = "from Order order where order.user=?";
	
	protected void initDao(){
		
	}
	
	//getAllOrders函数，传递一个参数，该参数就是用户的ID，通过用户的ID，函数从持久化层读入所有相关的订单信息。
	//若读取成功，则返回包含所有订单信息的List对象，若读取失败，则返回null
	public List getAllOrders(Long obj){
		List list = null;
		try{
			User u = (User)this.getHibernateTemplate().load(User.class, obj);
			list = this.getHibernateTemplate().find(hql,u);
		}catch(Exception e){
			log.error(e.getMessage());
			return null;
		}
		return list;
	}
}
