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
	//ͨ���û�������������������û���Ϣ��Ӧ�ĳ־û�������Ҫ����Ĳ��������û���¼ʱ������û���������
	//private String hql = "from User u where u.account_no=? and u.password=?";
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
	
	//IsUserValid��������Ҫ���������������û��������롣ͨ��������������IsUserValid�������־û�����Ѱ����
	//����Ϣƥ����û��־û���������ҵ��˷���true�����û���ҵ�����false
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
	
	//getUser�������ú������������������û��������롣ͨ��������������getUser�����ӳ־û�����
	//�ҵ������Ϣƥ����û�����Ȼ�󷵻ظĶ�����øú����Ķ�ط������û���ҵ��򷵻�null
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
