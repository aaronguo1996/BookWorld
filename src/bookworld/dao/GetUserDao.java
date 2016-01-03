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
	//ͨ���û�������������������û���Ϣ��Ӧ�ĳ־û�������Ҫ����Ĳ��������û���¼ʱ������û���������
	//Log������Log4j�ṩ����־��¼��������ٽ������ݿ�Ĳ����г����κ����⣬����ͨ��Log�����¼����
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
	
	//getUser�������ú������������������û��������롣ͨ��������������getUser�����ӳ־û�����
	//�ҵ������Ϣƥ����û�����Ȼ�󷵻ظĶ�����øú����Ķ�ط������û���ҵ��򷵻�null
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
