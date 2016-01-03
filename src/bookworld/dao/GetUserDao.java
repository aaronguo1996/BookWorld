package bookworld.dao;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Criteria;

import bookworld.orm.User;

public class GetUserDao extends HibernateDaoSupport{
	//通过用户名和密码来查找与该用户信息对应的持久化对象，需要传入的参数就是用户登录时输入的用户名和密码
	//Log对象是Log4j提供的日志记录对象，如果再进行数据库的操作中出现任何问题，都会通过Log对象记录下来
	private static final Log log = LogFactory.getLog(LogUserDao.class);
	private MongoTemplate mongoTemplate;
	
	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	protected void initDao() {
	}
	
	//getUser函数。该函数传递两个参数，用户名和密码。通过这两个参数，getUser函数从持久化层中
	//找到与该信息匹配的用户对象，然后返回改对象调用该函数的额地方。如果没有找到则返回null
	@SuppressWarnings("rawtypes")
	public User getUserByAccount(String accountNo){
		/*String hql = "from User u where u.account_no=?";
		List ret = null;
		Iterator it = null;
		
		try{
			ret = this.getHibernateTemplate().find(hql,accountNo);
			it = ret.iterator();
			return (User)it.next();
		}catch(Exception e){
			log.error(e.getMessage());
			return null;
		}*/
		return getUserByAccountMongo(accountNo);
	}
	
	public User getUserByAccountMongo(String accountNo){
		return  mongoTemplate.findOne(new Query(Criteria.where("account_no").is(accountNo)),User.class);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<User> getUserByName(String userName){
		String hql = "from User u where u.userName=?";
		List ret = null;
		
		try{
			ret = this.getHibernateTemplate().find(hql,userName);
			return ret;
		}catch(Exception e){
			log.error(e.getMessage());
			return null;
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<User> getUserByEmail(String email){
		//String hql = "from User u where u.email=?";
		List ret = null;
		
		try{
			//ret = this.getHibernateTemplate().find(hql,email);
			ret = mongoTemplate.find(new Query(Criteria.where("email").is(email)),User.class);
			return ret;
		}catch(Exception e){
			log.error(e.getMessage());
			return null;
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<User> getUserByAddress(String addr){
		//String hql = "from User u where u.address=?";
		List ret = null;
		
		try{
			//ret = this.getHibernateTemplate().find(hql,addr);
			ret = mongoTemplate.find(new Query(Criteria.where("address").is(addr)),User.class);
			return ret;
		}catch(Exception e){
			log.error(e.getMessage());
			return null;
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<User> getUserByPostcode(String postcode){
		//String hql = "from User u where u.postcode=?";
		List ret = null;
		
		try{
			//ret = this.getHibernateTemplate().find(hql,postcode);
			ret = mongoTemplate.find(new Query(Criteria.where("postcode").is(postcode)),User.class);
			return ret;
		}catch(Exception e){
			log.error(e.getMessage());
			return null;
		}
	}
}
