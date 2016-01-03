package bookworld.dao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import bookworld.orm.User;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Criteria;

public class ManageUserDao extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(ManageUserDao.class);
	private MongoTemplate mongoTemplate;
	
	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
	protected void initDao(){
		
	}
	public User getUser(int userid){
		try{
			//String hql = "from User as u where u.id=?";
			//User u = (User)this.getHibernateTemplate().find(hql,userid).get(0);
			User u = mongoTemplate.findOne(new Query(Criteria.where("id").is(userid)), User.class);
			return u;
		}catch(Exception e){
			log.error(e.getMessage());
			return null;
		}
	}
	//updateUser函数，需要传递一个User对象作为参数，然后根据该对象调用update的方法来
	//更新持久化层的User对象
	public boolean UpdateUser(User u){
		try{
			//this.getHibernateTemplate().update(u);
			mongoTemplate.remove(new Query(Criteria.where("id").is(u.getId())),User.class);
			mongoTemplate.save(u);
		}catch(Exception e){
			log.error(e.getMessage());
			return false;
		}
		return true;
	}
	
	public boolean deleteUser(User u){
		try{
			//this.getHibernateTemplate().delete(u);
			mongoTemplate.remove(u);
			return true;
		}catch(Exception e){
			log.error(e.getMessage());
			return false;
		}
	}
}
