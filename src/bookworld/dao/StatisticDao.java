package bookworld.dao;
import java.util.List;
import java.sql.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import bookworld.orm.Book;
import bookworld.orm.OrderItem;
import bookworld.orm.Order;


public class StatisticDao extends HibernateDaoSupport{
	private static final Log log = LogFactory.getLog(CreateOrderItemDao.class);
	
	protected void initDao(){
		
	}
	
	public List getOrderByUser(int userid,Date begin,Date end){
		String hql = "from Order as order where order.user.id = ? and order.date >= ? and order.date <= ?";
		try{
			List list = this.getHibernateTemplate().find(hql,userid,begin,end);
			return list;
		}catch(Exception e){
			log.error(e.getMessage());
			return null;
		}
	}
	
	public List getOrderByDate(Date begin,Date end){
		String hql = "from Order as order where order.date >= ? and order.date <= ?";
		try{
			List list = this.getHibernateTemplate().find(hql,begin,end);
			return list;
		}catch(Exception e){
			log.error(e.getMessage());
			return null;
		}
	}
	
	public List getItemInOrder(int orderid){
		String hql = "from OrderItem as item where item.order.id = ?";
		try{
			List list = this.getHibernateTemplate().find(hql,orderid);
			return list;
		}catch(Exception e){
			log.error(e.getMessage());
			return null;
		}
	}
	
	public List calculateByBook(int bookid){
		String hql = "from OrderItem as item where item.book.id = ?";
		try{
			List list = this.getHibernateTemplate().find(hql,bookid);
			return list;
		}catch(Exception e){
			log.error(e.getMessage());
			return null;
		}
	}
	
	public List calculateByCat(Date begin,Date end){
		String hql = "select from"
				+ "(select * from Book as book where book.id in (select bookid from OrderItem as item where item.order.id in (select order.id from Order as order where order.date >= ? and order.date <= ?)))"
				+ "group by catagory ";
		return null;
	}
}
