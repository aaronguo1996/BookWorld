package bookworld.dao;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import bookworld.orm.User;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Criteria;

public class LogUserDao extends HibernateDaoSupport{
	//通过用户名和密码来查找与该用户信息对应的持久化对象，需要传入的参数就是用户登录时输入的用户名和密码
	//private String hql = "from User u where u.account_no=? and u.password=?";
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
	
	//IsUserValid函数，需要传递两个参数：用户名和密码。通过这两个参数，IsUserValid函数到持久化层中寻找与
	//该信息匹配的用户持久化对象，如果找到了返回true，如果没有找到返回false
	@SuppressWarnings("rawtypes")
	public boolean IsUserValid(String userid,String password){
		/*String[] userlist = new String[2];
		userlist[0] = userid;
		userlist[1] = password;*/
		List ret = null;
		try{
			//ret = this.getHibernateTemplate().find(hql,userlist);
			Criteria criteria = new Criteria();
			criteria.andOperator(Criteria.where("account_no").is(userid),Criteria.where("password").is(password));
			ret = mongoTemplate.find(new Query(criteria),User.class);
		}catch(Exception e){
			log.error(e.getMessage());
			return false;
		}
		if(ret.size()>0)
			return true;
		else
			return false;
	}
	
	//getUser函数。该函数传递两个参数，用户名和密码。通过这两个参数，getUser函数从持久化层中
	//找到与该信息匹配的用户对象，然后返回改对象调用该函数的额地方。如果没有找到则返回null
	@SuppressWarnings("rawtypes")
	public User getUser(String userid,String password){
		/*String[] userlist = new String[2];
		userlist[0] = userid;
		userlist[1] = password;*/
		List ret = null;
		Iterator it = null;
		
		try{
			//ret = this.getHibernateTemplate().find(hql,userlist);
			Criteria criteria = new Criteria();
			criteria.andOperator(Criteria.where("account_no").is(userid),Criteria.where("password").is(password));
			ret = mongoTemplate.find(new Query(criteria),User.class);
			it = ret.iterator();
			return (User)it.next();
		}catch(Exception e){
			log.error(e.getMessage());
			return null;
		}
	}
}
