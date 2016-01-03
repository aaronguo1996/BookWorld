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
	//����һ����ָ���û��˻���User����
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
	
	//ifexist()��������Ҫ����һ���������ò������û�������û�����ͨ���ò���ȥ��֤�û�������û����Ƿ��Ѿ�������ע���
	//��û���򷵻�false�������򷵻�true
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
	
	//regUser���������û��������Ϣ������֤�󣬾Ϳ����ύ��RegUser��������ע��
	//��Ҫ����һ����������User����Ȼ�����save��������ע��
	//��ע��ɹ�����true����ע��ʧ�ܣ��򷵻�false
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
