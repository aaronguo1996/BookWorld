package bookworld.dao;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Criteria;
import bookworld.orm.User;

public class RegUserDao extends HibernateDaoSupport{
	//定义一个读指定用户账户的User对象
	private String hql = "from User u where u.account_no=?";
	private static final Log log = LogFactory.getLog(LogUserDao.class);
	private MongoTemplate mongoTemplate;
	
	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	protected void initDao(){
		
	}
	
	//ifexist()函数，需要传递一个参数，该参数是用户输入的用户名，通过该参数去验证用户输入的用户名是否已经被别人注册过
	//若没有则返回false；若有则返回true
	@SuppressWarnings("rawtypes")
	public boolean ifexist(String userid){
		List ret;
		
		try{
			//ret = this.getHibernateTemplate().find(hql,userid);
			ret = mongoTemplate.find(new Query(Criteria.where("account_no").is(userid)), User.class);
		}catch(Exception e){
			log.error("read failed-RegUserDao");
			return true;
		}
		if(ret.size()>0)
			return true;
		else
			return false;
	}
	
	//regUser函数。当用户输入的信息经过验证后，就可以提交给RegUser函数进行注册
	//需要传入一个参数就是User对象，然后调用save函数进行注册
	//若注册成功返回true；若注册失败，则返回false
	public boolean RegUser(User u){
		try{
			if(ifexist(u.getAccount_no()))
				return false;
			u.setLevel(0);
			//this.getHibernateTemplate().save(u);
			mongoTemplate.save(u);
		}catch(Exception e){
			log.error("save failed-RegUserDao");
			return false;
		}
		return true;
	}
}
